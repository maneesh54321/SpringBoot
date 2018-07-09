(function () {

	"use strict";

	var todoApp = angular.module('todo.app', ['ui.router']);
	
	todoApp.run(['$transitions', 'authService', function ($transitions, authService) {
		$transitions.onBefore({ to: 'home.**' }, function (trans) {
			if (!authService.isAuthenticated()) {
				// User isn't authenticated. Redirect to a new Target State
				return trans.router.stateService.target('login');
			}
		});
	}]);

	todoApp.config(['$httpProvider', function ($httpProvider) {
		$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
	}]);

	todoApp.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

		$stateProvider.state('login', {
			url: '/login',
			templateUrl: '../templates/login.template.htm',
			controller: 'LoginController',
			controllerAs: 'loginCtrl'
		}).state('home', {
			views: {
				'@': {
					templateUrl: '../templates/main-layout.template.htm'
				},
				'header@home': {
					templateUrl: '../templates/header.template.htm',
					controller: 'HeaderController',
					controllerAs: 'headerCtrl'
				},
				'footer@home': {
					templateUrl: '../templates/footer.template.htm'
				}
			}
		}).state('home.todos', {
			url: '/todos',
			views: {
				'content@home': {
					templateUrl: '../templates/todos.template.htm',
					controller: 'TodosController',
					controllerAs: 'todosCtrl'
				}
			}
		}).state('home.updateTodo', {
			url: '/todos/:id',
			views: {
				'content@home': {
					templateUrl: '../templates/update-todo.template.htm',
					controller: 'UpdateTodoController',
					controllerAs: 'updateTodoCtrl'
				}
			}
		});

		$urlRouterProvider.otherwise('/login');
	}]);

	todoApp.controller('LoginController', ['$state', 'authService', function ($state, authService) {

		var self = this;

		self.credentials = {};

		authService.authenticate();

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

	todoApp.controller('HeaderController', ['$state', 'authService', function ($state, authService) {
		
		var self = this;

		self.logout = function () {
			authService.logout().then(function (res) {
				$state.go("login");
			});
		}
	}]);

	todoApp.controller('TodosController', ['$state', 'todoService', '$scope', function ($state, todoService, $scope) {

		var self = this;

		self.todos = [];

		$scope.$on('updateTodos', function (evt, data) {
			self.todos = todoService.getTodos();
		});

		todoService.refreshTodos();

		self.todo = {};

		self.addTodo = function () {
			todoService.addTodo(self.todo).then(function (res) {
				todoService.refreshTodos();
				self.todo = {};
			},function(err){
				console.log('Unable to add todo!!',err);
			});
		}

		self.deleteTodo = function (id) {
			todoService.deleteTodo(id);
		}

	}]);

	todoApp.controller('UpdateTodoController', ['$state', 'todoService', function ($state, todoService) {

		var self = this;

		self.todo = todoService.getTodoByIndex($state.params.id);

		self.updateTodo = function () {
			todoService.updateTodo(self.todo).then(function(res){
				$state.go('home.todos');
			},function(err){
				console.log('Unable to update todo!!',err);
			});
			
		}
	}]);
}());