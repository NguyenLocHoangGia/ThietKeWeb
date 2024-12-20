package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BorrowingDAO;

@WebServlet("/delete-borrowing")
public class DeleteBorrowing extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int borrowingDetailsId = Integer.parseInt(request.getParameter("borrowingDetailsId"));
        BorrowingDAO borrowingDAO = new BorrowingDAO();
        borrowingDAO.deleteBorrowingDetail(borrowingDetailsId); // Phương thức xóa chi tiết phiếu mượn
        response.sendRedirect(request.getContextPath() + "/borrowings");
    }
}

