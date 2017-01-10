
CREATE TABLE customers(
    	customerNumber int not null PRIMARY KEY,
    	customerName varchar(50),
    	contactLastName varchar(50),
    	contactFirstName varchar(50),
    	phone varchar(50),
     	addressLine1 varchar(50),
   	  addressLine2 varchar(50),
     	city varchar(50),
     	state varchar(50),
    	postalCode varchar(50),
     	country varchar(50),
    	salesRepEmployeeNumber int,
    	creditLimit decimal
);