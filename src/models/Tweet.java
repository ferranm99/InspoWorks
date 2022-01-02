package models;
import java.sql.Timestamp;

public class Tweet implements java.io.Serializable {

	 private static final long serialVersionUID = 1L;

	 private int id;
	 private int uid;
	 private String uname;
	 private Timestamp postDateTime;
	 private String content;
	 private int pid;
	 private int numLikes;
	 private boolean liked;

	 public Tweet() {
	 }

	 public Integer getId() {
		 return this.id;
	 }
	 
	 public void setId(Integer id) {
		 this.id = id;
	 }

	 public int getUid() {
		 return this.uid;
	 }
	 
	 public void setUid(int uid) {
		 this.uid = uid;
	 }
	 
	 public String getUname() {
		 return this.uname;
	 }
	 
	 public void setUname(String uname) {
		 this.uname = uname;
	 }
	 
	 public Timestamp getPostDateTime() {
		 return this.postDateTime;
	 }
	 public void setPostDateTime(Timestamp postDateTime) {
		 this.postDateTime = postDateTime;
	 }
	 
	 public String getContent() {
		 return this.content;
	 }
	 public void setContent(String content) {
		 this.content = content;
	 }

	public Integer getPid() {
		return this.pid;
		
	}
	public void setPid(Integer pid) {
		this.pid = pid;
		
	}
	
	 public Integer getNumLikes() {
		 return this.numLikes;
	 }
	 public void setNumLikes(int numLikes) {
		 this.numLikes = numLikes;
	 }
	 public boolean getLiked() {
		 return this.liked;
	 }
	 public void setLiked(boolean liked) {
		 this.liked = liked;
	 }


}