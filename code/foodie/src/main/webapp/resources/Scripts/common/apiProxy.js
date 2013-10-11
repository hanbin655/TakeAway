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


