<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>	
	<p> Form Editing Emp</p>
	<c:url value="/editEmp" var="url" />
	<form:form modelAttribute="emp" method="post" action="${url }">
		<form:hidden path="manhanvien" />
		
		<p>Ho: </p>
		<form:input path="ho"/>
		<p style="color:red;">
			<form:errors path="ho"></form:errors> 
		</p>
		<p>TEn: </p><form:input path="ten"/>
		<p style="color:red;">
			<form:errors path="ten"></form:errors> 
		</p>
		<p>Dia chi: </p><form:input path="diachi"/>
	    <br><p style="color:red;">
			<form:errors path="diachi"></form:errors> 
		</p><br>
		<button type="submit">Submit OK</button>
	</form:form>
</body>
</html>