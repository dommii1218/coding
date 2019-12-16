--175. Combine Two Tables
--LEFT JOIN

************************************************************************************************************************
--176. Second Highest Salary
CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
  RETURN (
      # Write your MySQL query statement below.
      SELECT MAX(e1.Salary)
      FROM Employee e1
      WHERE (N-1) = (SELECT COUNT(DISTINCT(e2.Salary)) FROM Employee e2 WHERE e1.Salary < e2.Salary)
  );
END
                     
************************************************************************************************************************
--178. Rank Scores
SELECT a.Score, COUNT(b.Score) AS Rank
FROM Scores AS a, (SELECT DISTINCT Score FROM Scores) AS b
WHERE a.Score <= b.Score
GROUP BY a.Id, a.Score
ORDER BY a.Score DESC
                     
************************************************************************************************************************
--180. Consecutive Numbers
SELECT l1.Num AS ConsecutiveNums
FROM Logs l1, Logs l2, Logs l3
WHERE l1.Id + 1 = l2.Id AND l2.Id + 1 = l3.Id
AND l1.Num = l2.Num AND l2.Num = l3.Num   
                     
************************************************************************************************************************
--181. Employees Earning More Than Their Managers
--SELF JOIN
SELECT Employee
FROM (SELECT a.Name AS Employee, a.Salary AS EmployeeSalary, b.Salary AS ManagerSalary 
      FROM Employee a, Employee b 
      WHERE a.ManagerId = b.Id) AS T
WHERE EmployeeSalary > ManagerSalary
                     
************************************************************************************************************************
--182. Duplicate Emails
--conditions: GROUP BY ... HAVING ...
SELECT Email
FROM Person
GROUP BY Email
HAVING COUNT(*) > 1;
************************************************************************************************************************
--196. Delete Duplicate Emails
