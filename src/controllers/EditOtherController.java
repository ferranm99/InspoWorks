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

import managers.ManageUsers;
import models.User;

/**
 * Servlet implementation class GetUserInfo
 */
@WebServlet("/EditOtherController")
public class EditOtherController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditOtherController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("EditOtherController");
		HttpSession session = request.getSession(false);
		User otheruser = new User();

		User user = (User) session.getAttribute("user");

		
		if (session != null || otheruser != null) {
			ManageUsers userManager = new ManageUsers();

		   if(user.getAdmin() == true) {

			   try {
				  
				   BeanUtils.populate(otheruser, request.getParameterMap());

				   System.out.println(otheruser.getId());
				   userManager.editUser(otheruser);
				   userManager.finalize();
				   //request.setAttribute("content","ViewOwnTimeline.jsp");
				  
			   }
			   catch (IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
			   }
		   }
	   }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
