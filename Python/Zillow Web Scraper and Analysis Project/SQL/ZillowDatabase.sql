CREATE DATABASE Zillow;

USE Zillow;

CREATE TABLE PROPERTIES (
	price int,
    address varchar(255),
    city varchar(50),
    state varchar(10),
    zipcode varchar(5),
    beds varchar(10),
    baths varchar(10),
    floor_size varchar(10),
    year_built varchar(4),
    property_type varchar(50),
    HOA varchar(50),
    Lot_size varchar(20),
    price_per_sqft varchar(10),
    descript longtext,
    remodel varchar(255),
    nearby_schools varchar(255),
    num_nearby_schools varchar(255)
);