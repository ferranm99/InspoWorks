<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<form action="EditController" id="editform" method="POST">
	<h2>Edit your info </h2>
	<p>      
	<label class="w3-text-red" for="name"> Name </label>
    <input class="w3-input w3-border w3-light-grey" type="text" id="name" name="name" placeholder="Name" value="${user.name}"  ></p>
  	<p>
    <label class="w3-text-red" for="user id"> User id </label>
    <input class="w3-input w3-border w3-light-grey" type="text" name="user" placeholder="Username" value="${user.user}"  minlength="4" ></p>
    <p>
    <label class="w3-text-red" for="birthday"> Birthday</label>
    <input class="w3-input w3-border w3-light-grey" type="birthday" id="birthday" name="birthday" placeholder="Birthday" value="${user.birthday}"  pattern = "^([0-2][0-9]|(3)[0-1])(\/)(((0)[0-9])|((1)[0-2]))(\/)\d{4}$"></p>
    <p>
    <label class="w3-text-red" for="phone"> Phone</label>
    <input class="w3-input w3-border w3-light-grey" type="phone" id="phone" name="phone" placeholder="Phone" value="${user.phone}"  pattern = "(\+34|0034|34)?[ -]*(6|7)[ -]*([0-9][ -]*){8}"></p>
    <p>
    <label class="w3-text-red" for="gender"> Gender</label>
    <input class="w3-input w3-border w3-light-grey" type="gender" id="gender" name="gender" placeholder="Gender" value="${user.gender}" ></p>
    <p>
    <input class="w3-btn w3-teal" type="submit" name="sumbit" value="Submit"></p>
</form>

