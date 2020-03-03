<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Vehicle Management System</title>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script defer src="https://use.fontawesome.com/releases/v5.0.13/js/fontawesome.js" integrity="sha384-6OIrr52G08NpOFSZdxxz1xdNSndlD4vdcf/q2myIUVO0VsqaGHJsB0RaBE01VTOY" crossorigin="anonymous"></script>
<link rel="shortcut icon" href="#">


<style>
.navbar-custom {
    background-color: #0080ff;
}

body {
  font-family: 'Source Sans Pro', sans-serif;
}

header {
  position: absolute;
  top: 40%;
  left: 30%;
  transform: translate(-50%, -50%);
  color: white;
  text-align: left;
}
h1 {
  text-transform: uppercase;
  margin: 0;
  font-size: 4rem;
  white-space: nowrap;
}


</style>
<script>

function getVehicles(){
	$.ajax({
		  url: "http://localhost:8080/VehicleManagementSystem/vehicle/get",
		  type: "GET",
		  success: function(result){
			  $('#vehiclesTable').hide();
			  $('#vehiclesTable tbody').empty();
			    var searchData = JSON.parse(result);
			    
			    for(var i=0;i<searchData.length;i++){
			    	$("#vehiclesTable > tbody").append("<tr><td>"+searchData[i]["id"]+"</td><td>"+searchData[i]["make"]+"</td><td>"+searchData[i]["model"]+"</td><td>"+searchData[i]["year"]+"</td><td><button type='button' class='btn btn-success' style='height:30px' onclick='editVehcile("+'"'+searchData[i]["model"]+'","'+searchData[i]["id"]+'"'+");'>Edit</button><button type='button' class='btn btn-danger' style='height:30px' onclick='deleteVehicle("+'"'+searchData[i]["id"]+'"'+");'>Delete</button></td></tr>");
			    }
			    $('#vehiclesTable').show();
			    
	  	  },
	  	  error: function(jqXHR, textStatus, errorThrown) {
	  		
		  }
		 }); 
}

function getVehiclesById(id){
	$.ajax({
		  url: "http://localhost:8080/VehicleManagementSystem/vehicle/get/"+id,
		  type: "GET",
		  success: function(result){
			  $('#getIdModal').modal('hide');
			  $('#vehiclesTable').hide();
			  $('#vehiclesTable tbody').empty();
			    var searchData = JSON.parse(result);
			    for(var i=0;i<searchData.length;i++){
			    	$("#vehiclesTable > tbody").append("<tr><td>"+searchData[i]["id"]+"</td><td>"+searchData[i]["make"]+"</td><td>"+searchData[i]["model"]+"</td><td>"+searchData[i]["year"]+"</td><td><button type='button' class='btn btn-success' style='height:30px' onclick='editVehcile("+'"'+searchData[i]["model"]+'","'+searchData[i]["id"]+'"'+");'>Edit</button><button type='button' class='btn btn-danger' style='height:30px' onclick='deleteVehicle("+'"'+searchData[i]["id"]+'"'+");'>Delete</button></td></tr>");
			    }
			    $('#vehiclesTable').show();
			    $('#getId').val('');
	  	  },
	  	  error: function(jqXHR, textStatus, errorThrown) {
	  		
		  }
		 }); 
}

function editVehcile(model,id){
	$('#editField').val(model);
	$('#editModal').modal('show');
	$('#vehicleIdField').val(id);
}

function updateVehicle(id){
	$.ajax({
		  url: "http://localhost:8080/VehicleManagementSystem/vehicle/update",
		  type: "POST",
		  data:{
			  "id" : id,
			  "model" : $('#editField').val()
		  },
		  success: function(result){
			    console.log(result);
			    $('#editModal').modal('hide');
			    getVehicles();
	  	  },
	  	  error: function(jqXHR, textStatus, errorThrown) {
	  		
		  }
		 }); 
}

function deleteVehicle(id){
	$.ajax({
		  url: "http://localhost:8080/VehicleManagementSystem/vehicle/delete/"+id,
		  type: "DELETE",
		  /* data:{
			  "id" : id
		  }, */
		  success: function(result){
			    console.log(result);	
			    getVehicles();
	  	  },
	  	  error: function(jqXHR, textStatus, errorThrown) {
	  		
		  }
		 }); 
}

function insertVehicle(){
	$.ajax({
		  url: "http://localhost:8080/VehicleManagementSystem/vehicle/insert",
		  type: "POST",
		  data:{
			  "make" : $('#iMake').val(),
			  "model" : $('#iModel').val(),
			  "year" : $('#iYear').val()
		  },
		  success: function(result){
			    console.log(result);
			    $('#insertModal').modal('hide');
			    getVehicles();
			    $('#iMake').val('');
			    $('#iModel').val('');
			    $('#iYear').val('');
	  	  },
	  	  error: function(jqXHR, textStatus, errorThrown) {
	  		
		  }
		 }); 
}

