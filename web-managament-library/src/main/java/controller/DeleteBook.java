package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import dao.BookDAO;

@WebServlet("/delete-book")
public class DeleteBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteBook() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookDAO bookDAO = new BookDAO();
		int id = Integer.parseInt(request.getParameter("id"));
		bookDAO.deleteBookById(id);
		response.sendRedirect(request.getContextPath() + "/books");
	}

}
