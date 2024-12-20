<%@page import="model.Category"%>
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
					<%@ include file="add_category.jsp"%>
				</div>

				<table class="table table-striped">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">Thể loại</th>
							<th scope="col">Thao tác</th>
						</tr>
					</thead>
					<tbody>
						<%-- Ensure listUsers is not null and safely iterate over it --%>
						<%
						List<Category> listCategories = (List<Category>) request.getAttribute("listCategories");
						if (listCategories != null && !listCategories.isEmpty()) {
							for (Category category : listCategories) {
						%>
						<tr>
							<td><%=category.getId()%></td>
							<td><%=category.getName()%></td>
							<td>
								<button class="btn btn-primary">
									<i class="bi bi-pencil-square"></i>
								</button>
								<button type="button" class="btn btn-danger btn-delete-category"
									data-catgory-id="<%=category.getId()%>" data-bs-toggle="modal"
									data-bs-target="#deleteCategoryModal">
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


	<div class="modal fade" id="deleteCategoryModal" tabindex="-1"
		aria-labelledby="deleteCategoryModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="${pageContext.request.contextPath }/delete-category"
					method="POST">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="deleteCategoryModalLabel">Xác
						nhận</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<p class="fs-4">Bạn muốn xóa loại này?</p>
					<input type="hidden" id="categoryIdToDelete" name="id" value="">
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
    const deleteButtons = document.querySelectorAll('.btn-delete-category');

	    deleteButtons.forEach(button => {
	        button.addEventListener('click', function () {
	            const categoryId = this.getAttribute('data-catgory-id');
	            
	            const categoryIdInput = document.getElementById('categoryIdToDelete');
	            categoryIdInput.value = categoryId;
	        });
	    });
	</script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
		integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
		integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
		crossorigin="anonymous"></script>
</body>
<%@ include file="footer.jsp"%>