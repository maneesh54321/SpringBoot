(function(){
	var todoApp=angular.module('todo-app',['ui.router']);
	
	todoApp.config(['$stateProvider','$urlRouterProvider',function($stateProvider,$urlRouterProvider){
		$stateProvider.state('login',{
			url:'/login',
			templateUrl:'../templates/login.template.html',
			controller:'loginController',
			controllerAs:'loginCtrl'
		});
		$urlRouterProvider.otherwise('/login');
	}]);
}());