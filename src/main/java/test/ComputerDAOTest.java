package test;

import org.junit.Test;

import model.dao.ComputerDAO;
import model.dao.EquipmentDAO;
import model.dao.GraphicCardDAO;
import model.dao.ProcessorDAO;
import model.object.equipment.Computer;
import model.object.equipment.Equipment;
import model.object.equipment.GraphicCard;
import model.object.equipment.Processor;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import java.util.HashMap;

public class ComputerDAOTest {

	@Test
	public void getTest() {
		ComputerDAO computerDAO = new ComputerDAO();
		int id = 1;
		String name = "random equipment";
		boolean available = true;
		String imageUrl = "http://randomURL.com";
		String brand = "Apple";
		String model = "Macbook";
		String serialNumber = "ax8000xa";
		int memorySize = 500;
		boolean isLaptop = true;
		int screenSize = 13;
		int processorId = 1;
		int graphicCardId = 1;

		Computer computer = computerDAO.get(id);

		assertEquals(computer != null, true);
		assertEquals(computer.getId(), id);
		assertEquals(computer.getName(), name);
		assertEquals(computer.isAvailable(), available);
		assertEquals(computer.getImageUrl(), imageUrl);
		assertEquals(computer.getBrand(), brand);
		assertEquals(computer.getModel(), model);
		assertEquals(computer.getSerialNumber(), serialNumber);
		assertEquals(computer.getMemorySize(), memorySize);
		assertEquals(computer.isLaptop(), isLaptop);
		assertEquals(computer.getScreenSize(), screenSize);
		assertEquals(computer.getProcessor().getId(), processorId);
		;
		assertEquals(computer.getGraphicCard().getId(), graphicCardId);
	}

	@Test
	public void addTest() {
		ComputerDAO computerDAO = new ComputerDAO();
		EquipmentDAO equipmentDAO = new EquipmentDAO();

		int id = 2;
		String name = "random equipment 2";
		boolean available = true;
		String imageUrl = "http://randomURL2.com";
		String brand = "Apple";
		String model = "Macbook";
		String serialNumber = "ax8000xa";
		int memorySize = 500;
		boolean isLaptop = true;
		int screenSize = 13;
		String purchaseDateString = "2021-05-15";
		String renewalDateString = "2025-05-15";
		int processorId = 1;
		int graphicCardId = 1;

		if (equipmentDAO.get(id) == null) {
			equipmentDAO.add(new Equipment(id, name, available, imageUrl, true));
		}

		if (computerDAO.get(id) != null) {
			computerDAO.deleteById(id);
		}

		ProcessorDAO processorDAO = new ProcessorDAO();
		Processor processor = processorDAO.get(processorId);
		GraphicCardDAO graphicCardDAO = new GraphicCardDAO();
		GraphicCard graphicCard = graphicCardDAO.get(graphicCardId);

		Computer check = new Computer(id, name, available, imageUrl, true, brand, model, serialNumber, memorySize,
				isLaptop, screenSize, purchaseDateString, renewalDateString, processor, graphicCard);

		computerDAO.add(check);

		Computer computer = computerDAO.get(id);

		assertEquals(computer != null, true);
		assertEquals(computer.getId(), id);
		assertEquals(computer.getName(), name);
		assertEquals(computer.isAvailable(), available);
		assertEquals(computer.getImageUrl(), imageUrl);
		assertEquals(computer.getBrand(), brand);
		assertEquals(computer.getModel(), model);
		assertEquals(computer.getSerialNumber(), serialNumber);
		assertEquals(computer.getMemorySize(), memorySize);
		assertEquals(computer.isLaptop(), isLaptop);
		assertEquals(computer.getScreenSize(), screenSize);
		assertEquals(computer.getProcessor().getId(), processorId);
		assertEquals(computer.getGraphicCard().getId(), graphicCardId);

		computerDAO.delete(computer);
	}

	@Test
	public void deleteTest() {
		ComputerDAO computerDAO = new ComputerDAO();
		EquipmentDAO equipmentDAO = new EquipmentDAO();

		int id = 2;
		String name = "random equipment 2";
		boolean available = true;
		String imageUrl = "http://randomURL2.com";
		String brand = "Apple";
		String model = "Macbook";
		String serialNumber = "ax8000xa";
		int memorySize = 500;
		boolean isLaptop = true;
		int screenSize = 13;
		String purchaseDateString = "2021-05-15";
		String renewalDateString = "2025-05-15";
		int processorId = 1;
		int graphicCardId = 1;

		ProcessorDAO processorDAO = new ProcessorDAO();
		Processor processor = processorDAO.get(processorId);
		GraphicCardDAO graphicCardDAO = new GraphicCardDAO();
		GraphicCard graphicCard = graphicCardDAO.get(graphicCardId);

		Computer check = new Computer(id, name, available, imageUrl, true, brand, model, serialNumber, memorySize,
				isLaptop, screenSize, purchaseDateString, renewalDateString, processor, graphicCard);

		if (equipmentDAO.get(id) == null) {
			equipmentDAO.add(new Equipment(id, name, available, imageUrl, true));
		}

		if (computerDAO.get(id) == null) {
			computerDAO.add(check);
		}

		computerDAO.delete(check);

		Computer computer = computerDAO.get(id);

		assertEquals(computer == null, true);
	}

