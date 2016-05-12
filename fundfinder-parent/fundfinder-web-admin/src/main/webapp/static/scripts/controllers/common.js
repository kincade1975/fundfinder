angular.module('FundFinder')

// ====================================================================================
//	TopnavbarCtrl
// ====================================================================================
.controller('TopnavbarController', function($scope, $http, $window, $translate, $state) {
	
	/**
	 * Logout function.
	 */
	$scope.logout = function() {
		$http.post('logout', {})
			.success(function() {
				$window.location = "/";
			})
			.error(function(data) {
				
			});
	};
	
	/**
	 * Change language function.
	 */
	$scope.changeLanguage = function(langKey) {
		$translate.use(langKey);
		$state.go($state.current, {}, { reload: true });
	};
	
})

// ====================================================================================
//	NavigationCtrl
// ====================================================================================
.controller('NavigationCtrl', function($rootScope, $scope, $http, $window) {
	
	/**
	 * Logout function.
	 */
	$scope.logout = function() {
		$http.post('logout', {})
			.success(function() {
				$window.location = "/";
			})
			.error(function(data) {
				
			});
	};
	
	// get account 
//	$http.get('/api/v1/app/security/account')
//		.success(function(account) {
//			$rootScope.account = account;	
//		})
//		.error(function() {
//			
//		});
	
});