<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:forEach var="u" items="${users}">       
 <div id="${u.id}" class="w3-container w3-card w3-section w3-white w3-round w3-animate-opacity"><br>
   <img src="imgs/avatar2.png" alt="Avatar" class="w3-left w3-circle w3-margin-right" style="width:60px">
   <h4> ${u.name} </h4><br>
   <hr class="w3-clear">
   <button type="button" id="infoUser" class="infoUser w3-button w3-blue-gray w3-margin-bottom"><i class="fa fa-info-circle"></i> &nbsp;Info</button>
   
   <c:if test="${user.admin==true}">
	     <button type="button" class="makeAdmin w3-button w3-blue w3-margin-bottom"><i class="fa fa-user-circle"></i> &nbsp;Make Admin</button>  
	     <button type="button" class="editOtherUser w3-button w3-blue w3-margin-bottom"><i class="fa fa-user-circle"></i> &nbsp;Edit</button>   
   		 <button type="button" class="delUser w3-button w3-red w3-margin-bottom"><i class="fa fa-trash"></i> &nbsp;Delete</button>
	     
   </c:if>
   
   <button type="button" class="unfollowUser w3-button w3-red w3-margin-bottom"><i class="fa fa-trash"></i> &nbsp;Unfollow</button> 
   
   
 </div>
</c:forEach>