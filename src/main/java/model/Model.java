package model;

import java.sql.Connection;

public abstract class Model {

	protected Connection connection;
	protected String table;
	
	public void closeConn() {
		DBManager.getInstance().cleanup(connection, null, null);
	}
}
