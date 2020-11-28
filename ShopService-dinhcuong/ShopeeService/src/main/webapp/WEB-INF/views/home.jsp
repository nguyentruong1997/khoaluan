<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>

<head>
<meta charset="UTF-8">
<title>Home Page</title>
    <link rel="stylesheet" href="<c:url value='/namemapping/css/style.css' />" />
 	<script src="<c:url value='/namemapping/js/angular.js' />" ></script> 
 
<!-- <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.js"></script> -->
</head>

<body ng-app="myApp">
<h3>Trang chủ migitek</h3>

 
 <div ng-controller="myCtrl" ng-init="initFirst()" >
		<form  >
			<input type="text" ng-model="newEmployee.manhanvien"  />
			 
			<p>Ho:</p>
			<input type="text" ng-model="newEmployee.ho"  />
			 
			<p>TEn:</p>
			<input type="text"  ng-model="newEmployee.ten" />
			 
			<p>Dia chi:</p>
			<input type="text"  ng-model="newEmployee.diachi" />
			<br>
			<br>
	
			<button ng-click="add()">ADD</button>
			<button ng-click="edits()">EDIT</button>
		</form >

	<h4>danh sach nhan vien</h4>
	<table>
		<tr> 
	  		<th>MaNhanVien</th>
	  		<th>Ho&nbsp;</th>
	  		<th>Ten</th>
	  		<th>Diachi</th>
	  		<th></th>
	  	</tr>
	  	 <tr style="color:black" ng-repeat="emp in empList"> 
	  		<td>{{ emp.manhanvien }}</td>
	  		<td>{{ emp.ho }}</td>
	  		<td>{{ emp.ten }}</td>
	  		<td>{{ emp.diachi }}</td>
	  		 
	  		<td>
	   				<button ng-click="edit(emp)" /> Edit </button>
                    <button ng-click="deleteEmp(emp)" /> Delete </button>
	   	    </td> 
	  	</tr> 
	</table>
     <h3 style="color:red;"> {{errorResponse.value}}  </h3>
 </div> 

   
</body>
<script type="text/javascript">
     var app = angular.module('myApp', []);
	app.controller('myCtrl', function($scope,$http) {
		$scope.empList=[];     
		$scope.errorResponse="";   //message tra ve giao dien 
		resetDefaultValue();  //khoi tao gia tri mac dinh
		function resetDefaultValue(){
			$scope.newEmployee={
					manhanvien:'',
					ho:'',
					ten:'',
					diachi:''
			};
		};
		$scope.initFirst=function()
		{
			 $http({
		            method :'GET',
		            url : 'list'
		        }).then(function successCallback(response) {
		            $scope.empList = response.data;
		           
		        }, function errorCallback(response) {
		            console.log(response.statusText);
		        });
		 };
		 
	    $scope.deleteEmp = function(emp){
	    	$scope.errorResponse="";
	    	var method="DELETE";	
	        var url = "emp";
	        $http({
                method : method,
                url : url,
                data : angular.toJson(emp),
                headers : {
                    'Content-Type' : 'application/json'
                }
            }).then(function (response) {
	            $scope.errorResponse = response.data;
	            $scope.initFirst();
	            resetDefaultValue();
      
	        }, function (response) {
	        	console.log("Delete failed");
	        });
	    };
	    
	    
	    $scope.add = function(){
	    	$scope.errorResponse="";
	    	 
	        $http({
                method : "POST",
                url : "emp",
                data : angular.toJson($scope.newEmployee),
                headers : {
                    'Content-Type' : 'application/json'
                }
            }).then(function (response) {
	            $scope.errorResponse = response.data;
	            $scope.initFirst();
	            resetDefaultValue();
      
	        }, function (response) {
	        	$scope.errorResponse = response.data;
	        });
	        
	    };
	  
	    $scope.edits = function(){
	    	$scope.errorResponse="";
	        $http({
                method : "PUT",
                url : "emp",
                data : angular.toJson($scope.newEmployee),
                headers : {
                    'Content-Type' : 'application/json'
                }
            }).then(function (response) {
	            $scope.errorResponse = response.data;
	            $scope.initFirst();
	            resetDefaultValue();
	           
        
	        }, function (response) {
	        	$scope.errorResponse = response.data;
	        });
	    };

	    $scope.edit = function(emp){
	    	  $scope.errorResponse="";
	          $scope.newEmployee = angular.copy(emp);
	    };  
 
       
	}); 
	
	
	 
</script>
</html>