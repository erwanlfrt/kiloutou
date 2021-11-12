package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.DBManager;
import model.object.user.Employee;
import model.object.user.Profil;
import model.object.user.User;

public class EmployeeDAO implements Dao<Employee> {
	Connection connection;
	String table;
	
	public EmployeeDAO() {
		connection = DBManager.getInstance().getConnection();
		table = "Employee";
	}

  public void add(Employee object) {
    String mail = object.getMail();

    String function = object.getFunction();
    String service = object.getService();
    int deskNumber = object.getDeskNumber();
    String profil = object.getProfil().name();

    String query = "INSERT INTO " + this.table + " (mail, employeeService, employeeFunction, profil, deskNumber) VALUES (\'"+ mail +"\', \'" + service +"\', \'"+ function + "\', \'" + profil +"\', " + deskNumber + ");";
    try {
      Statement statement = this.connection.createStatement();
      statement.executeUpdate(query);
    } catch (SQLException e) {
			e.printStackTrace();
		}
  }

  public void delete(Employee object) {
    String mail = object.getMail();
    String query = "DELETE FROM " + this.table + " WHERE mail = \'" + mail + "\';";

    try {
      Statement statement = this.connection.createStatement();
      statement.executeUpdate(query);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  public void deleteById(Object id) {
	  if(id instanceof String) {
		  Employee employee = this.get(id);
		  this.delete(employee);
	  }
  }

  public Employee get(Object id) {
    Employee result = null;
    if(id instanceof String) {
    	String query = "SELECT * from " + this.table + " WHERE mail = ?;";
      try {
        PreparedStatement statement = this.connection.prepareStatement(query);
        statement.setString(1, (String)id);
        ResultSet rs = statement.executeQuery();
        UserDAO userDAO = new UserDAO();
        while(rs.next()) {
        	User user = userDAO.get(id);
        	result = new Employee(user.getName(), user.getFirstname(), user.getAddress(), user.getPhoneNumber(), user.getMail(), user.getLogin(), user.getPassword(), rs.getString("employeeFunction"), rs.getString("employeeService"), rs.getInt("deskNumber"), Profil.valueOf(rs.getString("profil")));
        }
      } catch(SQLException e) {
        e.printStackTrace();
      }
    }
    return result;
  }
	
	public ArrayList<Employee> listAll() {
		ArrayList<Employee> result = new ArrayList<Employee>();
		try {
			PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM "+this.table + ";");
			ResultSet rs = statement.executeQuery();
      UserDAO userDAO = new UserDAO();
			while (rs.next()) {
        User user = userDAO.get(rs.getString("mail"));
				result.add(new Employee(user.getName(), user.getFirstname(), user.getAddress(), user.getPhoneNumber(), user.getMail(), user.getLogin(), user.getPassword(), rs.getString("employeeFunction"), rs.getString("employeeService"), rs.getInt("deskNumber"), Profil.valueOf(rs.getString("profil"))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

  public void update(Employee object, HashMap<String, Object> parameters) {
    String mail = object.getMail();

    String changes = "";

    int numberOfChanges = parameters.values().size();
    int cpt = 1;

    for (Map.Entry<String, Object> parameter : parameters.entrySet()) {
      String column = parameter.getKey();
      Object value = parameter.getValue();
      
      String change = "";
      
      if (value instanceof String) {
        change = column + " = \'" + (String) value + "\'" + (cpt != numberOfChanges ? ", " : " "); 
      }
      else if (value instanceof Boolean || value instanceof Integer) {
        change = column + " = " + value.toString() + (cpt != numberOfChanges ? ", " : " ");
      }
      changes += change;
      cpt++;
    }
    if(!changes.equals("")) {
      String query = "UPDATE " + this.table + " SET " + changes +" WHERE mail = \'" + mail + "\';";
      Statement statement;
      try {
        statement = this.connection.createStatement();
        statement.executeUpdate(query);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
