<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List Emp</title>
</head>
<body>
	<h2>danh sach nhan vien</h2>
	<a href="<c:url value='/addEmp' />" >Them Emp</a>
	<table>
		<tr> 
	  		<th>ID</th>
	  		<th>Ho&nbsp;</th>
	  		<th>Ten</th>
	  		<th>Diachi</th>
	  		<th>Chi tiet EMP</th>
	  		<th>
	  	</tr>
	<c:forEach items="${ emplist}" var="l">
	   		<tr>
	   			<td> ${l.manhanvien }</td>
	   			<td> ${l.ho }&nbsp;</td>
	   			<td> ${l.ten }</td>
	   			<td> ${l.diachi }</td>
	   			<td>
	   				<a href="<c:url value='/viewEmp/${l.manhanvien}' />" >Chi tiet Emp</a> 
	   				<a href="<c:url value='/deleteEmp/${l.manhanvien}' />" >Delete</a>
	   				<a href="<c:url value='/editEmp/${l.manhanvien}' />" >Edit</a>
	   		   </td>
	   		</tr>
		 </c:forEach>
	</table>
</body>
</html>