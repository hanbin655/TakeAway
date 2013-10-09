viewModel = {};
$(function () {

    viewModel = menuItemViewModel();
    ko.applyBindings(viewModel);

});
function updateItemList(response,model){
	if(response.success){
		alert(response.data);
		model(response.data);
	}
};
function getMenuItems(){
	var self = this;
	self.menuItems = ko.observableArray([]);
	callApi("getMenuItem?menuId=ag1jb25mdXNlZC1sb3N0choLEgpSZXN0YXVyYW50GAUMCxIETWVudRgHDA", "GET", null, 
	function(response){
		updateItemList(response,self.menuItems);
	}, 
	showApiError);  
}

function menuItemViewModel() {
    var self = this;
    self.menuItems = getMenuItems();
}