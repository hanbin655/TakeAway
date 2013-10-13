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

