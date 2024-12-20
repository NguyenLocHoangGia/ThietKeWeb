package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class UserDAO {
	private Connection conn;

	public UserDAO() {
		conn = ConnectDatabase.getConnection();
		if (conn != null) {
			ConnectDatabase.printInfo(conn);
		}
	}

	public List<User> find() {
		List<User> listUsers = new ArrayList<User>();
		try {
			String sql = "SELECT * FROM Users";
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setPosition(rs.getString("position"));
				listUsers.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listUsers;
	}

	public User getUserById(int id) {
		User user = null;
		String query = "SELECT * FROM Users WHERE id = ?";

		try {
			PreparedStatement preparedStatement = conn.prepareStatement(query);

			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				user = new User(resultSet.getInt("id"), resultSet.getString("username"),
						resultSet.getString("password"), resultSet.getString("name"), resultSet.getString("position"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}
	
	public void addUser(User user) {
		String sql = "INSERT INTO Users (username, password, name, position) VALUES (?, ?, ?, ?)";

		try {
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getName());
			statement.setString(4, user.getPosition());

			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error inserting user: " + e.getMessage());
		}
	}
	
	public void deleteUserById(int id) {
	    String sql = "DELETE FROM Users WHERE id = ?";

	    try {
	        PreparedStatement statement = conn.prepareStatement(sql);

	        statement.setInt(1, id);

	        int rowsAffected = statement.executeUpdate();

	        if (rowsAffected > 0) {
	            System.out.println("User with ID " + id + " deleted successfully.");
	        } else {
	            System.out.println("No user found with ID " + id + ".");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	public User findUserByCredentials(String username, String password) {
	    User user = null;
	    String query = "SELECT * FROM Users WHERE username = ? AND password = ?";
	    try {
	        PreparedStatement stmt = conn.prepareStatement(query);
	        stmt.setString(1, username);
	        stmt.setString(2, password);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            user = new User();
	            user.setId(rs.getInt("id"));
	            user.setUsername(rs.getString("username"));
	            user.setPassword(rs.getString("password"));
	            user.setName(rs.getString("name"));
	            user.setPosition(rs.getString("position"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return user;
	}


}
