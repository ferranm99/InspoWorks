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
	   
		
		
		<c:if test = "${t.liked == false}">
			   <button type="button" class="likeTweet w3-button w3-teal  w3-margin-bottom"><i class="fa fa-thumbs-up"></i> &nbsp;Like</button>
		</c:if>
		<c:if test = "${t.liked}">
			   <button type="button" class="likeTweet w3-button w3-teal  w3-margin-bottom"><i class="fa fa-thumbs-down"></i> &nbsp;Dislike</button>
		</c:if>	
		<button type="button" class="commentTweet w3-button w3-blue-gray w3-margin-bottom"><i class="fa fa-comment"></i> &nbsp;Comment</button> 
	   
	   <span class="w3-right w3-opacity"> Likes: ${t.numLikes} </span>
	   
	   
	   
	   
	   
	   <c:choose>
	    	<c:when test="${user.admin==true}">
	        	
			   <button type="button" class="editTweet w3-button w3-blue w3-margin-bottom"><i class="fa fa-edit"></i> &nbsp;Edit</button>
			   <button type="button" class="delTweet w3-button w3-red w3-margin-bottom"><i class="fa fa-trash"></i> &nbsp;Delete</button>  
	    	</c:when>  
	    	<c:when test="${user.id==t.uid}">
	        	 
			   <button type="button" class="editTweet w3-button w3-blue-gray w3-margin-bottom"><i class="fa fa-edit"></i> &nbsp;Edit</button>
			   <button type="button" class="delTweet w3-button w3-red w3-margin-bottom"><i class="fa fa-trash"></i> &nbsp;Delete</button> 
	    	</c:when> 
		</c:choose>
	   
	    <c:forEach var="c" items="${tweets}"> 
	   			<c:if test="${t.id==c.pid }">
		   			<div id="${c.id}" class="w3-container w3-card w3-section w3-white w3-round w3-animate-opacity"><br>
				   <img src="imgs/avatar2.png" alt="Avatar" class="w3-left w3-circle w3-margin-right" style="width:60px">
				   <span class="w3-right w3-opacity"> ${c.postDateTime} </span>
				   <h4> ${c.uname} </h4><br>
				   <hr class="w3-clear">
				   <p> ${c.content} </p>
				   <c:if test = "${c.liked == false}">
			   			<button type="button" class="likeTweet w3-button w3-teal  w3-margin-bottom"><i class="fa fa-thumbs-up"></i> &nbsp;Like</button>
					</c:if>
					<c:if test = "${c.liked}">
						   <button type="button" class="likeTweet w3-button w3-teal  w3-margin-bottom"><i class="fa fa-thumbs-down"></i> &nbsp;Dislike</button>
					</c:if>	
				   <span class="w3-right w3-opacity"> Likes: ${c.numLikes} </span>
				   </div>
	   			</c:if>
	   </c:forEach>
	   
	 </div>
	 </c:if>
</c:forEach>
