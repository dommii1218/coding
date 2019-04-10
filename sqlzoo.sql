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
