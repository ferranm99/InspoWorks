package controllers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import managers.ManageTweets;
import managers.ManageUsers;
import models.Tweet;
import models.User;

/**
 * Servlet implementation class dTcontroller
 */
@WebServlet("/EditTweet")
public class EditTweet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditTweet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		User user = new User();
		Tweet tweet = new Tweet();
		
		try {
			BeanUtils.populate(tweet, request.getParameterMap());
			
			ManageTweets tweetManager = new ManageTweets();
			tweet = tweetManager.getTweet(tweet.getId());
			tweetManager.finalize();
			ManageUsers userManager = new ManageUsers();
			user = userManager.getUser(tweet.getUid());
			userManager.finalize();
			
			
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
			
		request.setAttribute("user",user);
		request.setAttribute("tweet",tweet);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ViewEditTweet.jsp"); 
		dispatcher.forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

