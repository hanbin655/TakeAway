
function renderPage() {
    console.log("Start calling api");   
    viewModel = new localViewModel();
}

function buildViewModel(){	
	var self = this;
	self.data = ko.observableArray([]);
	self.showData = ko.computed(function () {
        return viewModel.data().length > 0;
    });	
	self.buttonText = ko.computed(function () {
        if (viewModel.showData()) {
            return "Clear";
            
        } else {
            return "Get names";
        }
    });	
	
	self.getList = function () {
        if (viewModel.data().length > 0) {
        	viewModel.data([]);
        } else {
        	api.getHelloWorld(function(response){
        		console.log("Api retuns data");    
        	    //this generalResponse function is defined in apiProxy.js, it encapsulate the response string as a json object 
        	    var result = new generalResponse(response);    
        	    if (result.isSuccess()) {
        	        console.log("Api retuns data with success");        
        	        self.data(result.data);
        	    } else {
        	        handleUnsuccess(result);
        	    }
        	}, handleError);                  
        }
    };
}


//This function is used to handle unsuccess return from api, usual show an error message and redirect to other page
function handleUnsuccess(result) {
    console.log("api returns data with unsuccess");
}

//This function is use to handle error when calling api.
function handleError(response) {
    console.log("can't get api data");
}


function updateNameList(response,model){
	if(response.success){
		//alert(response.data);
		model(response.data);
	}
};