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
@WebServlet("/EditOther")
public class EditOther extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditOther() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("EditOther");
		HttpSession session = request.getSession(false);
		User otheruser = new User();
		
		try {
			BeanUtils.populate(otheruser, request.getParameterMap());

			System.out.println(otheruser.getId());
			ManageUsers userManager = new ManageUsers();
			otheruser = userManager.getUser(otheruser.getId());
			userManager.finalize();
			
			
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
			
		request.setAttribute("otheruser", otheruser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ViewEditOtherInfo.jsp"); 
		dispatcher.forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}