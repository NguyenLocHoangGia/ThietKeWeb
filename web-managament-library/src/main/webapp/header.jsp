<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Header -->
<header class="py-3 mb-3 border-bottom">
    <div class="d-flex align-items-center justify-content-between pe-3">
        <!-- Tiêu đề -->
        <div>
            <h1 class="fs-4 ms-3 mb-0"><%= request.getAttribute("titlePage") != null ? request.getAttribute("titlePage") : "Dashboard" %></h1>
        </div>
        <!-- Dropdown -->
    </div>
</header>

<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
      rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktbj1Zt5kaHBOeSOl1lvKnI4BIV6DkXQSk8pGn"
      crossorigin="anonymous">

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-QF1YD0zOQwHQLQVNBbIJDw5LnKJ0vZJApFuKD+IxmSc6Mmm+H6fAH9D9yd/y65B+"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"
        integrity="sha384-Z9mOBN0+6NO0W2ahMZHpZhskkX9Hh47pp0dd1RxTKi2xq8qhtQXNfc+GtFG7sx5S"
        crossorigin="anonymous"></script>
