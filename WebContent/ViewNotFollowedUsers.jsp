<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:forEach var="u" items="${users}">       
<div id="${u.id}" class="w3-container w3-card w3-round w3-white w3-center w3-section">
	<p>Friend Suggestion</p>
    <img src="imgs/avatar6.png" alt="Avatar" style="width:50%"><br>
    <div>${u.name}</div>
   	<button type="button" class="followUser w3-row w3-button w3-blue-gray w3-section"><i class="fa fa-user-plus"></i> &nbsp;Follow</button>
   	<c:if test="${user.admin==true}">
	 <button type="button" class="makeAdmin w3-button w3-blue w3-margin-bottom"><i class="fa fa-user-circle"></i> &nbsp;Make Admin</button>  
	 <button type="button" class="editOtherUser w3-button w3-blue w3-margin-bottom"><i class="fa fa-user-circle"></i> &nbsp;Edit</button>   
     <button type="button" class="delUser w3-button w3-red w3-margin-bottom"><i class="fa fa-trash"></i> &nbsp;Delete</button>  
    </c:if>
    
</div>
</c:forEach>