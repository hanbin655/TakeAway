var url = "getRestaurantById?restaurantId={0}";
function renderPage() {
    var id = 'ag9zfmNvbmZ1c2VkLWxvc3RyEQsSClJlc3RhdXJhbnQYwT4M';
    url = url.format(id);

    console.log("Start calling api");
    callApi(url, null,
	handleSuccess,
	handleError);

}

function handleSuccess(response) {
    console.log("Api retuns data");
    var result = new generalResponse(response);
    if (result.isSuccess()) {
        console.log("Api retuns data with success");
        viewModel = new localData(result.data);
    } else {
        handleUnsuccess(result);
    }
}

function localData(data) {
    console.log("Start building model for page view based on api data");
    var self = this;
    self = data;
}


function handleUnsuccess(result) {
    console.log("api returns data with unsuccess");
}

function handleError(response) {
    console.log("can't get api data");
}