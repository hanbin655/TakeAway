
viewModel = {};

$(function () {

    viewModel = renderViewModel();
    ko.applyBindings(viewModel);

});


function renderViewModel() {
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
            callApi("getNameList", "GET", null, 
				function(response){
					updateNameList(response,self.data);
				}, 
				showApiError);            
        }
    };
}

function updateNameList(response,model){
	if(response.success){
		//alert(response.data);
		model(response.data);
	}
};