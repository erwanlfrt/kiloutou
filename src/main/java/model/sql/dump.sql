INSERT INTO User (mail, name, firstname, login, password, address, phoneNumber) VALUES ("root@gmail.com", "Biden", "Joe", "root", SHA1("toor"), "1 root des USA", "0102030405"); 
INSERT INTO User (mail, name, firstname, login, password, address, phoneNumber) VALUES ("xi.jiping@gmail.com", "Jiping", "Xi", "xj", SHA1("toor"), "1 root de Chine", "0102030405"); 
INSERT INTO User (mail, name, firstname, login, password, address, phoneNumber) VALUES ("vladimir.putin@gmail.com", "Putin", "Vladimir", "vlad", SHA1("toor"), "1 root de Mother land", "0102030405"); 
INSERT INTO User (mail, name, firstname, login, password, address, phoneNumber) VALUES ("emmanuel.macron@gmail.com", "Macron", "Emmanuel", "EnMarche", SHA1("toor"), "1 root de France", "0102030405"); 
INSERT INTO User (mail, name, firstname, login, password, address, phoneNumber) VALUES ("angela.merkel@gmail.com", "Merkel", "Angela", "Deutsch92", SHA1("toor"), "1 root de Germany", "0102030405"); 
INSERT INTO User (mail, name, firstname, login, password, address, phoneNumber) VALUES ("justin.trudeau@gmail.com", "Trudeau", "Justin", "Canada93", SHA1("toor"), "1 root du Canada", "0102030405"); 

INSERT INTO Employee (mail, employeeFunction, employeeService, deskNumber, profil) VALUES ("root@gmail.com", "ceo", "administration", 1, "ADMIN");
INSERT INTO Employee (mail, employeeFunction, employeeService, deskNumber, profil) VALUES ("angela.merkel@gmail.com", "secrétaire", "secrétariat", 2, "LOAN_ADMIN");
INSERT INTO Employee (mail, employeeFunction, employeeService, deskNumber, profil) VALUES ("justin.trudeau@gmail.com", "secrétaire", "secrétariat", 2, "EQUIPMENT_ADMIN");

INSERT INTO Equipment (id, name, available, imageUrl) VALUES (1, "Projecteur", 1, "https://www.abtechnologies.net/1281-large_default/video-projecteur-sony-vlp-dx100-2300ansi-lumens.jpg");
INSERT INTO Equipment (id, name, available, imageUrl) VALUES (2, "Fiat 500 rouge", 1, "https://static.latribune.fr/full_width/491371/nouvelle-fiat-500-2015.jpg");
INSERT INTO Equipment (id, name, available, imageUrl) VALUES (3, "Peugeot Partner gris", 1, "https://i.ytimg.com/vi/Qptt_y5S1Uo/maxresdefault.jpg");
INSERT INTO Equipment (id, name, available, imageUrl) VALUES (4, "Harley Davidson Sportser 2 places", 1, "https://media.motoservices.com/media/cache/slider_full/media/gallery/17550/Harley-Davidson_Sportster_S-08.jpg");
INSERT INTO Equipment (id, name, available, imageUrl) VALUES (5, "Moto 2", 1, "https://m.media-amazon.com/images/I/7140AaGHKSL._AC_SL1500_.jpg");
INSERT INTO Equipment (id, name, available, imageUrl) VALUES (6, "Ordinateur 1", 1, "https://www.apple.com/v/macbook-pro-14-and-16/a/images/meta/macbook-pro-14-and-16_overview__fz0lron5xyuu_og.png");
INSERT INTO Equipment (id, name, available, imageUrl) VALUES (7, "Ordinateur 2", 1, "https://www.cdiscount.com/pdt2/b/i/s/1/700x700/r515jabq127tbis/rw/pc-portable-asus-vivobook-r515ja-bq127t-15-6-fh.jpg");
INSERT INTO Equipment (id, name, available, imageUrl) VALUES (8, "Ordinateur 3", 1, "https://media.ldlc.com/r1600/ld/products/00/04/86/96/LD0004869623_2.jpg");
INSERT INTO Equipment (id, name, available, imageUrl) VALUES (9, "Souris Logitech", 1, "https://m.media-amazon.com/images/I/61UxfXTUyvL._AC_SX425_.jpg");
INSERT INTO Equipment (id, name, available, imageUrl) VALUES (10, "Disque dur Toshiba", 1, "https://image.darty.com/informatique/accessoire_portable/disque_dur_portable/toshiba_canviobasics_2_5_1tb_t1508204076290A_112059316.jpg");
INSERT INTO Equipment (id, name, available, imageUrl) VALUES (11, "Clé USB Samsung", 1, "https://image.darty.com/darty?type=image&source=/market/2018/08/16/mkp_mobvsASBnjv7dg.png&width=400&height=300&quality=90");
INSERT INTO Equipment (id, name, available, imageUrl) VALUES (12, "Remorque", 1, "https://www.remorques-du-nord.fr/media/cache/product_image/trailers/R251M-Remorque-BW-R251-marron-1.jpeg");
INSERT INTO Equipment (id, name, available, imageUrl) VALUES (13, "Porte bagage", 1, "https://www.cdiscount.com/pdt2/9/9/0/1/700x700/auc0615200262990/rw/porte-bagages-en-metal-barre-support-auto-voiture.jpg");
INSERT INTO Equipment (id, name, available, imageUrl) VALUES (14, "Barres de toit", 1, "https://accessoires.skoda.fr/pub/media/catalog/product/cache/6b50632d4c5750c2ac55e8e9234d1d8c/b/a/barres-de-toit-scala-0.jpg");
INSERT INTO Equipment (id, name, available, imageUrl) VALUES (15, "Tractopelle", 1, "https://www.lemoniteurmateriels.fr/mediatheque/6/3/6/004714636_926x615_fb_ffffff.jpg");


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
INSERT INTO Computer (id, brand, model, serialNumber, memorySize, isLaptop, screenSize, purchaseDate, renewalDate, processorId, graphicCardId) VALUES (7, "Asus", "Vivobook 8", "ax8000xa", 500, true, 13, '2021-05-15', '2025-05-15', 1, 1);
INSERT INTO Computer (id, brand, model, serialNumber, memorySize, isLaptop, screenSize, purchaseDate, renewalDate, processorId, graphicCardId) VALUES (8, "HP", "EliteBook H5", "ax8000xa", 500, true, 13, '2021-05-15', '2025-05-15', 1, 1);


