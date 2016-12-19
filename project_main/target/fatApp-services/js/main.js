

var exerciseSearchUrl = undefined;
var exerciseDetailRequestUrl = undefined;


//var foodDetailRequestUrl = "webapi/foods/";
var foodSearchUrl = "http://api.nal.usda.gov/ndb/search?format=json&sort=r&max=100&api_key=omcaFN9P4v5xb3l2VM7EqPyxwWRPjkg31EivJ4Jb";
var logFoodUrl = "webapi/users/1/meals";
var activityUrl = "webapi/activities";
var exerciseSubmitUrl = "webapi/users/1/activity";
var getDateRequestUrl = "webapi/users/1/"; // +YYYYMMDD


function _2Decimals(num) {
  return Math.round(num * 100) / 100;
}

function getFormattedDate() {
  return (currentDate.getFullYear() + ('0' + (currentDate.getMonth() + 1)).slice(-
    2) + ('0' + currentDate.getDate()).slice(-2));
}
historyArray = [];
futureArray = [];

$(document).on('click', '.add-food-button', function(e) {
  $(this).parent().append($('#add-food-panel'));
  $('#add-food-panel').slideToggle(300);
  //console.log(e);
});

$foodSearch = $('#food-search-input');

$foodSearch.on('change', searchFood);
//$('#search-button').on('click', searchFood);

function searchFood(e) {
    //var keyword = encodeURIComponent($('#food-search-input').val());
    var keyword = $('#food-search-input').val();
    //console.log(keyword);
   $.ajax({
      type: "GET",
      url: foodSearchUrl,
      data: {
        q: keyword
      },
      success: listResponse,
      error: function(results) {
        $.notify({
          message: 'error searching'
        }, {
          type: 'warning'
        });
      }
    });



  function listResponse(res) {
    if (res.errors) {
      $('.search-result-panel').slideUp();
    } else {
      $('.search-result-panel').slideDown();
      $list = $('.search-result-list');
      $list.find('li').slideUp('fast', function() {
        $(this).remove();
      });
      var listArray = res.list.item;
      $.each(listArray, function(index, item) {
        var li_element = document.createElement(
          'li');
        li_element.classList.add('list-group-item');
        li_element.setAttribute('data-food-db-no',
          item.ndbno);
        $(li_element).html(
          "<i class='fa fa-cutlery'></i><span>" +
          item.name + "</span>").
        find('span').addClass('food-label').css('display',
          'inline-block').
        css('width', '80%').parent().find('i').css('padding',
          '5px');

        $list.append(li_element);
        $(li_element).slideDown();
      });
    }
  }
}

