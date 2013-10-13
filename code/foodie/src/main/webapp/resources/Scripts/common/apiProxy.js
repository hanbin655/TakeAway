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
}

function getHelloWorld(handleSuccess,handleError){
	url = "getNameList";
	callApi(url, null, handleSuccess, handleError);
}
