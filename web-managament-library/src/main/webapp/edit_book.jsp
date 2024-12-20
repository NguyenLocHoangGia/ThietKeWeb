<%@page import="model.Book"%>
<%@page import="model.Category"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>
<%
Book book = (Book) request.getAttribute("book");
%>
<body>
    <div class="d-flex" style="height: 100vh">
	    <%@ include file="sidebar.jsp" %>
	    <div class="flex-grow-1">
    		<%@ include file="header.jsp" %>
	     	<div class="border rounded m-4 p-4">
	     	<form acition="${pageContext.request.contextPath}/add-book" method="POST">
	     	<input type="hidden" class="form-control" id="id" name="id" value="<%=book.getId() %>">
			  <div class="form-group mb-2">
			    <label for="name">Tên sách</label>
			    <input type="text" class="form-control" id="name" name="name" value="<%=book.getName() %>">
			  </div>
			  <div class="form-group mb-2">
			    <label for="category">Thể loại sách </label>
			    <select class="form-control" id="category" name="category">
			      <%
			      	List<Category> listCategories = (List<Category>) request.getAttribute("listCategories");
			    	if (listCategories != null && !listCategories.isEmpty()) {
			    		for (Category category : listCategories) {
			      %>
			      	<option value="<%=category.getId() %>" <%= category.getId() == book.getCategory().getId() ? "selected" : ""%>><%= category.getName() %></option>
			      <%
			    		}
			    	}
			      %>
			    </select>
			  </div>
			  <div class="form-group mb-2">
			    <label for="author">Tác giả</label>
			    <input type="text" class="form-control" id="author" name="author" value="<%=book.getAuthor() %>">
			  </div>
			  <div class="form-group mb-2">
			    <label for="description">Mô tả</label>
			    <textarea class="form-control" name="description" id="description" rows="3" value="<%=book.getDescription() %>"></textarea>
			  </div>
			  <div class="form-group mb-2">
			    <label for="amount">Số lượng</label>
			    <input type="number" class="form-control" id="amount" name="amount" value="<%=book.getAmount() %>">
			  </div>
			  <div>
			  	<button class="btn btn-secondary">Hủy</button>
			  	<button class="btn btn-success">Lưu</button>
			  </div>
			</form>
	     	</div>
	    </div>
    </div>
</body>
<%@ include file="footer.jsp" %>