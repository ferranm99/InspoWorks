<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


    
 <c:forEach var="t" items="${tweets}"> 
	 <c:if test="${t.pid==0}">
	 <div id="${t.id}" class="w3-container w3-card w3-section w3-white w3-round w3-animate-opacity"><br>
	   <img src="imgs/avatar2.png" alt="Avatar" class="w3-left w3-circle w3-margin-right" style="width:60px">
	   <span class="w3-right w3-opacity"> ${t.postDateTime} </span>
	   <h4> ${t.uname} </h4><br>
	   <hr class="w3-clear">
	   <p> ${t.content} </p>
	   <span class="w3-right w3-opacity"> Likes: ${t.numLikes} </span><br><br>
	 
	   
	    <c:forEach var="c" items="${tweets}"> 
	   			<c:if test="${t.id==c.pid }">
		   			<div id="${c.id}" class="w3-container w3-card w3-section w3-white w3-round w3-animate-opacity"><br>
				   <img src="imgs/avatar2.png" alt="Avatar" class="w3-left w3-circle w3-margin-right" style="width:60px">
				   <span class="w3-right w3-opacity"> ${c.postDateTime} </span>
				   <h4> ${c.uname} </h4><br>
				   <hr class="w3-clear">
				   <p> ${c.content} </p>
				   <span class="w3-right w3-opacity"> Likes: ${c.numLikes} </span>
				   </div>
	   			</c:if>
	   </c:forEach>
	   
	 </div>
	 </c:if>
</c:forEach>


