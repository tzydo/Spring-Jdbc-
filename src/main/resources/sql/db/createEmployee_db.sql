CREATE TABLE EMPLOYEES(
  employeeNumber int NOT NULL  PRIMARY KEY ,
  lastName  varchar(50) NOT NULL ,
  firstName varchar(50) NOT NULL ,
  extension varchar(10)NOT NULL ,
  email varchar(100)NOT NULL ,
  officeCode varchar(10)NOT NULL ,
  reportsTo int(11),
  jobTitle varchar(50)
);