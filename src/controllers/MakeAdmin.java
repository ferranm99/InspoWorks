package controllers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

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
 * Servlet implementation class DelTweet
 */
@WebServlet("/MakeAdmin")
public class MakeAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MakeAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("MakeAdmin.java");
		User iuser = new User();
		ManageUsers userManager = new ManageUsers();
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		
		try {
			
			if (session != null || user != null) {
				BeanUtils.populate(iuser, request.getParameterMap());

				System.out.println(iuser.getId());
				
				if(user.getAdmin() == true) {

					userManager.makeAdmin(iuser.getId());
					
				}
				userManager.finalize();
				
			}

		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}