<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<c:if test = "${error}">
<div class="w3-panel w3-theme-l4 w3-display-container">
  <span onclick="this.parentElement.style.display='none'"
  class="w3-button w3-large w3-display-topright">&times;</span>
  <h3> Identification error! </h3>
  <p> Provided credentials do not match our database. </p>
</div>
</c:if>


<form action="LoginController" method="POST">
	<h2>Welcome back!</h2>
	<p>      
    <label class="w3-text-red" for="user id"> User id </label>
    <input class="w3-input w3-border w3-light-grey" type="text" name="user" placeholder="User" value="${user.user}" required minlength="4" ></p>
    <p>
    <label class="w3-text-red" for="password">Password</label>
    <input class="w3-input w3-border w3-light-grey" type="password" name="pwd2" placeholder="Password" value="${user.pwd2}" required pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$"><br>
    <p>
    <input class="w3-btn w3-teal" type="submit" name="sumbit" value="Submit"></p>
</form>
