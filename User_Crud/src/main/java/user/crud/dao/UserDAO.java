package user.crud.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import user.crud.model.User;

public class UserDAO {

	private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
	private String jdbcUserName="root";
	private String jdbcPassword="";
	
	private static final String INSERT_USER="INSERT INTO users (name,email,country) VALUES"
			+"(?,?,?)";
	private static final String SEELCT_USER_BY_ID ="SELECT * FROM USERS WHERE id = ?";
	private static final String SEELCT_ALL_USERS ="SELECT * FROM USERS";
	private static final String DELETE_USER_BY_ID ="DELETE FROM USERS WHERE id=?";
	private static final String UPDATE_USER_BY_ID ="UPDATE USERS SET name = ?,email=?,country=? WHERE id =?";
	
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection =DriverManager.getConnection(jdbcURL,jdbcUserName,jdbcPassword);
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public User selectUser(int id) {
		User user = null;
		try(Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SEELCT_USER_BY_ID);) {
			
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				String name = rs.getString("name");
				String email = rs.getString("email");
				String country = rs.getString("country");
				 user = new User(id,name,email,country);
			}
			
		} catch (SQLException e) {
            e.printStackTrace();
        }
		
		return user;
	}
	public List<User> selectAllUsers() {
		List<User> users = new ArrayList<>();
		try(Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SEELCT_ALL_USERS);) {
			
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String country = rs.getString("country");
				users.add(new User(id,name,email,country));
			}
			
		} catch (SQLException e) {
            e.printStackTrace();
        }
		
		return users;
	}
	public void insertUser(User user) throws SQLException{
		try(Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);) {
			
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getCountry());
			preparedStatement.executeUpdate();
			
		} catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public boolean updateUser(User user) throws SQLException{
		boolean isUpdated=false;
		try(Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_BY_ID);) {
			
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getCountry());
			preparedStatement.setInt(4, user.getId());
			isUpdated=preparedStatement.executeUpdate()>0;
			
		}
		return isUpdated;
	}
	public boolean deleteUser(int id) throws SQLException{
		boolean isDeleted=false;
		try(Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_BY_ID);) {
			
			preparedStatement.setInt(1, id);
			isDeleted=preparedStatement.executeUpdate()>0;
			
		}
		return isDeleted;
	}
}
