(function () {

	"use strict";

	var todoApp = angular.module('todo.app');

	todoApp.factory('todoService', ['$http', '$rootScope', function ($http, $rootScope) {

		var todos = [];

		return { getTodos, getTodoByIndex, refreshTodos, updateTodo, addTodo, deleteTodo };

		function getTodos() {
			return todos;
		}

		function getTodoByIndex(index) {
			return angular.copy(todos[index]);
		}

		function refreshTodos() {
			$http.get('/todos').then(function (res) {
				todos = res.data;
				$rootScope.$broadcast('updateTodos');
			}, function (err) {
				console.log(err);
			});
		}

		function updateTodo(todo) {
			return $http.put('/todos', todo);
		}

		function addTodo(todo) {
			return $http.post('/todos', todo);
		}

		function deleteTodo(id) {
			$http.delete('/todos/' + id).then(function (res) {
				refreshTodos();
			}, function (err) { });
		}
		
	}]);
}());