	@Test
	public void deleteByIdTest() {
		ComputerDAO computerDAO = new ComputerDAO();
		EquipmentDAO equipmentDAO = new EquipmentDAO();

		int id = 2;
		String name = "random equipment 2";
		boolean available = true;
		String imageUrl = "http://randomURL2.com";
		String brand = "Apple";
		String model = "Macbook";
		String serialNumber = "ax8000xa";
		int memorySize = 500;
		boolean isLaptop = true;
		int screenSize = 13;
		String purchaseDateString = "2021-05-15";
		String renewalDateString = "2025-05-15";
		int processorId = 1;
		int graphicCardId = 1;

		ProcessorDAO processorDAO = new ProcessorDAO();
		Processor processor = processorDAO.get(processorId);
		GraphicCardDAO graphicCardDAO = new GraphicCardDAO();
		GraphicCard graphicCard = graphicCardDAO.get(graphicCardId);

		Computer check = new Computer(id, name, available, imageUrl, true, brand, model, serialNumber, memorySize,
				isLaptop, screenSize, purchaseDateString, renewalDateString, processor, graphicCard);

		computerDAO.add(check);

		if (equipmentDAO.get(id) == null) {
			equipmentDAO.add(new Equipment(id, name, available, imageUrl, true));
		}

		if (computerDAO.get(id) == null) {
			computerDAO.add(check);
		}

		computerDAO.deleteById(id);

		Computer computer = computerDAO.get(id);

		assertEquals(computer == null, true);
	}

	@Test
	public void listAllTest() {
		ComputerDAO computerDAO = new ComputerDAO();
		ArrayList<Computer> list = computerDAO.listAll();
		assertEquals(list.size(), 1);
	}

	@Test
	public void update() {
		HashMap<String, Object> params = new HashMap<String, Object>();
		String newBrand = "Apple new";
		String newModel = "Macbook new";
		String newSerialNumber = "ax8000xa new";
		int newMemorySize = 1000;
		boolean newIsLaptop = false;
		int newScreenSize = 15;
		String newPurchaseDateString = "2022-05-15";
		String newRenewalDateString = "2024-05-15";

		params.put("brand", newBrand);
		params.put("model", newModel);
		params.put("serialNumber", newSerialNumber);
		params.put("memorySize", newMemorySize);
		params.put("isLaptop", newIsLaptop);
		params.put("screenSize", newScreenSize);
		params.put("purchaseDate", newPurchaseDateString);
		params.put("renewalDate", newRenewalDateString);

		ComputerDAO computerDAO = new ComputerDAO();
		EquipmentDAO equipmentDAO = new EquipmentDAO();

		int id = 2;
		String name = "random equipment 2";
		boolean available = true;
		String imageUrl = "http://randomURL2.com";
		String brand = "Apple";
		String model = "Macbook";
		String serialNumber = "ax8000xa";
		int memorySize = 500;
		boolean isLaptop = true;
		int screenSize = 13;
		String purchaseDateString = "2021-05-15";
		String renewalDateString = "2025-05-15";
		int processorId = 1;
		int graphicCardId = 1;

		if (equipmentDAO.get(id) == null) {
			equipmentDAO.add(new Equipment(id, name, available, imageUrl, true));
		}

		if (computerDAO.get(id) != null) {
			computerDAO.deleteById(id);
		}

		ProcessorDAO processorDAO = new ProcessorDAO();
		Processor processor = processorDAO.get(processorId);
		GraphicCardDAO graphicCardDAO = new GraphicCardDAO();
		GraphicCard graphicCard = graphicCardDAO.get(graphicCardId);

		Computer check = new Computer(id, name, available, imageUrl, true, brand, model, serialNumber, memorySize,
				isLaptop, screenSize, purchaseDateString, renewalDateString, processor, graphicCard);

		computerDAO.add(check);

		computerDAO.update(check, params);

		Computer update = computerDAO.get(id);

		assertEquals(update != null, true);
		assertEquals(update.getId(), id);
		assertEquals(update.getName(), name);
		assertEquals(update.isAvailable(), available);
		assertEquals(update.getImageUrl(), imageUrl);
		assertEquals(update.getBrand(), newBrand);
		assertEquals(update.getModel(), newModel);
		assertEquals(update.getSerialNumber(), newSerialNumber);
		assertEquals(update.getMemorySize(), newMemorySize);
		assertEquals(update.isLaptop(), newIsLaptop);
		assertEquals(update.getScreenSize(), newScreenSize);
		assertEquals(update.getProcessor().getId(), processorId);
		assertEquals(update.getGraphicCard().getId(), graphicCardId);

		computerDAO.delete(update);

	}

}