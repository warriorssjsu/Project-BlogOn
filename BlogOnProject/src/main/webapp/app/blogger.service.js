angular.module('bloggerpage').factory('BloggerUser',BloggerUser);

BloggerUser.$inject=['$resource'];

function BloggerUser($resource){
	
	var resourceUrl='api/User/:id';
	
	return $resource(resourceUrl,{},{
		'update':{
			method:'PUT'
		}
		
	});
}