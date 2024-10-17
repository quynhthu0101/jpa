package vn.iotstar.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.entity.User;
import vn.iotstar.services.UserService;
import vn.iotstar.services.IUserService;

@WebServlet(urlPatterns = {"/profile"})
public class ProfileController extends HttpServlet{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (session != null && session.getAttribute("account") != null) {
			User u = (User) session.getAttribute("account");

        if (u != null) {
 			 session.setAttribute("account", u);
 			req.setAttribute("username", u.getUsername());
 			req.setAttribute("avatar", u.getAvatar());
 			req.setAttribute("fullname", u.getFullname());
 			req.setAttribute("phone", u.getPhone());
 			req.setAttribute("email", u.getEmail());
 			req.setAttribute("createdday", u.getCreateddate());
 			req.setAttribute("rolename", u.getRole().getRolename());
 			req.getRequestDispatcher("/views/profile.jsp").forward(req, resp);	 
            
        } else {
            resp.sendRedirect(req.getContextPath() + "/login");
        }
	}

    }
}
