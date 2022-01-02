<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

      
 <div id="${tweet.id}" class="w3-container w3-card w3-section w3-white w3-round w3-animate-opacity"><br>
   <img src="imgs/avatar2.png" alt="Avatar" class="w3-left w3-circle w3-margin-right" style="width:60px">
   <span class="w3-right w3-opacity"> ${tweet.postDateTime} </span>
   <h4> ${user.name} </h4><br>
   <hr class="w3-clear">
   <p> ${tweet.content} </p>
   <button type="button" class="likeTweet w3-button w3-teal w3-margin-bottom"><i class="fa fa-thumbs-up"></i> &nbsp;Like</button>
   <button type="button" class="delTweet w3-button w3-red w3-margin-bottom"><i class="fa fa-trash"></i> &nbsp;Delete</button> 
   
   <div class="w3-container w3-card w3-round w3-white w3-section">
   <form action="EditTweetController" id="regform" method="POST">
	<h6 class="w3-opacity"> Replace your tweet: </h6>
	<input type="hidden" id="id" name="id" placeholder="Tweet Id" value="${tweet.id}" >
	<input class="w3-input w3-border w3-light-grey" id="content" name="content" placeholder="Update Tweet" value="${tweet.content}" required></p>
    <p>
	<input class="w3-btn w3-teal" type="submit" name="sumbit" value="Edit"></p>
	</form>
</div>
 </div>
