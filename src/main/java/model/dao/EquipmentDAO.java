package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.DBManager;
import model.object.equipment.Equipment;

public class EquipmentDAO implements Dao<Equipment> {
	Connection connection;
	String table;
	
	public EquipmentDAO() {
		connection = DBManager.getInstance().getConnection();
		table = "Equipment";
	}

  public void add(Equipment object) {
    int id = object.getId();
    String name = object.getName();
    boolean available = object.isAvailable();
    String imageUrl = object.getImageUrl();

    String query = "INSERT INTO " + this.table + " (id, name, available, imageUrl) VALUES ("+ id +", \'" + name +"\', " + available +", \'" + imageUrl +"\');";
    try {
      Statement statement = this.connection.createStatement();
      statement.executeUpdate(query);
    } catch (SQLException e) {
			e.printStackTrace();
		}
  }

  public void delete(Equipment object) {
    int id = object.getId();
    String query = "DELETE FROM " + this.table + " WHERE id = " + id + ";";

    try {
      Statement statement = this.connection.createStatement();
      statement.executeUpdate(query);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  public void deleteById(Object id) {
	  if(id instanceof Integer) {
		  Equipment equipment = this.get(id);
		  this.delete(equipment);
	  }
  }

  public Equipment get(Object id) {
    Equipment result = null;
    if(id instanceof Integer) {
    	String query = "SELECT * from " + this.table + " WHERE id = \'" + (Integer)id + "\';";
      try {
        Statement statement = this.connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        while(rs.next()) {
          result = new Equipment(rs.getInt("id"), rs.getString("name"), rs.getBoolean("available"), rs.getString("imageUrl"));
        }
      } catch(SQLException e) {
        e.printStackTrace();
      }
    }
    return result;
  }
	
	public ArrayList<Equipment> listAll() {
		ArrayList<Equipment> result = new ArrayList<Equipment>();
		try {
			Statement statement = this.connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM "+this.table + ";");
			while (rs.next()) {
				result.add(new Equipment(rs.getInt("id"), rs.getString("name"), rs.getBoolean("available"), rs.getString("imageUrl")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

  public void update(Equipment object, HashMap<String, Object> parameters) {
    int id = object.getId();

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
      String query = "UPDATE " + this.table + " SET " + changes +" WHERE id = " + id + ";";
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
