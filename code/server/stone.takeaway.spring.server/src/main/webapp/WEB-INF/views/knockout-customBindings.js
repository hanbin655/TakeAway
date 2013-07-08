
ko.bindingHandlers.fadeInOut = {    
    update: function (element, valueAccessor, allBindingsAccessor) {
        var visible = ko.utils.unwrapObservable(valueAccessor());
        if (visible) {
            $(element).show('fade',500);
        } else {
            $(element).hide('fade', 500);
        }
        
    }
}


ko.bindingHandlers.accordion = {
    init: function (element, valueAccessor, allBindingsAccessor) {
        $(element).accordion();;
    },
    update: function (element, valueAccessor, allBindingsAccessor) {
        $(element).accordion("refresh");;
    }
   
}
