(function () {
	var todoApp = angular.module('todo-app');
	todoApp.factory('todoService', ['$http','$rootScope', function ($http,$rootScope) {
		var todos = [];
		return {
			getTodos,
			updateTodos,
			getTodoByIndex,
			addTodo,
			deleteTodo,
			updateTodo
		};
		function getTodos() {
			return todos;
		};
		function updateTodos() {
			$http.get("http://localhost:8080/todos/").then(function (res) {
				todos=res.data;
				$rootScope.$broadcast('updateTodos');
			}, function (err) {
				console.log(err);
			});
		};
		function getTodoByIndex(index) {
			return angular.copy(todos[index]);
		};
		function addTodo(todo) {
			return $http.post("http://localhost:8080/todos/add", todo);
		};
		function deleteTodo(index) {
			return $http.delete("http://localhost:8080/todos/" + index);
		};
		function updateTodo(todo) {
			return $http.put("http://localhost:8080/todos/",todo);
		};
	}]);
}());