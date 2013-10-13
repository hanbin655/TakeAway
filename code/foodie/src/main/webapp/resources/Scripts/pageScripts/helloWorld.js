
function renderPage() {
    console.log("Start calling api");   
    viewModel = new localViewModel();
}

function localViewModel(){	
	var self = this;
	self.data = ko.observableArray([]);
	self.showData = ko.computed(function () {
        return self.data().length > 0;
    });	
	self.buttonText = ko.computed(function () {
        if (self.showData()) {
            return "Clear";
            
        } else {
            return "Get names";
        }
    });	
	
	self.getList = function () {
        if (self.data().length > 0) {
        	self.data([]);
        } else {
        	api.getHelloWorld(function(response){
        		console.log("Api retuns data");
        	    if (response.isSuccess) {
        	        console.log("Api retuns data with success");        
        	        self.data(result.data);
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


function updateNameList(response,model){
	if(response.success){
		//alert(response.data);
		model(response.data);
	}
};