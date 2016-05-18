angular.module('FundFinder')

.service('ArticlesService', function($http) {
	this.getPage = function(resource) {
		return $http.post('/api/v1/articles/page', resource);
	};
	this.activate = function(id) {
		return $http.get('/api/v1/articles/' + id + "/activate");
	};
	this.deactivate = function(id) {
		return $http.get('/api/v1/articles/' + id + "/deactivate");
	};
	this.delete = function(id) {
		return $http.delete('/api/v1/articles/' + id);
	};
	this.getRevisions = function(id) {
		return $http.get('/api/v1/articles/' + id + "/revisions");
	};
});