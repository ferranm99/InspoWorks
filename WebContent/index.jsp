<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false" %>

<!DOCTYPE html>
<html>
<head>
<link rel="icon" href="imgs/upf.jpg">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title> Lab 4 solution </title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-red.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href='https://fonts.googleapis.com/css?family=Didact Gothic' rel='stylesheet'>
<link rel="stylesheet" href="styles.css">
<meta name="viewport" content="width=device-width, initial-scale=1">

<script type="text/javascript">
$(document).ready(function(){
	
	$.ajaxSetup({ cache: false }); //Avoids Internet Explorer caching!	
	$(document).on("click",".menu",function(event) {
		$('#content').load($(this).attr('id'));
		event.preventDefault();
	});
	$(document).on("submit","form", function(event) {
		$('#content').load($(this).attr('action'),$(this).serialize());
	    event.preventDefault();
	});
	/* Add tweet */
	$(document).on("click","#addTweet",function(event){
		$.post( "AddTweet", { content: $("#tweetContent").text()}, function(event) {
			$("#content").load("GetOwnTimeline");		
		});
		event.preventDefault();
	});
	/* Delete tweet */
	$(document).on("click",".delTweet",function(event){
		var tweet = $(this).parent();
		$.post( "DelTweet", { id: $(this).parent().attr("id") } , function(event) {
			$("#content").load("GetOwnTimeline");				
		});
		event.preventDefault();
	});
	/* Follow user */
	$(document).on("click",".followUser",function(event){
		var user = $(this).parent();
		$.post( "FollowUser", { id: $(this).parent().attr("id") }, function(event) { 
			$("#content").load("GetFollowedUsers");
			$("#lcolumn").load("GetNotFollowedUsers");
		});
		event.preventDefault();
	});
	/* UnFollow user */
	$(document).on("click",".unfollowUser",function(event) {
		var user = $(this).parent();
		$.post( "UnFollowUser", { id: $(this).parent().attr("id") }, function(event) {
			$("#content").load("GetFollowedUsers");
			$("#lcolumn").load("GetNotFollowedUsers");
		});
		event.preventDefault();
	});
	/* Edit user */
	$(document).on("click",".editUser",function(event) {
		$("#content").load("ViewEditUserInfo.jsp");
		event.preventDefault();
	});
	/* Edit other user */
	$(document).on("click",".editOtherUser",function(event) {
		var otheruser = $(this).parent();
		$("#content").load("EditOther", { id: $(this).parent().attr("id") });
		event.preventDefault();
	});

	
	/* Info user */
	$(document).on("click",".infoUser",function(event) {
		var user = $(this).parent();
		$("#content").load("GetOtherUserInfo", { id: $(this).parent().attr("id") });
		event.preventDefault();
	});
	
	/* Edit tweet */
	$(document).on("click",".editTweet",function(event) {
		var tweet = $(this).parent();
		$("#content").load("EditTweet", { id: $(this).parent().attr("id") });
		event.preventDefault();
	});
	
	/* Comment tweet */
	$(document).on("click",".commentTweet",function(event) {
		var tweet = $(this).parent();
		$("#content").load("CommentTweet", { id: $(this).parent().attr("id") });
		event.preventDefault();
	});
	/* Delete user */
	$(document).on("click",".delUser",function(event){
		var otheruser = $(this).parent();
		$.post( "DelUser", { id: $(this).parent().attr("id") } , function(event) {
			$("#content").load("GetFollowedUsers");	
			$("#lcolumn").load("GetNotFollowedUsers");			
		});
		event.preventDefault();
	});
	/* Make user admin*/
	$(document).on("click",".makeAdmin",function(event){
		var otheruser = $(this).parent();
		$.post( "MakeAdmin", { id: $(this).parent().attr("id") } , function(event) {
			$("#content").load("GetFollowedUsers");	
			$("#lcolumn").load("GetNotFollowedUsers");			
		});
		event.preventDefault();
	});
	
	/* Like Tweet */
	$(document).on("click",".likeTweet",function(event){
		var tweet = $(this).parent();
		$.post( "LikeTweet", { id: $(this).parent().attr("id") } , function(event) {
			$("#content").load("GetOwnTimeline");				
		});
		event.preventDefault();
	});
	
});
</script>
</head>
<body>

 	<!-- Begin Navigation -->
 	<div class="w3-themes" id="navigation">
    <jsp:include page="${menu}" />
 	</div>
 	<!-- End Navigation -->
 
 	<!-- Begin Content -->
	<div class="w3-row-padding">
 	<!-- Left Column -->
	<div class="w3-container w3-col m3 w3-hide-small">
		<div id="rcolumn">
			<p></p>
		</div>
	</div>
	<!-- Middle Column -->	
	<div class="e3-container w3-col m6">
		<div id="content">
		<jsp:include page="${content}" />
		</div>
	</div>
	<!-- Right Column -->
	<div class="w3-container w3-col m3 w3-hide-small">
		<div id="lcolumn">
			<p></p>
		</div>
	</div>
	</div>
	<!-- End Content -->
	<!-- Footer -->
	<footer class="w3-container w3-themes">
	  <p> InspoWorks </p>
	</footer>
	
	<script>
		function stack() {
  			var x = document.getElementById("stack");
  			if (x.className.indexOf("w3-show") == -1) {
    			x.className += " w3-show";
  			} else { 
    		x.className = x.className.replace(" w3-show", "");
  			}
		}
	</script>

  </body>
</html>