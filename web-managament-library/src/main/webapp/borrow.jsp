<%@page import="model.Reader"%>
<%@page import="model.Book"%>
<%@page import="model.Borrowing"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="head.jsp"%>
<%
List<Book> books = (List<Book>) request.getAttribute("books");
List<Reader> readers = (List<Reader>) request.getAttribute("readers");
%>
<body>
	<div class="d-flex" style="height: 100vh">
		<%@ include file="sidebar.jsp"%>
		<div class="flex-grow-1 d-flex flex-column">
			<%@ include file="header.jsp"%>
			<div class="flex-grow-1 d-flex px-2 gap-1 pb-2">
				<div class="border rounded p-2">
					<!-- <h2 class="fs-4 text-center text-primary">PHIẾU MƯỢN</h2> -->
					<form class="row g-1" method="POST"
						action="${pageContext.request.contextPath}/borrow">
						<!-- Thông tin người mượn -->
						<h3 class="fs-5">Người mượn</h3>
						<div>
							<button class="btn btn-outline-primary w-100" type="button"
								data-bs-toggle="modal" data-bs-target="#searchReaderModal">
								<i class="bi bi-search"></i> Tìm kiếm người mượn
							</button>
						</div>
						<div class="col-md-6">
							<label for="phone" class="form-label">Số điện thoại</label> <input
								class="form-control" id="phone" name="phone">
						</div>
						<div class="col-md-6">
							<label for="name" class="form-label">Họ tên</label> <input
								type="text" class="form-control" id="name" name="name">
						</div>
						<div class="col-md-6">
							<label for="id" class="form-label">Mã thẻ</label> <input
								type="text" class="form-control" id="id" name="id">
						</div>
						<div class="col-md-6">
							<label for="email" class="form-label">Email</label> <input
								type="email" class="form-control" id="email" name="email">
						</div>
						<div class="col-md-6">
							<label for="dob" class="form-label">Ngày sinh</label> <input
								type="date" class="form-control" id="dob" name="dob">
						</div>
						<div class="col-6">
							<label for="address" class="form-label">Địa chỉ</label> <input
								type="text" class="form-control" id="address" name="address">
						</div>
						<!-- Danh sách sách mượn -->
						<h3 class="fs-5">Sách mượn</h3>
						<div class="col-12 overflow-y-scroll" style="height: 200px;">
							<table class="table table-striped">
								<thead>
									<tr>
										<th scope="col">#</th>
										<th scope="col">Tên</th>
										<th scope="col">Thể loại</th>
										<th scope="col">Tác giả</th>
										<th scope="col">Thao tác</th>
									</tr>
								</thead>
								<tbody id="borrowedBooksBody">
								</tbody>
							</table>
						</div>
						<div class="col-12">
							<button class="btn btn-secondary">Hủy</button>
							<button type="submit" class="btn btn-primary">Tạo phiếu
								mượn</button>
						</div>
					</form>
				</div>
				<div class="border rounded p-3 d-flex flex-column"
					style="min-width: 500px">
					<h3 class="fs-5">Tìm kiếm sách</h3>
					<div class="d-flex mb-3 mt-3">
						<input class="form-control me-2" placeholder="Tìm kiếm" /> <a
							href="${pageContext.request.contextPath}/borrow"
							class="btn btn-success"> <i class="bi bi-search"></i>
						</a>
					</div>
					<div class="flex-grow-1 overflow-y-scroll"
						style="max-height: 490px;">
						<table class="table table-striped">
							<thead>
								<tr>
									<th scope="col">#</th>
									<th scope="col">Tên</th>
									<th scope="col">Tác giả</th>
									<th scope="col"></th>
								</tr>
							</thead>
							<tbody>
								<%
								if (books != null && !books.isEmpty()) {
									for (Book book : books) {
								%>
								<tr>
									<td><%=book.getId()%></td>
									<td><%=book.getName()%></td>
									<td><%=book.getAuthor()%></td>
									<td>
										<button class="btn btn-primary btn-sm add-to-borrow-list"
											data-book-id="<%=book.getId()%>"
											data-book-category="<%=book.getCategory().getName()%>"
											data-book-name="<%=book.getName()%>"
											data-book-author="<%=book.getAuthor()%>">
											<i class="bi bi-plus-lg"></i>
										</button>
									</td>
								</tr>
								<%
								}
								} else {
								%>
								<tr>
									<td colspan="6" class="text-center">Không có sách nào</td>
								</tr>
								<%
								}
								%>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="searchReaderModal" tabindex="-1"
		aria-labelledby="searchReaderModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="searchReaderModalLabel">Tìm kiếm
						người mượn</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<!-- Search Input -->
					<div class="input-group mb-3">
						<input type="text" id="readerSearchInput" class="form-control"
							placeholder="Nhập tên, số điện thoại hoặc email" />
						<button class="btn btn-primary" type="button"
							id="searchReaderButton">
							<i class="bi bi-search"></i> Tìm kiếm
						</button>
					</div>
					<!-- Reader List Table -->
					<table class="table table-striped">
						<thead>
							<tr>
								<th>#</th>
								<th>Họ tên</th>
								<th>Số điện thoại</th>
								<th>Email</th>
								<th>Chọn</th>
							</tr>
						</thead>
						<tbody id="readerSearchResults">
						<tbody>
							<%
							if (readers != null && !readers.isEmpty()) {
								for (Reader reader : readers) {
							%>
							<tr>
								<td><%=reader.getId()%></td>
								<td><%=reader.getPhone()%></td>
								<td><%=reader.getName()%></td>
								<td><%=reader.getEmail()%></td>
								<td>
									<button class="btn btn-primary btn-sm select-reader"
										data-bs-dismiss="modal" data-reader-id="<%=reader.getId()%>"
										data-reader-name="<%=reader.getName()%>"
										data-reader-phone="<%=reader.getPhone()%>"
										data-reader-email="<%=reader.getEmail()%>"
										data-reader-dob="<%=reader.getDob()%>"
										data-reader-address="<%=reader.getAddress()%>">Chọn</button>
								</td>
							</tr>
							<%
							}
							} else {
							%>
							<tr>
								<td colspan="9" class="text-center">Không có dữ liệu</td>
							</tr>
							<%
							}
							%>
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Đóng</button>
				</div>
			</div>
		</div>
	</div>
	<script src="./public/borrow.js"></script>
	<script>
	document.querySelectorAll(".select-reader").forEach(button => {
		button.addEventListener("click", function() {
			const readerId = this.getAttribute("data-reader-id");
			const readerName = this.getAttribute("data-reader-name");
			const readerPhone = this.getAttribute("data-reader-phone");
			const readerEmail = this.getAttribute("data-reader-email");
			const readerDob = this.getAttribute("data-reader-dob");
			const readerAddress = this.getAttribute("data-reader-address");
			
			console.log(readerId);

			document.getElementById("id").value = readerId;
			document.getElementById("name").value = readerName;
			document.getElementById("phone").value = readerPhone;
			document.getElementById("email").value = readerEmail;
			document.getElementById("dob").value = readerDob;
			document.getElementById("address").value = readerAddress;
			const modal = bootstrap.Modal.getInstance(document.getElementById("searchReaderModal"));
            modal.hide()
		});
	});
	</script>
</body>