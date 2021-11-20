INSERT INTO User (mail, name, firstname, login, password, address, phoneNumber) VALUES ("root@gmail.com", "Biden", "Joe", "root", SHA1("toor"), "1 root des USA", "0102030405"); 
INSERT INTO User (mail, name, firstname, login, password, address, phoneNumber) VALUES ("user1@gmail.com", "Jiping", "Xi", "xj", SHA1("toor"), "1 root de Chine", "0102030405"); 
INSERT INTO User (mail, name, firstname, login, password, address, phoneNumber) VALUES ("user2@gmail.com", "Putin", "Vladimir", "vlad", SHA1("toor"), "1 root de Mother land", "0102030405"); 
INSERT INTO User (mail, name, firstname, login, password, address, phoneNumber) VALUES ("user3@gmail.com", "Macron", "Emmanuel", "EnMarche", SHA1("toor"), "1 root de France", "0102030405"); 
INSERT INTO User (mail, name, firstname, login, password, address, phoneNumber) VALUES ("user4@gmail.com", "Merkel", "Angela", "Deutsch92", SHA1("toor"), "1 root de Germany", "0102030405"); 

INSERT INTO Employee (mail, employeeFunction, employeeService, deskNumber, profil) VALUES ("root@gmail.com", "ceo", "administration", 1, "ADMIN");
INSERT INTO Employee (mail, employeeFunction, employeeService, deskNumber, profil) VALUES ("user4@gmail.com", "chancelor", "administration", 2, "ADMIN");

INSERT INTO Equipment (id, name, available, imageUrl) VALUES (1, "random equipment", 1, "http://randomURL.com");
INSERT INTO Equipment (id, name, available, imageUrl) VALUES (2, "Voiture 1", 1, "http://randomURL.com");
INSERT INTO Equipment (id, name, available, imageUrl) VALUES (3, "Voiture 2", 1, "http://randomURL.com");
INSERT INTO Equipment (id, name, available, imageUrl) VALUES (4, "Moto 1", 1, "http://randomURL.com");
INSERT INTO Equipment (id, name, available, imageUrl) VALUES (5, "Moto 2", 1, "http://randomURL.com");
INSERT INTO Equipment (id, name, available, imageUrl) VALUES (6, "Ordinateur 1", 1, "http://randomURL.com");
INSERT INTO Equipment (id, name, available, imageUrl) VALUES (7, "Ordinateur 2", 1, "http://randomURL.com");
INSERT INTO Equipment (id, name, available, imageUrl) VALUES (8, "Ordinateur 3", 1, "http://randomURL.com");
INSERT INTO Equipment (id, name, available, imageUrl) VALUES (9, "Souris Logitech", 1, "http://randomURL.com");
INSERT INTO Equipment (id, name, available, imageUrl) VALUES (10, "Disque dur Toshiba", 1, "http://randomURL.com");
INSERT INTO Equipment (id, name, available, imageUrl) VALUES (11, "Clé USB Samsung", 1, "http://randomURL.com");
INSERT INTO Equipment (id, name, available, imageUrl) VALUES (12, "Remorque", 1, "http://randomURL.com");
INSERT INTO Equipment (id, name, available, imageUrl) VALUES (13, "Porte bagage", 1, "http://randomURL.com");
INSERT INTO Equipment (id, name, available, imageUrl) VALUES (14, "Barres de toit", 1, "http://randomURL.com");
INSERT INTO Equipment (id, name, available, imageUrl) VALUES (15, "Tractopelle", 1, "http://randomURL.com");
INSERT INTO Equipment (name, available, imageUrl) VALUES ("Projecteur", 1, "http://randomURL.com");

INSERT INTO Processor (id, name, brand, numberOfCores, frequency) VALUES (1, "I5", "Intel", 8, 3.2);

INSERT INTO GraphicCard (id, name, brand, frequency) VALUES (1, "RTX1080", "Nvidia", 3.5);

INSERT INTO ComputerAccessory (id) VALUES (9);
INSERT INTO ComputerAccessory (id) VALUES (10);
INSERT INTO ComputerAccessory (id) VALUES (11);

INSERT INTO VehicleAccessory (id) VALUES (12);
INSERT INTO VehicleAccessory (id) VALUES (13);
INSERT INTO VehicleAccessory (id) VALUES (14);