$(document).on('click', '.search-result-list li', function(e) {
  //console.log($(this));
  $element = $(this);
  $val = $(this).text();
  $dbid = $(this).attr('data-food-db-no');
  if ($element.find('.nutrientList').length == 0 && $element.hasClass(
      'list-group-item')) {



      // var foodUrl =   foodDetailRequestUrl + $dbid;
      // $.ajax({
      //     url: foodUrl,
      //     type: 'GET',

$.ajax({
        url: 'https://api.nal.usda.gov/ndb/reports/',
        type: 'GET',
        data: {
          ndbno: $dbid,
          type: 'b',
          format: 'json',
          api_key: 'omcaFN9P4v5xb3l2VM7EqPyxwWRPjkg31EivJ4Jb'
        },
      success: function(res) {
        var divToExpand = document.createElement('ul');
        $(divToExpand).css('display', 'none').css(
          'background-color', '#f3f3f3').css('padding',
          '15px').css('color', '#313534').css('margin',
          '10px 0 0 0').addClass('divtoexpand');



        $foodObject = res.report.food;
        $nutrientsArray = res.report.food.nutrients;
        /*==============GET MEASURES =========================*/

        if (res.report.food.nutrients.length !== -1) {
          $measuresArrayDefault = res.report.food.nutrients[0].measures;
          $measuresArray = [];
          $.each($measuresArrayDefault, function(ind, obj) {
            $measureName = obj.label;
            $measureGram = (obj.eqv / obj.qty);
            $measuresArray.push({
              measure: $measureName,
              weightOfMeasure: $measureGram
            });
          });

        }

        /* ============ Measure got $measuresArray = [{measure: ..., weightOfMeasure: ...g}] =========*/

        var div3 = document.createElement('div');
        $(div3).addClass('col-sm-3');
        var inputAmount = document.createElement('input');
        $(inputAmount).addClass('form-control').addClass(
          'amount-selection');
        $(inputAmount).attr('value', ($nutrientsArray[0].measures[0].qty))
          .attr('type', 'text');

        $(divToExpand).html(
          '<div class="col-sm-3"><strong>Amount:</strong></div>'
        );
        $(div3).append(inputAmount);
        $(divToExpand).append(div3);

        var dropdown = document.createElement('div');
        $(dropdown)
          .addClass('col-sm-3') /*.addClass('col-sm-offset-2');*/ ;
        var select = document.createElement('select');
        $(select).addClass('form-control').addClass('unit-selection');

        var buttonDiv = document.createElement('div');
        var chooseFoodButton = document.createElement('button');
        $(chooseFoodButton).addClass('btn').addClass('btn-sm').addClass(
          'btn-primary').html('Add Food');
        $(buttonDiv).addClass('col-sm-3').append($(chooseFoodButton));

        $(chooseFoodButton).on('click', addFoodToMeal);

        $.each($measuresArray, function(ind,
          obj) {
          var option = document.createElement('option');
          $(option).html(obj.measure);
          $(select).append($(option));
        });
        $(dropdown).append($(select));
        $(divToExpand).append(
          $(dropdown));
        $(divToExpand).append($(buttonDiv));
        $(divToExpand).append(
          '<br/><hr/><h5> Nutrients <h5>');

        function updateList(amount, preferedMeasure) {
          var listOfNutrients = document.createElement('ul');
          $(listOfNutrients).addClass('nutrientList');

          $.each($nutrientsArray, function(index, value) {
            $nutrientName = value.name;
            $unit = value.unit;
            $valuePerHundredGrams = value.value;
            $measures = value.measures;

            $defaultMeasure = $measures[0].label;
            $defaultMeasureQuantity = $measures[0].qty;
            $defaultMeasureValue = $measures[0].value;

            $measureChoosen = "";
            $measureChoosenAmount = "";
            $measureChoosenValue = "";
            $valueOfNutrient = "";

            if (preferedMeasure) {
              $.each($measures, function(ind, measureObj) {
                if (measureObj.label == preferedMeasure) {
                  $measureChoosen = measureObj.label;
                  $measureChoosenAmount = measureObj.qty;
                  $measureChoosenValue = measureObj.value;
                }
              });
            } else {
              $measureChoosen = $defaultMeasure;
              $measureChoosenAmount = $defaultMeasureQuantity;
              $measureChoosenValue = $defaultMeasureValue;
            }

            if (amount) {
              $valueOfNutrient = ($measureChoosenValue /
                $measureChoosenAmount) * amount;
            } else {
              $valueOfNutrient = $defaultMeasureValue;
            }
            var li = document.createElement('li');
            $(li).html(
              "<strong>" + $nutrientName +
              "</strong> : " +
              "<span class='value-per-measure'>" +
              _2Decimals($valueOfNutrient) + $unit +
              "</span> <span class='per-tag'>/per " +
              _2Decimals(amount) + " " +
              $measureChoosen +
              "</span>");
            $(listOfNutrients).append($(li));
          });

          if ($(divToExpand).find('.nutrientList').length !== 0) {
            $(divToExpand).find('.nutrientList').remove();
          }
          $(divToExpand).append($(listOfNutrients));


        }

        updateList($(inputAmount).val());

        $(select).on('change', function(e) {
          updateList($(inputAmount).val(), e.target.value);
        });

        $(inputAmount).on('change keyup paste', function(e) {
          updateList(e.target.value, $(select).val());
        });

        $element.append($(divToExpand));
        $(divToExpand).slideDown();
      }
    });

  } else {

    $element.find('.divtoexpand').slideToggle();
  }
});
$(document).on('click', '.divtoexpand', function(e) {
  return false;
});

