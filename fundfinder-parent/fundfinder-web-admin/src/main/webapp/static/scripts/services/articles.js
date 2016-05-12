angular.module('FundFinder')

.service('ArticlesService', function($http) {
	this.getPage = function(resource) {
		return $http.post('/api/v1/articles/page', resource);
	};
});