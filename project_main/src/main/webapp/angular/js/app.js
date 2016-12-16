var fatApp = angular.module('fatApp', []);

fatApp.controller('DaysMealsController', function DaysMealsController($scope) {
	$scope.test = "bunu yazan ";
	var meals =  [
		{
			mealDay: 20161211,
			mealId: 8,
			mealType: "BREAKFAST",
			foodConsumptions: [
				{ food: {
					id: 1,
					jsonData: "{ sample json data }",
					name: "Apple"
				},
				quantity: 100,
				unit: "g"
		},
				{ food: {
					id: 1,
					jsonData: "{ sample json data }",
					name: "Apple"
				},
					quantity: 100,
					unit: "g"
				}]}];

	$scope.breakfast = meals[0];



});