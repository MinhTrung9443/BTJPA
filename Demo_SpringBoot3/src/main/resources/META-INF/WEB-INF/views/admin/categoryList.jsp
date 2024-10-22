<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<a href="${pageContext.request.contextPath}/admin/category/insert">Them category</a>
<table border = "1", width= 100%>
	<tr>
		<th>STT</th>
		<th>CategoryId</th>
		<th>Icon</th>
		<th>CategoryName</th>
		<th>Active</th>
		<th>Action</th>
	</tr>
	<c:forEach items="${listcate}" var="cate" varStatus="STT">
		<tr >
			<td>${STT.index+1 }</td>
			<td>${cate.cate_id}</td>
			
			<td>
			<c:if test = "${cate.icon.substring(0,5)  != 'https'}" >
			<c:url value="/image?fname=${cate.icon}" var="imgUrl"></c:url>
			</c:if>
			<c:if test = "${cate.icon.substring(0,5)  == 'https'}" >
			<c:url value="${cate.icon}" var="imgUrl"></c:url>
			</c:if>
			<img height="150" width="200" src="${imgUrl}" />
			</td>
			<td>${cate.cate_name }</td>
			<td>${cate.active ? "hoat dong" : "dung hoat dong"}</td>
			<td><a
				href="<c:url value='/admin/category/update?id=${cate.cate_id }'/>"
				class="center">Sửa </a> | <a
				href="<c:url value='/admin/category/delete?id=${cate.cate_id }'/>"
				class="center">Xóa </a></
		</tr>
	</c:forEach>

</table>