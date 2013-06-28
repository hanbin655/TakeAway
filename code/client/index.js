
viewModel = {};

$(function () {

    viewModel = renderViewModel();
    ko.applyBindings(viewModel);

});


function renderViewModel() {
    var self = this;
    this.data = ko.observableArray([]);
    this.showData = ko.computed(function () {
        return data().length > 0;
    });
    this.buttonText = ko.computed(function () {
        if (this.showData()) {
            return "Clear";
            
        } else {
            return "Get names";
        }
    });
    this.getList = function () {
        if (this.data().length > 0) {
            this.data([]);
        } else {
            var names = [
            { Forename: 'Bin', Surname: 'Han' },
            { Forename: 'Zejun', Surname: 'Wu' },
            { Forename: 'Yan', Surname: 'Li' },
            { Forename: 'Wenxin', Surname: 'Shi' }];

            this.data(names);
        }
    };
}

