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
			
			HEADER_ARTICLES_DETAILS: 'Detalji članka',
			
			TAB_INFO: 'Osnovne informacije',
			TAB_STATISTICS: 'Statistika',
			TAB_REVISION_HISTORY: 'Povijest izmjena',
			
			UI_GRID_LOADING_DATA: 'Učitavam podatke...',
			UI_GRID_NO_DATA: 'Nema podataka',
			
			COLUMN_ID: 'ID',
			COLUMN_TITLE: 'Naziv',
			COLUMN_STATUS: 'Status',
			COLUMN_CREATION_DATE: 'Kreiran',
			COLUMN_LAST_MODIFIED_DATE: 'Zadnja promjena',
			COLUMN_REVISION: 'Revizija',
			COLUMN_REVISION_DATE: 'Datum',
			COLUMN_REVISION_USER: 'Korisnik',
			COLUMN_REVISION_TYPE: 'Tip',

			DATETIMEPICKER_TODAY: 'Danas',
			DATETIMEPICKER_YESTERDAY: 'Jučer',
			DATETIMEPICKER_LAST_7_DAYS: 'Zadnjih 7 dana',
			DATETIMEPICKER_LAST_30_DAYS: 'Zadnjih 30 dana',
			DATETIMEPICKER_THIS_MONTH: 'Ovaj mjesec',
			DATETIMEPICKER_LAST_MONTH: 'Prošli mjesec',
			DATETIMEPICKER_CUSTOM: 'Proizvoljan period',
			
			STATUS_ACTIVE: 'AKTIVAN',
			STATUS_INACTIVE: 'NEAKTIVAN',
			
			REVISION_TYPE_ADD: 'ADD',
			REVISION_TYPE_MOD: 'MOD',
			REVISION_TYPE_DEL: 'DEL',

			ENTITY_ARTICLE: 'Članak',
			ENTITY_ARTICLE_GEN: 'članka',
			
			ACTION_TOOLTIP_ACTIVATE: 'Aktiviraj',
			ACTION_TOOLTIP_DEACTIVATE: 'Deaktiviraj',
			ACTION_TOOLTIP_EDIT: 'Uredi',
			ACTION_TOOLTIP_DELETE: 'Obriši',
			ACTION_TOOLTIP_DETAILS: 'Detalji',
			
			ACTION_ACTIVATE_SUCCESS_MESSAGE: '{{entity}} je uspješno aktiviran',
			ACTION_ACTIVATE_FAILURE_MESSAGE: 'Došlo je do pogreške prilikom aktiviranja {{entity}}',
			ACTION_DEACTIVATE_SUCCESS_MESSAGE: '{{entity}} je uspješno deaktiviran',
			ACTION_DEACTIVATE_FAILURE_MESSAGE: 'Došlo je do pogreške prilikom deaktiviranja {{entity}}',
			ACTION_DELETE_SUCCESS_MESSAGE: '{{entity}} je uspješno obrisan',
			ACTION_DELETE_FAILURE_MESSAGE: 'Došlo je do pogreške prilikom brisanja {{entity}}',
			ACTION_LOAD_FAILURE_MESSAGE: 'Došlo je do pogreške prilikom dohvaćanja podataka',
			
			DIALOG_DELETE_HEADER: 'Obriši {{entity}}',
			DIALOG_DELETE_MESSAGE: 'Da li doista želite obrisati {{entity}}?',
			
			BUTTON_YES: 'Da',
			BUTTON_NO: 'Ne',
			BUTTON_BACK: 'Natrag',
		})
		.translations('en', {
			LANGUAGE: 'Language',
			ENGLISH: 'English',
			CROATIAN: 'Croatian',
			LOGOUT: 'Log out',
			HEADER_MAIN: 'ADMINISTRATION CONSOLE',
			
			MENU_INVESTMENTS: 'Investments',
			MENU_ARTICLES: 'Articles',
			
			HEADER_ARTICLES_DETAILS: 'Article details',
			
			TAB_INFO: 'Basic information',
			TAB_STATISTICS: 'Statistics',
			TAB_REVISION_HISTORY: 'Revision history',
			
			UI_GRID_LOADING_DATA: 'Loading data...',
			UI_GRID_NO_DATA: 'No Data',
			
			COLUMN_ID: 'ID',
			COLUMN_TITLE: 'Title',
			COLUMN_STATUS: 'Status',
			COLUMN_CREATION_DATE: 'Creation date',
			COLUMN_LAST_MODIFIED_DATE: 'Last modified date',
			COLUMN_REVISION: 'Revision',
			COLUMN_REVISION_DATE: 'Revision date',
			COLUMN_REVISION_USER: 'User',
			COLUMN_REVISION_TYPE: 'Revision type',
			
			DATETIMEPICKER_TODAY: 'Today',
			DATETIMEPICKER_YESTERDAY: 'Yesterday',
			DATETIMEPICKER_LAST_7_DAYS: 'Last 7 days',
			DATETIMEPICKER_LAST_30_DAYS: 'Last 30 days',
			DATETIMEPICKER_THIS_MONTH: 'This month',
			DATETIMEPICKER_LAST_MONTH: 'Last month',
			DATETIMEPICKER_CUSTOM: 'Custom range',
			
			STATUS_ACTIVE: 'ACTIVE',
			STATUS_INACTIVE: 'INACTIVE',
			
			REVISION_TYPE_ADD: 'ADD',
			REVISION_TYPE_MOD: 'MOD',
			REVISION_TYPE_DEL: 'DEL',
			
			ENTITY_ARTICLE: 'Article',
			ENTITY_ARTICLE_GEN: 'article',
			
			ACTION_TOOLTIP_ACTIVATE: 'Activate',
			ACTION_TOOLTIP_DEACTIVATE: 'Deactivate',
			ACTION_TOOLTIP_EDIT: 'Edit',
			ACTION_TOOLTIP_DELETE: 'Delete',
			ACTION_TOOLTIP_DETAILS: 'Details',
			
			ACTION_ACTIVATE_SUCCESS_MESSAGE: '{{entity}} successfully activated',
			ACTION_ACTIVATE_FAILURE_MESSAGE: 'Activating {{entity}} failed',
			ACTION_DEACTIVATE_SUCCESS_MESSAGE: '{{entity}} successfully deactivated',
			ACTION_DEACTIVATE_FAILURE_MESSAGE: 'Deactivating {{entity}} failed',
			ACTION_DELETE_SUCCESS_MESSAGE: '{{entity}} successfully deleted',
			ACTION_DELETE_FAILURE_MESSAGE: 'Deleting {{entity}} failed',
			ACTION_LOAD_FAILURE_MESSAGE: 'Retrieving data failed',
			
			DIALOG_DELETE_HEADER: 'Delete {{entity}}',
			DIALOG_DELETE_MESSAGE: 'Do you really want to delete {{entity}}?',
			
			BUTTON_YES: 'Yes',
			BUTTON_NO: 'No',
			BUTTON_BACK: 'Back',
		});
	
	$translateProvider.preferredLanguage("hr");
};

angular.module('FundFinder').config(config);
