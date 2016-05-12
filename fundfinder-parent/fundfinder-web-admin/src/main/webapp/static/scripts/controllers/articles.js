angular.module('FundFinder')
	.controller('ArticleOverviewController', ArticleOverviewController);

// ========================================================================
//	ARTICLES OVERVIEW CONTROLLER
// ========================================================================
function ArticleOverviewController($rootScope, $scope, $state, $log, $timeout, $filter, uiGridConstants, ArticlesService) {
	var $translate = $filter('translate');
	
	$scope.gridOptions = {
			paginationPageSizes: [10, 20, 30, 50],
			enableFiltering: true,
			useExternalFiltering: true,
			useExternalSorting: true,
			useExternalPagination: true,
			rowHeight: 32,
			enableColumnMenus: false,
			enableHorizontalScrollbar: uiGridConstants.scrollbars.NEVER,
			enableVerticalScrollbar: uiGridConstants.scrollbars.NEVER,
			enableGridMenu: true,
			columnDefs: [
				{
					displayName: $translate('COLUMN_ID'),
					field: 'id',
					type: 'number',
					cellTooltip: false, 
					enableSorting: true,
					enableFiltering: true,
					filterHeaderTemplate: 
						'<div class="ui-grid-filter-container" ng-repeat="colFilter in col.filters">' +
							'<input type="number" min="0" class="ui-grid-filter-input ui-grid-filter-input-0 ng-dirty ng-valid-parse ng-touched ui-grid-filter-input-port" ng-model="colFilter.term" ng-attr-placeholder="{{colFilter.placeholder || \'\'}}" aria-label="Filter for column" placeholder="">' + 
						'</div>', 
					enableHiding: true,
					visible: false,
					width: 60
				},        
				{
					displayName: $translate('COLUMN_TITLE'),
					field: 'title',
					type: 'string',
					cellTooltip: false, 
					enableSorting: true,
					enableFiltering: true,
					enableHiding: false
				},
				{
					displayName: $translate('COLUMN_STATUS'),
					field: 'status',
					type: 'string',
					cellTooltip: false, 
					enableSorting: true,
					enableFiltering: true,
					filter: {
						type: uiGridConstants.filter.SELECT,
						disableCancelFilterButton: true,
						selectOptions: [
							{ value: 'ACTIVE', label: $translate('STATUS_ACTIVE') },
							{ value: 'INACTIVE', label: $translate('STATUS_INACTIVE') }]
					},
					enableHiding: false,
					width: 100,
					cellTemplate:
						'<div ng-show="row.entity.status == \'ACTIVE\'" class="ui-grid-cell-contents"><span class="badge badge-primary"><small>{{\'STATUS_ACTIVE\' | translate}}</small></span></div>' + 
						'<div ng-show="row.entity.status == \'INACTIVE\'" class="ui-grid-cell-contents"><span class="badge badge-danger"><small>{{\'STATUS_INACTIVE\' | translate}}</small></span></div>'
				},
				{
					displayName: $translate('COLUMN_CREATION_DATE'),
					field: 'creationDate',
					type: 'date',
					cellFilter: 'date:grid.appScope.dateTimeFormat',
					cellTooltip: false, 
					enableSorting: true,
					enableFiltering: true,
					filterHeaderTemplate: 
						'<div class="ui-grid-filter-container" ng-repeat="colFilter in col.filters">' +
							'<div class="input-group">' +
								'<input id="filterCreationDate" date-range-picker options="grid.appScope.dpOptions" class="form-control date-picker ui-grid-filter-datepicker" type="text" readonly="readonly" style="background: white;" ng-model="grid.appScope.dpCreationDate" />' +
								'<span class="input-group-addon ui-grid-filter-datepicker-span" ng-click="grid.appScope.clearDateFilter(\'filterCreationDate\')"><i class="ui-grid-icon-cancel ui-grid-filter-datepicker-i"></i></span>' +
							'</div>' + 
						'</div>',
					enableHiding: true,
					visible: false,
					width: 175
				},
				{
					displayName: $translate('COLUMN_LAST_MODIFIED_DATE'),
					field: 'lastModifiedDate',
					type: 'date',
					cellFilter: 'date:grid.appScope.dateTimeFormat',
					cellTooltip: false, 
					enableSorting: true,
					enableFiltering: true,
					filterHeaderTemplate: 
						'<div class="ui-grid-filter-container" ng-repeat="colFilter in col.filters">' +
							'<div class="input-group">' +
								'<input id="filterLastModifiedDate" date-range-picker options="grid.appScope.dpOptions" class="form-control date-picker ui-grid-filter-datepicker" type="text" readonly="readonly" style="background: white;" ng-model="grid.appScope.dpLastModifiedDate" />' +
								'<span class="input-group-addon ui-grid-filter-datepicker-span" ng-click="grid.appScope.clearDateFilter(\'filterLastModifiedDate\')"><i class="ui-grid-icon-cancel ui-grid-filter-datepicker-i"></i></span>' +
							'</div>' + 
						'</div>',
					enableHiding: true,
					width: 175
				},
				{
					name: ' ',
					type: 'string',
					cellTooltip: false, 
					enableSorting: false,
					enableFiltering: false,
					enableHiding: false,
					width: 126,
					cellTemplate:
						'<div style="padding-top: 1px">' +
						'<button uib-tooltip="Activate" tooltip-append-to-body="true" ng-show="row.entity.status == \'INACTIVE\'" ng-click="grid.appScope.activateNetwork(row.entity)" class="btn-xs btn-white m-l-xs"><i class="fa fa-2x fa-toggle-off"></i></button>' + 
						'<button uib-tooltip="Deactivate" tooltip-append-to-body="true" ng-show="row.entity.status == \'ACTIVE\'" ng-click="grid.appScope.deactivateNetwork(row.entity)" class="btn-xs btn-white m-l-xs"><i class="fa fa-2x fa-toggle-on"></i></button>' +
						'<button uib-tooltip="Edit" tooltip-append-to-body="true" ng-click="grid.appScope.editNetwork(row.entity)" class="btn-xs btn-white m-l-xs"><i class="fa fa-2x fa-edit"></i></button>' +
						'<button uib-tooltip="Delete" tooltip-append-to-body="true" ng-click="grid.appScope.deleteNetwork(row.entity)" class="btn-xs btn-white m-l-xs"><i class="fa fa-2x fa-times"></i></button>' + 
						'</div>'
				}
			],
			onRegisterApi: function(gridApi) {
				$scope.gridApi = gridApi;
				
				// register pagination changed handler
				$scope.gridApi.pagination.on.paginationChanged($scope, function(currentPage, pageSize) {
					$scope.getPage(currentPage, pageSize);
				});
				
				// register sort changed handler 
				$scope.gridApi.core.on.sortChanged($scope, $scope.sortChanged);
				
				// register filter changed handler
				$scope.gridApi.core.on.filterChanged($scope, function() {
					var grid = this.grid;
					
					var filterArray = new Array();
					for (var i=0; i<grid.columns.length; i++) {
						var name = grid.columns[i].field;
						var term = grid.columns[i].filters[0].term;
						if (name && term) {
							filterArray.push({
								"name" : name,
								"term" : term
							});
						}
					}
					
					// date filters (e.g. creationDate, lastModifiedDate)
					$rootScope.processDateFilters($('#filterCreationDate'), $('#filterLastModifiedDate'), filterArray);
					
					$scope.filterArray = filterArray;
					$scope.getPage($scope.gridApi.pagination.getPage(), $scope.gridOptions.paginationPageSize);
				});
				
				$scope.gridApi.grid.clearAllFilters = function() {
					this.columns.forEach(function(column) {
						column.filters.forEach(function(filter) {
							filter.term = undefined;
						});
					});
					$scope.clearDateFilter('filterCreationDate');
					$scope.clearDateFilter('filterLastModifiedDate');
				};
			}
		};
	
	$scope.getPage = function(page, size) {
		// save current state
		$rootScope.saveGridState($state.current.name, $scope.gridApi, $('#filterCreationDate'), $('#filterLastModifiedDate'));
		
		var uiGridResource = {
			"pagination" : {
				"page" : page - 1,
				"size" : size
			},
			"sort" : $scope.sortArray,
			"filter" : $scope.filterArray
		};
		
		ArticlesService.getPage(uiGridResource)
			.success(function(data, status, headers, config) {
				$scope.loading = false;
				if (status == 200) {
					$scope.gridOptions.data = data.data;
					$scope.gridOptions.totalItems = data.total;
	
					var newHeight = data.data.length * 32 + (($scope.gridOptions.totalItems == 0) ? 380 : 92);
					angular.element(document.getElementsByClassName('grid')[0]).css('height', newHeight + 'px');
				} else {
					$log.error(data);
					toastr.error('Retrieving articles failed');
				}
			})
			.error(function(data, status, headers, config) {
				$scope.loading = false;
				toastr.error('Retrieving articles failed');
			});
	};
	
	$scope.sortChanged = function (grid, sortColumns) {
		var sortArray = new Array();
		for (var i=0; i<sortColumns.length; i++) {
			sortArray.push({
				"name" : sortColumns[i].field,
				"priority" : sortColumns[i].sort.priority,
				"direction" : sortColumns[i].sort.direction
			});
		}
		$scope.sortArray = sortArray;
		$scope.getPage($scope.gridApi.pagination.getPage(), $scope.gridOptions.paginationPageSize);
	};
	
	// date picker options
	$scope.dpOptions = {
		opens: 'left',
		format: $rootScope.dateFormat.toUpperCase(),
		ranges: {
			'Today': [moment(), moment()],
			'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
			'Last 7 Days': [moment().subtract(6, 'days'), moment()],
			'Last 30 Days': [moment().subtract(29, 'days'), moment()],
			'This Month': [moment().startOf('month'), moment().endOf('month')],
			'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
		},
		showDropdowns: true,
		eventHandlers: {
			'apply.daterangepicker': function(ev, picker) {
				$scope.applyDateFilter();
			}
		}
	};
	
	/**
	 * Function clears filter of the given date range picker field.
	 */
	$scope.clearDateFilter = function(field) {
		$('#' + field).val(null);
		$scope.applyDateFilter();	
	};
	
	/**
	 * Function applies date filters.
	 */
	$scope.applyDateFilter = function() {
		setTimeout(function () {
			$scope.$apply(function() {
				$scope.gridApi.core.raise.filterChanged();
			});
		}, 100);
	};
	
	// initial load
	$scope.loading = true;
		
	$timeout(function() {
		//restore saved grid state
		$rootScope.restoreGridState($state.current.name, $scope.gridApi, $('#filterCreationDate'), $('#filterLastModifiedDate'));
		$scope.applyDateFilter();
		
		//load page
		$scope.getPage($scope.gridApi.grid.options.paginationCurrentPage, $scope.gridApi.grid.options.paginationPageSize);
	}, 1000);
};
