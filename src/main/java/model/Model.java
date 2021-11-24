package model;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class Model {

	protected Connection connection;
	protected String table;
	
	public void closeConn() {
		DBManager.getInstance().cleanup(connection, null, null);
	}

  public void refreshConnection() {
    try {
		if(this.connection.isClosed()) {
		  this.connection = DBManager.getInstance().getConnection();
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
  }
}
