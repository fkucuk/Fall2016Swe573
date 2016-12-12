 create table User (UserId int PRIMARY KEY AUTO_INCREMENT,
	Email varchar(255) NOT NULL, 
	Name nvarchar(255) NOT NULL, 
	Password nvarchar(255) NOT NULL,
	IsActive bit NOT NULL,
	RegistrationDate datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
	Weight float NOT NULL,
	Height float NOT NULL
)



CREATE TABLE MealType(MealTypeId int PRIMARY KEY, Name varchar(25) NOT NULL)

INSERT MealType
VALUES 
(1, 'BREAKFAST'),
(2, 'LUNCH'),
(3, 'DINNER'),
(4, 'SNACK')

create table Food (FoodId int PRIMARY KEY, FoodName nvarchar(255) NOT NULL, FoodData nvarchar(8000))

CREATE TABLE UserMeal (UserMealId int PRIMARY KEY AUTO_INCREMENT , MealTypeId INT, MealDay int, UserId int)

CREATE TABLE MealFood (MealFoodId INT PRIMARY KEY AUTO_INCREMENT, UserMealId int, FoodId int, UnitRef int, Unit nvarchar(255), Quantity float)

CREATE TABLE Activity (ActivityId INT PRIMARY KEY AUTO_INCREMENT, Description nvarchar(255) NOT NULL, Cat1 INT NOT NULL, Cat2 INT NOT NULL, Cat3 INT NOT NULL, Cat4 INT NOT NULL)

CREATE TABLE UserActivity (UserActivityId INT PRIMARY KEY AUTO_INCREMENT, ActivityId int not null, UserId int NOT NULL, Duration INT not null, Moment DateTime not null, Record_Date DateTime not null default CURRENT_TIMESTAMP)


CREATE TABLE Unit (UnitID int primary key auto_increment, symbol nvarchar(25) not null, description varchar(25) not null)

CREATE table DailyReferenceValue (DailyReferenceValueId int primary key auto_increment, FoodComponent nvarchar(100) not null, DailyValue float not null, Unit nvarchar(25) not null, UnitId int )

