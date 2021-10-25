package test;

import org.junit.Test;

import model.dao.ProcessorDAO;
import model.object.equipment.Processor;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;


public class ProcessorDAOTest {
	
	@Test
	public void getTest() {
		ProcessorDAO processorDAO = new ProcessorDAO();
		
		int id = 1;
		String name = "I5";
		String brand = "Intel";
		int numberOfCores = 8;
		float frequency = 3.2f;

		Processor processor = processorDAO.get(id);
		
		assertEquals(processor != null, true);
		assertEquals(processor.getId(), id);
		assertEquals(processor.getName(), name);
		assertEquals(processor.getBrand(), brand);
		assertEquals(processor.getNumberOfCores(), numberOfCores);
		assertEquals(processor.getFrequency(), frequency, 0.1);
		
	}
 	
	@Test
	public void addTest() {
		ProcessorDAO processorDAO = new ProcessorDAO();
		
		int id = 2;
		String name = "XX";
		String brand = "AMD";
		int numberOfCores = 4;
		float frequency = 3.7f;
		
		if(processorDAO.get(id) != null ) {
			processorDAO.deleteById(id);
		}
		
		Processor processor = new Processor(id, name, brand, numberOfCores, frequency);
		
		processorDAO.add(processor);
		
		Processor check = processorDAO.get(id);
		
		assertEquals(check != null, true);
		assertEquals(check.getId(), id);
		assertEquals(check.getName(), name);
		assertEquals(check.getBrand(), brand);
		assertEquals(check.getNumberOfCores(), numberOfCores);
		assertEquals(check.getFrequency(), frequency, 0.1);
		
		processorDAO.delete(check);
		
	}
	
	@Test
	public void deleteTest() {
		ProcessorDAO processorDAO = new ProcessorDAO();
		
		int id = 2;
		String name = "XX";
		String brand = "AMD";
		int numberOfCores = 4;
		float frequency = 3.7f;
		
		Processor processor = new Processor(id, name, brand, numberOfCores, frequency);
		
		if(processorDAO.get(id) == null ) {
			processorDAO.add(processor);
		}
		
		processorDAO.delete(processor);
		
		assertEquals(processorDAO.get(id) == null, true);
	}
	
	@Test
	public void deleteByIdTest() {
		ProcessorDAO processorDAO = new ProcessorDAO();
		
		int id = 2;
		String name = "XX";
		String brand = "AMD";
		int numberOfCores = 4;
		float frequency = 3.7f;
		
		Processor processor = new Processor(id, name, brand, numberOfCores, frequency);
		
		if(processorDAO.get(id) == null ) {
			processorDAO.add(processor);
		}
		
		processorDAO.deleteById(id);
		
		assertEquals(processorDAO.get(id) == null, true);
	}
	
	@Test
	public void listAllTest() {
		ProcessorDAO processorDAO = new ProcessorDAO();
		
		ArrayList<Processor> list = processorDAO.listAll();
		
		assertEquals(list.size(), 1);
	}
	
	@Test
	public void update() {
		ProcessorDAO processorDAO = new ProcessorDAO();
		HashMap<String, Object> params = new HashMap<String, Object>();
		
		int id = 2;
		String name = "XX";
		String brand = "AMD";
		int numberOfCores = 4;
		float frequency = 3.7f;
		
		Processor processor = new Processor(id, name, brand, numberOfCores, frequency);
		
		if(processorDAO.get(id) == null ) {
			processorDAO.add(processor);
		}
		
		String newName = "XY";
		String newBrand = "AZDKAZD";
		int newNumberOfCores = 2;
		float newFrequency = 2f;
		
		params.put("name", newName);
		params.put("brand", newBrand);
		params.put("numberOfCores", newNumberOfCores);
		params.put("frequency", newFrequency);
		
		processorDAO.update(processor, params);
		
		
		Processor check = processorDAO.get(id);
		
		assertEquals(check != null, true);
		assertEquals(check.getId(), id);
		assertEquals(check.getName(), newName);
		assertEquals(check.getBrand(), newBrand);
		assertEquals(check.getNumberOfCores(), newNumberOfCores);
		assertEquals(check.getFrequency(), newFrequency, 0.1);
		
		processorDAO.delete(check);
	}

}