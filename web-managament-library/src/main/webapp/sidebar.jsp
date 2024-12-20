<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex flex-column flex-shrink-0 p-3 bg-dark text-white shadow" style="width: 280px; height: 100vh;">
    <a href="/" class="d-flex align-items-center mb-4 text-white text-decoration-none">
        <i class="bi bi-book-fill fs-3 me-3"></i>
        <span class="fs-4">Quản lý thư viện</span>
    </a>
    <hr class="text-secondary">
    <ul class="nav nav-pills flex-column mb-auto">
        <li class="nav-item">
            <a href="${pageContext.request.contextPath}/borrow" 
               class="nav-link <%= "borrow".equals(request.getAttribute("activePage")) ? "active bg-secondary text-white" : "text-white" %> d-flex align-items-center">
                <i class="bi bi-file-earmark-plus me-3 fs-5"></i> Cho mượn sách
            </a>
        </li>
        <li class="nav-item">
            <a href="${pageContext.request.contextPath}/borrowings" 
               class="nav-link <%= "borrowings".equals(request.getAttribute("activePage")) ? "active bg-secondary text-white" : "text-white" %> d-flex align-items-center">
                <i class="bi bi-file-earmark-text me-3 fs-5"></i> Phiếu mượn
            </a>
        </li>
        <li class="nav-item">
            <a href="${pageContext.request.contextPath}/readers" 
               class="nav-link <%= "readers".equals(request.getAttribute("activePage")) ? "active bg-secondary text-white" : "text-white" %> d-flex align-items-center">
                <i class="bi bi-person-check me-3 fs-5"></i> Thành viên
            </a>
        </li>
        <li class="nav-item">
            <a href="${pageContext.request.contextPath}/books" 
               class="nav-link <%= "books".equals(request.getAttribute("activePage")) ? "active bg-secondary text-white" : "text-white" %> d-flex align-items-center">
                <i class="bi bi-book me-3 fs-5"></i> Sách
            </a>
        </li>
        <li class="nav-item">
            <a href="${pageContext.request.contextPath}/categories" 
               class="nav-link <%= "categories".equals(request.getAttribute("activePage")) ? "active bg-secondary text-white" : "text-white" %> d-flex align-items-center">
                <i class="bi bi-bookmark me-3 fs-5"></i> Thể loại
            </a>
        </li>
        <li class="nav-item">
            <a href="${pageContext.request.contextPath}/users" 
               class="nav-link <%= "users".equals(request.getAttribute("activePage")) ? "active bg-secondary text-white" : "text-white" %> d-flex align-items-center">
                <i class="bi bi-people me-3 fs-5"></i> Nhân viên
            </a>
        </li>
    </ul>
    <hr class="text-secondary">
    <div class="mt-auto text-center">
        <a href="${pageContext.request.contextPath}/login" class="btn btn-outline-light btn-sm w-100">
            <i class="bi bi-box-arrow-right me-2"></i> Đăng xuất
        </a>
    </div>
</div>
