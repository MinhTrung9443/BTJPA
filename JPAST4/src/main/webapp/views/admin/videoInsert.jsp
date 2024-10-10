<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>


<form action="${pageContext.request.contextPath}/admin/video/insert"
	method="post" enctype="multipart/form-data" >
	<label>Video title:</label> <br> 
	<input type="text"  name="videotitle" ><br> 
	<label>Video description:</label> <br>
	<input type="text" name = "videodescription"> <br>
	
	<label >Poster:</label> <br>
	<c:if test="${video.poster.substring(0,5)  != 'https'}">
		<c:url value="/image?fname=${video.poster}" var="imgUrl"></c:url>
	</c:if>
	<c:if test="${video.poster.substring(0,5)  == 'https'}">
		<c:url value="${video.poster}" var="imgUrl"></c:url>
	</c:if>
	<img id="image" height="150" width="200" src="${imgUrl}" /> 
	<input type="file" onchange="chooseFile(this)" name="poster" ><br>
	<label>Views :</label> <br>
	<input type="text" name="videoviews"> <br>
	<label for="active">Active</label> <br>
	<input type="text"  name="active" value=""><br>
	<!--<label>Category:</label> <br>
	 <select name="categoryid" style="weight: 300px;">
		<c:forEach items="${categoryList}" var="category" >
			<option value="${category.categoryid}">${category.categoryid}</option>
		</c:forEach>
	</select><br>-->
	<input type="submit" value="Submit">
</form>