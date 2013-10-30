var webServiceUrl = "/api/";
function callApi(url, data, onSuccess, onError){
    url = webServiceUrl + url;

	$.ajax({
		url: url,
		type: data != null ? "POST" : "GET",
		headers: {
		    'Accept': "application/json",
		    "Content-type" : "application/json"
		},
		contentType: "application/json",
		data: data,
		success: onSuccess,
		error: onError
	});
}

var api = new apiConstructor();

function apiConstructor(){
	var self = this;
	self.getHelloWorld = getHelloWorld;
	self.getRestaurantById = getRestaurantById;
	self.getMenuItem=getMenuItem;
}

function getHelloWorld(handleSuccess,handleError){
	url = "getNameList";
	callApi(url, null, handleSuccess, handleError);
}

function getRestaurantById(id, handleSuccess,handleError){
	var url = "getRestaurantById?restaurantId={0}";
	url = url.format(id);
	callApi(url, null, handleSuccess, handleError)
}
function getMenuItem(handleSuccess,handleError){
	//just for test 
	var url = "getMenuItem?menuId=ag1jb25mdXNlZC1sb3N0choLEgpSZXN0YXVyYW50GAUMCxIETWVudRgHDA";
	callApi(url,null,handleSuccess,handleError)
}
