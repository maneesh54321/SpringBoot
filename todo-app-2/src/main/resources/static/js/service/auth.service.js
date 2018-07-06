(function () {

	"use strict";

	var todoApp = angular.module('todo-app');

	todoApp.factory('authService', ['$http', function ($http) {

		var _authenticated = false;

		return { authenticate, logout, isAuthenticated };

		function authenticate(credentials, callback) {
			var headers = credentials ? { authorization: "Basic " + btoa(credentials.username + ":" + credentials.password) } : {};
			$http.get('/user', { headers: headers }).then(function (res) {
				if (res.data.name) {
					_authenticated = true;
				} else {
					console.log('failed to login!!');
					_authenticated = false;
				}
				callback && callback(_authenticated);
			}, function (err) {
				console.log('failed to login: ' + err);
				_authenticated = false;
				callback && callback(_authenticated);
			});
		}
		
		function logout(){
			return $http.post('logout', {}).then(function(res) {
				_authenticated = false;
				return;
			});
		}

		function isAuthenticated() {
			return _authenticated;
		}
	}])
}());