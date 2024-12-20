package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDAO;
import dao.ReaderDAO;
import java.io.IOException;

@WebServlet("/delete-reader")
public class DeleteReader extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteReader() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ReaderDAO readerDAO = new ReaderDAO();
		int id = Integer.parseInt(request.getParameter("id"));
		readerDAO.deleteReaderById(id);
		response.sendRedirect(request.getContextPath() + "/readers");
    }

}
