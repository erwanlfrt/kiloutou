package test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import model.dao.UserDAO;
import model.object.user.User;


public class UserDAOTest {
	
	@Test
	public void getTest() {
		UserDAO userDAO = new UserDAO();
		
		// get the root user, if the test fails check the database and the code.
		User root = userDAO.get("root@gmail.com");
		
		assertEquals(root.getMail(), "root@gmail.com");
		assertEquals(root.getName(), "Bob");
		assertEquals(root.getFirstname(), "Billy");
		assertEquals(root.getLogin(), "root");
		assertEquals(root.getPassword(), "toor");
		assertEquals(root.getAddress(), "1 root de Bretagne");
		assertEquals(root.getPhoneNumber(), "0102030405");
		
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
		
		UserDAO userDAO = new UserDAO();
		User check = userDAO.get(mail);
		if(check != null ) {
			userDAO.deleteById(mail);
		}
		
		
		User user = new User(name, firstname, address, phoneNumber, mail, login, pwd);
		userDAO.add(user);
		
		User checkAdded = userDAO.get("test@gmail.com");
		
		assertEquals(checkAdded.getMail(), mail);
		assertEquals(checkAdded.getName(), name);
		assertEquals(checkAdded.getFirstname(), firstname);
		assertEquals(checkAdded.getLogin(), login);
		assertEquals(checkAdded.getPassword(), pwd);
		assertEquals(checkAdded.getAddress(), address);
		assertEquals(checkAdded.getPhoneNumber(), phoneNumber);
	}
	
	@Test
	public void deleteTest() {
		UserDAO userDAO = new UserDAO();
		
		String name = "TestName";
		String firstname = "TestFirstname";
		String address = "TestAddress";
		String phoneNumber = "0011223344";
		String mail = "test@gmail.com";
		String login = "testLogin";
		String pwd = "testPwd";
		
		User user = new User(name, firstname, address, phoneNumber, mail, login, pwd);
		
		if(userDAO.get(mail) == null) {
			
			userDAO.add(user);
		}
		
		userDAO.delete(user);
		
		assertEquals(userDAO.get(mail), null);
		
	}
	
	@Test
	public void deleteByIdTest() {
		UserDAO userDAO = new UserDAO();
		
		String name = "TestName";
		String firstname = "TestFirstname";
		String address = "TestAddress";
		String phoneNumber = "0011223344";
		String mail = "test@gmail.com";
		String login = "testLogin";
		String pwd = "testPwd";
		
		User user = new User(name, firstname, address, phoneNumber, mail, login, pwd);
		
		if(userDAO.get(mail) == null) {
			
			userDAO.add(user);
		}
		
		userDAO.deleteById(mail);
		
		assertEquals(userDAO.get(mail), null);
	}
	
	@Test
	public void ListAllTest() {
		UserDAO userDAO = new UserDAO();
		
		ArrayList<User> users = userDAO.listAll();
		
		if(userDAO.get("test@gmail.com") == null) {
			assertEquals(users.size(), 1);
		} else {
			assertEquals(users.size(), 2);
		}
		
	}
	
	@Test
	public void updateTest() {
		UserDAO userDAO = new UserDAO();
		
		String oldName = "TestName";
		String oldFirstname = "TestFirstname";
		String oldAddress = "TestAddress";
		String oldPhoneNumber = "0011223344";
		String oldLogin = "testLogin";
		String oldPwd = "testPwd";
		
		String name = "newName";
		String firstname = "newFirstname";
		String address = "newAddress";
		String phoneNumber = "4433221100";
		String mail = "test@gmail.com";
		String login = "newLogin";
		String pwd = "newPwd";
		
		if(userDAO.get(mail) == null) {
			User user = new User(oldName, oldFirstname, oldAddress, oldPhoneNumber, mail, oldLogin, oldPwd);
			userDAO.add(user);
		}
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("name", name);
		params.put("firstname", firstname);
		params.put("address" , address);
		params.put("phoneNumber" , phoneNumber);
		params.put("login", login);
		params.put("password", pwd);
		
		userDAO.update(userDAO.get(mail), params);
		
		User check = userDAO.get(mail);
		
		assertEquals(check.getMail(), mail);
		assertEquals(check.getName(), name);
		assertEquals(check.getFirstname(), firstname);
		assertEquals(check.getLogin(), login);
		assertEquals(check.getPassword(), pwd);
		assertEquals(check.getAddress(), address);
		assertEquals(check.getPhoneNumber(), phoneNumber);
	}
	
	
}
