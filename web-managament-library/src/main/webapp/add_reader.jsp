<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Button trigger modal -->
<button type="button" class="btn btn-primary" data-bs-toggle="modal"
	data-bs-target="#addReaderModal">
	<i class="bi bi-plus"></i>Thêm
</button>

<!-- Modal -->
<div class="modal fade" id="addReaderModal" tabindex="-1"
	aria-labelledby="addReaderModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<form action="${pageContext.request.contextPath}/readers"
				method="POST">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="addReaderModalLabel">Thêm thành viên</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div class="form-group mb-2">
						<label for="identityCard">CMND/CCCD</label>
						<input type="text" class="form-control" id="identityCard" name="identityCard" required>
					</div>
					<div class="form-group mb-2">
						<label for="name">Tên thành viên</label>
						<input type="text" class="form-control" id="name" name="name" required>
					</div>
					<div class="form-group mb-2">
						<label for="dob">Ngày sinh</label>
						<input type="date" class="form-control" id="dob" name="dob" required>
					</div>
					<div class="form-group mb-2">
						<label for="email">Email</label>
						<input type="email" class="form-control" id="email" name="email" required>
					</div>
					<div class="form-group mb-2">
						<label for="phone">Số điện thoại</label>
						<input type="text" class="form-control" id="phone" name="phone" required>
					</div>
					<div class="form-group mb-2">
						<label for="address">Địa chỉ</label>
						<input type="text" class="form-control" id="address" name="address" required>
					</div>
					<div class="form-group mb-2">
						<label for="startDate">Ngày bắt đầu</label>
						<input type="date" class="form-control" id="startDate" name="startDate" required>
					</div>
					<div class="form-group mb-2">
						<label for="endDate">Ngày kết thúc</label>
						<input type="date" class="form-control" id="endDate" name="endDate">
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
					<button type="submit" class="btn btn-primary">Lưu</button>
				</div>
			</form>
		</div>
	</div>
</div>
