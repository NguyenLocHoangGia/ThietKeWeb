package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Category;

import java.io.IOException;
import java.util.List;

import dao.CategoryDAO;

@WebServlet("/categories")
public class Categories extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Categories() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		request.setAttribute("activePage", "categories");
		request.setAttribute("titlePage", "Quản lý loại sách");
		CategoryDAO categoryDAO = new CategoryDAO();
		
		List<Category> listCategories = categoryDAO.find();
		request.setAttribute("listCategories", listCategories);

		RequestDispatcher dispatcher = request.getRequestDispatcher("categories.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		
		Category category = new Category();
		category.setName(name);
		category.setDescription(description);
		CategoryDAO categoryDAO = new CategoryDAO();
		System.out.println(category);
		categoryDAO.createCategory(category);
		response.sendRedirect(request.getContextPath() + "/categories");
	}

}
