package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Category;

public class CategoryDAO {
	private Connection conn;
	
	public CategoryDAO() {
		conn = ConnectDatabase.getConnection();
	}
	
	public List<Category> find() {
		List<Category> listCategories = new ArrayList<Category>();
		
		try {
			String sql = "SELECT * FROM Categories";
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Category category = new Category();
				category.setId(rs.getInt("id"));
				category.setName(rs.getString("name"));
				listCategories.add(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listCategories;
	}
	
	public Category findById(int id) {
		
		Category category = new Category();
		try {
			String sql = "SELECT * FROM Categories WHERE id = ?";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				category.setId(rs.getInt("id"));
				category.setName(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return category;
	}
	
	public void createCategory(Category category) {
        String sql = "INSERT INTO Categories (name, description) VALUES (?, ?)";

        try {
             PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, category.getName());
            statement.setString(2, category.getDescription());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public void deleteById(int id) {
	    String sql = "DELETE FROM Categories WHERE id = ?";
	    try {
	        PreparedStatement statement = conn.prepareStatement(sql);
	        statement.setInt(1, id);
	        statement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

}
