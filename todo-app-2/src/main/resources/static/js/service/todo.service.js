(function () {

	"use strict";

	var todoApp = angular.module('todo-app');

	todoApp.factory('todoService', ['$http', function ($http) {

		var todos = [];

		return { updateTodos };

		function updateTodos() {
			$http.get('/todos').then(function (res) {
				todos = res.data;
			}, function (err) { });
		}
	}]);
}());