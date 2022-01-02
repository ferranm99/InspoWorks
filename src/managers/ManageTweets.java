package managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Tweet;
import models.User;
import utils.DB;


public class ManageTweets {
	
	private DB db = null ;
	
	public ManageTweets() {
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
	
	/* Add a tweet */
	public void addTweet(Tweet tweet) {
		String query = "INSERT INTO tweets (uid,postdatetime,content) VALUES (?,?,?)";
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			statement.setInt(1,tweet.getUid());
			statement.setTimestamp(2,tweet.getPostDateTime());
			statement.setString(3,tweet.getContent());
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/* Add a tweet */
	public void addComment(Tweet tweet) {
		String query = "INSERT INTO tweets (uid,postdatetime,content, pid) VALUES (?,?,?, ?)";
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			statement.setInt(1,tweet.getUid());
			statement.setTimestamp(2,tweet.getPostDateTime());
			statement.setString(3,tweet.getContent());
			statement.setInt(4,tweet.getPid());
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/* Delete existing tweet */
	public void deleteTweet(Integer id,Integer uid) {
		String query = "DELETE FROM tweets WHERE id = ? AND uid=?";
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			statement.setInt(1,id);
			statement.setInt(2,uid);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void adminDeleteTweet(Integer id) {
		String query = "DELETE FROM tweets WHERE id = ?";
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			statement.setInt(1,id);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Tweet getTweet(Integer id) {
		String query = "SELECT * FROM tweets WHERE id = ?";
		PreparedStatement statement = null;
		ResultSet rs = null;
		Tweet tweet = null;
		try {
			statement = db.prepareStatement(query);
			statement.setInt(1,id);
			rs = statement.executeQuery();
			if (rs.next()) {
				tweet = new Tweet();
				tweet.setId(rs.getInt("id"));
				tweet.setUid(rs.getInt("uid"));
				tweet.setPostDateTime(rs.getTimestamp("postdatetime"));
				tweet.setContent(rs.getString("content"));
				tweet.setPid(rs.getInt("pid"));
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return tweet;
	}
	
	public void updateTweet(Integer id, String content) {
		String query = "UPDATE tweets set content = ? where id = ?;";
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			statement.setString(1,content);
			statement.setInt(2,id);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/* Get tweets from a user given start and end*/
	public List<Tweet> getUserTweets(Integer uid,Integer start, Integer end) {
 		String query = "SELECT DISTINCT ts.id, ts.uid, ts.postdatetime, ts.content, ts.pid, ts.numlikes, (SELECT ux.name FROM users ux WHERE ts.uid = ux.id) AS name\r\n"
 				+ "FROM tweets ts, users u, follows f\r\n"
 				+ "WHERE ts.uid IN (SELECT f.fid from follows f\r\n"
 				+ "WHERE f.uid =?) or ts.uid = ?\r\n"
 				+ "ORDER BY ts.postdatetime DESC LIMIT ?,?;";
		 PreparedStatement statement = null;
		 List<Tweet> l = new ArrayList<Tweet>();
		 try {
			 statement = db.prepareStatement(query);
			 statement.setInt(1,uid);
			 statement.setInt(2,uid);
			 statement.setInt(3,start);
			 statement.setInt(4,end);
			 ResultSet rs = statement.executeQuery();
			 while (rs.next()) {
				 Tweet tweet = new Tweet();
       		     tweet.setId(rs.getInt("id"));
				 tweet.setUid(rs.getInt("uid"));
				 tweet.setPostDateTime(rs.getTimestamp("postdatetime"));
				 tweet.setContent(rs.getString("content"));
				 tweet.setUname(rs.getString("name"));
				 tweet.setPid(rs.getInt("pid"));
				 tweet.setNumLikes(rs.getInt("numlikes"));
				 tweet.setLiked(checkLikedTweet(tweet.getId(), uid));
				 l.add(tweet);
			 }
			 rs.close();
			 statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return  l;
	}

	
	public List<Tweet> getAnonymousTweets(Integer start, Integer end) {
 		String query = "SELECT DISTINCT ts.id, ts.uid, ts.postdatetime, ts.numlikes,ts.content, ts.pid FROM tweets ts ORDER BY ts.postdatetime DESC LIMIT ?,?;";
		 PreparedStatement statement = null;
		 List<Tweet> l = new ArrayList<Tweet>();
		 try {
			 statement = db.prepareStatement(query);
			 statement.setInt(1,start);
			 statement.setInt(2,end);
			 ResultSet rs = statement.executeQuery();
			 while (rs.next()) {
				 Tweet tweet = new Tweet();
       		     tweet.setId(rs.getInt("id"));
				 tweet.setUid(rs.getInt("uid"));
				 tweet.setPostDateTime(rs.getTimestamp("postdatetime"));
				 tweet.setContent(rs.getString("content"));
				 tweet.setPid(rs.getInt("pid"));
				 tweet.setNumLikes(rs.getInt("numlikes"));
				 l.add(tweet);
			 }
			 rs.close();
			 statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return  l;
	}
	
	public void likeTweet(Integer tid, Integer uid) {
		
		boolean alreadyLiked = checkLikedTweet(tid, uid);		
		String query = "";
		String query2 = "";
		
		if(alreadyLiked) { //dislike tweet and update both tweets and likes tables
			query = "DELETE FROM likes WHERE tid = ? AND uid = ?";
			query2 = "UPDATE tweets SET numlikes = numlikes - 1 WHERE id = ?";
		}else { //like tweet
			query = "INSERT INTO likes(tid, uid) VALUES (?, ?)";
			query2 = "UPDATE tweets SET numlikes = numlikes + 1 WHERE id = ?";
		}
		//Execute query 1
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			statement.setInt(1,tid);
			statement.setInt(2,uid);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Execute query 2
		PreparedStatement statement2 = null;
		try {
			statement2 = db.prepareStatement(query2);
			statement2.setInt(1,tid);
			statement2.executeUpdate();
			statement2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//check if the user already liked the tweet
	public boolean checkLikedTweet(Integer tid, Integer uid) {
		String query = "SELECT tid FROM likes WHERE tid = ? AND uid = ? ;";
		boolean output = false;
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			statement.setInt(1,tid);
			statement.setInt(2,uid);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				//System.out.println("checkpoint: " + rs.getInt("tid"));
				output = true;
			}			
			rs.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return output;
	}
	
	
}