INSERT INTO Vehicle (id, kilometers, renewalKilometers, brand, model, registrationNumber, state, maxSpeed, numberOfSpeeds, power) VALUES (2, 1000, 6000, "Fiat", "500", "AA-000-AA", "GOOD", 180, 5, 80);
INSERT INTO Vehicle (id, kilometers, renewalKilometers, brand, model, registrationNumber, state, maxSpeed, numberOfSpeeds, power) VALUES (3, 1000, 6000, "Opel", "Corsa", "AA-0X0-AA", "GOOD", 500, 5, 1200);
INSERT INTO Vehicle (id, kilometers, renewalKilometers, brand, model, registrationNumber, state, maxSpeed, numberOfSpeeds, power) VALUES (4, 1000, 6000, "Harley Davidson", "Sportser S", "AA-BX0-AA", "GOOD", 500, 5, 1200);
INSERT INTO Vehicle (id, kilometers, renewalKilometers, brand, model, registrationNumber, state, maxSpeed, numberOfSpeeds, power) VALUES (5, 1000, 6000, "Yamaha", "MT07", "AE-0X0-AA", "GOOD", 500, 5, 1200);
INSERT INTO Vehicle (id, kilometers, renewalKilometers, brand, model, registrationNumber, state, maxSpeed, numberOfSpeeds, power) VALUES (15, 1000, 6000, "Caterpillar", "MT07", "AE-0X0-AA", "GOOD", 500, 5, 1200);

INSERT INTO Car (id, numberOfSeats) VALUES (2, 5);
INSERT INTO Car (id, numberOfSeats) VALUES (3, 5);

INSERT INTO Bike (id, numberOfCylinders) VALUES (4, 8);
INSERT INTO Bike (id, numberOfCylinders) VALUES (5, 16);

INSERT INTO SuitableVehicle (accessoryId, vehicleId) VALUES (12,2);
INSERT INTO SuitableVehicle (accessoryId, vehicleId) VALUES (13,3);
INSERT INTO SuitableVehicle (accessoryId, vehicleId) VALUES (14,2);

INSERT INTO Computer (id, brand, model, serialNumber, memorySize, isLaptop, screenSize, purchaseDate, renewalDate, processorId, graphicCardId) VALUES (6, "Apple", "Macbook", "ax8000xa", 500, true, 13, '2021-05-15', '2025-05-15', 1, 1);
INSERT INTO Computer (id, brand, model, serialNumber, memorySize, isLaptop, screenSize, purchaseDate, renewalDate, processorId, graphicCardId) VALUES (7, "Asus", "Notebook 8", "ax8000xa", 500, true, 13, '2021-05-15', '2025-05-15', 1, 1);
INSERT INTO Computer (id, brand, model, serialNumber, memorySize, isLaptop, screenSize, purchaseDate, renewalDate, processorId, graphicCardId) VALUES (8, "HP", "EliteBook H5", "ax8000xa", 500, true, 13, '2021-05-15', '2025-05-15', 1, 1);


INSERT INTO Loan (id, equipmentId, userMail, beginningDate, endDate, isBorrowed) VALUES (0, 1, "root@gmail.com", "2021-11-16", "2021-11-29", false);
INSERT INTO Loan (id, equipmentId, userMail, beginningDate, endDate, isBorrowed) VALUES (1, 1, "root@gmail.com", "2021-12-14", "2021-12-19", false);
INSERT INTO Loan (id, equipmentId, userMail, beginningDate, endDate, isBorrowed) VALUES (2, 2, "root@gmail.com", "2021-10-12", "2021-11-02", false);
INSERT INTO Loan (id, equipmentId, userMail, beginningDate, endDate, isBorrowed) VALUES (3, 3, "root@gmail.com", "2021-10-12", "2021-11-02", true);
INSERT INTO Loan (id, equipmentId, userMail, beginningDate, endDate, isBorrowed) VALUES (4, 4, "root@gmail.com", "2021-11-30", "2021-12-15", false);
INSERT INTO Loan (id, equipmentId, userMail, beginningDate, endDate, isBorrowed) VALUES (5, 4, "user4@gmail.com", "2021-12-15", "2021-12-17", false);
INSERT INTO Loan (id, equipmentId, userMail, beginningDate, endDate, isBorrowed) VALUES (6, 4, "user4@gmail.com", "2022-11-30", "2022-12-15", false);
INSERT INTO Loan (id, equipmentId, userMail, beginningDate, endDate, isBorrowed) VALUES (7, 1, "root@gmail.com", "2021-10-10", "2021-10-30", false);
INSERT INTO Loan (id, equipmentId, userMail, beginningDate, endDate, isBorrowed) VALUES (8, 1, "root@gmail.com", "2022-01-10", "2022-01-25", false);
INSERT INTO Loan (id, equipmentId, userMail, beginningDate, endDate, isBorrowed) VALUES (9, 1, "root@gmail.com", "2022-02-22", "2022-03-09", false);