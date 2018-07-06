(function () {

	"use strict";

	var todoApp = angular.module('todo-app', ['ui.router']);
	
	todoApp.config(['$httpProvider',function($httpProvider){
		$httpProvider.defaults.headers.common['X-Requested-With']='XMLHttpRequest';
	}]);

	todoApp.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

		$stateProvider.state('login', {
			url: '/login',
			templateUrl: '../templates/login.template.htm',
			controller: 'loginController',
			controllerAs: 'loginCtrl'
		}).state('home',{
			views:{
				'@': {
					templateUrl:'../templates/main-layout.template.htm'
				},
				'header@home':{
					templateUrl:'../templates/header.template.htm',
					controller:'headerController',
					controllerAs:'headerCtrl'
				},
				'footer@home':{
					templateUrl:'../templates/footer.template.htm'
				}
			}
		}).state('home.todos',{
			url:'/todos',
			views:{
				'content@home':{
					templateUrl:'../templates/todos.template.htm',
					controller:'todosController',
					controllerAs:'todosCtrl'
				}
			}
		}).state('home.updateTodo',{
			url:'/todos/:id',
			views:{
				'content@home':{
					templateUrl:'../templates/update-todo.template.htm',
					controller:'updateTodoController',
					controllerAs:'updateTodoCtrl'
				}
			}
		});

		$urlRouterProvider.otherwise('/login');
	}]);

	todoApp.controller('loginController', ['$state', 'authService', function ($state, authService) {

		var self = this;

		self.credentials = {};

		self.login = function () {
			authService.authenticate(self.credentials, function (authenticated) {
				if (authenticated) {
					$state.go('home.todos');
				} else {
					// stay on this route
				}
			});
		};
	}]);
	
	todoApp.controller('headerController',['$state','authService',function($state,authService){
		var self=this;
		
		self.logout = function() {	
			authService.logout().then(function(res){
				$state.go("login");
			});			
		}
	}]);
	
	todoApp.controller('todosController',['$state','todoService','$scope',function($state,todoService,$scope){
		
		var self = this;
		
		self.todos=[];
		
		$scope.$on('updateTodos',function(evt,data){
			self.todos=todoService.getTodos();
		});
		
		todoService.updateTodos();
		
		self.todo={};
		
		self.addTodo=function(){
			todoService.addTodo(self.todo);
			todoService.updateTodos();
		}
		
		self.deleteTodo=function(id){
			todoService.deleteTodo(id);
			todoService.updateTodos();
		}
		
	}]);
	
	todoApp.controller('updateTodoController',['$state','todoService',function($state,todoService){
		
		var self=this;
		
		self.todo=todoService.getTodoByIndex($state.params.id);
		
		self.updateTodo=function(){
			$state.go('home.todos');
		}
	}]);
}());