<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>Thong tin Employee</h2>
	  <hr/>
	  <table>
	  	<tr> 
	  		<th>ID</th>
	  		<th>Ho</th>
	  		<th>Ten</th>
	  		<th>Dia Chi</th>
	  	</tr>
	  	 <c:set var="l" value="${ emp }"/>
	   		<tr>
	   			<td> ${l.manhanvien }</td>
	   			<td> ${l.ho }&nbsp;</td>
	   			<td> ${l.ten }</td>
	   			<td> ${l.diachi }</td>
	   		</tr>

	  </table>
</body>
</html>