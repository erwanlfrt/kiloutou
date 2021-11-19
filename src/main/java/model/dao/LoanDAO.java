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
import model.object.equipment.Equipment;
import model.object.user.User;
import model.object.loan.Loan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LoanDAO implements Dao<Loan> {
	Connection connection;
	String table;
	
	public LoanDAO() {
		connection = DBManager.getInstance().getConnection();
		table = "Loan";
	}

  public void add(Loan object) {
    int id = this.autoIncrementId();
    int equipmentId = object.getEquipment().getId();
    String userMail = object.getUser().getMail();
    LocalDate beginningDate = object.getBeginningDate();
    LocalDate endDate = object.getEndDate();
    boolean isBorrowed = object.isBorrowed();

    String query = "INSERT INTO " + this.table + " (id, equipmentId, userMail, beginningDate, endDate, isBorrowed ) VALUES ("+ id +", "+ equipmentId  + ", \'" + userMail +"\', \'" + this.javaDateToMysqlDate(beginningDate) +"\', \'" + this.javaDateToMysqlDate(endDate) +"\', " + isBorrowed+ ");";
    try {
      Statement statement = this.connection.createStatement();
      statement.executeUpdate(query);
    } catch (SQLException e) {
			e.printStackTrace();
		}
  }

  public void delete(Loan object) {
    int id = object.getId();
    String query = "DELETE FROM " + this.table + " WHERE id = " + id;

    try {
      Statement statement = this.connection.createStatement();
      statement.executeUpdate(query);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  public void deleteById(Object id) {
	  if(id instanceof Object[]) {
		  Loan equipment = this.get(id);
		  this.delete(equipment);
	  }
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

  public Loan get(Object id) {
    Loan result = null;
    if(id instanceof Object) {
    	String query = "SELECT * from " + this.table + " WHERE id = ? ;";
      try {
        PreparedStatement statement = this.connection.prepareStatement(query);
        statement.setInt(1, (Integer)id);
        ResultSet rs = statement.executeQuery();
        EquipmentDAO equipmentDAO = new EquipmentDAO();
        while(rs.next()) {
          Equipment equipment = equipmentDAO.get(rs.getInt("equipmentId"));
          UserDAO userDAO = new UserDAO();
          User user = userDAO.get(rs.getString("userMail"));

          result = new Loan((int)id, equipment, user, this.mysqlDateToJavaDate(rs.getString("beginningDate")), this.mysqlDateToJavaDate(rs.getString("endDate")), rs.getBoolean("isBorrowed"));
        }
      } catch(SQLException e) {
        e.printStackTrace();
      }
    }
    return result;
  }
	
	public ArrayList<Loan> listAll() {
		ArrayList<Loan> result = new ArrayList<Loan>();
		try {
			PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM "+this.table + ";");
			ResultSet rs = statement.executeQuery();
      EquipmentDAO equipmentDAO = new EquipmentDAO();
			while (rs.next()) {
            int id = rs.getInt("id");	        
		        Equipment equipment = equipmentDAO.get(rs.getInt("equipmentId"));
		        UserDAO userDAO = new UserDAO();
		        User user = userDAO.get(rs.getString("userMail"));

				result.add(new Loan(id, equipment, user, this.mysqlDateToJavaDate(rs.getString("beginningDate")), this.mysqlDateToJavaDate(rs.getString("endDate")), rs.getBoolean("isBorrowed")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

  public void update(Loan object, HashMap<String, Object> parameters) {
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
      String query = "UPDATE " + this.table + " SET " + changes +" WHERE id = " + object.getId();
      Statement statement;
      try {
        statement = this.connection.createStatement();
        statement.executeUpdate(query);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  private String javaDateToMysqlDate(LocalDate date) {
	  DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	  String formattedDate = date.format(myFormatObj);
	  return formattedDate;
  }

  private LocalDate mysqlDateToJavaDate(String mysqlDate) {
	  DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	  LocalDate result = LocalDate.parse(mysqlDate);
	  return result;
  }

  public void replaceUser(String mail, User user) {
    String query = "UPDATE " + this.table + " SET userMail = ? WHERE userMail = ?";
    
    try {
      PreparedStatement statement = this.connection.prepareStatement(query);
      statement.setString(1, user.getMail());
      statement.setString(2, mail);
      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
