var fatApp = angular.module('fatApp', []);

fatApp.controller('DaysMealsController', function DaysMealsController($scope, $http, foodService) {

	// var webapiURL = 'http://fatappenv.eu-central-1.elasticbeanstalk.com/webapi/';
    var webapiURL = 'http://localhost:8080/webapi/';
    var foodSearchURI = webapiURL + 'foods?keyword=';
	var postFoodUrl = webapiURL + 'users/1/meals';
	$scope.currentDate = today();

	var currentFoodMeasures = null;

    $scope.currentFood = null;
    $scope.selectedMeasure = null;

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

	$scope.searchFood = function($event, keyword, type){
        var searchUrl = foodSearchURI + keyword;

        $http.get(searchUrl).
        then(function(response) {

            if (type == 1)
                $scope.searchResultB = response.data;
            else if (type == 2)
                $scope.searchResultL = response.data;
            else if (type == 3)
                $scope.searchResultD = response.data;
            else if (type == 4)
                $scope.searchResultS = response.data;
        });
	}

	$scope.alert = function(text) {
		alert(text);
	}

	$scope.showNutritients = function (foodId) {
        foodService.async(foodId).then(function(d){
            generateTableFromFoodData(d.groupedNutrients, d.measures[0], d.measures[0].qty);
            $scope.currentFood = d;
            if (d.measures[0]){
                $scope.selectedMeasure = d.measures[0];
			}else {
                $scope.selectedMeasure = {
                	qty: 100,
					label: "g",
					eqv: 100
				}
			}
            currentFoodMeasures = d.measures;
        });
    };

	$scope.updateFoodMeasure = function() {
		if($scope.selectedMeasure.qty > 0){
			var measure;
			for(var i = 0; i < currentFoodMeasures.length; i++){
            	if(currentFoodMeasures[i].label == $scope.selectedMeasure.label){
            		measure = currentFoodMeasures[i];
				}
			}
            generateTableFromFoodData($scope.currentFood.groupedNutrients, measure, $scope.selectedMeasure.qty);
		}
	};

	var generateTableFromFoodData = function(foodGroupedData, measure, newQuantity) {
		var table = '<table border="1"><thead><th>Nutrient</th><th>Unit</th><th>Value</th></thead><tbody>'

        for (var i = 0; i < foodGroupedData.length; i++){
            table += '<tr colspan="3" ><td>' + foodGroupedData[i].groupName + '</td></tr>';
            var iNutrient = foodGroupedData[i];
            for (var y = 0; y < iNutrient.nutrient.length; y++){
                table += '<tr><td>' + iNutrient.nutrient[y].name + '</td>'
                    + '<td>' + iNutrient.nutrient[y].unit + '</td>'
                    + '<td>' + calculateNutritientValue(measure, iNutrient.nutrient[y], newQuantity) + '</td>'
                    + '</tr>';
            }
        }
        table += '</tbody></table>'
        console.log(table);
	};

	var calculateNutritientValue = function(measure, nutrient, newQuantity){
		if (!measure)
			measure = {
				eqv: 1,
				qty: 100
			};
		var result = (newQuantity / measure.qty) * (measure.eqv * nutrient.value / 100);
		return Math.round(result * 100) / 100;
	};

	$scope.addSelectedFood = function(){
        // //    private int userId;
        // private int foodId;
        // private float quantity;
        // private int unitRef;
        // private int day;
        // private int mealTypeRef;
		var data = {
			userId: 1,
			foodId: 45111453,
			quantity: 100,
			unit: "g",
			day: 20161208,
			mealTypeRef: 1
		};
		var config = {};

		$http.post(postFoodUrl, data, config);
	};

});


fatApp.factory('foodService', function($http) {

    var webapiURL = 'http://localhost:8080/webapi/';
    var getFoodURI = webapiURL + 'foods/';

    var promise;
    var foodService = {
        async: function(foodId) {

                promise = $http.get(getFoodURI + foodId).then(function (response) {
                    return response.data;
                });
            return promise;
        }
    };
    return foodService;
});
