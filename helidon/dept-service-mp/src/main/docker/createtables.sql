CREATE TABLE [TrainingDepartments] ( 
[DepartmentId] NVARCHAR(256)  NOT NULL PRIMARY KEY, 
[DepartmentBudget] INTEGER NULL
); 

CREATE TABLE [TrainingUsers] ( 
[UserId] NVARCHAR(256)  NOT NULL PRIMARY KEY, 
[DepartmentId] NVARCHAR(256)
); 

CREATE TABLE [TrainingHistory] ( 
[UserId] NVARCHAR(256)  NOT NULL PRIMARY KEY, 
[TrainingId] NVARCHAR(256),
[TrainingDate] DATE,
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
         ON departments (department_id) ;