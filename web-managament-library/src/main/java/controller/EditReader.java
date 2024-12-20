package controller;

import dao.ReaderDAO;
import model.Reader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit-reader")
public class EditReader extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditReader() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");

        // Kiểm tra null hoặc rỗng
        if (idParam == null || idParam.trim().isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid or missing 'id' parameter.");
            return;
        }

        try {
            int id = Integer.parseInt(idParam);

            ReaderDAO readerDAO = new ReaderDAO();
            Reader reader = readerDAO.findById(id);

            if (reader == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("Reader not found.");
                return;
            }

            // Truyền dữ liệu reader sang JSP
            request.setAttribute("reader", reader);
            RequestDispatcher dispatcher = request.getRequestDispatcher("edit_reader.jsp");
            dispatcher.forward(request, response);
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid 'id' format.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");

            ReaderDAO readerDAO = new ReaderDAO();
            Reader reader = readerDAO.findById(id);

            if (reader == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("Reader not found.");
                return;
            }

            // Cập nhật thông tin người đọc
            reader.setName(name);
            reader.setEmail(email);
            reader.setPhone(phone);
            reader.setAddress(address);

            boolean isUpdated = readerDAO.update(reader);
            if (isUpdated) {
                response.sendRedirect(request.getContextPath() + "/readers");
            } else {
                request.setAttribute("error", "Cập nhật thất bại!");
                request.setAttribute("reader", reader);
                RequestDispatcher dispatcher = request.getRequestDispatcher("edit_reader.jsp");
                dispatcher.forward(request, response);
            }

        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid input format.");
        }
    }
}