$(document).on('click', '.close-food-panel', function() {
  $(this).parent().parent().parent().slideUp();
});

/* ========================|||||||||||||||||||||||||||||||||||||||||||||||||||||||====================== */
/* ========================|||||||||||-------ADD ITEM TO DAY----------||||||||||||====================== */
/* ========================|||||||||||||||||||||||||||||||||||||||||||||||||||||||====================== */
function addFoodToMeal(e) {
  $item = $(e.currentTarget).closest('.list-group-item');
  $mealContainer = $(e.currentTarget).closest('.day-row');
  $label = $item.children('span').text();
    $ndbno = $item.attr('data-food-db-no');

  $unit = $item.find('.unit-selection').val();
  $amount = $item.find('.amount-selection').val();

console.log($mealContainer);


    var logFoodObject = {
        userId: 1,
        foodId: $ndbno,
        quantity: $amount,
        unit: $unit,
        day: getFormattedDate(),
        mealTypeRef: 1
    };



    $.ajax({
      type: "POST",
        contentType: "application/json; charset=utf-8",
      url: logFoodUrl,
        dataType: "json",
      data: JSON.stringify(logFoodObject),
      success: function(res) {
        //dateChangeHandler();
          addByDom();
      },
      error: function(results) {
        $.notify({
          message: 'There was an error.'
        }, {
          type: 'warning'
        });
        addByDom();
      }
    });


  function addByDom() {
    $.notify('Food saved to list');
    $cloned = $item.clone();
    $cloned.attr('data-amount', $amount).attr(
      'data-unit', $unit).attr('data-label', $label);
    $labelCl = $cloned.children('span');
    $labelCl.append('&nbsp;&nbsp;<span class="per-tag">' + $amount + ' ' +
      $unit +
      '</span>');
    $(
      '<span class="pull-right glyphicon glyphicon-remove food-saved-remove"></span>'
    ).insertAfter($labelCl);
    $cloned.css('transform', 'scale(1.1)');
    $mealContainer.find('.added-food').append($cloned);
    $cloned.find('.divtoexpand').hide();
    $item.find('.divtoexpand').slideUp();
    $cloned.css('transform', 'scale(1)');

    var history_obj = {
      element: $cloned,
      from: $mealContainer.find('.added-food'),
      undo: function() {
        $(this.element).detach();
        futureArray.push(historyArray.pop());
      },
      redo: function() {
        $(this.from).append($(this.element));
        historyArray.push(futureArray.pop());
      }
    }
    historyArray.push(history_obj);
  }

}
/* ========================^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^====================== */
/* ========================|||||||||||-------ADD ITEM TO DAY----------||||||||||||====================== */
/* ========================|||||||||||||||||||||||||||||||||||||||||||||||||||||||====================== */


/////////////// DATE PICKER /////////////////////
var today = new Date();
var currentDate = new Date();
$("#datepicker").datepicker({
  showOn: "button",
  buttonText: '<span style="font-size: 16px; padding: 5px" class="glyphicon glyphicon-calendar"></span>',
  buttonImageOnly: false,
  dateFormat: 'DD, dd.mm.yy'
}).datepicker('setDate', currentDate);
dateChangeHandler();

$('.next-day-button').click(function(e) {
  currentDate.setDate(currentDate.getDate() + 1);
  $("#datepicker").datepicker('setDate', currentDate);
  dateChangeHandler();
});
$('.prev-day-button').click(function(e) {
  currentDate.setDate(currentDate.getDate() - 1)
  $("#datepicker").datepicker('setDate', currentDate);
  dateChangeHandler();
});

$('#datepicker').on('change', dateChangeHandler);

$('.day-container').attr('data-date', currentDate.toDateString());



