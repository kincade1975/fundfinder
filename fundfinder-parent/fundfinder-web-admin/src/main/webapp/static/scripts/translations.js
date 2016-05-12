function config($translateProvider) {
	$translateProvider
		.translations('hr', {
			LANGUAGE: 'Jezik',
			ENGLISH: 'Engleski',
			CROATIAN: 'Hrvatski',
			LOGOUT: 'Odjava',
			HEADER_MAIN: 'ADMINISTRACIJSKA KONZOLA',
			MENU_INVESTMENTS: 'Investicije',
			MENU_ARTICLES: 'Kako do sredstava',
			LOADING_DATA: 'Učitavam podatke...',
			NO_DATA: 'Nema podataka',
			COLUMN_ID: 'ID',
			COLUMN_TITLE: 'Naziv',
			COLUMN_STATUS: 'Status',
			COLUMN_CREATION_DATE: 'Kreiran',
			COLUMN_LAST_MODIFIED_DATE: 'Zadnja promjena',
			STATUS_ACTIVE: 'AKTIVAN',
			STATUS_INACTIVE: 'NEAKTIVAN',
		})
		.translations('en', {
			LANGUAGE: 'Language',
			ENGLISH: 'English',
			CROATIAN: 'Croatian',
			LOGOUT: 'Log out',
			HEADER_MAIN: 'ADMINISTRATION CONSOLE',
			MENU_INVESTMENTS: 'Investments',
			MENU_ARTICLES: 'Articles',
			LOADING_DATA: 'Loading Data...',
			NO_DATA: 'No Data',
			COLUMN_ID: 'ID',
			COLUMN_TITLE: 'Title',
			COLUMN_STATUS: 'Status',
			COLUMN_CREATION_DATE: 'Creation Date',
			COLUMN_LAST_MODIFIED_DATE: 'Last Modified Date',
			STATUS_ACTIVE: 'ACTIVE',
			STATUS_INACTIVE: 'INACTIVE',
		});
	$translateProvider.preferredLanguage("hr");
};

angular.module('FundFinder').config(config);