function filterVehicle(){
	$.ajax({
		  url: "http://localhost:8080/VehicleManagementSystem/vehicle/filter",
		  type: "GET",
		  data:{
			  "make" : $('#fMake').val(),
			  "model" : $('#fModel').val(),
		  },
		  success: function(result){
			    console.log(result);
			    $('#filterModal').modal('hide');
			    $('#vehiclesTable').hide();
				$('#vehiclesTable tbody').empty();
				var searchData = JSON.parse(result);
			    for(var i=0;i<searchData.length;i++){
			    	$("#vehiclesTable > tbody").append("<tr><td>"+searchData[i]["id"]+"</td><td>"+searchData[i]["make"]+"</td><td>"+searchData[i]["model"]+"</td><td>"+searchData[i]["year"]+"</td><td><button type='button' class='btn btn-success' style='height:30px' onclick='editVehcile("+'"'+searchData[i]["model"]+'","'+searchData[i]["id"]+'"'+");'>Edit</button><button type='button' class='btn btn-danger' style='height:30px' onclick='deleteVehicle("+'"'+searchData[i]["id"]+'"'+");'>Delete</button></td></tr>");
		   		}
				$('#vehiclesTable').show();
			    $('#fMake').val('');
			    $('#fModel').val('');
	  	  },
	  	  error: function(jqXHR, textStatus, errorThrown) {
	  		
		  }
		 }); 
}

$(document).ready(function(){
	
	$('#vehiclesTable').hide();
	
	$("#searchVehicles").click(function(){
		getVehicles();
	});
	
	$("#doneEdit").click(function(){
		updateVehicle($('#vehicleIdField').val());
	});
	
	$("#insertVehicles").click(function(){
		$('#insertModal').modal('show');
	});
	
	$("#doneInsert").click(function(){
		insertVehicle();
	});
	
	$('#searchVehiclesById').click(function(){
		$('#getIdModal').modal('show');
	});
	
	$('#doneGetId').click(function(){
		getVehiclesById($('#getId').val());
	});
	
	$('#filterVehicles').click(function(){
		$('#filterModal').modal('show');
	});
	
	$("#doneFilter").click(function(){
		filterVehicle();
	});
	
});
</script>

</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark navbar-custom">
  <a class="navbar-brand" href="#">Vehicle Management System</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
</nav>

		<h6  id="vehicleIdField" hidden=true></h6>
		<div class="container">
		<br>
		<div class="row">
				<div class="col-sm">
				<button type="button" class="btn btn-primary" id="searchVehicles" style="margin-bottom: 10px;">Get Vehicles</button>
   				</div>
   				<div class="col-sm">
				<button type="button" class="btn btn-primary" id="searchVehiclesById" style="margin-bottom: 10px;">Get Vehicles By Id</button>
   				</div>
   				<div class="col-sm">
				<button type="button" class="btn btn-primary" id="insertVehicles" style="margin-bottom: 10px;">Insert Vehicle</button>
				</div>
				<div class="col-sm">
				<button type="button" class="btn btn-primary" id="filterVehicles" style="margin-bottom: 10px;">Filter Vehicles</button>
   				</div>
		</div>
		
				<table class="table table-bordered" id="vehiclesTable">
					  <thead>
					    <tr>
					      <th scope="col">Id</th>
					      <th scope="col">Make</th>
					      <th scope="col">Model</th>
					      <th scope="col">Year</th>
					      <th scope="col">Action</th>
					    </tr>
					  </thead>
					  <tbody>
					  </tbody>
					</table>
		</div>
		
		
		
		<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">Update Vehicle Details</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		        <input type="text" id="editField">
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
		        <button type="button" class="btn btn-primary" id="doneEdit">Update</button>
		      </div>
		    </div>
		  </div>
		</div>
		
		<div class="modal fade" id="insertModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">Insert Vehicle Details</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		        <span>Make:<input type="text" id="iMake"></span><br>
		        <span>Model:<input type="text" id="iModel"></span><br>
		        <span>Year:<input type="text" id="iYear"></span>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
		        <button type="button" class="btn btn-primary" id="doneInsert">Insert</button>
		      </div>
		    </div>
		  </div>
		</div>
		
		
		<div class="modal fade" id="getIdModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">Get Vehicle Details By Id</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		        <span>Id: <input type="text" id="getId"></span>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
		        <button type="button" class="btn btn-primary" id="doneGetId">Fetch</button>
		      </div>
		    </div>
		  </div>
		</div>
		
		<div class="modal fade" id="filterModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">Filter Vehicle Details</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		        <span>Make:<input type="text" id="fMake"></span><br>
		        <span>Model:<input type="text" id="fModel"></span>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
		        <button type="button" class="btn btn-primary" id="doneFilter">Filter</button>
		      </div>
		    </div>
		  </div>
		</div>
		
</body>
</html>