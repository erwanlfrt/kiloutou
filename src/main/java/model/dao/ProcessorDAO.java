package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.DBManager;
import model.object.equipment.Processor;

public class ProcessorDAO implements Dao<Processor> {
	Connection connection;
	String table;
	
	public ProcessorDAO() {
		connection = DBManager.getInstance().getConnection();
		table = "Processor";
	}

  public void add(Processor object) {
    int id = object.getId();
    String name = object.getName();
    String brand = object.getBrand();
    int numberOfCores = object.getNumberOfCores();
    float frequency = object.getFrequency();

    String query = "INSERT INTO " + this.table + " (id, name, numberOfCores, frequency, brand) VALUES ("+ id +", \'" + name +"\', \'" + numberOfCores +"\', " + frequency +", \'" + brand +"\');";
    try {
      Statement statement = this.connection.createStatement();
      statement.executeUpdate(query);
    } catch (SQLException e) {
			e.printStackTrace();
		}
  }

  public void delete(Processor object) {
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
		  Processor equipment = this.get(id);
		  this.delete(equipment);
	  }
  }

  public Processor get(Object id) {
    Processor result = null;
    if(id instanceof Integer) {
    	String query = "SELECT * from " + this.table + " WHERE id = " + (Integer)id + ";";
      try {
        Statement statement = this.connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        while(rs.next()) {
          result = new Processor(rs.getInt("id"), rs.getString("name"),rs.getString("brand"), rs.getInt("numberOfCores"), rs.getFloat("frequency"));
        }
      } catch(SQLException e) {
        e.printStackTrace();
      }
    }
    return result;
  }
	
	public ArrayList<Processor> listAll() {
		ArrayList<Processor> result = new ArrayList<Processor>();
		try {
			Statement statement = this.connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM "+this.table + ";");
			while (rs.next()) {
				result.add( new Processor(rs.getInt("id"), rs.getString("name"),rs.getString("brand"), rs.getInt("numberOfCores"), rs.getFloat("frequency")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

  public void update(Processor object, HashMap<String, Object> parameters) {
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
      else if (value instanceof Boolean || value instanceof Integer || value instanceof Float) {
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