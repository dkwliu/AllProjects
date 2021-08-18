CREATE DATABASE BIKES;
USE BIKES;

-- Creates the mini outrigger dimension for DimCustomer Table
-- This is a type 5 for Customer dimension
CREATE TABLE CurrentMiniDimCustomer (
	CurrentDemographicsKey INT IDENTITY(1,1) NOT NULL,
	CurrentAgeFrom INT,
	CurrentAgeTo INT,
	CurrentYearsOfExperienceFrom INT,
	CurrentYearsOfExperienceTo INT,
CONSTRAINT CurrentMiniDimCustomer_PK PRIMARY KEY (CurrentDemographicsKey));

-- Populates outrigger mini dimension table
INSERT INTO CurrentMiniDimCustomer
(CurrentAgeFrom, CurrentAgeTo, CurrentYearsOfExperienceFrom, CurrentYearsOfExperienceTo)
VALUES
(20,25,0,5),
(26,30,0,5),
(31,35,0,5),
(20,25,6,20),
(26,30,6,20),
(31,35,6,20);

-- Department Table
CREATE TABLE DimDepartment(
	DepartmentID INT NOT NULL,
	DepartmentName VARCHAR(255),
CONSTRAINT DimDimension_PK PRIMARY KEY(DepartmentID));

-- Populate the Department table
BULK
INSERT DimDepartment
FROM 'C:/Users/dkwliu/Desktop/MSBA_CSUEB/BAN_622/Project 2/Department.txt'
WITH
(
FIRSTROW = 2,
FIELDTERMINATOR = ',',
ROWTERMINATOR = '\n'
)
GO


-- Region Table
CREATE TABLE DimRegion(
	RegionID INT NOT NULL,
	RegionName VARCHAR(255),
CONSTRAINT DimRegion_PK PRIMARY KEY(RegionID));

-- Populate the Region table
BULK
INSERT DimRegion
FROM 'C:/Users/dkwliu/Desktop/MSBA_CSUEB/BAN_622/Project 2/Region.txt'
WITH
(
FIRSTROW = 2,
FIELDTERMINATOR = ',',
ROWTERMINATOR = '\n'
)

-- below creates the DimCustomer Table
-- CustomerFirstName = type 0, CustomerLastName = type 1, CustomerLocation =type 2
CREATE TABLE DimCustomer(
	CustomerKey INT IDENTITY(1,1) NOT NULL,
	CustomerID INT NOT NULL,
	CustomerFirstName VARCHAR(255) NOT NULL,
	CustomerLastName VARCHAR(255) NOT NULL,
	CustomerLocation VARCHAR(255),
	RowEffectiveDate DATETIME,
	RowExpirationDate DATETIME,
	CurrentRowIndicator VARCHAR(255) DEFAULT 'Current',
	CurrentDemographicsKey INT,
CONSTRAINT DimCustomer_PK PRIMARY KEY (CustomerKey),
CONSTRAINT DimCustomer_FK FOREIGN KEY (CurrentDemographicsKey) REFERENCES CurrentMiniDimCustomer(CurrentDemographicsKey));


-- Creates the DimProduct Table
-- Type 3 SCD is used in this dimension, having the PreviousWholeSalePrice to
-- keep track of the previous price, and WholeSalePrice to keep track of the
-- current price.
CREATE TABLE DimProduct(
	ProductKey INT IDENTITY(1,1) NOT NULL,
	ProductID INT NOT NULL,
	ProductName VARCHAR(255) NOT NULL,
	Cost MONEY,
	WholeSalePrice MONEY,
	MSRP MONEY,
	PreviousWholeSalePrice MONEY,
	EffectivePriceDate DATETIME,
	PriceChangePercentage FLOAT,
CONSTRAINT DimProduct_PK PRIMARY KEY (ProductKey));

-- Creates the Employee dimension
-- EmployeeFirstName = type 0, EmployeeLastName = type 1, DepartmentID = type 6
CREATE TABLE DimEmployee (
	EmployeeKey INT IDENTITY(1,1) NOT NULL,
	EmployeeID INT NOT NULL,
	EmployeeFirstName VARCHAR(255) NOT NULL,
	EmployeeLastName VARCHAR(255) NOT NULL,
	HistoricalDepartmentID INT,
	CurrentDepartmentID INT,
	EmployeeAddress VARCHAR(255),
	Gender VARCHAR(255),
	EmployeeBirthDate DATETIME,
	Salary FLOAT,
	RegionID INT,
	RowEffectiveDate DATETIME,
	RowExpirationDate DATETIME,
	CurrentRowIndicator VARCHAR(255) DEFAULT 'Current',
CONSTRAINT DimEmployee_PK PRIMARY KEY(EmployeeKey),
CONSTRAINT DimEmployee_FK1 FOREIGN KEY(RegionID) REFERENCES DimRegion(RegionID),
CONSTRAINT DimEmployee_FK2 FOREIGN KEY(CurrentDepartmentID) REFERENCES DimDepartment(DepartmentID));

-- Creates the mini dimension for DimCustomer Table that is attached to the fact table
-- This is a type 4 scd for the Customer dimension
CREATE TABLE MiniDimCustomer (
	DemographicsKey INT IDENTITY(1,1) NOT NULL,
	AgeFrom INT,
	AgeTo INT,
	YearsOfExperienceFrom INT,
	YearsOfExperienceTo INT,
CONSTRAINT MiniDimCustomer_PK PRIMARY KEY (DemographicsKey));

-- Populates the mini dimension table
INSERT INTO MiniDimCustomer
(AgeFrom, AgeTo, YearsOfExperienceFrom, YearsOfExperienceTo)
VALUES
(20,25,0,5),
(26,30,0,5),
(31,35,0,5),
(20,25,6,20),
(26,30,6,20),
(31,35,6,20);

-- Sales order fact table
CREATE TABLE FactSalesOrder(
	OrderID BIGINT NOT NULL,
	PODate DATETIME,
	ProductKey INT NOT NULL,
	CustomerKey INT NOT NULL,
	CustomerPO BIGINT,
	EmployeeKey INT NOT NULL,
	Quantity INT,
	UnitPrice MONEY,
	DemographicsKey INT,
CONSTRAINT OrderID_PK PRIMARY KEY (OrderID),
CONSTRAINT SalesOrder_FK1 FOREIGN KEY(ProductKey) REFERENCES DimProduct(ProductKey),
CONSTRAINT SalesOrder_FK2 FOREIGN KEY(CustomerKey) REFERENCES DimCustomer(CustomerKey),
CONSTRAINT SalesOrder_FK3 FOREIGN KEY(EmployeeKey) REFERENCES DimEmployee(EmployeeKey),
CONSTRAINT SalesOrder_FK4 FOREIGN KEY(DemographicsKey) REFERENCES MiniDimCustomer(DemographicsKey));
