package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Book;
import model.Category;

public class BookDAO {
	private Connection conn;
	
	public BookDAO() {
		conn = ConnectDatabase.getConnection();
	}
	
	public List<Book> find() {
		List<Book> listBooks = new ArrayList<Book>();
		try {
			String sql = "SELECT b.id, b.name, image, author, amount, b.description, createdAt, categoryId, c.name AS categoryName FROM Books b\n"
					+ "INNER JOIN Categories c ON b.categoryId = c.id;";
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Book book = new Book();
				book.setId(rs.getInt("id"));
				book.setName(rs.getString("name"));
				book.setAuthor(rs.getString("author"));
				book.setAmount(rs.getInt("amount"));
				book.setDescription(rs.getString("description"));
				book.setCreatedAt(rs.getString("createdAt"));
				
				Category category = new Category();
				category.setId(rs.getInt("categoryId"));
				category.setName(rs.getString("categoryName"));
				book.setCategory(category);
				
				listBooks.add(book);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listBooks;
	}
	
	public List<Book> find(String key) {
		List<Book> listBooks = new ArrayList<Book>();
		try {
			String sql = "SELECT b.id, b.name, image, author, amount, b.description, createdAt, categoryId, c.name AS categoryName FROM Books b\n"
					+ "INNER JOIN Categories c ON b.categoryId = c.id WHERE b.name LIKE ?;";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, "%" + key + "%");
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Book book = new Book();
				book.setId(rs.getInt("id"));
				book.setName(rs.getString("name"));
				book.setAuthor(rs.getString("author"));
				book.setAmount(rs.getInt("amount"));
				book.setDescription(rs.getString("description"));
				book.setCreatedAt(rs.getString("createdAt"));
				
				Category category = new Category();
				category.setId(rs.getInt("categoryId"));
				category.setName(rs.getString("categoryName"));
				book.setCategory(category);
				
				listBooks.add(book);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listBooks;
	}
	
	public void create(Book book) {
		String sql = "INSERT INTO Books (name, categoryId, image, author, amount, description) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, book.getName());
			st.setInt(2, book.getCategory().getId());
			st.setString(3,	null);
			st.setString(4, book.getAuthor());
			st.setInt(5, book.getAmount());
			st.setString(6, book.getDescription());
			st.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Book findById(int id) {
        Book book = null;
        String query = "SELECT b.id, b.name, image, author, amount, b.description, createdAt, categoryId, c.name AS categoryName, c.description AS categoryDescription FROM Books b INNER JOIN Categories c ON b.categoryId = c.id WHERE b.id = ?";

        try {
        	PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int bookId = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String image = resultSet.getString("image");
                    String author = resultSet.getString("author");
                    String description = resultSet.getString("description");
                    int amount = resultSet.getInt("amount");
                    String createdAt = resultSet.getString("createdAt");

                    Category category = new Category(resultSet.getInt("categoryId"), resultSet.getString("categoryName"), resultSet.getString("categoryDescription"));

                    book = new Book(bookId, name, image, category, author, description, amount, createdAt);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log error or handle it as appropriate
        }

        return book;
    }
	
	public void deleteBookById(int id) {
	    String sql = "DELETE FROM Books WHERE id = ?";

	    try {
	        PreparedStatement statement = conn.prepareStatement(sql);

	        // Set the ID parameter
	        statement.setInt(1, id);

	        // Execute the delete query
	        int rowsAffected = statement.executeUpdate();

	        if (rowsAffected > 0) {
	            System.out.println("Book with ID " + id + " deleted successfully.");
	        } else {
	            System.out.println("No book found with ID " + id + ".");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Error deleting book: " + e.getMessage());
	    }
	}
	
	public void editBook(Book book) {
	    String sql = "UPDATE Books SET name = ?, categoryId = ?, image = ?, author = ?, amount = ?, description = ? WHERE id = ?";

	    try {
	        PreparedStatement statement = conn.prepareStatement(sql);
	        // Set parameters for the SQL query
	        statement.setString(1, book.getName());
	        statement.setInt(2, book.getCategory().getId());
	        statement.setString(3, book.getImage());  // Assuming you have a method to set the image
	        statement.setString(4, book.getAuthor());
	        statement.setInt(5, book.getAmount());
	        statement.setString(6, book.getDescription());
	        statement.setInt(7, book.getId());

	        // Execute the update query
	        int rowsAffected = statement.executeUpdate();

	        if (rowsAffected > 0) {
	            System.out.println("Book with ID " + book.getId() + " updated successfully.");
	        } else {
	            System.out.println("No book found with ID " + book.getId() + ".");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Error updating book: " + e.getMessage());
	    }
	}


}
