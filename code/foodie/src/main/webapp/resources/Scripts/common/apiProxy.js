var webServiceUrl = "http://test.just4test.in/api/";
function callApi(url, data, onSuccess, onError){
    url = webServiceUrl + url;

	$.ajax({
		url: url,
		type: data != null ? "POST" : "GET",
		headers: {
		    'Accept': "application/json",
		    "Content-type" : "application/json",
		    'Access-Control-Allow-Origin' : '*'
		},
		crossDomain: true,	
		contentType: "application/json",
		dataType : "jsonp",		
		jsonpCallback: "handleSuccess",
		data: data,
		success: onSuccess,
		error: onError
	});
}

function generalResponse(responseStr){
	var self = this;
	console.log("Start converting api to general response");
	self = JSON.parse(responseStr);
	self.isSuccess = ko.computed(function(){
		return self.success;
    });
}


var api = new apiConstructor();

function apiConstructor(){
	var self = this;
	self.getHelloWorld = getHelloWorld;
}

function getHelloWorld(handleSuccess,handleError){
	url = "getNameList";
	callApi(url, null, handleSuccess, handleError);
}
