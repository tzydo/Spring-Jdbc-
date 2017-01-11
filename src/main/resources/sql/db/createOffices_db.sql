CREATE TABLE offices(
  officeCode varchar(10) NOT NULL PRIMARY KEY ,
  city varchar(50) NOT NULL,
  phone varchar(50) NOT NULL,
  addressLine1 varchar(50) NOT NULL,
  addressLine2 varchar(50) ,
  state varchar(50),
  country varchar(50) NOT NULL,
  postalCode varchar(15) NOT NULL,
  territory  varchar(10) NOT NULL
);