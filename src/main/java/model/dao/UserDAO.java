package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
// import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.DBManager;
import model.object.user.User;

public class UserDAO implements Dao<User> {
	Connection connection;
	String table;
	
	public UserDAO() {
		connection = DBManager.getInstance().getConnection();
		table = "User";
	}

  public void add(User object) {
    String name = object.getName();
    String firstname = object.getFirstname();
    String address = object.getAddress();
    String phoneNumber = object.getPhoneNumber();
    String mail = object.getMail();
    String login = object.getLogin();
    String password = object.getPassword();

    String query = "INSERT INTO " + this.table + " (name, firstname, address, phoneNumber, mail, login, password) VALUES (\'"+ name +"\', \'" + firstname +"\', \'" + address +"\', \'" + phoneNumber +"\', \'" + mail +"\', \'" + login +"\', SHA1(\'" + password + "\'));";
    System.out.println("query = " + query);
    try {
      Statement statement = this.connection.createStatement();
      statement.executeUpdate(query);
    } catch (SQLException e) {
			e.printStackTrace();
		}
  }

  public void delete(User object) {
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
		  User user = this.get(id);
		  this.delete(user);
	  }
  }

  public User get(Object id) {
    User result = null;
    if(id instanceof String) {
    	String query = "SELECT * from " + this.table + " WHERE mail = \'" + (String)id + "\';";
        try {
          Statement statement = this.connection.createStatement();
          ResultSet rs = statement.executeQuery(query);
          while(rs.next()) {
        	  result = new User(rs.getString("name"), rs.getString("firstname"), rs.getString("address"), rs.getString("phoneNumber"), rs.getString("mail"), rs.getString("login"), rs.getString("password"));
          }
        } catch(SQLException e) {
          e.printStackTrace();
        }
    }
    return result;
  }
	
	public ArrayList<User> listAll() {
		ArrayList<User> result = new ArrayList<User>();
		try {
			Statement statement = this.connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM "+ this.table + ";");
			while (rs.next()) {
				result.add(new User(rs.getString("name"), rs.getString("firstname"), rs.getString("address"), rs.getString("phoneNumber"), rs.getString("mail"), rs.getString("login"), rs.getString("password")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

  public void update(User object, HashMap<String, Object> parameters) {
    String mail = object.getMail();

    String changes = "";

    int numberOfChanges = parameters.values().size();
    int cpt = 1;

    for (Map.Entry<String, Object> parameter : parameters.entrySet()) {
      String column = parameter.getKey();
      Object value = parameter.getValue();
      
      String change = "";
      
      if (value instanceof String) {
        if (column.equals("password")) {
          change = column + " = SHA1(\'" + (String) value + "\')" + (cpt != numberOfChanges ? ", " : " "); 
        }
        else {
          change = column + " = \'" + (String) value + "\'" + (cpt != numberOfChanges ? ", " : " "); 
        }
        
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
