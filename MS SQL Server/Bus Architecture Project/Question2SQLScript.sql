CREATE DATABASE PetStoreEDW;
USE PetStoreEDW;

CREATE TABLE DimAnimal(Animal_ID int NOT NULL,
						Animal_Name nvarchar(100), 
						Category nvarchar(50),
						Breed nvarchar(50),
						Date_Born datetime,
						Gender nvarchar(100),
						Registered nvarchar(100),
						Color nvarchar(100),
						List_Price money,
						Photo image, 
						Image_File nvarchar(250),
						Image_Height int,
						Image_Width int,
CONSTRAINT DimAnimal_PK PRIMARY KEY (Animal_ID));

CREATE TABLE DimEmployee(Emp_ID int NOT NULL,
							Last_Name nvarchar(100),
							First_Name nvarchar(100),
							Phone nvarchar(50),
							Emp_Address nvarchar(max),
							Zip_Code nvarchar(50),
							City_ID int,
							Tax_Payer_ID nvarchar(50),
							Date_Hired datetime,
							Date_Released datetime,
							Manager_ID int,
							Employee_Level tinyint,
							Title nvarchar(50),
CONSTRAINT DimEmployee_PK PRIMARY KEY (Emp_ID));

CREATE TABLE DimMerchandise(Item_ID int NOT NULL,
							Merch_Description nvarchar(50),
							QuantityOnHand int,
							ListPrice money,
							Category nvarchar(50),
CONSTRAINT DimMerchandise_PK PRIMARY KEY (Item_ID));

CREATE TABLE DimSupplier(Supplier_ID int NOT NULL,
							Supplier_Name nvarchar(100),
							Contact_Name nvarchar(100),
							Phone nvarchar(50),
							Supplier_Address nvarchar(50),
							Zip_Code nvarchar(50),
							City_ID int,
							Avg_Order_Days real,
CONSTRAINT DimSupplier_PK PRIMARY KEY (Supplier_ID));

CREATE TABLE DimDate(Date Datetime,
					Year INT,
					Month INT,
					Day INT,
					WeekDayValue INT,
					MonthValueName VARCHAR(20),
					WeekDayValueName VARCHAR(20),
CONSTRAINT DimDate_PK PRIMARY KEY (Date));

DECLARE @StartDate DATE = '2001-01-01';
DECLARE @EndDate DATE = '2001-12-31';
WHILE @StartDate <= @EndDate
BEGIN
	INSERT INTO DimDate(Date,
						Year,
						Month,
						Day,
						WeekDayValue,
						MonthValueName,
						WeekDayValueName)
	VALUES(@StartDate,
		DATEPART(YY, @StartDate),
		DATEPART(mm, @StartDate),
		DATEPART(dd, @StartDate),
		DATEPART(dw, @StartDate),
		DATENAME(mm, @StartDate),
		DATENAME(dw, @StartDate))
	SET @StartDate = DATEADD(dd, 1, @StartDate)
END;