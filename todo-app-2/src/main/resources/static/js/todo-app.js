(function () {

	"use strict";

	var todoApp = angular.module('todo-app', ['ui.router']);

	todoApp.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

		$stateProvider.state('login', {
			url: '/login',
			templateUrl: '../templates/login.template.htm',
			controller: 'loginController',
			controllerAs: 'loginCtrl'
		});

		$urlRouterProvider.otherwise('/login');
	}]);

	todoApp.controller('loginController', ['$state', 'authService', function ($state, authService) {

		var self = this;

		self.credentials = {};

		self.login = function () {
			authService.authenticate(self.credentials, function (authenticated) {
				if (authenticated) {
					// $state.go('todos');
				} else {
					// stay on this route
				}
			});
		};
	}]);
}());