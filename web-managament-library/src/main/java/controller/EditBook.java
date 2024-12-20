package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Book; // Assuming Book model exists
import model.Category;
import dao.BookDAO; // Assuming BookDAO exists
import dao.CategoryDAO;

import java.io.IOException;
import java.util.List;

@WebServlet("/edit-book/*")
public class EditBook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditBook() {
		super();
	}

	@Override
	public void init() throws ServletException {
		super.init();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pathInfo = request.getPathInfo();

		if (pathInfo == null || pathInfo.equals("/")) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().write("ID is required.");
			return;
		}

		int id = Integer.parseInt(pathInfo.substring(1)); // Extract the book ID from the URL
		System.out.println("Editing Book with ID: " + id);

		// Fetch the book data based on the ID
		BookDAO bookDAO = new BookDAO();
		Book book = bookDAO.findById(id); // Assuming a method findById exists in BookDAO

		if (book == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.getWriter().write("Book not found.");
			return;
		}

		// Fetch the list of categories
		CategoryDAO categoryDAO = new CategoryDAO();
		List<Category> listCategories = categoryDAO.find();

		// Set attributes for JSP
		request.setAttribute("book", book);
		request.setAttribute("listCategories", listCategories);
		request.setAttribute("activePage", "books");
		request.setAttribute("titlePage", "Chỉnh sửa thông tin sách");

		// Forward to the edit_book.jsp page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/edit_book.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		System.out.println(request.getParameter("description"));
		int categoryId = Integer.parseInt(request.getParameter("category"));
		String author = request.getParameter("author");
		String description = request.getParameter("description");
		int amount = Integer.parseInt(request.getParameter("amount"));

		BookDAO bookDAO = new BookDAO();
		Book book = bookDAO.findById(id);

		if (book == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.getWriter().write("Book not found.");
			return;
		}

		book.setName(name);
		book.setAuthor(author);
		book.setDescription(description);
		book.setAmount(amount);

		CategoryDAO categoryDAO = new CategoryDAO();
		Category category = categoryDAO.findById(categoryId);
		if (category != null) {
			book.setCategory(category);
		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().write("Invalid category.");
			return;
		}

		bookDAO.editBook(book);

		response.sendRedirect(request.getContextPath() + "/books");
	}

}
