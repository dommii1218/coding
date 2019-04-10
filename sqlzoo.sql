SELECT name, 
CASE WHEN continent IN ('Europe', 'Asia') THEN 'Eurasia'
WHEN continent IN ('North America', 'South America', 'Caribbean') THEN 'America'
ELSE continent END
FROM world
WHERE name LIKE 'A%' OR name LIKE 'B%'
