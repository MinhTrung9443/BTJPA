<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>


<h2>${category.isEdit ? 'Edit Category' :  'Add New Category'}</h2>
<form action="${pageContext.request.contextPath}/admin/category/save" method="post" >
	<input type="hidden" value="${category.isEdit}" name="isEdit">
	<input type="hidden" value="${category.id}" name="id">
	<label>Category name:</label> <br>
	<input type="text" id="name" name="name" value="${category.name}"><br> 
	<label>Images:</label> <br>
	<input type="text"  id="image" name="images" value="${category.images}"><br> 
	<label>Status</label> <br>
	<input type="text" id="status" name="status" value="${category.status}"><br>
	<c:if test="${!category.isEdit}">
		<input type="submit" value="Insert">
	</c:if>
	<c:if test="${category.isEdit}">
		<input type="submit" value="Update">
	</c:if>
	
</form>


<!-- enctype="multipart/form-data" -->