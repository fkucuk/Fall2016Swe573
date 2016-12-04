-- create table User (userId int PRIMARY KEY AUTO_INCREMENT,
-- email varchar(255) NOT NULL, 
-- name nvarchar(255) NOT NULL, 
-- password nvarchar(255) NOT NULL,
-- isActive bit NOT NULL,
-- registrationDate datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
-- weight float NOT NULL,
-- height float NOT NULL
-- )
-- 


CREATE TABLE MealType(MealTypeId int PRIMARY KEY, Name varchar(25) NOT NULL)

INSERT MealType
VALUES 
(1, 'BREAKFAST'),
(2, 'LUNCH'),
(3, 'DINNER'),
(4, 'SNACK')

select * from MealType