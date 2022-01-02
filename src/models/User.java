package models;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User implements java.io.Serializable {
	

	private static final long serialVersionUID = 1L;
	
	private int id = 0;
	private String user = "";
	private String mail = "";
	private String pwd1 = "";
	private String pwd2 = "";
	private String name = "";
	private String birthday = "";
	private String gender = "";
	private String phone = "";
	private boolean admin;

	private HashMap<String,Boolean> error = null;
	
	public User() {
		 error = new HashMap<String, Boolean>();
		 error.put("user", false);
		 error.put("mail", false);
		 error.put("pwd1", false);
		 error.put("pwd2", false);
		 error.put("name", false);
		 error.put("birthday", false);
		 error.put("gender", false);
		 error.put("phone", false);
	}
	
	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		/* We can simulate that a user with the same name exists in our DB and mark error[0] as true  */
		//error[0] = true;
		if(user.length() >=4) {
			this.user = user;
			System.out.println(user);
		}else {
			error.put("user", true);
			System.out.println(user);
		}
	}
	
	public String getMail() {
		return this.mail;
	}
	
	public void setMail(String mail) {
		String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(mail);
		if (matcher.matches()) {
			this.mail = mail;
			System.out.println(mail);
		} else {
			error.put("mail", true);
			System.out.println(mail);
		}
	}
	
	public String getPwd1() {
		return this.pwd1;
	}
	
	public void setPwd1(String pwd1) {
		/* TODO check restriction with pattern */
		//8 chars, upperCase,Lowercase and number
		String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(pwd1);
		if (matcher.matches()) {
			this.pwd1 = pwd1;
			System.out.println(pwd1);
		} else {
			error.put("pwd1", true);
			System.out.println(pwd1);
		}
	}
	
	public String getPwd2() {
		return this.pwd2;
	}
	
	public void setPwd2(String pwd2) {
		/* TODO check restriction with pattern and check if pwd1=pwd2*/
		
		this.pwd2 = pwd2;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
		System.out.println(name);
	}
	
	public String getBirthday() {
		return this.birthday;
	}
	
	public void setBirthday(String birthday) {
		String regex = "^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(birthday);
		if (matcher.matches()) {
			this.birthday = birthday;
			System.out.println(birthday);
		} else {
			error.put("birthday", true);
			System.out.println(birthday);
		}
	}
	
	public String getGender() {
		return this.gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
		System.out.println(gender);
	}
	
	public String getPhone() {
		return this.phone;
	}
	
	public void setPhone(String phone) {
		String regex = "(\\+34|0034|34)?[ -]*(6|7)[ -]*([0-9][ -]*){8}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(phone);
		if (matcher.matches()) {
			this.phone = phone;
			System.out.println(phone);
		} else {
			error.put("phone", true);
			System.out.println(phone);
		}
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public boolean getAdmin() {
		return this.admin;
	}	
	
	public void setAdmin(boolean ad) {
		this.admin = ad;
	}
	
	public HashMap<String,Boolean> getError() {
		return this.error;
	}
	
	public void setError(String name, boolean error) {
		this.error.put(name, error);
	}
		
}
