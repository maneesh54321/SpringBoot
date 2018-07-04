(function(){
	var app=angular.module('todo-app');
	
	app.service('authService',['$http',function($http,$rootScope){
		var _authenticated=false;
		this.authenticate = function(credentials, callback) {
			var headers = credentials ? {
				authorization : "Basic "
						+ btoa(credentials.username + ":"
								+ credentials.password)
			} : {};

			$http.get('user', {
				headers : headers
			}).then(function(response) {
				if (response.data.name) {
					_authenticated = true;
				} else {
					_authenticated = false;
				}
				callback && callback(_authenticated);
			}, function() {
				_authenticated = false;
				callback && callback(false);
			});
		}
		
		this.logout=function(){
			 return $http.post('logout', {}).then(function(res) {
				_authenticated = false;
				return;
			});
		}
		
		this.isAuthenticated=function(){
			return _authenticated;
		}
	}]);
}());