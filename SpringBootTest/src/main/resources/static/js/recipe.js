function refresh() {
	location.reload();
}


var app = angular.module('myApp', ['angularUtils.directives.dirPagination']);
app.controller('myCtrl', function($scope, $http) {
	
	
	$scope.refresh= function(){
		location.reload();
	}
	$scope.openModal2= function(id){
	   	 
	   	 $('#openModal2').modal('show'); 
	   	
	   	$http.get("http://localhost:8082/items?search=(id:'"+id+"')")
		  
		  .then(function(response) {
			 
			  var popName=response.data[0].name;
			  var popIng=response.data[0].ingredient;
			  var popRecipe=response.data[0].recipe;
			  
		  
	   	document.getElementById("editName").value=popName;
	   		document.getElementById("editIng").value=popIng;
	   			document.getElementById("editRecipe").value=popRecipe;
		  });
	    
	   	 $scope.ok = function () {
	   		
	   		
	   		 var name= $("#editName").val();
	   	     var ingredient= $("#editIng").val();
	   	     var recipe= $("#editRecipe").val();
	   	   
	   	  	var obj='{ "id": "'+id+'", "name":"'+name+'", "ingredient":"'+ingredient+'", "recipe":"'+recipe+'" }';
	   		var data= JSON.parse(obj);
	   		var config = {
	                headers : {
	                    'Content-Type': 'application/json'
	                }
	            }
	   		
	   		
	   		$http.put("http://localhost:8082/updateItem",data, config).then(function (response) {
	   				
	   			 $('#success').modal('show'); 
	       		
	   				}, function (response) {
	   					$('#error').modal('show'); 
	   				});
	   		 
	   	 }
	   	
	    }
	
	$scope.openModal= function(id){
	   	 
	   	 $('#openModal').modal('show'); 
	  	 $scope.ok = function () {
	   		 
	  		var obj='{ "id": "'+id+'" }';
	   		var data= JSON.parse(obj);
	   		var config = {
	                headers : {
	                    'Content-Type': 'application/json'
	                }
	            }
	   		
	   	
	   		$http.post("http://localhost:8082/deleteItem",data, config).then(function (response) {
	   			 $('#success').modal('show'); 
	        	
	   				
	   				}, function (response) {
	   					$('#error').modal('show'); 
	   	        	 

	   				/*alert(response.status);

	   				alert(response.statusText);*/

	   		}); 
	   	 }
	   	
	    }
	
	$scope.items=[];
	
	
	
		
	 $http.get("http://localhost:8082/items")
		  .then(function(response) {
			  
			  var qstring1="";
				var tech="";
				var resp= [];
				  var q2="",q3="";
				 var sPageURL = window.location.search.substring(1);
			     var sURLVariables = sPageURL.split('&');
			     for (var i = 0; i < sURLVariables.length; i++)
			     {
			         var sParameterName = sURLVariables[i].split('=');
			         if (sParameterName[0] == 'qstring')
			         {
			        	 qstring1 = sParameterName[1];
			 	    	
			         }
			         else  if (sParameterName[0] == 'search')
			         {
			        	 qstring1 = sParameterName[1].split('(id:%27');
			        	 q2=qstring1[1].split('%27)');
			        	 q3=q2[0];
			 	    	
			         }
			     }
			     var i;
			     for(i=0; i<response.data.length;i++){
			 
			    	
			     if(qstring1!="" || q3!="")
	        	 {
			    	 if(qstring1 == "snack"){
			    		 
			    		 if(response.data[i].type == "snack"){
			    			
			    			 resp.push(response.data[i]);
			    			 
			    		 }
			    	 	}
			    	 else if(qstring1=="main"){
			    		 if(response.data[i].type == "main"){
			    			 resp.push(response.data[i]);
			    		 }
			    	 }
			    	 else if(qstring1 == "lunch"){
			    		 if(response.data[i].type == "lunch"){
			    			 resp.push(response.data[i]);
			    		 }
			    	 }
			    	 
			    	 else if(qstring1 == "dinner"){
			    		 if(response.data[i].type == "dinner"){
			    			 resp.push(response.data[i]);
			    		 }
			    	 }
			    	 
			    	 else if(qstring1 == "dessert"){
			    		 if(response.data[i].type == "dessert"){
			    			 resp.push(response.data[i]);
			    		 }
			    	 }
			    	 else if(qstring1 == "starter"){
			    		 if(response.data[i].type == "starter"){
			    			 resp.push(response.data[i]);
			    		 }
			    	 }
			    	 
			    	 else if(response.data[i].id == q3){
		    			 resp.push(response.data[i]);
		    		 }
			    	 
			    	 
	        	    }
			     else{
		    		 resp.push(response.data[i]);
		    	 }
			     }
			     
			  $scope.items=resp;
			  
			  
			  
		  });
	 

	 
	 $scope.sort = function(keyname){
			//alert($scope.sortKey)
		 //$scope.reverse=false;
			$scope.sortKey = keyname;   //set the sortKey to the param passed
			$scope.reverse = !$scope.reverse; //if true make it false and vice versa
		}
			
	
});