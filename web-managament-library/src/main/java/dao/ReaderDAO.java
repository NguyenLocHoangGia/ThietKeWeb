package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Reader;

public class ReaderDAO {
	private Connection conn;

	public ReaderDAO() {
		conn = ConnectDatabase.getConnection();
	}

	public List<Reader> find() {
        List<Reader> readers = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Readers";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Reader reader = new Reader();
                reader.setId(rs.getInt("id"));
                reader.setName(rs.getString("name"));
                reader.setIdentityCard(rs.getString("identityCard"));
                reader.setDob(rs.getDate("dob"));
                reader.setEmail(rs.getString("email"));
                reader.setPhone(rs.getString("phone"));
                reader.setAddress(rs.getString("address"));
                reader.setStartDate(rs.getDate("startDate"));
                reader.setEndDate(rs.getDate("endDate"));

                readers.add(reader);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return readers;
    }
	
    public Reader findById(int id) {
        Reader reader = null;
        try {
            String sql = "SELECT * FROM Readers WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                reader = new Reader();
                reader.setId(rs.getInt("id"));
                reader.setName(rs.getString("name"));
                reader.setIdentityCard(rs.getString("identityCard"));
                reader.setDob(rs.getDate("dob"));
                reader.setEmail(rs.getString("email"));
                reader.setPhone(rs.getString("phone"));
                reader.setAddress(rs.getString("address"));
                reader.setStartDate(rs.getDate("startDate"));
                reader.setEndDate(rs.getDate("endDate"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reader;
    }

	
	public List<Reader> findByKeyword(String keyword) {
	    List<Reader> readers = new ArrayList<>();
	    String query = "SELECT * FROM Readers WHERE phone LIKE ? OR name LIKE ? OR email LIKE ?";
	    try (PreparedStatement stmt = conn.prepareStatement(query)) {
	        String searchKeyword = "%" + keyword + "%";
	        stmt.setString(1, searchKeyword);
	        stmt.setString(2, searchKeyword);
	        stmt.setString(3, searchKeyword);

	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            Reader reader = new Reader(
	                rs.getInt("id"),
	                rs.getString("identityCard"),
	                rs.getString("name"),
	                rs.getDate("dob"),
	                rs.getString("email"),
	                rs.getString("phone"),
	                rs.getString("address"),
	                rs.getDate("startDate"),
	                rs.getDate("endDate")
	            );
	            readers.add(reader);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return readers;
	}

	
	public List<Reader> find(String phone) {
		List<Reader> readers = new ArrayList<Reader>();
		String query = "SELECT * FROM Readers WHERE phone LIKE ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1,"%" + phone + "%");
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Reader reader = new Reader(rs.getInt("id"), rs.getString("identityCard"), rs.getString("name"),
						rs.getDate("dob"), rs.getString("email"), rs.getString("phone"), rs.getString("address"),
						rs.getDate("startDate"), rs.getDate("endDate"));
				readers.add(reader);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return readers;
	}

	public boolean addReader(Reader reader) {
		String query = "INSERT INTO Readers(identityCard, name, dob, email, phone, address, startDate, endDate) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setString(1, reader.getIdentityCard());
			stmt.setString(2, reader.getName());
			stmt.setDate(3, new java.sql.Date(reader.getDob().getTime()));
			stmt.setString(4, reader.getEmail());
			stmt.setString(5, reader.getPhone());
			stmt.setString(6, reader.getAddress());
			stmt.setDate(7, reader.getStartDate());
			stmt.setDate(8, reader.getEndDate());
			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Reader getReaderById(int id) {
        Reader reader = null;
        String query = "SELECT * FROM Readers WHERE id = ?";

        try {
        	PreparedStatement preparedStatement = conn.prepareStatement(query);

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                reader = new Reader(
                    resultSet.getInt("id"),
                    resultSet.getString("identityCard"),
                    resultSet.getString("name"),
                    resultSet.getDate("dob"),
                    resultSet.getString("email"),
                    resultSet.getString("phone"),
                    resultSet.getString("address"),
                    resultSet.getDate("startDate"),
                    resultSet.getDate("endDate")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reader;
    }
	
    public void deleteReaderById(int id) {
    	String sql = "DELETE FROM Readers WHERE id = ?";

	    try {
	        PreparedStatement statement = conn.prepareStatement(sql);

	        // Set the ID parameter
	        statement.setInt(1, id);

	        // Execute the delete query
	        int rowsAffected = statement.executeUpdate();

	        if (rowsAffected > 0) {
	            System.out.println("reader with ID " + id + " deleted successfully.");
	        } else {
	            System.out.println("No readers found with ID " + id + ".");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Error deleting book: " + e.getMessage());
	    }
    }

    public boolean update(Reader reader) {
        String sql = "UPDATE Readers SET name = ?, identityCard = ?, dob = ?, email = ?, phone = ?, address = ? WHERE id = ?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, reader.getName());
            st.setString(2, reader.getIdentityCard());
            st.setDate(3, reader.getDob());
            st.setString(4, reader.getEmail());
            st.setString(5, reader.getPhone());
            st.setString(6, reader.getAddress());
            st.setInt(7, reader.getId());
            return st.executeUpdate() > 0; // Trả về true nếu cập nhật thành công
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
