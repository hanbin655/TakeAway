// To use this template, copy it and rename it with the same name as page
//Every page needs a renderPage function
function renderPage() {
    console.log("Start calling api");    
    //this api object is defined in ../common/apiProxy.js, if request is get then data should be null
    api.call(url, data, handleSuccess, handleError);

}

//This function is used for handling success return from api
function handleSuccess(response) {
    console.log("Api retuns data");    
    //this generalResponse function is defined in apiProxy.js, it encapsulate the response string as a json object 
    var result = new generalResponse(response);
    
    if (result.isSuccess()) {
        console.log("Api retuns data with success");
        viewModel = new localData(result.data);
    } else {
        handleUnsuccess(result);
    }
}

//This function is used to convert data from api to local data model and assign it to view Model
function localData(data) {
    console.log("Start building model for page view based on api data");
    var self = this;
    self = data;
}

//This function is used to handle unsuccess return from api, usual show an error message and redirect to other page
function handleUnsuccess(result) {
    console.log("api returns data with unsuccess");
}

//This function is use to handle error when calling api.
function handleError(response) {
    console.log("can't get api data");
}