function dateChangeHandler() {
  $newDate = $('#datepicker').datepicker('getDate').toDateString();

  /*=== === === === CHECK FOR TODAY === === ===*/
  if ($newDate == today.toDateString()) {
    $('#datepicker').css('background-color', '#eee');
  } else {
    $('#datepicker').css('background-color', '#fff');
  }
  /*=== === === === CHECK FOR TODAY === === ===*/

  $breakfast = [];
  $lunch = [];
  $dinner = [];
  $snack = [];
    var uri = getDateRequestUrl + getFormattedDate();
    $.ajax({
      url: uri,
      type: 'GET',
      success: loadDay
    });


  function loadDay(res) {

    $.each(res.userMeal.meals, function(ind, obj) {
      if (obj.mealType == "BREAKFAST") {
        $breakfast = obj.foodConsumptions;
      }
      if (obj.mealType == "LUNCH") {
        $lunch = obj.foodConsumptions;
      }
      if (obj.mealType == "DINNER") {
        $dinner = obj.foodConsumptions;
      }
      if (obj.mealType == "SNACK") {
        $snack = obj.foodConsumptions;
      }
    });
    /*if ($('.day-container[data-date="' + $newDate + '"]').length == 0) {*/
    $('.day-container').fadeOut().remove();
    $newDay = $('.day-container-template').clone();
    $('.content').append($newDay);
    $newDay.attr('class', 'row').addClass('day-container').attr(
      'data-date', $newDate).fadeIn();
    /*  } else {
        $('.day-container').fadeOut();
        $('.day-container[data-date="' + $newDate + '"]').fadeIn();
      }*/
      function exerciseItemGenerate(label, amount){
          $item = $('.food-li-template').clone();
          $item.css('display', 'block');
          $item.attr('class', 'list-group-item');
          $item.attr('data-minutes', amount);
          $perTag = $item.find('.per-tag').text(amount+' minutes').detach();
          $item.find('.food-label').toggleClass('food-label').addClass('exercise-label').text(label+'  ').append($perTag);
          return $item;
      }
    function foodItemGenerate(label, amount, unit) {
      $item = $('.food-li-template').clone();
      $item.css('display', 'block');
      $item.attr('class', 'list-group-item');
      $item.attr('data-food-db-no', '');
      $item.attr('data-label', label);
      $item.attr('data-amount', amount);
      $item.attr('data-unit', unit);
      $perTag = $item.find('.per-tag').text(amount + ' ' + unit).detach();
      $item.find('.food-label').text(label + '  ').append($perTag);

      return $item;
    }

    $.each($breakfast, function(ind, val) {

      $label = val.food.foodName;
      $amount = val.quantity;
      $unit = val.unit;

      $newDay.find('.breakfast').find('.added-food').append(
        foodItemGenerate($label, $amount, $unit));
    });
    $.each($lunch, function(ind, val) {
      $label = val.food.foodName;
      $amount = val.quantity;
      $unit = val.unit;
      $newDay.find('.lunch').find('.added-food').append(
        foodItemGenerate($label, $amount, $unit));
    });
    $.each($dinner, function(ind, val) {
      $label = val.food.foodName;
      $amount = val.quantity;
      $unit = val.unit;
      $newDay.find('.dinner').find('.added-food').append(
        foodItemGenerate($label, $amount, $unit));
    });
    $.each($snack, function(ind, val) {
      $label = val.food.foodName;
      $amount = val.quantity;
      $unit = val.unit;
      $newDay.find('.snack').find('.added-food').append(
        foodItemGenerate($label, $amount, $unit));
    });
      $.each(res.activityList, function(ind, obj){
          $activity = obj.activity;
          $label = $activity.description;
          $duration = obj.duration;
          $newDay.find('.added-exercise').append(exerciseItemGenerate($label, $duration));
      });
    // console.log($newDate);
    // console.log(today.toDateString());
  }
}
/////////////// DATE PICKER /////////////////////


