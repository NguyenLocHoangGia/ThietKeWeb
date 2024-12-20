package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Reader;

import java.io.IOException;
import java.util.List;

import dao.ReaderDAO;

@WebServlet("/readers")
public class Readers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Readers() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("q"); // Lấy từ khóa tìm kiếm
        ReaderDAO readerDAO = new ReaderDAO();
        List<Reader> readers;

        if (keyword != null && !keyword.trim().isEmpty()) {
            readers = readerDAO.findByKeyword(keyword); // Tìm kiếm theo từ khóa
        } else {
            readers = readerDAO.find(); // Lấy toàn bộ danh sách nếu không có từ khóa
        }

        response.setContentType("text/html");
        request.setAttribute("readers", readers);
        request.setAttribute("activePage", "readers");
        request.setAttribute("titlePage", "Quản lý thành viên");
        request.setAttribute("keyword", keyword); // Để hiển thị lại từ khóa trên ô tìm kiếm

        RequestDispatcher dispatcher = request.getRequestDispatcher("readers.jsp");
        dispatcher.forward(request, response);
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy dữ liệu từ form
        String identityCard = request.getParameter("identityCard");
        String name = request.getParameter("name");
        String dob = request.getParameter("dob");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        // Tạo đối tượng Reader
        Reader reader = new Reader();
        reader.setIdentityCard(identityCard);
        reader.setName(name);
        reader.setDob(dob != null && !dob.isEmpty() ? java.sql.Date.valueOf(dob) : null);
        reader.setEmail(email);
        reader.setPhone(phone);
        reader.setAddress(address);
        reader.setStartDate(java.sql.Date.valueOf(startDate));
        reader.setEndDate(endDate != null && !endDate.isEmpty() ? java.sql.Date.valueOf(endDate) : null);

        // Gọi DAO để thêm Reader vào cơ sở dữ liệu
        ReaderDAO readerDAO = new ReaderDAO();
        boolean isAdded = readerDAO.addReader(reader);

        // Điều hướng hoặc phản hồi
        if (isAdded) {
            response.sendRedirect(request.getContextPath() + "/readers?success=true");
        } else {
            response.sendRedirect(request.getContextPath() + "/readers?success=false");
        }
    }

}
