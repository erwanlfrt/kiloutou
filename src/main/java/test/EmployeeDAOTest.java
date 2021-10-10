package test;

import org.junit.Test;

import model.dao.EmployeeDAO;
import model.dao.UserDAO;
import model.object.user.Employee;
import model.object.user.Profil;
import model.object.user.User;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

public class EmployeeDAOTest {
	
	@Test
	public void getTest() {
		EmployeeDAO employeeDAO = new EmployeeDAO();
		
		String name = "Bob";
		String firstname = "Billy";
		String address = "1 root de Bretagne";
		String phoneNumber = "0102030405";
		String mail = "root@gmail.com";
		String login = "root";
		String pwd = "toor";
		String function = "ceo";
		String service = "administration";
		int deskNumber = 1;
		Profil profil = Profil.ADMIN;
		
		Employee emp = employeeDAO.get(mail);
		
		assertEquals(emp.getMail(), mail);
		assertEquals(emp.getName(), name);
		assertEquals(emp.getFirstname(), firstname);
		assertEquals(emp.getLogin(), login);
		assertEquals(emp.getPassword(), pwd);
		assertEquals(emp.getAddress(), address);
		assertEquals(emp.getPhoneNumber(), phoneNumber);
		assertEquals(emp.getFunction(), function);
		assertEquals(emp.getService(), service);
		assertEquals(emp.getDeskNumber(), deskNumber);
		assertEquals(emp.getProfil(), profil);
	}
	
	@Test
	public void addTest() {
		String name = "TestName";
		String firstname = "TestFirstname";
		String address = "TestAddress";
		String phoneNumber = "0011223344";
		String mail = "test@gmail.com";
		String login = "testLogin";
		String pwd = "testPwd";
		String function = "testFunction";
		String service = "testService";
		int deskNumber = 2;
		Profil profil = Profil.EQUIPMENT_ADMIN;
		
		UserDAO userDAO = new UserDAO();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		
		if(userDAO.get(mail) == null) {
			User user = new User(name, firstname, address, phoneNumber, mail, login, pwd);
			userDAO.add(user);
		}
		
		if(employeeDAO.get(mail) != null) {
			employeeDAO.deleteById(mail);
		}
		
		Employee emp = new Employee(name, firstname, address, phoneNumber, mail, login, pwd, function, service, deskNumber, profil );
		
		employeeDAO.add(emp);
		
		Employee checkAdded = employeeDAO.get(mail);
		
		assertEquals(checkAdded != null, true);
		assertEquals(checkAdded.getMail(), mail);
		assertEquals(checkAdded.getName(), name);
		assertEquals(checkAdded.getFirstname(), firstname);
		assertEquals(checkAdded.getLogin(), login);
		assertEquals(checkAdded.getPassword(), pwd);
		assertEquals(checkAdded.getAddress(), address);
		assertEquals(checkAdded.getPhoneNumber(), phoneNumber);
		assertEquals(checkAdded.getFunction(), function);
		assertEquals(checkAdded.getService(), service);
		assertEquals(checkAdded.getDeskNumber(), deskNumber);
		assertEquals(checkAdded.getProfil(), profil);
	}
	
	@Test
	public void deleteTest() {
		EmployeeDAO employeeDAO = new EmployeeDAO();
		
		String mail = "test@gmail.com";
		String name = "TestName";
		String firstname = "TestFirstname";
		String address = "TestAddress";
		String phoneNumber = "0011223344";
		String login = "testLogin";
		String pwd = "testPwd";
		String function = "testFunction";
		String service = "testService";
		int deskNumber = 2;
		Profil profil = Profil.EQUIPMENT_ADMIN;
		Employee emp = new Employee(name, firstname, address, phoneNumber, mail, login, pwd, function, service, deskNumber, profil );
		
		if(employeeDAO.get(mail) == null ) {
			UserDAO userDAO = new UserDAO();
			if(userDAO.get(mail) == null) {
				User user = new User(name, firstname, address, phoneNumber, mail, login, pwd);
				userDAO.add(user);
			}
			employeeDAO.add(emp);
		}
		
		employeeDAO.delete(emp);
		assertEquals(employeeDAO.get(mail), null);
	}
	
