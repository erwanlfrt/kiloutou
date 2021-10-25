package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.DBManager;
import model.object.equipment.GraphicCard;

public class GraphicCardDAO implements Dao<GraphicCard> {
	Connection connection;
	String table;
	
	public GraphicCardDAO() {
		connection = DBManager.getInstance().getConnection();
		table = "GraphicCard";
	}

  public void add(GraphicCard object) {
    int id = object.getId();
    String name = object.getName();
    String brand = object.getBrand();
    String gpu = object.getGpu();
    float frequency = object.getFrequency();

    String query = "INSERT INTO " + this.table + " (id, name, gpu, frequency, brand) VALUES ("+ id +", \'" + name +"\', \'" + gpu +"\', " + frequency +", \'" + brand +"\');";
    try {
      Statement statement = this.connection.createStatement();
      statement.executeUpdate(query);
    } catch (SQLException e) {
			e.printStackTrace();
		}
  }

  public void delete(GraphicCard object) {
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
		  GraphicCard equipment = this.get(id);
		  this.delete(equipment);
	  }
  }

  public GraphicCard get(Object id) {
    GraphicCard result = null;
    if(id instanceof Integer) {
    	String query = "SELECT * from " + this.table + " WHERE id = " + (Integer)id + ";";
      try {
        Statement statement = this.connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        while(rs.next()) {
          result = new GraphicCard(rs.getInt("id"), rs.getString("name"),rs.getString("brand"), rs.getString("gpu"), rs.getFloat("frequency"));
        }
      } catch(SQLException e) {
        e.printStackTrace();
      }
    }
    return result;
  }
	
	public ArrayList<GraphicCard> listAll() {
		ArrayList<GraphicCard> result = new ArrayList<GraphicCard>();
		try {
			Statement statement = this.connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM "+this.table + ";");
			while (rs.next()) {
				result.add( new GraphicCard(rs.getInt("id"), rs.getString("name"),rs.getString("brand"), rs.getString("gpu"), rs.getFloat("frequency")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

  public void update(GraphicCard object, HashMap<String, Object> parameters) {
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
