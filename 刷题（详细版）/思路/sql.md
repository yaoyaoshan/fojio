# sql

## 交换性别

```sql
UPDATE salary
SET
    sex = CASE sex
        WHEN 'm' THEN 'f'
        ELSE 'm'
    END;
```

## 删除重复邮箱

```sql
DELETE p1
FROM Person p1,
    Person p2
WHERE p1.Id > p2.Id
AND p1.Email = p2.Email
```

## 比经理工资高的员工

```sql
SELECT p1.Name AS Employee
From Employee p1
INNER JOIN Employee p2
WHERE p1.ManagerId = p2.Id 
AND p1.Salary > p2.Salary
```

## 部门最高工资

```sql
SELECT
    D.NAME Department,
    E.NAME Employee,
    E.Salary
FROM
    Employee E,
    Department D,
    ( SELECT DepartmentId, MAX( Salary ) Salary 
     FROM Employee 
     GROUP BY DepartmentId ) M
WHERE
    E.DepartmentId = D.Id
    AND E.DepartmentId = M.DepartmentId
    AND E.Salary = M.Salary;
```

## 第二高薪水

```sql
SELECT(
SELECT distinct salary 
FROM Employee
ORDER BY salary DESC
LIMIT 1, 1) AS SecondHighestSalary
```

## 组合两个表

```sql
SELECT Person.FirstName AS FirstName, Person.LastName AS LastName, Address.City AS City, Address.State AS State
FROM Person LEFT JOIN Address
ON Person.PersonId = Address.PersonId
```

## 查找工资第 N 高的员工

```sql
CREATE FUNCTION getNthHighestSalary ( N INT ) RETURNS INT BEGIN

SET N = N - 1;
RETURN ( 
    SELECT ( 
        SELECT DISTINCT Salary 
        FROM Employee 
        ORDER BY Salary DESC 
        LIMIT N, 1 
    ) 
);

END
```

## 分数排名

```sql
select a.Score as Score,
(select count(distinct b.Score) from Scores b where b.Score >= a.Score) as Rank
from Scores a
order by a.Score DESC
```

## 部门工资前三高的员工

```sql
SELECT
    d.Name AS 'Department', e1.Name AS 'Employee', e1.Salary
FROM
    Employee e1
        JOIN
    Department d ON e1.DepartmentId = d.Id
WHERE
    3 > (SELECT
            COUNT(DISTINCT e2.Salary)
        FROM
            Employee e2
        WHERE
            e2.Salary > e1.Salary
                AND e1.DepartmentId = e2.DepartmentId
        )
;
```