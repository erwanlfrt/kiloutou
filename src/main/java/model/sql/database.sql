DROP TABLE IF EXISTS Loan;
DROP TABLE IF EXISTS SuitableVehicle;
DROP TABLE IF EXISTS Computer;
DROP TABLE IF EXISTS Car;
DROP TABLE IF EXISTS Bike;
DROP TABLE IF EXISTS Employee;
DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS Processor;
DROP TABLE IF EXISTS GraphicCard;
DROP TABLE IF EXISTS ComputerAccessory;
DROP TABLE IF EXISTS VehicleAccessory;
DROP TABLE IF EXISTS Vehicle;
DROP TABLE IF EXISTS Equipment;


-- #region User
CREATE TABLE User (
  mail VARCHAR(100) PRIMARY KEY ,
  name VARCHAR(30) NOT NULL,
  firstname VARCHAR(30) NOT NULL,
  login VARCHAR(30) NOT NULL,
  password VARCHAR(200) NOT NULL, -- add SHA function
  address VARCHAR(200),
  phoneNumber VARCHAR(20)
);

CREATE TABLE Employee (
  mail VARCHAR(100) PRIMARY KEY,
  employeeFunction VARCHAR(30) NOT NULL,
  employeeService VARCHAR(30) NOT NULL,
  deskNumber INT,
  profil enum('ADMIN', 'EQUIPMENT_ADMIN', 'LOAN_ADMIN') NOT NULL,
  FOREIGN KEY (mail) REFERENCES User(mail)
);
-- #endregion User


-- #region Equipment

CREATE TABLE Equipment (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(30) NOT NULL,
  available BOOLEAN DEFAULT 1,
  imageURL VARCHAR(300)
);

CREATE TABLE Processor ( 
  id INT PRIMARY KEY,
  name VARCHAR(30) NOT NULL,
  brand VARCHAR(30) NOT NULL,
  numberOfCores INT,
  frequency FLOAT
);

CREATE TABLE GraphicCard (
  id INT PRIMARY KEY,
  name VARCHAR(30) NOT NULL,
  brand VARCHAR(30) NOT NULL ,
  frequency FLOAT
);

CREATE TABLE ComputerAccessory (
  id INT PRIMARY KEY,
  FOREIGN KEY (id) REFERENCES Equipment(id)
);

CREATE TABLE VehicleAccessory (
  id INT PRIMARY KEY,
  FOREIGN KEY (id) REFERENCES Equipment(id)
);

CREATE TABLE Vehicle (
  id INT PRIMARY KEY,
  kilometers INT NOT NULL,
  renewalKilometers INT NOT NULL,
  brand VARCHAR(30) NOT NULL,
  model VARCHAR(30) NOT NULL,
  registrationNumber VARCHAR(30) NOT NULL,
  state enum('GOOD', 'AVERAGE', 'BAD'),
  maxSpeed INT,
  numberOfSpeeds INT,
  power INT,
  FOREIGN KEY (id) REFERENCES Equipment(id)
);

CREATE TABLE Car (
  id INT PRIMARY KEY,
  numberOfSeats INT,
  FOREIGN KEY (id) REFERENCES Vehicle(id)
);

CREATE TABLE Bike (
  id INT PRIMARY KEY,
  numberOfCylinders INT,
  FOREIGN KEY (id) REFERENCES Vehicle(id)
);

CREATE TABLE SuitableVehicle (
  accessoryId INT ,
  vehicleId INT,
  PRIMARY KEY (accessoryId, vehicleId),
  FOREIGN KEY (accessoryId) REFERENCES VehicleAccessory(id),
  FOREIGN KEY (vehicleId) REFERENCES Vehicle(id)
);

CREATE TABLE Computer (
  id INT PRIMARY KEY,
  brand VARCHAR(30) NOT NULL,
  model VARCHAR(30) NOT NULL,
  serialNumber VARCHAR(30) NOT NULL,
  memorySize INT ,
  isLaptop BOOLEAN NOT NULL,
  screenSize INT ,
  purchaseDate DATE NOT NULL,
  renewalDate DATE NOT NULL,
  processorId INT NOT NULL,
  graphicCardId INT NOT NULL,
  FOREIGN KEY (id) REFERENCES Equipment(id),
  FOREIGN KEY (processorId) REFERENCES Processor(id),
  FOREIGN KEY (graphicCardId) REFERENCES GraphicCard(id)
);

-- #endregion Equipment


-- #region Loan

CREATE TABLE Loan (
  id INT PRIMARY KEY,
  equipmentId INT NOT NULL,
  userMail VARCHAR(100) NOT NULL,
  beginningDate DATE NOT NULL,
  endDate DATE NOT NULL,
  isBorrowed BOOLEAN NOT NULL,
  FOREIGN KEY (equipmentId) REFERENCES Equipment(id),
  FOREIGN KEY (userMail) REFERENCES User(mail) 
);

-- #endregion Loan