/*function wrapToJson(dayContainerElement) {
  $(dayContainerElement).find('.breakfast');

  $meals = [];
  $userId;


  function saveLists(meal) {
    $foods = $(dayContainerElement).find('.' + meal).find('.added-food').find(
      'li');
    $exercises = $(dayContainerElement).find('.' + meal).find(
        '.added-exercise')
      .find('li');
    foodsArray = [];
    excercisesArray = [];
    $.each($foods, function(ind, val) {
      $label = val.attr('data-label');
      $amount = val.attr('data-amount');
      $unit = val.attr('data-unit');
      $ndbno = val.attr('data-food-db-no');
      foodObject = {
        food: {
          name: $label,
          id: $ndbno,
          jsonData: '{sample json data}'
        },
        amount: $amount,
        unit: $unit
      };
      foodsArray.push(foodObject);
    });
  }
}
*/
$(document).on('click', '.food-saved-remove', function() {
  $element = $(this).parent();
  $from = $(this).closest('.day-row');
  $clone = $element.clone();
  $element.slideUp(300, function() {
    $(this).detach();
  });
  $.notify('You can undo by: CTLR+Z');
  var history_obj = {
    element: $clone,
    removedFrom: $from,
    undo: function() {
      $(this.removedFrom).find('.added-food').append($(this.element));
      $(this.element).css('display', 'none').slideDown();
      $.notify('You can redo by: CTLR+Y');
      futureArray.push(historyArray.pop());
    },
    redo: function() {
      $(this.element).detach();
      historyArray.push(futureArray.pop());
    }
  };
  historyArray.push(history_obj);
});

$(document).on('keypress', function(e) {
  //  console.log(e);
  if (e.ctrlKey && e.keyCode == 26) {
    e.preventDefault();
    e.stopPropagation();
    return false;
  }
  if (e.ctrlKey && e.keyCode == 25) {
    e.preventDefault();
    e.stopPropagation();
    return false;
  }
});
$(document).on('keypress', function(e) {
  //  console.log(e);
  if (e.ctrlKey && e.keyCode == 26) {
    if (historyArray.length !== 0) {
      historyArray[historyArray.length - 1].undo();
    }
  }
  if (e.ctrlKey && e.keyCode == 25) {
    if (historyArray.length !== 0) {
      futureArray[futureArray.length - 1].redo();
    }
  }
});


$(document).on('click', '.add-exercise', function() {
  $('#add-exercise-panel').slideToggle();
});

$(document).on('keyup', '#exercise-search-input', searchExercise);

function searchExercise(e) {
  $query = encodeURIComponent($('#exercise-search-input').val());



    $.ajax({
      type: "GET",
      url: activityUrl,
      data: {
        keyword: $query
      },
      success: listResponse,
      error: function(results) {
        $.notify({
          message: 'Call returned an error: It may be endpoint, https error or local access restriction. Therefore a representational list loaded below not results'
        }, {
          type: 'warning'
        });
      }
    });


  function listResponse(res) {
    if (res.errors) {
      $('.search-result-panel-exercise').slideUp();
    } else {
      $('.search-result-panel-exercise').slideDown();
      $list = $('.search-result-list-exercise');
      $list.find('li').fadeOut('fast', function() {
        $(this).remove();
      });

      var listArray = res;
      $.each(listArray, function(index, item) {
        var li_element = document.createElement(
          'li');
        li_element.classList.add('list-group-item');

        li_element.setAttribute('data-activity-id',
          item.activityId);

        $(li_element).html(
          "<i class='fa fa-bicycle'></i><span>" +
          item.description + "</span>").
        find('span').addClass('exercise-label').css('display',
          'inline-block').
        css('width', '80%').parent().find('i').css('padding',
          '5px');

        $list.append(li_element);
        $(li_element).css('display', 'none')
        $(li_element).slideDown();
      });

    }
  }
}

