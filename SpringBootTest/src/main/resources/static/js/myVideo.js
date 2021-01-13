function refresh() {
	location.reload();
}


var app = angular.module('myVideoApp', ['angularUtils.directives.dirPagination']);
app.controller('myVideoCtrl', function($scope, $http) {

	$scope.items=[];
	
		
	 $http.get("http://localhost:8082/items")
		  .then(function(response) {
			  var qstring1="";
			  var q2="",q3="";
				var tech="";
				var resp= [];
				 var sPageURL = window.location.search.substring(1);
			     var sURLVariables = sPageURL.split('&');
			     for (var i = 0; i < sURLVariables.length; i++)
			     {
			         var sParameterName = sURLVariables[i].split('=');
			         if (sParameterName[0] == 'search')
			         {
			        	 qstring1 = sParameterName[1].split('(id:%27');
			        	 q2=qstring1[1].split('%27)');
			        	 q3=q2[0];
			         }
			         else if (sParameterName[0] == 'qstring')
			         {
			        	 qstring1 = sParameterName[1];
			 	    	
			         }
			     }
			     var i;
			     for(i=0; i<response.data.length;i++){
			    	if(q3!="" || qstring1!="" ){
			  
			    		 if(response.data[i].id == q3){
			    			 if(response.data[i].video!= "")
			    				{
			    			 resp.push(response.data[i]);
			    				}
			    		 }
			    		 
			    		 else if(qstring1 == "snack"){
				    		 
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
			    			 
			    			 if (response.data[i].video!= null){
				    		 resp.push(response.data[i]);
				    	 }
			    		 }
			    	
			     } 
			    
			  $scope.items=resp;
			
		  });
	 
	 
});