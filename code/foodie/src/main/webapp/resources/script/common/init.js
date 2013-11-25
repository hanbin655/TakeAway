var viewModel = null;

$(function(){
	loadHeaderFooter();
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


function loadHeaderFooter(){	
	$('header').load("header.html");
	$('footer').load("footer.html");
}
