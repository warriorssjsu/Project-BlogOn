<!--<!DOCTYPE html>
<html>
<head>

    <meta chrset="UTF 8">
    <title>Event Registration</title>
</head>

<body  ng-app="sampleApp">
<script src="https://code.angularjs.org/1.6.9/angular-route.js"></script>
<script src="https://code.angularjs.org/1.6.9/angular.min.js"></script>
<script src="https://code.angularjs.org/1.6.9/angular.js"></script>

<h1> Guru99 Global Event</h1>
<div ng-controller="AngularController">
<script>
    var app = angular.module("sampleApp",[]);

    app.controller("AngularController",function($scope) {

        $scope.Display = function () {
            $scope.AllTopic.push($scope.Topic);
        }
    });
</script>
    <form ng-submit="Display()">
        &nbsp;&nbsp;&nbsp;
        Enter which topic you would like to learn
        <input type="text"  ng-app="sampleApp" ng-model="Topic"><br>&nbsp;&nbsp;&nbsp;

        <input type="submit" value="Submit"/>
         <a>{{Topic}}</a>
        <ul ng-repeat="topicname in AllTopic">
            <li>{{topicname}}</li>
        </ul>
    </form>
</div>


</body>
</html>
 
-->

<!DOCTYPE html>
<html>
<head>
	<title>BloG ON</title>
<meta charset="UTF-8">
<script type="text/javascript"  src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.3/angular.min.js"></script>
 <script type="text/javascript"  src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.3/angular-resource.js"></script>  
  <script type="text/javascript" src="app/bloggerpage.js"></script> 
  <script type="text/javascript" src="app/general.controller.js"></script> 
  <script type="text/javascript" src="app/blogger.service.js"></script> 
  
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
  
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
  
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>


<body ng-app="bloggerpage" ng-controller="GeneralController">

<div class="panel panel-default">
		<div class="panel-heading">BLOGGERPAGE</div>
		<div class="panel-body">
			<form class="col-md-4" ng-submit="saveBloggerUser">
				<div class="form-group">
					<label for="Name">BloggerName</label> 
					<input type="text"  ng-model="bloggerUser.Name" class="form-control" id="Name" placeholder="Name">		
                 </div>
				
				
				<div class="form-group">
					<label for="Description">Blog Description</label> <input type="text"
						ng-model="bloggerUser.Description" class="form-control"
						id="Description" placeholder="Description">
				</div>

				<div class="form-group">
					<label for="Category">Blog Category</label> 
					<input type="text"
						ng-model="bloggerUser.Category" class="form-control"
						id="Category" placeholder="Category">
				</div>

				<button type="submit" class="btn btn-default">Submit</button>
				
			</form>

 <div class="table-responsive col-md-6">
				<table class="table table-striped">
					<tr>
						<th>Name</th>
						<th>Description</th>
						<th>Category</th>
						<th></th>
					</tr>
					<tr ng-repeat="bloggerUser in bloggerUsers">
						<td>{{bloggeruser.Name}}</td>
						<td>{{bloggeruser.Description}}</td>
						<td>{{bloggeruser.Category}}</td>
						<td>
							<button type="button" class="btn btn-default"
								ng-click="updateBloggerUserInit(bloggeruser)">
								<span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
							</button>
							<button type="button" class="btn btn-default"
								ng-click ="deleteBloggerUser(bloggeruser)">
								<span class=" glyphicon glyphicon-remove-circle"
									aria-hidden="true"></span>
							</button>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	
</body>
</html>
