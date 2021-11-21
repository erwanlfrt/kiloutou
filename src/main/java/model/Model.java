package model;

import java.sql.Connection;

public abstract class Model {

	protected Connection connection;
	protected String table;
	
	public void destroy() {
		DBManager.getInstance().cleanup(connection, null, null);
	}
}
