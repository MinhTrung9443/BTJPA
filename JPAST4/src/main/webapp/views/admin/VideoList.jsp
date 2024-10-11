<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<a href="${pageContext.request.contextPath}/admin/video/insert">Them Video</a><br>
<a href="${pageContext.request.contextPath}/admin/video/search">Tim Video</a>
<table border = "1", width= 100%>
	<tr>
		<th>STT</th>
		<th>VideoId</th>
		<th>Title</th>
		<th>Description</th>
		<th>Poster</th>
		<th>Views</th>
		<th>Active</th>
		<th>Category</th>
		<th>Action</th>
	</tr>
	<c:forEach items="${listvideo}" var="video" varStatus="STT">
		<tr >
			<td>${STT.index+1 }</td>
			<td>${video.videoid}</td>
			<td>${video.title }</td>
			<td>${video.description}</td>
			<td>
			<c:if test = "${video.poster.substring(0,5)  != 'https'}" >
			<c:url value="/image?fname=${video.poster}" var="imgUrl"></c:url>
			</c:if>
			<c:if test = "${video.poster.substring(0,5)  == 'https'}" >
			<c:url value="${video.poster}" var="imgUrl"></c:url>
			</c:if>
			<img height="150" width="200" src="${imgUrl}" />
			</td>
			<td>${video.views }</td>
			<td>${video.active==true ? "hoat dong" : "dung hoat dong"}</td>
			<td>${video.category.categoryname}</td>
			
			<td><a
				href="<c:url value='/admin/video/update?id=${video.videoid }'/>"
				class="center">Sửa </a> | <a
				href="<c:url value='/admin/video/delete?id=${video.videoid }'/>"
				class="center">Xóa </a></
		</tr>
	</c:forEach>

</table>