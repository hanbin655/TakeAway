// To use this template, copy it and rename it with the same name as page
//Every page needs a renderPage function
function renderPage() {
    console.log("Start calling api");    
    viewModel = new localViewModel();

}

function localViewModel(){	
	var self = this;
	self.data = ko.observableArray([]);	
	self.getList = function () {        
        	api.call(function(response){
        		console.log("Api retuns data");
        	    if (response.success) {
        	        console.log("Api retuns data with success");        
        	        self.data(response.data);
        	    } else {
        	        handleUnsuccess(response);
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


