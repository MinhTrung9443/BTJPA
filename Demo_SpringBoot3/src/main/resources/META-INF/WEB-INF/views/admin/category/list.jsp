<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:if test="${message != null }">
	<h2>${message }</h2>
</c:if>
<a href="${pageContext.request.contextPath}/admin/category/add">Them category</a>
<table border = "1", width= 100%>
	<tr>
		<th>STT</th>
		<th>CategoryId</th>
		<th>CategoryName</th>
		<th>Image</th>
		<th>Status</th>
		<th>Action</th>
	</tr>
	<c:forEach items="${list}" var="cate" varStatus="STT">
		<tr >
			<td>${STT.index+1 }</td>
			<td>${cate.id}</td>
			<td>${cate.name }</td>
			<td>${cate.images} </td>
			<td>${cate.status==1 ? "hoat dong" : "dung hoat dong"}</td>
			<td><a
				href="<c:url value='/admin/category/edit/${cate.id }'/>"
				class="center">Sửa </a> | <a
				href="<c:url value='/admin/category/delete/${cate.id }'/>"
				class="center">Xóa </a></
		</tr>
	</c:forEach>

</table>