package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Book;
import model.Category;
import dao.BookDAO;
import dao.CategoryDAO;

import java.io.IOException;
import java.util.List;

@WebServlet("/add-book")
public class AddBook extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddBook() {
        super();
    }

    // Hiển thị form thêm sách
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setAttribute("activePage", "books");
        request.setAttribute("titlePage", "Thêm sách");

        // Lấy danh sách thể loại
        CategoryDAO categoryDAO = new CategoryDAO();
        List<Category> listCategories = categoryDAO.find();
        request.setAttribute("listCategories", listCategories);

        // Hiển thị trang thêm sách
        RequestDispatcher dispatcher = request.getRequestDispatcher("add_book.jsp");
        dispatcher.forward(request, response);
    }

    // Xử lý form thêm sách
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Đảm bảo request và response sử dụng UTF-8
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
        	
            // Lấy dữ liệu từ form
            String name = request.getParameter("name");
            String categoryIdStr = request.getParameter("category");
            String author = request.getParameter("author");
            String amountStr = request.getParameter("amount");
            String description = request.getParameter("description");

            // Kiểm tra dữ liệu rỗng
            if (name == null || name.isEmpty() ||
                categoryIdStr == null || categoryIdStr.isEmpty() ||
                amountStr == null || amountStr.isEmpty()) {
                request.setAttribute("error", "Vui lòng điền đầy đủ thông tin!");
                doGet(request, response); // Quay lại form thêm sách
                return;
            }

            // Chuyển đổi dữ liệu
            int categoryId = Integer.parseInt(categoryIdStr);
            int amount = Integer.parseInt(amountStr);

            // Tạo đối tượng Book
            Book book = new Book();
            book.setName(name);
            book.setAuthor(author);
            book.setAmount(amount);
            book.setDescription(description);

            // Tạo đối tượng Category và gắn vào Book
            Category category = new Category();
            category.setId(categoryId);
            book.setCategory(category);

            // Lưu vào cơ sở dữ liệu
            BookDAO bookDAO = new BookDAO();
            bookDAO.create(book);

            // Chuyển hướng về danh sách sách
            response.sendRedirect(request.getContextPath() + "/books");

        } catch (NumberFormatException e) {
            // Xử lý lỗi định dạng số
            request.setAttribute("error", "Dữ liệu nhập không hợp lệ!");
            doGet(request, response);
        } catch (Exception e) {
            // Xử lý lỗi chung
            e.printStackTrace();
            request.setAttribute("error", "Đã xảy ra lỗi! Vui lòng thử lại.");
            doGet(request, response);
        }
    }
}
