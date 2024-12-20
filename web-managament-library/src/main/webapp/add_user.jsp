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
			<form action="${pageContext.request.contextPath}/add_category"
				method="POST">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="exampleModalLabel">Thêm thể
						loại</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div class="form-group mb-2">
						<label for="name">Họ và tên</label> <input type="text"
							class="form-control" id="name" name="name">
					</div>
					<div class="form-group mb-2">
						<label for="username">Tên tài khoản</label> <input type="text"
							class="form-control" id="username" name="username">
					</div>
					<div class="form-group mb-2">
						<label for="password">Mật khẩu</label> <input type="password"
							class="form-control" id="password" name="password">
					</div>
					<div class="form-group mb-2">
						<label for="confirmPassword">Xác nhận mật khẩu</label> <input
							type="password" class="form-control" id="confirmPassword"
							name="confirmPassword">
					</div>
					<div class="form-group mb-2">
						<label for="position">Chức vụ</label> <select class="form-control"
							id="position" name="position">
							<option disabled="disabled" selected>Chức vụ</option>
							<option value="Quản trị viên">Quản trị viên</option>
							<option value="Thủ thư">Thủ thư</option>
							<option value="Lễ tân">Lễ tân</option>
						</select>
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