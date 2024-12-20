<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="head.jsp" %>

<body>
    <!-- Layout chính -->
    <div class="d-flex" style="height: 100vh">
        <!-- Sidebar -->
        <%@ include file="sidebar.jsp" %>
        
        <!-- Phần chính -->
        <div class="flex-grow-1">
            <!-- Header -->
            <%@ include file="header.jsp" %>

            <!-- Nội dung chính -->
            <div class="border rounded m-4 p-4">
                <h2 class="mb-3">Danh sách phiếu mượn</h2>
                <div class="d-flex justify-content-between mb-3">
                    <div>
                        <input class="form-control" placeholder="Tìm kiếm" />
                    </div>
                    <a href="${pageContext.request.contextPath}/borrow" class="btn btn-success">
                        <i class="bi bi-plus"></i> Thêm phiếu mượn
                    </a>
                </div>

                <!-- Bảng danh sách phiếu mượn -->
                <table class="table table-striped">
                    <thead class="table-dark">
                        <tr>
                            <th>STT</th>
                            <th>Tên người mượn</th>
                            <th>Ngày mượn</th>
                            <th>Thao tác</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="borrowing" items="${borrowings}" varStatus="status">
                            <tr>
                                <td>${status.count}</td>
                                <td>${borrowing.reader.name}</td>
                                <td>${borrowing.borrowDate}</td>
                                <td>
                                    <button class="btn btn-primary btn-sm" data-bs-toggle="modal" data-bs-target="#detailsModal-${borrowing.id}">
                                        Xem chi tiết
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <!-- Modal chi tiết (đặt ngoài bảng để không phá vỡ cấu trúc) -->
    <c:forEach var="borrowing" items="${borrowings}">
        <div class="modal fade" id="detailsModal-${borrowing.id}" tabindex="-1" aria-labelledby="detailsModalLabel-${borrowing.id}" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Chi tiết phiếu mượn</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <table class="table table-bordered">
                            <thead class="table-light">
                                <tr>
                                    <th>Tên sách</th>
                                    <th>Loại sách</th>
                                    <th>Trạng thái</th>
                                    <th>Thao tác</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="detail" items="${borrowing.borrowingDetails}">
                                    <tr>
                                        <td>${detail.book.name}</td>
                                        <td>${detail.book.category.name}</td>
                                        <td>${detail.status}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${detail.status == 'Quá thời hạn'}">
                                                    <button class="btn btn-danger btn-sm delete-borrowing" data-detail-id="${detail.id}">
                                                        Xóa
                                                    </button>
                                                </c:when>
                                                <c:otherwise>
                                                    <span>-</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>

    <script>
        // Xử lý xóa borrowing
        document.querySelectorAll('.delete-borrowing').forEach(button => {
            button.addEventListener('click', function () {
                const detailId = this.dataset.detailId;
                if (confirm('Bạn có chắc chắn muốn xóa mục này?')) {
                    fetch(`${window.location.origin}/LibMan/deleteBorrowingDetail`, {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify({ detailId })
                    }).then(response => {
                        if (response.ok) {
                            alert('Xóa thành công');
                            location.reload();
                        } else {
                            alert('Xóa thất bại');
                        }
                    });
                }
            });
        });
    </script>
</body>
