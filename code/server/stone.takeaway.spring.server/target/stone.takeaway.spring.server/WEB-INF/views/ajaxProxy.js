function callApi(service, method, data, onSuccess, onError){
	var url = "http://127.0.0.1:8080/api/" + service;
	
	$.ajax({
		url: url,
		type: method,
		data:data,
		success: onSuccess,
		error: onError
	});
	
}