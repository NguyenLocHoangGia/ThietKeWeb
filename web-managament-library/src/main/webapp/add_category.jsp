<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Button trigger modal -->
<button type="button" class="btn btn-primary" data-bs-toggle="modal"
	data-bs-target="#exampleModal">
	<i class="bi bi-plus"></i>Thêm
</button>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<form action="${pageContext.request.contextPath}/categories"
				method="POST">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="exampleModalLabel">Thêm thể loại</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div class="form-group mb-2">
						<label for="name">Tên thể loại</label> <input type="text"
							class="form-control" id="name" name="name">
					</div>
					<div class="form-group mb-2">
						<label for="description">Mô tả</label>
						<textarea class="form-control" name="description" id="description"
							rows="3"></textarea>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Hủy</button>
					<button type="submit" type="button" class="btn btn-primary">Lưu</button>
				</div>
			</form>
		</div>
	</div>
</div>