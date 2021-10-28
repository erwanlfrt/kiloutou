INSERT INTO User (mail, name, firstname, login, password, address, phoneNumber) VALUES ("root@gmail.com", "Bob", "Billy", "root", SHA1("toor"), "1 root de Bretagne", "0102030405"); 

INSERT INTO Employee (mail, employeeFunction, employeeService, deskNumber, profil) VALUES ("root@gmail.com", "ceo", "administration", 1, "ADMIN");

INSERT INTO Equipment (id, name, available, imageUrl) VALUES (1, "random equipment", 1, "http://randomURL.com");



INSERT INTO Processor (id, name, brand, numberOfCores, frequency) VALUES (1, "I5", "Intel", 8, 3.2);

INSERT INTO GraphicCard (id, name, brand, gpu, frequency) VALUES (1, "RTX1080", "Nvidia", "Intel I9", 3.5);

INSERT INTO ComputerAccessory (id) VALUES (1);

INSERT INTO VehicleAccessory (id) VALUES (1);

INSERT INTO Vehicle (id, kilometers, renewalKilometers, brand, model, registrationNumber, state, maxSpeed, numberOfSpeeds, power) VALUES (1, 1000, 6000, "Fiat", "500", "AA-000-AA", "GOOD", 180, 5, 80);

INSERT INTO Car (id, numberOfSeats) VALUES (1, 5);

INSERT INTO Bike (id, numberOfCylinders) VALUES (1, 8);

INSERT INTO SuitableVehicle (accessoryId, vehicleId) VALUES (1,1);

INSERT INTO Computer (id, brand, model, serialNumber, memorySize, isLaptop, screenSize, purchaseDate, renewalDate, processorId, graphicCardId) VALUES (1, "Apple", "Macbook", "ax8000xa", 500, true, 13, '2021-05-15', '2025-05-15', 1, 1);

INSERT INTO Loan (equipmentId, userMail, beginningDate, endDate, isBorrowed) VALUES (1, "root@gmail.com", "2021-11-12", "2021-11-30", false);
