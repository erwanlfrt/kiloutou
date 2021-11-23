package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
// import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import model.DBManager;
import model.Model;
import model.object.user.User;

public class UserDAO extends Model implements Dao<User> {
	

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
		boolean isReal = object.isReal();

		String query = "INSERT INTO " + this.table
				+ " (name, firstname, address, phoneNumber, mail, login, password, isReal) VALUES (\'" + name + "\', \'"
				+ firstname + "\', \'" + address + "\', \'" + phoneNumber + "\', \'" + mail + "\', \'" + login
				+ "\', SHA1(\'" + password + "\'), " + isReal + " );";
		try {
			Statement statement = this.connection.createStatement();
			statement.executeUpdate(query);
			statement.close();
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
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteById(Object id) {
		if (id instanceof String) {
			User user = this.get(id);
			this.delete(user);
		}
	}

	public User get(Object id) {
		User result = null;
		if (id instanceof String) {
			String query = "SELECT * from " + this.table + " WHERE mail = ? ;";
			try {
				PreparedStatement statement = this.connection.prepareStatement(query);
				statement.setString(1, (String) id);
				ResultSet rs = statement.executeQuery();
				while (rs.next()) {
					result = new User(rs.getString("name"), rs.getString("firstname"), rs.getString("address"),
							rs.getString("phoneNumber"), rs.getString("mail"), rs.getString("login"),
							rs.getString("password"));
					result.setReal(rs.getBoolean("isReal"));
				}
				rs.close();
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public ArrayList<User> listAll() {
		ArrayList<User> result = new ArrayList<User>();
		try {
			PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM " + this.table + ";");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				if (rs.getBoolean("isReal")) {
					User user = new User(rs.getString("name"), rs.getString("firstname"), rs.getString("address"),
							rs.getString("phoneNumber"), rs.getString("mail"), rs.getString("login"),
							rs.getString("password"));
					user.setReal(rs.getBoolean("isReal"));
					result.add(user);
				}
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void generateRandomPwd(String mail) {
		int min = 8;
		int max = 15;
		int size = (int) (min + (Math.random() * (max - min)));

		User user = this.get(mail);

		if (user != null) {
			String generatedPwd = UUID.randomUUID().toString();
			generatedPwd = generatedPwd.replace("-", "");
			generatedPwd = generatedPwd.substring(0, size);

			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("password", generatedPwd);
			this.update(user, params);
		}
	}

	public String getRandomLogin() {
		String login = UUID.randomUUID().toString();
		login = login.replace("-", "");
		boolean alreadyExist = true;
		int count = 0;
		try {
			// check if login doesn't already exist in database
			while (alreadyExist) {
				PreparedStatement statement = this.connection
						.prepareStatement("SELECT COUNT(*) AS count FROM " + this.table + " WHERE login = ?");
				statement.setString(1, login);
				ResultSet rs = statement.executeQuery();
				while (rs.next()) {
					count = rs.getInt("count");
					if (count == 0) {
						alreadyExist = false;
					} else {
						login = UUID.randomUUID().toString();
						login = login.replace("-", "");
					}
				}
				rs.close();
				statement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return login;
	}

	public String getRandomEmail() {
		String email = UUID.randomUUID().toString();
		email = email.replace("-", "") + "@unknown.com";
		boolean alreadyExist = true;
		int count = 0;
		try {
			// check if login doesn't already exist in database
			while (alreadyExist) {
				PreparedStatement statement = this.connection
						.prepareStatement("SELECT COUNT(*) AS count FROM " + this.table + " WHERE mail = ?");
				statement.setString(1, email);
				ResultSet rs = statement.executeQuery();
				while (rs.next()) {
					count = rs.getInt("count");
					if (count == 0) {
						alreadyExist = false;
					} else {
						email = UUID.randomUUID().toString();
						email = email.replace("-", "") + "@unknown.com";
					}
				}
				rs.close();
				statement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return email;
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
				} else {
					change = column + " = \'" + (String) value + "\'" + (cpt != numberOfChanges ? ", " : " ");
				}

			} else if (value instanceof Boolean || value instanceof Integer) {
				change = column + " = " + value.toString() + (cpt != numberOfChanges ? ", " : " ");
			}
			changes += change;
			cpt++;
		}
		if (!changes.equals("")) {
			String query = "UPDATE " + this.table + " SET " + changes + " WHERE mail = \'" + mail + "\';";
			Statement statement;
			try {
				statement = this.connection.createStatement();
				statement.executeUpdate(query);
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
