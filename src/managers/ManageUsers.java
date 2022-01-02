package managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import models.User;
import utils.DB;

public class ManageUsers {
	
	private DB db = null ;
	
	public ManageUsers() {
		try {
			db = new DB();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void finalize() {
		try {
			db.disconnectBD();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	/* Get a user given its PK*/
	public User getUser(Integer id) {
		String query = "SELECT * FROM users WHERE id = ? ;";
		PreparedStatement statement = null;
		ResultSet rs = null;
		User user = null;
		try {
			statement = db.prepareStatement(query);
			statement.setInt(1,id);
			rs = statement.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUser(rs.getString("usr"));
				user.setName(rs.getString("name"));
				user.setMail(rs.getString("mail"));
				user.setBirthday(rs.getString("birthday"));
				user.setPhone(rs.getString("phone"));
				user.setGender(rs.getString("gender"));
				user.setPwd2(rs.getString("pwd"));
				user.setAdmin(rs.getBoolean("admin"));
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
		
	// Add new user
	public void addUser(User user) {
		String query = "INSERT INTO users (usr, mail, pwd, name, birthday, phone, gender) VALUES (?,?,?,?,?,?,?)";
		PreparedStatement statement = null;
		
		try {
			statement = db.prepareStatement(query);
			statement.setString(1,user.getUser());
			statement.setString(2,user.getMail());
			statement.setString(3,user.getPwd2());
			statement.setString(4,user.getName());
			statement.setString(5,user.getBirthday());
			statement.setString(6,user.getPhone());
			statement.setString(7,user.getGender());
			statement.executeUpdate();
			statement.close();

		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Follow a user
	public void followUser(Integer uid, Integer fid) {
		String query = "INSERT INTO follows (uid,fid) VALUES (?,?)";
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			statement.setInt(1,uid);
			statement.setInt(2,fid);
			statement.executeUpdate();
			statement.close();
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Unfollow a user
	public void unfollowUser(Integer uid, Integer fid) {
		String query = "DELETE FROM follows WHERE uid = ? AND fid = ?";
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			statement.setInt(1,uid);
			statement.setInt(2,fid);
			statement.executeUpdate();
			statement.close();
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	// Get all the users
	public List<User> getUsers(Integer start, Integer end) {
		 String query = "SELECT id, name FROM users ORDER BY name ASC LIMIT ?,?;";
		 PreparedStatement statement = null;
		 List<User> l = new ArrayList<User>();
		 try {
			 statement = db.prepareStatement(query);
			 statement.setInt(1,start);
			 statement.setInt(2,end);
			 ResultSet rs = statement.executeQuery();
			 while (rs.next()) {
				 User user = new User();
				 user.setId(rs.getInt("id"));
				 user.setName(rs.getString("name"));
				 l.add(user);
			 }
			 rs.close();
			 statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return  l;
	}
	
	public List<User> getNotFollowedUsers(Integer id, Integer start, Integer end) {
		 String query = "SELECT id,name,admin FROM users WHERE id NOT IN (SELECT id FROM users,follows WHERE id = fid AND uid = ?) AND id <> ? ORDER BY name LIMIT ?,?;";
		 PreparedStatement statement = null;
		 List<User> l = new ArrayList<User>();
		 try {
			 statement = db.prepareStatement(query);
			 statement.setInt(1,id);
			 statement.setInt(2,id);
			 statement.setInt(3,start);
			 statement.setInt(4,end);
			 ResultSet rs = statement.executeQuery();
			 while (rs.next()) {
				 User user = new User();
				 user.setId(rs.getInt("id"));
				 user.setName(rs.getString("name"));
				 user.setAdmin(rs.getBoolean("admin"));
				 l.add(user);
			 }
			 rs.close();
			 statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return  l;
	}
	
	public List<User> getFollowedUsers(Integer id, Integer start, Integer end) {
		 String query = "SELECT id,name, admin FROM users,follows WHERE id = fid AND uid = ? ORDER BY name LIMIT ?,?;";
		 PreparedStatement statement = null;
		 List<User> l = new ArrayList<User>();
		 try {
			 statement = db.prepareStatement(query);
			 statement.setInt(1,id);
			 statement.setInt(2,start);
			 statement.setInt(3,end);
			 ResultSet rs = statement.executeQuery();
			 while (rs.next()) {
				 User user = new User();
				 user.setId(rs.getInt("id"));
				 user.setName(rs.getString("name"));
				 user.setAdmin(rs.getBoolean("admin"));
				 l.add(user);
			 }
			 rs.close();
			 statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return  l;
	}
	
	public Pair<Boolean,User> checkLogin(User user) {
		
		String query = "SELECT id, usr, pwd from users where usr=? AND pwd=?";
		PreparedStatement statement = null;
		boolean output = false;
		try {
			statement = db.prepareStatement(query);
			statement.setString(1,user.getUser());
			statement.setString(2,user.getPwd2());
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {

				user.setId(rs.getInt("id"));
				user.setUser(rs.getString("usr"));
				user.setPwd2(rs.getString("pwd"));
				
				output = true;
			} 
			rs.close();
			statement.close();
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Pair.of(output,user);
		
	}
	
	//EditUser
	public void editUser(User user) {
		String query = "UPDATE users\r\n"
				+ "SET usr =?, name=?, birthday=?, gender=?, phone=?"
				+ "where id = ?;";
		PreparedStatement statement = null;
		
		try {
			statement = db.prepareStatement(query);
			statement.setString(1,user.getUser());
			statement.setString(2,user.getName());
			statement.setString(3,user.getBirthday());
			statement.setString(4,user.getPhone());
			statement.setString(5,user.getGender());
			statement.setInt(6,user.getId());
			statement.executeUpdate();
			statement.close();

		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	
	public boolean checkUser(String user) {
		
		String query = "SELECT name from users where name=?";
		PreparedStatement statement = null;
		ResultSet rs = null;
		boolean output = false;
		try {
			statement = db.prepareStatement(query);
			statement.setString(1,user);
			rs = statement.executeQuery();
			if (rs.isBeforeFirst()) {
				output = true;
			}
			rs.close();
			statement.close();
			return output;
			
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return output;
		
	}
	
	public boolean chekMail(String mail) {
		
		String query = "SELECT mail from users where mail=?";
		PreparedStatement statement = null;
		ResultSet rs = null;
		boolean output = false;
		try {
			
			statement = db.prepareStatement(query);
			statement.setString(1,mail);
			rs = statement.executeQuery();
			if (rs.isBeforeFirst()) {
				output = true;
			}
			rs.close();
			statement.close();
			return output;
			
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return output;
		
	}
		
	/*Check if all the fields are filled correctly */
	public boolean isComplete(User user) {
	    return(hasValue(user.getName()) &&
	    	   hasValue(user.getMail()) &&
	    	   hasValue(user.getPwd2()));
	}
	
	public boolean isLoginComplete(User user) {
	    return(hasValue(user.getUser()) &&
	    	   hasValue(user.getPwd2()) );
	}
	
	private boolean hasValue(String val) {
		return((val != null) && (!val.equals("")));
	}
		
	
	// TODO: add other methods 

	public void delUser(Integer id) {
		String query = "SET SQL_SAFE_UPDATES = 0;";
		String query2 = "DELETE FROM follows WHERE uid = ? OR fid =?";
		String query3 = "DELETE FROM tweets WHERE uid = ?;";
		String query4 = "DELETE FROM users WHERE id = ?;";
		String query5 = "SET SQL_SAFE_UPDATES = 1;";
		PreparedStatement statement = null;
		PreparedStatement statement2 = null;
		PreparedStatement statement3 = null;
		PreparedStatement statement4 = null;
		PreparedStatement statement5 = null;
		try {
			statement = db.prepareStatement(query);
			statement2 = db.prepareStatement(query2);
			statement3 = db.prepareStatement(query3);
			statement4 = db.prepareStatement(query4);
			statement5 = db.prepareStatement(query5);
			statement2.setInt(1,id);
			statement2.setInt(2,id);
			statement3.setInt(1,id);
			statement4.setInt(1,id);
			
			statement.executeUpdate();
			statement2.executeUpdate();
			statement3.executeUpdate();
			statement4.executeUpdate();
			statement5.executeUpdate();
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void makeAdmin(Integer id) {
		
		String query = "UPDATE users SET admin=1 WHERE id=?; ";
		PreparedStatement statement = null;
		
		try {
			statement = db.prepareStatement(query);
			statement.setInt(1, id);
			statement.executeUpdate();
			statement.close();

		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}