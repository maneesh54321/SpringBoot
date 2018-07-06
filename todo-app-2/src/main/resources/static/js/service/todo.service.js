(function () {

	"use strict";

	var todoApp = angular.module('todo-app');

	todoApp.factory('todoService', ['$http','$rootScope', function ($http,$rootScope) {

		var todos = [];

		return { getTodos, getTodoByIndex, updateTodos, addTodo, deleteTodo };
		
		function getTodos(){
			return todos;
		}
		
		function getTodoByIndex(index){
			return todos[index];
		}
		
		function updateTodos() {
			$http.get('/todos').then(function (res) {
				todos = res.data;
				$rootScope.$broadcast('updateTodos');
			}, function (err) { 
				console.log(err);
			});
		}
		
		function addTodo(todo){
			$http.post('/todos',todo).then(function(res){
				updateTodos();
			},function(err){});
		}
		
		function deleteTodo(id){
			$http.delete('/todos/'+id).then(function(res){
				updateTodos();
			},function(err){});
		}
	}]);
}());