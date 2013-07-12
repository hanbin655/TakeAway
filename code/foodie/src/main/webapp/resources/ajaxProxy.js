function callApi(service, method, data, onSuccess, onError){
	//var proxy = "simple-proxy.php?mode=native&url=";
	var remotepath = "http://localhost:8080/api/" + service;
	//var url = proxy + remotepath;
	var url = remotepath;
	$.ajax({
		url: url,
		type: method,
		data:data,
		success: onSuccess,
		error: onError
	});
	
	
}

function showApiError(error){
	alert("There are some errors : " + error);
};