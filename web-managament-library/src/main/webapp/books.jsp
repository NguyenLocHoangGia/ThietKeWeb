<%@page import="model.Book"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>
<body>
    <div class="d-flex" style="height: 100vh">
	    <%@ include file="sidebar.jsp" %>
	    <div class="flex-grow-1">
    		<%@ include file="header.jsp" %>
	     	<div class="border rounded m-4 p-4">
	     		<div class="d-flex justify-content-between mb-3">
                    <form action="${pageContext.request.contextPath }/books" method="GET">
                    <div class="d-flex">
                        <input name="q" class="form-control me-2" value="<%= request.getAttribute("key") != null ? request.getAttribute("key") : ""  %>" placeholder="Tìm kiếm"/>
                        <button type="submit" class="btn btn-success"><i class="bi bi-search"></i></button>
                    </div>
                    </form>
                    <a href="${pageContext.request.contextPath}/add-book" class="btn btn-success">
                        <i class="bi bi-plus"></i>Thêm
                    </a>
                </div>

                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Tên</th>
                            <th scope="col">Thể loại</th>
                            <th scope="col">Tác giả</th>
                            <th scope="col">Số lượng</th>
                            <th scope="col">Ngày tạo</th>
                            <th scope="col">Thao tác</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%-- Ensure listUsers is not null and safely iterate over it --%>
                        <%
                            List<Book> listBooks = (List<Book>) request.getAttribute("listBooks");
                            if (listBooks != null && !listBooks.isEmpty()) {
                                for (Book book : listBooks) {
                        %>
                                    <tr>
                                        <td><%= book.getId() %></td>
                                        <td><%= book.getName() %></td>
                                        <td><%= book.getCategory().getName() %></td>
                                        <td><%= book.getAuthor() %></td>
                                        <td><%= book.getAmount() %></td>
                                        <td><%= book.getCreatedAt() %></td>
                                        <td>
                                            <a href="${pageContext.request.contextPath }/edit-book/<%= book.getId() %>" class="btn btn-primary">
                                                <i class="bi bi-pencil-square"></i>
                                            </a>
                                            <button type="button" class="btn btn-danger btn-delete-book"
												data-book-id="<%=book.getId()%>" data-bs-toggle="modal"
												data-bs-target="#deleteBookModal">
												<i class="bi bi-trash"></i>
											</button>
                                        </td>
                                    </tr>
                        <%
                                }
                            } else {
                        %>
                                <tr>
                                    <td colspan="6" class="text-center">Không có thể loại nào</td>
                                </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
	     	</div>
	    </div>
    </div>
    
	<div class="modal fade" id="deleteBookModal" tabindex="-1"
		aria-labelledby="deleteBookModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="${pageContext.request.contextPath }/delete-book"
					method="POST">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="deleteBookModalLabel">Xác
						nhận</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<p class="fs-4">Bạn muốn xóa sách này?</p>
					<input type="hidden" id="bookIdToDelete" name="id" value="">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Hủy</button>
					<button type="submit" class="btn btn-primary">Xác nhận</button>
				</div>
				</form>
			</div>
		</div>
	</div>
	<script>
    const deleteButtons = document.querySelectorAll('.btn-delete-book');

	    deleteButtons.forEach(button => {
	        button.addEventListener('click', function () {
	            const bookId = this.getAttribute('data-book-id');
	            
	            const bookIdInput = document.getElementById('bookIdToDelete');
	            bookIdInput.value = bookId;
	        });
	    });
	</script>
</body>
