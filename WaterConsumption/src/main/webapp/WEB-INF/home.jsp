<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
<meta charset="ISO-8859-1">

<title>Dashboard</title>
</head>
<body>
<h1>Hello, ${thisUser.getUserName()}</h1>
<h2  class="text-center">Water table</h2>
<div class="position-absolute top-0 end-0"><a href="/logout" type="button" class="btn btn-primary btn-sm">Logout</a></div>
<a href="/create" type="button" class="btn btn-link">Add a task</a>
<div class="table-wrapper-scroll-y my-custom-scrollbar">

  <table class="table table-bordered table-striped mb-0">
    <thead>
      <tr>
        <th scope="col">Task</th>
        <th scope="col">Time (min)</th>
        <th scope="col">Action(s)</th>
      </tr>
    </thead>
    <tbody>
    <c:forEach var = "t" items="${tasks}">
      <tr>
        <td scope="row">${t.getTask()}</td>
        <td>${t.getTime()}</td>
        <td>
        	<a href="/edit/${t.getId()}" class="text-decoration-none">edit</a>
        	|
        	<a href="/delete/${t.getId()}" class="text-decoration-none">delete</a>
        </td>
      </tr>
      </c:forEach>
    </tbody>
  </table>

</div>
<div>
<h1 class="text-center">Water Graph</h1>
<div class="chart-container">
    <canvas id="myChart"></canvas>
</div>
</div>
</body>
<script>
var monthlist = ${monthlist};
var activity = ${activitylist};
var timelist = ${timelist}; 
var dataArray = [];
var label = [];
var months = [
		"January", "February", "March", "April", 
		"May", "June", "July", "August", "September", 
		"October", "November", "December"];
var activitylist = [
	"Shower", "Bath", "Toilet", "Dishes (by hand)", 
	"Dishes (by machine)", "Laundry", "Car Wash", "Watered Lawn"];
testtt(months, monthlist, activity, timelist);
addData(60);

var ctx = document.getElementById('myChart').getContext('2d');
var chart = new Chart(ctx, {
    // The type of chart we want to create
    type: 'bar',

    // The data for our dataset
    data: {
        labels: label,
        datasets: [{
            label: 'Amount of water (in gallons)',
            backgroundColor: 'rgb(255, 99, 132)',
            borderColor: 'rgb(255, 99, 132)',
            data: dataArray
        }]
    },

    // Configuration options go here
    options: {}
});

function addData(value){
	dataArray.push(value)
};
function addMonth(month){
	label.push(month)
};
function testt(monthData, monthlist){
	for(let i=0; i<monthData.length; i++){
		for(let j=0; j<monthlist.length; j++){
			if(monthlist[j] == monthData[i]){
				label.push(monthlist[j])
				break
			}
		}
	}
};
function testtt(monthData, monthlist, activity, time){
	var shower = 0;
	var bath = 0;
	var toilet = 0;
	var dishHand = 0;
	var dishMachine = 0;
	var laundry = 0;
	var carWash = 0;
	var waterLawned = 0;
	for(let i=0; i<monthData.length; i++){
		var total = 0;
		for(let j=0; j<monthlist.length; j++){
			if(monthlist[j] == monthData[i]){
				label.push(monthlist[j])
				if(activity[j] == "Shower"){
					shower += (time[j] * 2.1);
				}
				if(activity[j] == "Bath"){
					bath += 50;
				}
				if(activity[j] == "toilet"){
					toilet += 1.6;
				}
				if(activity[j] == "Dishes (by hand)"){
					dishHand += (time[j] * 18);
				}
				if(activity[j] == "Dishes (by machine)"){
					dishMachine += (time[j] * 11);
				}
				if(activity[j] == "Laundry"){
					laundry += (time[j] * 4);
				}
				if(activity[j] == "Car Wash"){
					carWash += (time[j] * 1.5);
				}
				if(activity[j] == "Watered Lawn"){
					waterLawned += (time[j] * 15);
				}
				total = total + shower + bath + toilet + dishHand + dishMachine + laundry + carWash + waterLawned;
				dataArray.push(total);
				break
			}
		}
	}
};

</script>
</html>