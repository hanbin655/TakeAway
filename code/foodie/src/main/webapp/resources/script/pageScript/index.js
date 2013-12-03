
function renderPage() {
    viewModel = new localViewModel();       
}


function localViewModel(){		
	var self = this;	
	console.log("Start calling api");    
	self.data = ko.observable();	
	self.showData = ko.computed(function () {
        return self.data() && self.data().length > 0;
    });		
	
	var id = 'ag1jb25mdXNlZC1sb3N0chALEgpSZXN0YXVyYW50GAUM';
	api.getRestaurantById(id, handleSuccess, handleError);
}


function handleSuccess(response) {
    console.log("Api retuns data");    
    if (response.isSuccess) {
        console.log("Api retuns data with success");
        viewModel.data(response.data);
    } else {
        handleUnsuccess(response);
    }
}


function handleUnsuccess(response) {
    console.log("api returns data with unsuccess");
}

function handleError(response) {
    console.log("can't get api data");
}