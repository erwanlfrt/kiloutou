package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import model.DBManager;
import model.object.equipment.Computer;
import model.object.equipment.Equipment;
import model.object.equipment.Processor;
import model.object.equipment.GraphicCard;


public class ComputerDAO implements Dao<Computer> {
	Connection connection;
	String table;
	
	public ComputerDAO() {
		connection = DBManager.getInstance().getConnection();
		table = "Computer";
	}

  public void add(Computer object) {
    int id = object.getId();

    String brand = object.getBrand();
    String model = object.getModel();
    String serialNumber = object.getSerialNumber();
    int memorySize = object.getMemorySize();
    boolean isLaptop = object.isLaptop();
    int screenSize = object.getScreenSize();
    String renewalDate = object.getRenewalDate();
    String purchaseDate = object.getPurchaseDate();
    int processorId = object.getProcessor().getId();
    int graphicCardId = object.getGraphicCard().getId();
    

    String query = "INSERT INTO " + this.table + " (id, brand, model, serialNumber, memorySize, isLaptop, screenSize, purchaseDate, renewalDate, processorId, graphicCardId) VALUES ("+ id  + ", \'" + brand +"\', \'" + model +"\', \'" + serialNumber +"\', " + memorySize +", " + isLaptop +", " + screenSize +", \'" + purchaseDate +"\', \'" + renewalDate +"\', " + processorId+", " + graphicCardId +");";
    try {
      Statement statement = this.connection.createStatement();
      statement.executeUpdate(query);
    } catch (SQLException e) {
			e.printStackTrace();
		}
  }

  public void delete(Computer object) {
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
		  Computer equipment = this.get(id);
		  this.delete(equipment);
	  }
  }

  public Computer get(Object id) {
    Computer result = null;
    if(id instanceof Integer) {
    	String query = "SELECT * from " + this.table + " WHERE id = ? ;";
      try {
        PreparedStatement statement = this.connection.prepareStatement(query);
        statement.setInt(1, (Integer)id);
        ResultSet rs = statement.executeQuery(query);
        while(rs.next()) {
          EquipmentDAO equipmentDAO = new EquipmentDAO();
          Equipment equipment = equipmentDAO.get(id);
          ProcessorDAO processorDAO = new ProcessorDAO();
          Processor processor = processorDAO.get(rs.getInt("processorId"));
          GraphicCardDAO graphicCardDAO = new GraphicCardDAO();
          GraphicCard graphicCard = graphicCardDAO.get(rs.getInt("graphicCardId"));
          result = new Computer(equipment.getId(), equipment.getName(), equipment.isAvailable(), equipment.getImageUrl(), equipment.canBeLoaned(), rs.getString("brand"),rs.getString("model"), rs.getString("serialNumber"), rs.getInt("memorySize"), rs.getBoolean("isLaptop"), rs.getInt("screenSize"), rs.getString("purchaseDate"), rs.getString("renewalDate"), processor, graphicCard );
        }
      } catch(SQLException e) {
        e.printStackTrace();
      }
    }
    return result;
  }
	
  public int autoIncrementId() {
    int res = -1;
    String query = "SELECT MAX(id) AS maxId FROM " + this.table + ";";
    try {
      Statement statement = this.connection.createStatement();
      ResultSet rs = statement.executeQuery(query);
      if (rs.next()) {
        res= rs.getInt("maxId") + 1;
      }
    }
    catch(SQLException e) {
        e.printStackTrace();
    }
    return res;
  }

	public ArrayList<Computer> listAll() {
    System.out.println("COMPUTER DAO LIST ALL START");
		ArrayList<Computer> result = new ArrayList<Computer>();
		try {
			PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM "+this.table + ";");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				EquipmentDAO equipmentDAO = new EquipmentDAO();
        System.out.println("EquipmentDAO get");
				Equipment equipment = equipmentDAO.get(rs.getInt("id"));
				ProcessorDAO processorDAO = new ProcessorDAO();
        System.out.println("processorDAO get");
				Processor processor = processorDAO.get(rs.getInt("processorId"));
				GraphicCardDAO graphicCardDAO = new GraphicCardDAO();
        System.out.println("GraphicCardDAO get");
				GraphicCard graphicCard = graphicCardDAO.get(rs.getInt("graphicCardId"));
				result.add(new Computer(equipment.getId(), equipment.getName(), equipment.isAvailable(), equipment.getImageUrl(), equipment.canBeLoaned(), rs.getString("brand"),rs.getString("model"), rs.getString("serialNumber"), rs.getInt("memorySize"), rs.getBoolean("isLaptop"), rs.getInt("screenSize"), rs.getString("purchaseDate"), rs.getString("renewalDate"), processor, graphicCard ));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

  public ArrayList<Equipment> listAllIdAndName() {
    ArrayList<Equipment> result = new ArrayList<Equipment>();
		try {
			Statement statement = this.connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT id, name FROM Equipment WHERE id IN (SELECT id FROM " + this.table +") ;");
			while (rs.next()) {
        Equipment e = new Equipment(rs.getInt("id"), rs.getString("name"),false,  "", true);
				result.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
  }

  public ArrayList<Computer> simplifiedListAll() {
    System.out.println("COMPUTER DAO LIST ALL START");
		ArrayList<Computer> result = new ArrayList<Computer>();
		try {
			PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM "+this.table + ";");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				EquipmentDAO equipmentDAO = new EquipmentDAO();
        System.out.println("EquipmentDAO get");
				Equipment equipment = equipmentDAO.get(rs.getInt("id"));
        Processor processor = new Processor(0, "", "", 0, 0);
        GraphicCard graphicCard = new GraphicCard(0, "", "", 0);
				result.add(new Computer(equipment.getId(), equipment.getName(), equipment.isAvailable(), equipment.getImageUrl(), equipment.canBeLoaned(), rs.getString("brand"),rs.getString("model"), rs.getString("serialNumber"), rs.getInt("memorySize"), rs.getBoolean("isLaptop"), rs.getInt("screenSize"), rs.getString("purchaseDate"), rs.getString("renewalDate"), processor, graphicCard ));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
  }

  public void update(Computer object, HashMap<String, Object> parameters) {
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
