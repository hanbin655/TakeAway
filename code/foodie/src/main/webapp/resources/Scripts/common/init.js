var viewModel = null;

$(function(){
	console.log("Loading page scripts");
	if(typeof renderPage == 'function')
	{
		console.log("Start rending page");
		renderPage();
		ko.applyBindings(viewModel);
	}else {
		console.log("Can't find page rendering function");
	}
});

function generalResponse(responseStr){
	var self = this;
	console.log("Start converting api to general response");
	self = JSON.parse(responseStr);
	self.isSuccess = ko.computed(function(){
		return self.success;
    });
}