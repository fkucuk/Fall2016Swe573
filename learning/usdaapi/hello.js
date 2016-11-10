angular.module('demo', [])
.controller('Hello', function($scope, $http) {
	
	var keyword = 'butter';
	
	var url = 'http://api.nal.usda.gov/ndb/search/?format=json&q=' + keyword + '&sort=n&max=25&offset=0&api_key=DEMO_KEY';

	
	
	$scope.setUrl = function(keyword) {
        url = 'http://api.nal.usda.gov/ndb/search/?format=json&q=' + keyword + '&sort=n&max=25&offset=0&api_key=DEMO_KEY';
		
		    $http.get(url).
        then(function(response) {
            $scope.greeting = response.data;
        });
    };

});


