<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test = "${user.error['user']}">
<div class="w3-panel w3-theme-l4 w3-display-container">
  <span onclick="this.parentElement.style.display='none'"
  class="w3-button w3-large w3-display-topright">&times;</span>
  <h3> Registration error! </h3>
  <p> Given user name already exist on our database. </p>
</div>
</c:if>

<c:if test = "${user.error['mail']}">
<div class="w3-panel w3-theme-l4 w3-display-container">
  <span onclick="this.parentElement.style.display='none'"
  class="w3-button w3-large w3-display-topright">&times;</span>
  <h3> Registration error! </h3>
  <p> Given mail already exist on our database. </p>
</div>
</c:if>

<c:if test = "${user.error['pwd2']}">
<div class="w3-panel w3-theme-l4 w3-display-container">
  <span onclick="this.parentElement.style.display='none'"
  class="w3-button w3-large w3-display-topright">&times;</span>
  <h3> Registration error! </h3>
  <p> Passwords do not match. </p>
</div>
</c:if>

<form action="RegisterController" id="regform" method="POST">
	<h2>Create your account</h2>
	<p>      
	<label class="w3-text-red" for="name"> Name </label>
    <input class="w3-input w3-border w3-light-grey" type="text" id="name" name="name" placeholder="Name" value="${user.name}" required ></p>
  	<p>
    <label class="w3-text-red" for="user id"> User id </label>
    <input class="w3-input w3-border w3-light-grey" type="text" name="user" placeholder="Username" value="${user.user}" required minlength="4" ></p>
    <p>      
    <label class="w3-text-red" for="mail">Mail address</label>
    <input class="w3-input w3-border w3-light-grey" type="email" name="mail" placeholder="Mail Address" value = "${user.mail}" required></p>
    <p>
    <label class="w3-text-red" for="birthday"> Birthday</label>
    <input class="w3-input w3-border w3-light-grey" type="birthday" id="birthday" name="birthday" placeholder="Birthday" value="${user.birthday}" required pattern = "^([0-2][0-9]|(3)[0-1])(\/)(((0)[0-9])|((1)[0-2]))(\/)\d{4}$"></p>
    <p>
    <label class="w3-text-red" for="phone"> Phone</label>
    <input class="w3-input w3-border w3-light-grey" type="phone" id="phone" name="phone" placeholder="Phone" value="${user.phone}" required pattern = "(\+34|0034|34)?[ -]*(6|7)[ -]*([0-9][ -]*){8}"></p>
    <p>
    <label class="w3-text-red" for="gender"> Gender</label>
    <input class="w3-input w3-border w3-light-grey" type="gender" id="gender" name="gender" placeholder="Gender" value="${user.gender}" required></p>
    <p>
    <label class="w3-text-red" for="pwd1"> Password</label>
    <input class="w3-input w3-border w3-light-grey" type="password" id="pwd1" name="pwd1" placeholder="Password" value="${user.pwd1}" required pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$"></p>
    <p>
    <label class="w3-text-red" for="pwd2"> Confirm Password</label>
    <input class="w3-input w3-border w3-light-grey" type="password" id="pwd2" name="pwd2" placeholder="Confirm Password" value="${user.pwd2}" required pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$"></p>
    <p>
    
    <input class="w3-btn w3-teal" type="submit" name="sumbit" value="Submit"></p>
</form>

