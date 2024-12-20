package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import dao.CategoryDAO;

@WebServlet("/delete-category")
public class DeleteCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteCategory() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		CategoryDAO categoryDAO = new CategoryDAO();
		categoryDAO.deleteById(id);
		response.sendRedirect(request.getContextPath() + "/categories");
	}

}
