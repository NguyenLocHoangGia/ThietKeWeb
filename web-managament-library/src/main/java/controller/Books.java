package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Book;

import java.io.IOException;
import java.util.List;

import dao.BookDAO;


@WebServlet("/books")
public class Books extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Books() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		request.setAttribute("activePage", "books");
		request.setAttribute("titlePage", "Quản lý sách");
		String key = request.getParameter("q");
		request.setAttribute("key", key);
		BookDAO bookDAO = new BookDAO();
		List<Book> listBooks;
		if(key != null) {
			listBooks = bookDAO.find(key);
		} else {
			listBooks = bookDAO.find();
		}
		request.setAttribute("listBooks", listBooks);

		RequestDispatcher dispatcher = request.getRequestDispatcher("books.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
