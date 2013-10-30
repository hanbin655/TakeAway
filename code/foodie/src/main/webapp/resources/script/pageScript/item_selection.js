function renderPage() {
    console.log("Start calling api");   
    viewModel = new localViewModel();
}

function localViewModel(){	
	var self = this;
	self.menuItems = ko.observableArray([]);	
	//self.getList = function () {
		api.getMenuItem( function(response){
			console.log("Api retuns data"); 
			if (response.success) {
				console.log("Api retuns data with success");        
				self.menuItems(response.data);
			} else {
				handleUnsuccess(response);
			}
		}, handleError);                  
   // };
}

//This function is used to handle unsuccess return from api, usual show an error message and redirect to other page
function handleUnsuccess(result) {
	console.log(result);
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