--175. Combine Two Tables
--LEFT JOIN

--176. Second Highest Salary

--181. Employees Earning More Than Their Managers
--SELF JOIN
SELECT Employee
FROM (SELECT a.Name AS Employee, a.Salary AS EmployeeSalary, b.Salary AS ManagerSalary 
      FROM Employee a, Employee b 
      WHERE a.ManagerId = b.Id) AS T
WHERE EmployeeSalary > ManagerSalary

--182. Duplicate Emails
--conditions: GROUP BY ... HAVING ...
SELECT Email
FROM Person
GROUP BY Email
HAVING COUNT(*) > 1;

--196. Delete Duplicate Emails
