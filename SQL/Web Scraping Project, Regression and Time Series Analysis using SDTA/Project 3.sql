CREATE DATABASE Movie;
USE Movie;

-- Creates the DimMovie Table
CREATE TABLE DimMovie (
	MovieKey INT IDENTITY(1,1) NOT NULL,
	Title VARCHAR(255),
	Certif VARCHAR(255),
	Runtime INT,
	Genres VARCHAR(255),
	Rating FLOAT,
CONSTRAINT DimMovie_PK PRIMARY KEY(MovieKey));

-- Creates the FactMovie Table
CREATE TABLE FactMovie (
	RowKey INT IDENTITY(1,1) NOT NULL,
	Date DATETIME NOT NULL,
	Day INT,
	MovieKey INT NOT NULL,
	Top10gross INT,
	Top1gross INT,
CONSTRAINT FactMovie_PK PRIMARY KEY (RowKey),
CONSTRAINT FactMovie_FK1 FOREIGN KEY (Date) REFERENCES DimDate(Date),
CONSTRAINT FactMovie_FK2 FOREIGN KEY (MovieKey) REFERENCES DimMovie(MovieKey));

-- Creates the DimDate Table
CREATE TABLE DimDate(Date Datetime,
					Year INT,
					Month INT,
					Day INT,
					WeekDayValue INT,
					MonthValueName VARCHAR(20),
					WeekDayValueName VARCHAR(20),
CONSTRAINT DimDate_PK PRIMARY KEY (Date));

-- Populates the DimDate Table
DECLARE @StartDate DATE = '2019-01-01';
DECLARE @EndDate DATE = '2019-12-31';
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



-- Command for seeing how many rows of data are in the DimDate dimension
select COUNT(Date) from DimDate;


-- Creating the Rating view for linear regression
CREATE VIEW Rating AS
SELECT DimMovie.Title as 'Movie Title', DimMovie.Rating as IMDbRating, COUNT(FactMovie.MovieKey) as NoOfDays
FROM DimMovie, FactMovie
WHERE DimMovie.MovieKey = FactMovie.MovieKey
GROUP BY DimMovie.Title, DimMovie.Rating;

-- Command for seeing the data inside the Rating view
select * from Rating
order by 'Movie Title';