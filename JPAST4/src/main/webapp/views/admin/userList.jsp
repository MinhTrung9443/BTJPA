<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<a href="${pageContext.request.contextPath}/admin/user/insert">Them user</a><br>
<table border = "1", width= 100%>
	<tr>
		<th>STT</th>
		<th>UserId</th>
		<th>Name</th>
		<th>UserName</th>
		<th>Password</th>
		<th>Image</th>
		<th>Active</th>
		<th>Action</th>
	</tr>
	<c:forEach items="${listuser}" var="user" varStatus="STT">
		<tr >
			<td>${STT.index+1 }</td>
			<td>${user.userid}</td>
			<td>${user.name }</td>
			<td>${user.username}</td>
			<td>${user.password}</td>
			<td>
			<c:if test = "${user.images.substring(0,5)  != 'https'}" >
			<c:url value="/image?fname=${user.images}" var="imgUrl"></c:url>
			</c:if>
			<c:if test = "${user.images.substring(0,5)  == 'https'}" >
			<c:url value="${user.images}" var="imgUrl"></c:url>
			</c:if>
			<img height="150" width="200" src="${imgUrl}" />
			</td>
			<td>${user.status==1 ? "hoat dong" : "dung hoat dong"}</td>
			
			
			<td><a
				href="<c:url value='/admin/user/update?id=${user.userid }'/>"
				class="center">Sửa </a> | <a
				href="<c:url value='/admin/user/delete?id=${user.userid }'/>"
				class="center">Xóa </a></
		</tr>
	</c:forEach>

</table>