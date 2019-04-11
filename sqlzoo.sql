--8. 找出所有國家,其名字以t作第二個字母。
SELECT name 
FROM world
WHERE name LIKE '_t%'
ORDER BY name

--12. 顯示所有國家名字,其首都是國家名字加上”City”。
SELECT name
FROM world
WHERE capital LIKE concat(name, ' City')

--14.找出所有首都和其國家名字,而首都是國家名字的延伸。你應顯示Mexico City,因它比其國家名字Mexico長。你不應顯示Luxembourg,因它的首都和國家名相是相同的。
SELECT name, capital
FROM world
WHERE capital LIKE Concat('%', name, '%_')

--15. 顯示國家名字，及其延伸詞，如首都是國家名字的延伸。
SELECT name, REPLACE(capital, name, '')
FROM world
WHERE capital LIKE Concat('%', name, '%_')

--12. Show the name and the continent - but substitute Eurasia for Europe and Asia; substitute America - for each country in North America or South America or Caribbean. Show countries beginning with A or B
SELECT name, 
CASE WHEN continent IN ('Europe', 'Asia') THEN 'Eurasia'
WHEN continent IN ('North America', 'South America', 'Caribbean') THEN 'America'
ELSE continent END
FROM world
WHERE name LIKE 'A%' OR name LIKE 'B%'

--10. 顯示萬億元國家的人均國內生產總值，四捨五入到最近的$1000。
SELECT name, round(gdp/population, -3)
FROM world
WHERE gdp >= 1000000000000

--13. Put the continents right... Oceania becomes Australasia; Countries in Eurasia and Turkey go to Europe/Asia; Caribbean islands starting with 'B' go to North America, other Caribbean islands go to South America. Show the name, the original continent and the new continent of all countries.
SELECT name, continent,
       CASE WHEN continent = 'Oceania' THEN 'Australasia'
            WHEN continent = 'Eurasia' THEN 'Europe/Asia'
            WHEN name = 'Turkey' THEN 'Europe/Asia'
            WHEN continent ='Caribbean' AND continent LIKE 'B%' THEN 'North America'
            WHEN continent ='Caribbean' THEN 'South America' 
            ELSE continent END
FROM world
ORDER BY name

--14. The expression subject IN ('Chemistry','Physics') can be used as a value - it will be 0 or 1.
--Show the 1984 winners and subject ordered by subject and winner name; but list Chemistry and Physics last.
SELECT winner, subject
FROM nobel
WHERE yr = 1984
ORDER BY subject IN ('Physics','Chemistry'), subject, winner

--5. 顯示歐洲的國家名稱name和每個國家的人口population。以德國的人口的百分比作人口顯示。
SELECT name,
CONCAT(ROUND(population/(SELECT population FROM world WHERE name = 'Germany')*100,0), '%')
FROM world
WHERE continent = 'Europe'

--6. 哪些國家的GDP比Europe歐洲的全部國家都要高呢? [只需列出 name 。] (有些國家的記錄中，GDP是NULL，沒有填入資料的。)
SELECT name
FROM world
WHERE gdp > ALL(SELECT gdp FROM world WHERE continent = 'Europe' AND gdp IS NOT NULL)

--7. 在每一個州中找出最大面積的國家，列出洲份 continent, 國家名字 name 及面積 area。 (有些國家的記錄中，AREA是NULL，沒有填入資料的。)
--Method 1
SELECT continent, name, area
FROM world AS w1
WHERE area >= ALL(SELECT area FROM world AS w2
                  WHERE w1.continent = w2.continent
                  AND area IS NOT NULL)
--Method 2  
SELECT w.continent,w.name,w.area 
FROM world AS w,
           (SELECT continent, MAX(area) AS area FROM world GROUP BY continent) AS maxw
WHERE w.continent = maxw.continent AND w.area=maxw.area

--OR OMIT continent
SELECT w.continent,w.name,w.area 
FROM world AS w,
           (SELECT MAX(area) AS area FROM world GROUP BY continent) AS maxw
WHERE w.area = maxw.area

--8. 列出洲份名稱，和每個洲份中國家名字按子母順序是排首位的國家名。(即每洲只有列一國)
SELECT continent, name
FROM world AS w1
WHERE name = (SELECT name FROM world AS w2 
              WHERE w1.continent = w2.continent 
              ORDER BY name LIMIT 1)

--9. 找出洲份，當中全部國家都有少於或等於 25000000 人口. 在這些洲份中，列出國家名字name，continent 洲份和population人口。
SELECT name, continent, population
FROM world AS w1
WHERE 25000000 >= ALL(SELECT population FROM world AS w2 
                      WHERE w1.continent = w2.continent 
                      AND population IS NOT NULL)

--10. 有些國家的人口是同洲份的所有其他國的3倍或以上。列出 國家名字name 和 洲份 continent。
SELECT name, continent
FROM world AS w1
WHERE population/3 >= ALL(SELECT population FROM world AS w2 
                          WHERE w1.continent = w2.continent 
                          AND NOT w1.population = w2.population 
                          AND population IS NOT NULL)
