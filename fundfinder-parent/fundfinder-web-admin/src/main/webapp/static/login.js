angular.module('Login', [])

.controller('LoginController', function($rootScope, $scope, $http, $window) {
	if ($window.location.hash.indexOf('logoutSuccess') != -1) {
		$scope.msg = "Logout success";
		$scope.msgClass = "text-success";
		$window.location.hash = "";
	}
	
	$scope.login = function() {
		$http.post("/login.html", null, {
			params : {
				username: $scope.username,
				password: $scope.password
			}
		})
		.success(function(data, status, headers, config) {
			$scope.msg = data.message;
			$scope.msgClass = "text-danger";
			if (data.url != null) {
				$scope.msgClass = "text-success";
				$window.location.href = data.url;
			}
		})
		.error(function(data, status, headers, config) {
			$scope.msg = "Error";
			$scope.msgClass = "text-danger";
		});
	}
});
