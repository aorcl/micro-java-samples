CREATE TABLE [Departments] ( 
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


CREATE VIEW TrainingUsersBudgets 
AS SELECT TrainingUsers.UserId, TrainingUsers.DepartmentId, Departments.TrainingBudget
FROM TrainingUsers INNER JOIN Departments
ON TrainingUsers.DepartmentId = Departments.DepartmentId;


insert into Departments values('Finance', 10000);
insert into Departments values('Operations', 20000);
insert into Departments values('Development', 30000);

insert into TrainingUsers values('John', 'Finance');
insert into TrainingUsers values('Dennis', 'Finance');
insert into TrainingUsers values('Ann', 'Operations');
insert into TrainingUsers values('Ed', 'Operations');
insert into TrainingUsers values('Maria', 'Development');

insert into TrainingHistory values('John', '42', date('2018-01-15'), 1000);
insert into TrainingHistory values('John', 'Everything in a nutshell', date('2018-02-10'), 2000);
insert into TrainingHistory values('Ann', 'Why bother?', date('2019-01-01'), 100);
insert into TrainingHistory values('Ed', 'One more training', date('2019-02-02'), 3000);
