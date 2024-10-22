<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>


<form action="${pageContext.request.contextPath}/admin/category/update"
	method="post" enctype="multipart/form-data">
	<input type="text"
		id="cateid" name="cateid" value="${cate.cate_id}" hidden="hidden"><br>
	<label for="fname">Category name:</label> <br> <input type="text"
		id="categoryname" name="categoryname" value="${cate.cate_name}"><br>
	<label for="image">Images name:</label> <br>
	<c:if test="${cate.icon.substring(0,5)  != 'https'}">
		<c:url value="/image?fname=${cate.icon}" var="imgUrl"></c:url>
	</c:if>
	<c:if test="${cate.icon.substring(0,5)  == 'https'}">
		<c:url value="${cate.icon}" var="imgUrl"></c:url>
	</c:if>
				<img id="image" height="150" width="200" src="${imgUrl}" />
	<input type="file" onchange="chooseFile(this)" id="image" name="image" value="${cate.icon}"><br>
	<label for="active">Active</label> <br> <input type="text"
		id="active" name="active" value="${cate.active}"><br> <input
		type="submit" value="Submit">"
</form>

<!--  enctype="multipart/form-data" -->