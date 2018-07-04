(function () {
	
	var todoApp = angular.module('todo-app', ['ui.router']);
	
	
	todoApp.run(['$transitions',function($transitions) {
		  $transitions.onBefore({ to: 'todos.**'}, function(trans) {
		    var auth = trans.injector().get('authService');
		    if (!auth.isAuthenticated()) {
		      // User isn't authenticated. Redirect to a new Target State
		      return trans.router.stateService.target('login');
		    }
		  });
		}]);
	
	todoApp.config(['$httpProvider',function($httpProvider){
		$httpProvider.defaults.headers.common['X-Requested-With']='XMLHttpRequest';
	}]);
	
	todoApp.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
		$stateProvider.state('login', {
			url: '/login',
			templateUrl: './templates/login.template.htm',
			controller: 'loginController',
			controllerAs: 'controller'
		}).state('todos', {
			views: {
				'@': {
					templateUrl: './templates/home-layout.template.htm'
				},
				'header@todos': {
					templateUrl: './templates/header.template.htm',
					controller: 'headerController',
					controllerAs:'controller'
				},
				'todo-table@todos': {
					templateUrl: './templates/todo-table.template.htm',
					controller: 'todosController'
				},
				'footer@todos': {
					templateUrl: './templates/footer.template.htm'
				}
			}
		}).state('todos.add',{
			url:'/todos/add',
			views:{
				'todo@todos':{
					templateUrl: './templates/add-todo.template.htm',
					controller: 'addTodoController'
				}
			}
		}).state('todos.edit',{
			url:'/todos/edit/:id',
			views:{
				'todo@todos': {
					templateUrl: './templates/update-todo.template.htm',
					controller: 'editTodoController'
				}
			}
		});
		
		$urlRouterProvider.otherwise('/login');
	}]);
	
	todoApp.controller('headerController',['$state','authService',function($state,authService){
		
		var self=this;
		
		self.logout = function() {	
			authService.logout().then(function(res){
				$state.go("login");
			});			
		}
	}]);

	todoApp.controller('loginController', ['$state','authService', function ($state,authService) {
		
		var self = this;

		authService.authenticate(null,function(authenticated) {
			if (authenticated) {
				console.log("Already logged in!!");
				$state.go("todos.add");
			}
		});

		self.credentials = {};
		
		self.login = function() {
			authService.authenticate(self.credentials, function(authenticated) {
				if (authenticated) {
					console.log("Login succeeded")
					$state.go("todos.add");
					self.error = false;
				} else {
					console.log("Login failed")
					$state.go("login");
					self.error = true;
				}
			})
		};
	}]);

	todoApp.controller('todosController', ['$scope', 'todoService', function ($scope, todoService) {
		
		todoService.updateTodos();
		
		$scope.deleteTodo = function (id) {
			todoService.deleteTodo(id).then(function (res) {
				todoService.updateTodos();
				$scope.todos=todoService.getTodos();
			});
		};
		
		$scope.$on('updateTodos',function(evt,data){
			$scope.todos=todoService.getTodos();
		});
	}]);

	todoApp.controller('addTodoController', ['$scope', 'todoService', function ($scope, todoService) {
		
		$scope.todo = {};
		
		$scope.addTodo = function () {
			$scope.todo.status = 'Pending';
			todoService.addTodo($scope.todo).then(function (res) {
				todoService.updateTodos();
				$scope.todo = {};
			});
		};
	}]);


	todoApp.controller('editTodoController', ['$scope', '$state', 'todoService', function ($scope, $state, todoService) {

		var index = Number($state.params.id);

		$scope.todo = todoService.getTodoByIndex(index);

		$scope.updateTodo = function () {
			todoService.updateTodo($scope.todo).then(function(res){
				todoService.updateTodos();
				$state.go('todos.add');
			});			
		}
	}]);
}());
