angular.module("bloggerpage").controller("GeneralController",GeneralController);

GeneralController.inject=['$scope','BloggerUser'];

function GeneralController($scope,BloggerUser){
	
	$scope.bloggerusers=BloggerUser.query();
	$scope.bloggeruser={};
	$scope.buttonText="Submit";
	
	
	$scope.saveBloggerUser=function(){
		
		if($scope.bloggeruser.id!==undefined){
			BloggerUser.update($scope,bloggeruser,function(){
				$scope.bloggerusers=BloggerUser.query();
				$scope.bloggeruser={};
				$scope.buttonText="Submit";
			});
		}else{
			BloggerUser.save($scope,bloggeruser,function(){
				$scope.bloggerusers=BloggerUser.query();
				$scope.bloggeruser={};
				$scope.buttonText="Submit";
			});
		}
	}
	
	$scope.updateBloggerUserInit=function(bloggeruser){
		$scope.buttonText="Update";
		$scope.bloggeruser=bloggeruser;
	}
	
	$scope.deleteBloggerUser=function(bloggeruser){
		bloggeruser.$delete({id:bloggeruser.id},function(){
			$scope.bloggerusers=BloggerUser.query();
		});
		
	}
	
}