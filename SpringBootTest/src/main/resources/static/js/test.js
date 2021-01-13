var app = angular.module('dayApp', []);
app.controller('dayCtrl', function($scope, $http) {
	 
	$scope.items=[];
	
	
	 $http.get("http://localhost:8082/items")
		  .then(function(response) {
			  $scope.items=response.data[1];
			  //alert(response.data[0].picByte);
		  });
	
	
	
});