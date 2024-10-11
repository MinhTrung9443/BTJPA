<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>


<form action="${pageContext.request.contextPath}/admin/user/insert"
	method="post" enctype="multipart/form-data" >
	<label>Name: </label> <br> 
	<input type="text"  name="name" ><br> 
	<label>User name:</label> <br>
	<input type="text" name = "username"> <br>
	<label>Password</label> <br>
	<input type="password" name = "password"> <br>
	<label >Image:</label> <br>
	<c:if test="${user.images.substring(0,5)  != 'https'}">
		<c:url value="/image?fname=${user.images}" var="imgUrl"></c:url>
	</c:if>
	<c:if test="${user.images.substring(0,5)  == 'https'}">
		<c:url value="${user.images}" var="imgUrl"></c:url>
	</c:if>
	<img id="image" height="150" width="200" src="${imgUrl}" /> 
	<input type="file" onchange="chooseFile(this)" name="images" ><br>
	<label >Status</label> <br>
	<input type="text"  name="status" value=""><br>
	<input type="submit" value="Submit">
</form>