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