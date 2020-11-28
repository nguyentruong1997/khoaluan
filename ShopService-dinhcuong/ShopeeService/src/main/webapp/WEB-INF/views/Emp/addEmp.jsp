<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Thêm emp</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
</head>
<body ng-app="myApp">
	<p>Form Adding Employee</p>
	<div ng-controller="myCtrl">
		<c:url value="/addEmp" var="url" />
		<form >
			<input type="text" ng-model="newEmployee.manhanvien" />
			 
			<p>Ho:</p>
			<input ng-model="newEmployee.ho"  />
			 
			<p>TEn:</p>
			<input ng-model="newEmployee.ten" />
			 
			<p>Dia chi:</p>
			<input ng-model="newEmployee.diachi" />
			<br>
		
			<br>
			<button type="submit">Submit OK</button>
		</form >
	</div>

	<script type="text/javascript">
		var app = angular.module('myApp', []);
		app.controller('myCtrl', function($scope, $http) {
			$scope.empList = [];
			 
			$scope.submit = function(form){
				 
 
			}
			 
			 
			;

		});
	</script>
</body>
</html>