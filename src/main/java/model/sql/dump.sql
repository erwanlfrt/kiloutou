INSERT INTO User (mail, name, firstname, login, password, address, phoneNumber) VALUES ("root@gmail.com", "Bob", "Billy", "root", SHA1("toor"), "1 root de Bretagne", "0102030405"); 

INSERT INTO Employee (mail, employeeFunction, employeeService, deskNumber, profil) VALUES ("root@gmail.com", "ceo", "administration", 1, "ADMIN");

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


INSERT INTO Loan (id, equipmentId, userMail, beginningDate, endDate, isBorrowed) VALUES (0, 1, "root@gmail.com", "2021-11-01", "2021-11-30", false);
INSERT INTO Loan (id, equipmentId, userMail, beginningDate, endDate, isBorrowed) VALUES (1, 2, "root@gmail.com", "2021-10-12", "2021-11-02", false);
INSERT INTO Loan (id, equipmentId, userMail, beginningDate, endDate, isBorrowed) VALUES (2, 3, "root@gmail.com", "2021-10-12", "2021-11-02", true);
INSERT INTO Loan (id, equipmentId, userMail, beginningDate, endDate, isBorrowed) VALUES (3, 4, "root@gmail.com", "2021-11-30", "2021-12-15", false);