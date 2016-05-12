function config($provide, $stateProvider, $httpProvider, $urlRouterProvider) {
	
	// define 'ajaxInterceptor'
	$provide.factory('ajaxInterceptor', function($log, $window) {
		var requestHandler = function(config) {
			// let the server know it was AJAX request so it can return header for redirect instead of make redirect
			config.headers['X-Requested-With'] = 'XMLHttpRequest';
			return config;
		};
		
		var responseHandler = function(response) {
			var redirect = response.headers('X-REDIRECT');
			if (redirect != undefined) {
				$log.debug('Redirecting to: ' + redirect);
				$window.location.href = redirect;
			}
			return response;
		};
		
		return {
			'request': requestHandler,
			'response': responseHandler,
			'responseError': responseHandler
		}
	});
	
	// actualize 'ajaxInterceptor'
	$httpProvider.interceptors.push('ajaxInterceptor');
	
	$urlRouterProvider.otherwise("/investments/overview");
	
	$stateProvider
		.state('investments', {
	        abstract: true,
	        url: "/investments",
	        templateUrl: "views/common/content.html",
	    })
	    .state('investments.overview', {
	        url: "/overview",
	        templateUrl: "views/investments/overview.html",
	        controller: 'InvestmentsOverviewController',
	        data: { pageTitle: 'Investments Overview' }
	    })
		.state('articles', {
	        abstract: true,
	        url: "/articles",
	        templateUrl: "views/common/content.html",
	    })
	    .state('articles.overview', {
	        url: "/overview",
	        templateUrl: "views/articles/overview.html",
	        controller: 'ArticleOverviewController',
	        data: { pageTitle: 'Articles Overview' }
	    });
}

angular.module('FundFinder').config(config)
	.run(function ($rootScope, $state, $stateParams, $log, $localStorage) {
		$rootScope.$state = $state;
		$rootScope.$stateParams = $stateParams;
		
		$rootScope.version = "v1.0.0";
		
		$rootScope.dateFormat = "yyyy.MM.dd";
		$rootScope.dateTimeFormat = "yyyy.MM.dd HH:mm:ss";
		
		// toastr default settings
		toastr.options.preventDuplicates = true;
		toastr.options.closeButton = true;
		
		$rootScope.processDateFilters = function (creationDateEl, lastModifiedDateEl, filterArray) {
			if (creationDateEl && creationDateEl.val() && creationDateEl.val().length > 0) {
				filterArray.push({ "name" : "creationDate", "term" : creationDateEl.val() });
			}

			if (lastModifiedDateEl && lastModifiedDateEl.val() && lastModifiedDateEl.val().length > 0) {
				filterArray.push({ "name" : "lastModifiedDate", "term" :  lastModifiedDateEl.val() });
			}
		}
		
		$rootScope.setDateFilters = function (creationDateEl, lastModifiedDateEl, filterArray) {
			for(var i in filterArray) {
				var filter = filterArray[i];
				
				if(filter.name == "creationDate" && creationDateEl != undefined) {
					creationDateEl.val(filter.term);
				}
				if(filter.name == "lastModifiedDate" && lastModifiedDateEl != undefined) {
					lastModifiedDateEl.val(filter.term);
				}
			}
		}

		$rootScope.saveGridState = function (view, gridApi, creationDateEl, lastModifiedDateEl) {
			if(view != undefined && gridApi != undefined && gridApi.saveState != undefined) {
				if($localStorage.gridStates == undefined) {
					$localStorage.gridStates = new Array();
				}
				var gridState = gridApi.saveState.save();
				var dateFilters = new Array();
				$rootScope.processDateFilters(creationDateEl, lastModifiedDateEl, dateFilters);
				
				$localStorage.gridStates[view] = {
					gridState: gridState,
					dateFilters: dateFilters
				};
			}
		}
		
		$rootScope.restoreGridState = function (view, gridApi, creationDateEl, lastModifiedDateEl) {
			if(view != undefined && $localStorage.gridStates != undefined && $localStorage.gridStates[view] != undefined
					&& gridApi != undefined && gridApi.saveState != undefined) {
				var state = $localStorage.gridStates[view];
				
				gridApi.saveState.restore(null, state.gridState);
				$rootScope.setDateFilters(creationDateEl, lastModifiedDateEl, state.dateFilters);
			}
		}
	});