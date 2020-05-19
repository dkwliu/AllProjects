USE BIKES;

-- SQL commands for looking at the responses

-- RESPONSE 1
-- Checking the DimCustomer table after initial insert of
-- 1000000,YourFirstName,YourLastName,California,25,5
SELECT * FROM DimCustomer
ORDER BY CustomerID;
-- Checking the DimCustomer table after updating customer 
-- 1000000 with location: Texas and age: 26
SELECT * FROM DimCustomer
ORDER BY CustomerID;

-- RESPONSE 2
-- Checking the DimCustomer table after updating customer 1000000 with 
-- last name: Doe, location: California, and Years of experience: 6
SELECT* FROM DimCustomer
ORDER BY CustomerID;

-- RESPONSE 4 and 5
SELECT * FROM DimEmployee
ORDER BY EmployeeID;

-- Response 7 and 8
-- Commands for checking the product dimension after updating the whole 
-- sale price of customer 30000100 to 250.00
-- Commands for checking the product dimension after updating the whole 
-- sale price of customer 30000100 from 250.00 to 350.00
SELECT * FROM DimProduct
ORDER BY ProductID;

-- RESPONSE 3, 6, and 9
SELECT * FROM FactSalesOrder
ORDER BY OrderID desc;
