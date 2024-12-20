package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Book;
import model.Borrowing;
import model.BorrowingDetails;
import model.Reader;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.BookDAO;
import dao.BorrowingDAO;
import dao.ReaderDAO;

@WebServlet("/borrow")
public class Borrow extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Borrow() {
        super();
    }

    // Hiển thị trang tạo phiếu mượn
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setAttribute("activePage", "borrow");
        request.setAttribute("titlePage", "Cho mượn sách");

        // Lấy danh sách sách và người đọc
        BookDAO bookDAO = new BookDAO();
        List<Book> books = bookDAO.find();

        ReaderDAO readerDAO = new ReaderDAO();
        List<Reader> readers = readerDAO.find();

        // Gắn vào request
        request.setAttribute("books", books);
        request.setAttribute("readers", readers);

        // Chuyển hướng đến borrow.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("borrow.jsp");
        dispatcher.forward(request, response);
    }

    // Xử lý tạo phiếu mượn
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] bookIds = request.getParameterValues("bookId[]");
        if (bookIds == null || bookIds.length == 0) {
            request.setAttribute("error", "Không có sách nào được chọn. Vui lòng thử lại.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }

        try {
            // Lấy thông tin người mượn từ form
            int id = Integer.parseInt(request.getParameter("id"));
            String phone = request.getParameter("phone");
            String name = request.getParameter("name");
            String dobString = request.getParameter("dob");
            String email = request.getParameter("email");
            String address = request.getParameter("address");

            // Xử lý ngày sinh
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dob = dateFormat.parse(dobString);

            // Tạo đối tượng Reader
            Reader reader = new Reader();
            reader.setId(id);
            reader.setPhone(phone);
            reader.setName(name);
            reader.setDob(new java.sql.Date(dob.getTime())); // Chuyển sang java.sql.Date
            reader.setEmail(email);
            reader.setAddress(address);

            // Lấy thông tin sách từ BookDAO
            BookDAO bookDAO = new BookDAO();
            List<BorrowingDetails> listBorrowingDetails = new ArrayList<>();
            for (String bookId : bookIds) {
                BorrowingDetails b = new BorrowingDetails();
                Book book = bookDAO.findById(Integer.parseInt(bookId));
                if (book == null) {
                    request.setAttribute("error", "Sách với ID " + bookId + " không tồn tại.");
                    request.getRequestDispatcher("/error.jsp").forward(request, response);
                    return;
                }
                b.setBook(book);
                b.setReturnDate(null);
                listBorrowingDetails.add(b);
            }

            // Tạo đối tượng Borrowing
            Borrowing borrowing = new Borrowing();
            borrowing.setBorrowingDetails(listBorrowingDetails);
            borrowing.setReader(reader);

            // Lưu phiếu mượn vào cơ sở dữ liệu
            BorrowingDAO borrowingDAO = new BorrowingDAO();
            borrowingDAO.createBorrowing(borrowing);

            // Chuyển hướng về trang danh sách phiếu mượn
            response.sendRedirect(request.getContextPath() + "/borrow");

        } catch (NumberFormatException e) {
            request.setAttribute("error", "Dữ liệu nhập vào không hợp lệ.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } catch (ParseException e) {
            request.setAttribute("error", "Ngày sinh không đúng định dạng.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Đã xảy ra lỗi không mong muốn.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
