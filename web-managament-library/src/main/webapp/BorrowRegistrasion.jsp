<%@page import="model.Book"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="head.jsp"%>

<body>
	<div class="d-flex" style="height: 100vh">
		<%@ include file="sidebar.jsp"%>
		<div class="flex-grow-1">
			<%@ include file="header.jsp"%>
			<div class="container mt-4">
				<h2 class="mb-3">Đăng ký mượn sách</h2>

				<form action="${pageContext.request.contextPath}/BorrowRegistrasion"
					method="POST">
					<div class="mb-3">
						<label for="readerName" class="form-label">Tên người mượn</label>
						<input type="text" class="form-control" id="readerName"
							name="readerName" placeholder="Nhập tên người mượn" required>
					</div>

					<div class="mb-3">
						<label for="readerEmail" class="form-label">Email</label> <input
							type="email" class="form-control" id="readerEmail"
							name="readerEmail" placeholder="Nhập email" required>
					</div>

					<div class="mb-3">
						<label for="borrowDate" class="form-label">Ngày mượn</label> <input
							type="date" class="form-control" id="borrowDate"
							name="borrowDate" required>
					</div>

					<div class="mb-3">
						<label for="returnDate" class="form-label">Ngày trả dự
							kiến</label> <input type="date" class="form-control" id="returnDate"
							name="returnDate" required>
					</div>

					<div class="mb-3">
						<label class="form-label">Chọn sách muốn mượn</label>
						<div class="overflow-y-scroll" style="height: 300px;">
							<table class="table table-bordered">
								<thead>
									<tr>
										<th>#</th>
										<th>Tên sách</th>
										<th>Tác giả</th>
										<th>Chọn</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="book" items="${books}">
										<tr>
											<td>${book.id}</td>
											<td>${book.name}</td>
											<td>${book.author}</td>
											<td>
												<button class="btn btn-primary" data-book-id="${book.id}">Chọn</button>
											</td>
										</tr>
									</c:forEach>

								</tbody>
							</table>
						</div>
					</div>

					<button type="submit" class="btn btn-primary">Đăng ký mượn
						sách</button>
				</form>
			</div>
		</div>
	</div>
</body>