	@Test
	public void deleteByIdTest() {
		EmployeeDAO employeeDAO = new EmployeeDAO();
		
		String mail = "test@gmail.com";
		String name = "TestName";
		String firstname = "TestFirstname";
		String address = "TestAddress";
		String phoneNumber = "0011223344";
		String login = "testLogin";
		String pwd = "testPwd";
		String function = "testFunction";
		String service = "testService";
		int deskNumber = 2;
		Profil profil = Profil.EQUIPMENT_ADMIN;
		Employee emp = new Employee(name, firstname, address, phoneNumber, mail, login, pwd, function, service, deskNumber, profil );
		
		if(employeeDAO.get(mail) == null ) {
			UserDAO userDAO = new UserDAO();
			if(userDAO.get(mail) == null) {
				User user = new User(name, firstname, address, phoneNumber, mail, login, pwd);
				userDAO.add(user);
			}
			employeeDAO.add(emp);
		}
		
		employeeDAO.deleteById(mail);
		assertEquals(employeeDAO.get(mail), null);
	}
	
	@Test
	public void listAllTest() {
		EmployeeDAO employeeDAO = new EmployeeDAO();
		ArrayList<Employee> list = employeeDAO.listAll();
		
		if(employeeDAO.get("test@gmail.com") == null ) {
			assertEquals(list.size(), 1);
		} else {
			assertEquals(list.size(), 2);
		}
	}
	
	@Test
	public void updateTest() {
		EmployeeDAO employeeDAO = new EmployeeDAO();
		HashMap<String, Object> params = new HashMap<String, Object>();
		
		String mail = "test@gmail.com";
		String name = "TestName";
		String firstname = "TestFirstname";
		String address = "TestAddress";
		String phoneNumber = "0011223344";
		String login = "testLogin";
		String pwd = "testPwd";
		
		String function = "testFunction";
		String service = "testService";
		int deskNumber = 2;
		Profil profil = Profil.EQUIPMENT_ADMIN;
		
		String newFunction = "newFunction";
		String newService = "newService";
		int newDeskNumber = 4;
		Profil newProfil = Profil.LOAN_ADMIN;
		
		Employee emp = new Employee(name, firstname, address, phoneNumber, mail, login, pwd, function, service, deskNumber, profil );
		
		if(employeeDAO.get(mail) == null ) {
			UserDAO userDAO = new UserDAO();
			if(userDAO.get(mail) == null) {
				User user = new User(name, firstname, address, phoneNumber, mail, login, pwd);
				userDAO.add(user);
			}
			employeeDAO.add(emp);
		}
		
		params.put("employeeFunction", newFunction);
		params.put("employeeService", newService);
		params.put("deskNumber", newDeskNumber);
		params.put("profil", newProfil.toString());
		
		employeeDAO.update(emp, params);
		
		Employee checkUpdated = employeeDAO.get(mail);
		
		assertEquals(checkUpdated != null, true);
		assertEquals(checkUpdated.getMail(), mail);
		assertEquals(checkUpdated.getName(), name);
		assertEquals(checkUpdated.getFirstname(), firstname);
		assertEquals(checkUpdated.getLogin(), login);
		assertEquals(checkUpdated.getPassword(), pwd);
		assertEquals(checkUpdated.getAddress(), address);
		assertEquals(checkUpdated.getPhoneNumber(), phoneNumber);
		assertEquals(checkUpdated.getFunction(), newFunction);
		assertEquals(checkUpdated.getService(), newService);
		assertEquals(checkUpdated.getDeskNumber(), newDeskNumber);
		assertEquals(checkUpdated.getProfil(), newProfil);
		
		
		
	}
}
