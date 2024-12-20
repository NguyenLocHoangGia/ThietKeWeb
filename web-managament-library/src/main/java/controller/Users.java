package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

import java.io.IOException;
import java.util.List;

import dao.UserDAO;


@WebServlet("/users")
public class Users extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Users() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserDAO userDAO = new UserDAO();
		List<User> listUsers = userDAO.find();
		for (User user : listUsers) {
			System.out.println(user);
		}
		
		response.setContentType("text/html");
		request.setAttribute("listUsers", listUsers);
		request.setAttribute("activePage", "users");
		request.setAttribute("titlePage", "Quản lý nhân viên");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("users.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String position = request.getParameter("position");

        if (username == null || username.isEmpty() || password == null || password.isEmpty() || 
            name == null || name.isEmpty() || position == null || position.isEmpty()) {
            response.sendRedirect("error.jsp");
            return;
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setName(name);
        user.setPosition(position);

        UserDAO userDAO = new UserDAO();
        userDAO.addUser(user);
        response.sendRedirect(request.getContextPath() + "/users");
	}

}
