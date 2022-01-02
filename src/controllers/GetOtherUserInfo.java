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
import org.apache.commons.lang3.tuple.Pair;

import managers.ManageUsers;
import models.User;

/**
 * Servlet implementation class GetUserInfo
 */
@WebServlet("/GetOtherUserInfo")
public class GetOtherUserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetOtherUserInfo() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GetOtherUserInfo.java");
		User user = new User();
		
		ManageUsers manager = new ManageUsers();
		
	    try {
	    	BeanUtils.populate(user, request.getParameterMap());
		    user = manager.getUser(user.getId());
		    manager.finalize();
		    request.setAttribute("otheruser",user);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/ViewOtherUserInfo.jsp"); 
			dispatcher.include(request,response);
		    
	    } catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	    

	    


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
