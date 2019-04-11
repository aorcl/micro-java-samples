CREATE TABLE [TrainingDepartments] ( 
[DepartmentId] NVARCHAR(256)  NOT NULL PRIMARY KEY, 
[TrainingBudget] INTEGER NULL
); 

CREATE TABLE [TrainingUsers] ( 
[UserId] NVARCHAR(256)  NOT NULL PRIMARY KEY, 
[DepartmentId] NVARCHAR(256)
); 

CREATE TABLE [TrainingHistory] ( 
[UserId] NVARCHAR(256)  NOT NULL , 
[TrainingId] NVARCHAR(256) NOT NULL ,
[TrainingDate] DATE NOT NULL ,
[TrainingCost] INTEGER
); 

CREATE TABLE departments ( 
department_id NUMBER(4)
, department_name VARCHAR2(30)
CONSTRAINT dept_name_nn NOT NULL
, manager_id NUMBER(6)
, location_id NUMBER(4)
);

CREATE UNIQUE INDEX dept_id_pk
ON departments (department_id);

CREATE VIEW TrainingUsersBudgets (UserId, DepartmentId, TrainingBudget)
AS SELECT UserId, DepartmentId, TrainingBudget
FROM TrainingUsers INNER JOIN TrainingDepartments
ON TrainingUsers.DepartmentId = TrainingDepartments.DepartmentId;


insert into TrainingDepartments values('10', 10000);
insert into TrainingDepartments values('20', 20000);
insert into TrainingDepartments values('30', 30000);

insert into TrainingUsers values('John', '10');
insert into TrainingUsers values('Dennis', '10');
insert into TrainingUsers values('Ann', '20');
insert into TrainingUsers values('Ed', '20');
insert into TrainingUsers values('Maria', '30');

insert into TrainingHistory values('John', '42', date('2018-01-15'), 1000);
insert into TrainingHistory values('John', 'Everything in a nutshell', date('2018-02-10'), 2000);
insert into TrainingHistory values('Ann', 'Why bother?', date('2019-01-01'), 100);
insert into TrainingHistory values('Ed', 'One more training', date('2019-02-02'), 3000);