INSERT INTO Loan (id, equipmentId, userMail, beginningDate, endDate, isOver) VALUES (0, 1, "angela.merkel@gmail.com", "2021-11-16", "2021-11-29", false);
INSERT INTO Loan (id, equipmentId, userMail, beginningDate, endDate, isOver) VALUES (1, 2, "angela.merkel@gmail.com", "2021-12-14", "2021-12-19", false);
INSERT INTO Loan (id, equipmentId, userMail, beginningDate, endDate, isOver) VALUES (2, 3, "angela.merkel@gmail.com", "2021-10-12", "2021-11-02", false);
INSERT INTO Loan (id, equipmentId, userMail, beginningDate, endDate, isOver) VALUES (3, 4, "angela.merkel@gmail.com", "2022-10-12", "2022-11-02", false);

INSERT INTO Loan (id, equipmentId, userMail, beginningDate, endDate, isOver) VALUES (4, 1, "root@gmail.com", "2022-01-14", "2022-01-16", false);
INSERT INTO Loan (id, equipmentId, userMail, beginningDate, endDate, isOver) VALUES (5, 2, "root@gmail.com", "2021-11-30", "2021-12-03", false);
INSERT INTO Loan (id, equipmentId, userMail, beginningDate, endDate, isOver) VALUES (6, 3, "root@gmail.com", "2021-11-30", "2021-12-15", false);
INSERT INTO Loan (id, equipmentId, userMail, beginningDate, endDate, isOver) VALUES (7, 4, "root@gmail.com", "2022-11-30", "2022-12-15", false);

INSERT INTO Loan (id, equipmentId, userMail, beginningDate, endDate, isOver) VALUES (8, 1, "vladimir.putin@gmail.com", "2022-03-02", "2022-03-13", false);
INSERT INTO Loan (id, equipmentId, userMail, beginningDate, endDate, isOver) VALUES (9, 5, "vladimir.putin@gmail.com", "2022-06-03", "2022-06-15", false);
INSERT INTO Loan (id, equipmentId, userMail, beginningDate, endDate, isOver) VALUES (10, 6, "vladimir.putin@gmail.com", "2021-10-10", "2021-10-30", true);

INSERT INTO Loan (id, equipmentId, userMail, beginningDate, endDate, isOver) VALUES (11, 1, "emmanuel.macron@gmail.com", "2021-10-15", "2021-10-17", true);
INSERT INTO Loan (id, equipmentId, userMail, beginningDate, endDate, isOver) VALUES (12, 5, "emmanuel.macron@gmail.com", "2022-02-22", "2022-03-09", false);
INSERT INTO Loan (id, equipmentId, userMail, beginningDate, endDate, isOver) VALUES (13, 5, "emmanuel.macron@gmail.com", "2022-03-10", "2022-04-25", false);
INSERT INTO Loan (id, equipmentId, userMail, beginningDate, endDate, isOver) VALUES (14, 6, "emmanuel.macron@gmail.com", "2022-02-22", "2022-03-09", false);
INSERT INTO Loan (id, equipmentId, userMail, beginningDate, endDate, isOver) VALUES (15, 7, "emmanuel.macron@gmail.com", "2022-01-10", "2022-01-25", false);
INSERT INTO Loan (id, equipmentId, userMail, beginningDate, endDate, isOver) VALUES (16, 8, "emmanuel.macron@gmail.com", "2021-02-24", "2021-04-08", true);
INSERT INTO Loan (id, equipmentId, userMail, beginningDate, endDate, isOver) VALUES (17, 9, "emmanuel.macron@gmail.com", "2021-11-23", "2021-11-28", false);
INSERT INTO Loan (id, equipmentId, userMail, beginningDate, endDate, isOver) VALUES (18, 10, "emmanuel.macron@gmail.com", "2021-09-05", "2021-09-06", true);

INSERT INTO Loan (id, equipmentId, userMail, beginningDate, endDate, isOver) VALUES (19, 1, "justin.trudeau@gmail.com", "2021-06-10", "2021-06-25", true);
INSERT INTO Loan (id, equipmentId, userMail, beginningDate, endDate, isOver) VALUES (20, 11, "justin.trudeau@gmail.com", "2021-06-01", "2021-06-09", true);
INSERT INTO Loan (id, equipmentId, userMail, beginningDate, endDate, isOver) VALUES (21, 12, "justin.trudeau@gmail.com", "2021-06-13", "2021-06-16", true);
INSERT INTO Loan (id, equipmentId, userMail, beginningDate, endDate, isOver) VALUES (22, 13, "justin.trudeau@gmail.com", "2021-06-22", "2021-06-27", true);