$(document).on('click', '.search-result-list-exercise li', function(e) {

  $element = $(this);
  $val = $(this).text();
  $dbid = $(this).attr('data-activity-id');

  if ($element.find('.divtoexpand').length == 0) {
      //console.log($dbid);
      var pActivityURL = activityUrl + '/' + $dbid;
    $.ajax({
      url: pActivityURL,
      type: 'GET',
      success: function(res) {
        var divToExpand = document.createElement('ul');
        $(divToExpand).css('display', 'none').css(
          'background-color', '#f3f3f3').css('padding',
          '15px').css('color', '#313534').css('margin',
          '10px 0 0 0').addClass('divtoexpand');
        /*res = {

          activityId: 195,
          calorieBurnPerHour: 817.0,
          description: 'asd'
        };*/

        var div3 = document.createElement('div');
        $(div3).addClass('col-sm-3');
        var input = document.createElement('input');
        $(input).addClass('form-control').addClass(
          'exercise-minutes').attr('type', 'text');



        $(divToExpand).html(
          '<div class="col-sm-3"><strong>Minutes:</strong></div>'
        );
        $(div3).append(input);
        $(divToExpand).append(div3);

        $element.attr('data-kcal-per-hour', res.calorieBurnPerHour);

        var buttonDiv = document.createElement('div');
        var addExerciseButton = document.createElement('button');
        $(addExerciseButton).addClass('btn').addClass('btn-sm').addClass(
          'btn-primary').addClass('add-exercise-save').html(
          'Add Activity');
        $(buttonDiv).addClass('col-sm-3').append($(
          addExerciseButton));
        var caloriLabel = document.createElement('div');
        $(caloriLabel).addClass('col-sm-3').html(
          "<span class=calories> 0.00 </span><span class='per-tag'>/kcal</span>"
        );

        $(addExerciseButton).on('click', addExercise);
        $(divToExpand).append($(caloriLabel)).append($(buttonDiv)).append(
          '<br/><hr/>');

        /* ========================|||||||||||||||||||||||||||||||||||||||||||||||||||||||====================== */
        /* ========================|||||||||||-------ADD ITEM TO DAY----------||||||||||||====================== */
        /* ========================|||||||||||||||||||||||||||||||||||||||||||||||||||||||====================== */
        function addExercise(e) {

            $item = $(e.currentTarget).closest('.list-group-item');

            $label = $item.children('span').text();
            $activityId = $item.attr('data-activity-id');

            $activityMin = $item.find('.exercise-minutes').val();

            //console.log($item + " - "+ $label +" - "+ $activityId + " - " + $activityMin);

            var addedActivity = {
                userId: 1,
                duration: $activityMin,
                moment: currentDate,
                activity : {
                    activityId: $activityId
                }

            };



            $.ajax({
              type: "POST",
                contentType: "application/json; charset=utf-8",
                url: exerciseSubmitUrl,
                dataType: "json",
                data:  JSON.stringify(addedActivity),
                success: function(res) {
                addByDom();
                },
                error: function(results) {
                $.notify({
                  message: 'There was an error while adding the activity.'
                }, {
                  type: 'danger'
                });
              }
            });


          function addByDom() {
            $liItem = $(e.currentTarget).closest('.list-group-item');
            $clonedEx = $liItem.clone();

            $minutes = $liItem.find('.exercise-minutes').val();

            $clonedEx.attr('data-minutes', $minutes);
            $labelCl = $clonedEx.children('span');
            $labelCl.append('&nbsp;&nbsp;<span class="per-tag">' +
              $minutes + ' ' +
              'minutes' +
              '</span>');
            $(
              '<span class="pull-right glyphicon glyphicon-remove exercise-saved-remove"></span>'
            ).insertAfter($labelCl);
            $clonedEx.find('.divtoexpand').remove();
            $clonedEx.css('transform', 'scale(1.2)');
            $('.added-exercise').append($clonedEx).find(
              '.list-group-item').css('transform', 'scale(1)');
          }

          $item.find('.divtoexpand').slideUp();
        }
        /* ========================^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^====================== */
        /* ========================|||||||||||-------ADD ITEM TO DAY----------||||||||||||====================== */
        /* ========================|||||||||||||||||||||||||||||||||||||||||||||||||||||||====================== */
        $(input).on('keyup paste', function(e) {
          $minutes = $(e.target).val();
          $tagToUpdate = $(e.target).closest('.divtoexpand').find(
            '.calories');
          $calorieBurnPerHour = $(e.target).closest(
            '.list-group-item').attr('data-kcal-per-hour');
          $tagToUpdate.text(_2Decimals((+$calorieBurnPerHour /
            60) * +$minutes));
          //console.log($minutes);

        });

        $element.append($(divToExpand));
        $(divToExpand).slideDown();
      }

    })
  } else {
    $element.find('.divtoexpand').slideToggle();
  }
});

$(document).on('click', '.close-exercise-panel', function() {
  $('#add-exercise-panel').slideUp();
});

