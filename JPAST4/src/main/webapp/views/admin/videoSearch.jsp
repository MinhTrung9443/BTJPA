<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<form action="${pageContext.request.contextPath}/admin/video/search"
	method="post" enctype="multipart/form-data">
	<label>Nhap ten video: </label> <input type="text"
		name="videoname"> <br>
	<input type="submit" value="submit">
</form>