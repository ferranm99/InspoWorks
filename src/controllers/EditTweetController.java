package controllers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

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
 * Servlet implementation class GetUserInfo
 */
@WebServlet("/EditTweetController")
public class EditTweetController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditTweetController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		Tweet tweet = new Tweet();
		System.out.println("EditTweetController.java");
		try {
			BeanUtils.populate(tweet, request.getParameterMap());
			
			ManageTweets tweetManager = new ManageTweets();
			
			System.out.println(tweet.getId()+tweet.getContent());
			tweetManager.updateTweet(tweet.getId(),tweet.getContent());
			tweetManager.finalize();
	   
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ViewOwnTimeline.jsp"); 
		dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
