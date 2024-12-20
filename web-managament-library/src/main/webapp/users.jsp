<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="head.jsp"%>
<%@ page import="model.User"%>
<%@ page import="java.util.List"%>
<body>
	<div class="d-flex" style="height: 100vh">
		<%@ include file="sidebar.jsp"%>
		<div class="flex-grow-1">
			<%@ include file="header.jsp"%>
			<div class="border rounded m-4 p-4">
				<div class="d-flex justify-content-between mb-3">
					<form action="${pageContext.request.contextPath}/users"
						method="GET">
						<div class="d-flex">
							<input name="q" class="form-control me-2"
								value="<%=request.getAttribute("key") != null ? request.getAttribute("key") : ""%>"
								placeholder="Tìm kiếm người dùng" />
							<button type="submit" class="btn btn-success">
								<i class="bi bi-search"></i>
							</button>
						</div>
					</form>
					<%@ include file="add_user.jsp"%>
				</div>

				<table class="table table-striped">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">Họ tên</th>
							<th scope="col">Tên tài khoản</th>
							<th scope="col">Mật khẩu</th>
							<th scope="col">Vị trí</th>
							<th scope="col">Thao tác</th>
						</tr>
					</thead>
					<tbody>
						<%-- Ensure listUsers is not null and safely iterate over it --%>
						<%
						List<User> listUsers = (List<User>) request.getAttribute("listUsers");
						if (listUsers != null && !listUsers.isEmpty()) {
							for (User user : listUsers) {
						%>
						<tr>
							<td><%=user.getId()%></td>
							<td><%=user.getName()%></td>
							<td><%=user.getUsername()%></td>
							<td><%=user.getPassword()%></td>
							<td><%=user.getPosition()%></td>
							<td>
								<button class="btn btn-primary">
									<i class="bi bi-pencil-square"></i>
								</button>
								<button type="button" class="btn btn-danger btn-delete-user"
									data-user-id="<%=user.getId()%>" data-bs-toggle="modal"
									data-bs-target="#deleteUserModal">
									<i class="bi bi-trash"></i>
								</button>
							</td>
						</tr>
						<%
						}
						} else {
						%>
						<tr>
							<td colspan="6" class="text-center">Không có người dùng nào</td>
						</tr>
						<%
						}
						%>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<div class="modal fade" id="deleteUserModal" tabindex="-1"
		aria-labelledby="deleteUserModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="${pageContext.request.contextPath }/delete-user"
					method="POST">
					<div class="modal-header">
						<h1 class="modal-title fs-5" id="deleteUserModalLabel">Xác
							nhận</h1>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<p class="fs-4">Bạn muốn xóa người dùng này?</p>
						<input type="hidden" id="userIdToDelete" name="id" value="">
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
    const deleteButtons = document.querySelectorAll('.btn-delete-user');

	    deleteButtons.forEach(button => {
	        button.addEventListener('click', function () {
	            const userId = this.getAttribute('data-user-id');
	            
	            const userIdInput = document.getElementById('userIdToDelete');
	            userIdInput.value = userId;
	        });
	    });
	</script>
</body>
<%@ include file="footer.jsp"%>
