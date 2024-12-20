<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-6 offset-md-3">
            <h2 class="text-center text-dark mt-5">Quản lý thư viện</h2>
            <div class="text-center mb-5 text-dark">Đăng nhập</div>
            <div class="card my-5">
                <form class="card-body cardbody-color p-lg-5" method="POST" action="${pageContext.request.contextPath}/login">
                    <div class="text-center">
                        <!-- Logo mới không có viền tròn -->
                        <img src="https://tuyensinhmut.edu.vn/wp-content/uploads/2022/09/logo-sp-da-nang.jpg" 
                            class="img-fluid my-3" 
                            width="150px" alt="Library Logo">
                    </div>

                    <!-- Hiển thị thông báo lỗi nếu có -->
                    <% if (request.getAttribute("error") != null) { %>
                        <div class="alert alert-danger text-center">
                            <%= request.getAttribute("error") %>
                        </div>
                    <% } %>

                    <div class="mb-3">
                        <input type="text" class="form-control" id="username" name="username" placeholder="Tên tài khoản" required>
                    </div>
                    <div class="mb-3">
                        <input type="password" class="form-control" id="password" name="password" placeholder="Mật khẩu" required>
                    </div>
                    <div class="text-center">
                        <button type="submit" class="btn btn-primary px-5 mb-5 w-100">Đăng nhập</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<%@ include file="footer.jsp" %>
