<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<form action="${pageContext.request.contextPath}/admin/video/update"
	method="post" enctype="multipart/form-data" >
	<input type="text" name="videoid" value="${video.videoid}" hidden="hidden"><br>
	<label>Video title:</label> <br> 
	<input type="text"  name="videotitle" value="${video.title}"><br> 
	<label>Video description:</label> <br>
	<input type="text" name = "videodescription" value="${video.description}"> <br>
	
	<label >Poster:</label> <br>
	<c:if test="${video.poster.substring(0,5)  != 'https'}">
		<c:url value="/image?fname=${video.poster}" var="imgUrl"></c:url>
	</c:if>
	<c:if test="${video.poster.substring(0,5)  == 'https'}">
		<c:url value="${video.poster}" var="imgUrl"></c:url>
	</c:if>
	<img id="image" height="150" width="200" src="${imgUrl}" />
	<input type="file" onchange="chooseFile(this)" id="poster" name="poster" value="${video.poster}" ><br>
	<label>Views :</label> <br>
	<input type="text" name="videoviews" value="${video.views}"> <br>
	<label for="active">Active</label> <br>
	<input type="text"  name="active" value="${video.active == true ? 'Dang hoat dong':'Ngung cung cap'}"><br>
	 <label>Category:</label> <br>
	<select name="categoryid" style="weight: 300px;" value="${video.category}">
		<c:forEach items="${categoryList}" var="category" >
			<option value="${category.categoryid}">${category.categoryname}</option>
		</c:forEach>
	</select><br>
	<input type="submit" value="Submit">"
</form>

<!-- enctype="multipart/form-data" -->