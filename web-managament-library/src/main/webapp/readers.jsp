<%@page import="model.Reader"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="head.jsp"%>
<body>
	<div class="d-flex" style="height: 100vh">
		<%@ include file="sidebar.jsp"%>
		<div class="flex-grow-1">
			<%@ include file="header.jsp"%>
			<div class="border rounded m-4 p-4">
				<div class="d-flex justify-content-between mb-3">
					<form action="${pageContext.request.contextPath}/readers"
						method="GET" class="d-flex">
						<input type="text" name="q" class="form-control me-2"
							value="<%=request.getAttribute("keyword") != null ? request.getAttribute("keyword") : ""%>"
							placeholder="Tìm kiếm theo tên">
						<button type="submit" class="btn btn-success">
							<i class="bi bi-search"></i>
						</button>
					</form>
					<%@ include file="add_reader.jsp"%>
				</div>
				<table class="table table-striped">
					<thead>
						<tr>
							<th>ID</th>
							<th>CMND/CCCD</th>
							<th>Tên</th>
							<th>Ngày sinh</th>
							<th>Email</th>
							<th>Số điện thoại</th>
							<th>Địa chỉ</th>
							<th>Ngày bắt đầu</th>
							<th>Ngày kết thúc</th>
							<th>Thao tác</th>
						</tr>
					</thead>
					<tbody>
						<%
						List<Reader> readers = (List<Reader>) request.getAttribute("readers");
						if (readers != null && !readers.isEmpty()) {
							for (Reader reader : readers) {
						%>
						<tr>
							<td><%=reader.getId()%></td>
							<td><%=reader.getIdentityCard()%></td>
							<td><%=reader.getName()%></td>
							<td><%=new java.text.SimpleDateFormat("dd/MM/yyyy").format(reader.getDob())%></td>
							<td><%=reader.getEmail()%></td>
							<td><%=reader.getPhone()%></td>
							<td><%=reader.getAddress()%></td>
							<td><%=reader.getStartDate()%></td>
							<td><%=reader.getEndDate()%></td>
							<td>
								<div class="d-flex gap-1">
									<!-- Nút sửa -->
									<form action="${pageContext.request.contextPath}/edit-reader"
										method="GET">
										<input type="hidden" name="id" value="${reader.id}">
										<button type="submit" class="btn btn-primary btn-sm">
											<i class="bi bi-pencil-square"></i>
										</button>
									</form>
									<!-- Nút xóa -->
									<button type="button"
										class="btn btn-danger btn-sm btn-delete-reader"
										data-reader-id="${reader.id}" data-bs-toggle="modal"
										data-bs-target="#deleteReaderModal">
										<i class="bi bi-trash"></i>
									</button>
								</div>
							</td>

						</tr>

						<!-- Modal chỉnh sửa -->
						<div class="modal fade" id="editModal-<%=reader.getId()%>"
							tabindex="-1" aria-labelledby="editModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title">Chỉnh sửa thông tin người đọc</h5>
										<button type="button" class="btn-close"
											data-bs-dismiss="modal" aria-label="Close"></button>
									</div>
									<form action="${pageContext.request.contextPath}/editReader"
										method="POST">
										<div class="modal-body">
											<input type="hidden" name="id" value="<%=reader.getId()%>" />
											<div class="mb-3">
												<label for="name" class="form-label">Tên</label> <input
													type="text" class="form-control" name="name"
													value="<%=reader.getName()%>" required />
											</div>
											<div class="mb-3">
												<label for="dob" class="form-label">Ngày sinh</label> <input
													type="date" class="form-control" name="dob"
													value="<%=reader.getDob()%>" required />
											</div>
											<div class="mb-3">
												<label for="email" class="form-label">Email</label> <input
													type="email" class="form-control" name="email"
													value="<%=reader.getEmail()%>" required />
											</div>
											<div class="mb-3">
												<label for="phone" class="form-label">Số điện thoại</label>
												<input type="text" class="form-control" name="phone"
													value="<%=reader.getPhone()%>" required />
											</div>
											<div class="mb-3">
												<label for="address" class="form-label">Địa chỉ</label> <input
													type="text" class="form-control" name="address"
													value="<%=reader.getAddress()%>" required />
											</div>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-secondary"
												data-bs-dismiss="modal">Đóng</button>
											<button type="submit" class="btn btn-primary">Lưu
												thay đổi</button>
										</div>
									</form>
								</div>
							</div>
						</div>

						<!-- Modal xóa -->
						<div class="modal fade" id="deleteReaderModal" tabindex="-1"
							aria-labelledby="deleteReaderModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<form action="${pageContext.request.contextPath}/delete-reader"
										method="POST">
										<div class="modal-header">
											<h1 class="modal-title fs-5" id="deleteReaderModalLabel">Xác
												nhận</h1>
											<button type="button" class="btn-close"
												data-bs-dismiss="modal" aria-label="Close"></button>
										</div>
										<div class="modal-body">
											<p class="fs-4">Bạn có chắc chắn muốn xóa người này?</p>
											<input type="hidden" id="readerIdToDelete" name="id" value="">
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-secondary"
												data-bs-dismiss="modal">Hủy</button>
											<button type="submit" class="btn btn-primary">Xác
												nhận</button>
										</div>
									</form>
								</div>
							</div>
						</div>

						<script>
    const deleteButtons = document.querySelectorAll('.btn-delete-reader');

    deleteButtons.forEach(button => {
        button.addEventListener('click', function () {
            const readerId = this.getAttribute('data-reader-id');
            document.getElementById('readerIdToDelete').value = readerId;
        });
    });
</script>

						<%
						}
						} else {
						%>
						<tr>
							<td colspan="10" class="text-center">Không có dữ liệu</td>
						</tr>
						<%
						}
						%>
					</tbody>
				</table>
			</div>

		</div>
	</div>
</body>
<%@ include file="footer.jsp"%>
