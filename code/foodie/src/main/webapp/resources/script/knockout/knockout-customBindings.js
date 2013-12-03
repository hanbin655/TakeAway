
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

ko.bindingHandlers.starRating = {

    init: function(element, valueAccessor) { 
        maxRate = 5;
        var observable = valueAccessor();
        var values = observable.split(" ",2);
        
        var target_score = values[0];
        var class_name = values[1];
        console.log('values: '+ class_name);

        var tmp_html = '<input name="'+class_name+'" type="radio" class="' + class_name + ' star {split:2}" value="0.5"/>';
        tmp_html += '<input name="'+class_name+'" type="radio" class="' + class_name + ' star {split:2}" value="1.0"/>';
        tmp_html += '<input name="'+class_name+'" type="radio" class="' + class_name + ' star {split:2}" value="1.5"/>';
        tmp_html += '<input name="'+class_name+'" type="radio" class="' + class_name + ' star {split:2}" value="2.0"/>';
        tmp_html += '<input name="'+class_name+'" type="radio" class="' + class_name + ' star {split:2}" value="2.5"/>';
        tmp_html += '<input name="'+class_name+'" type="radio" class="' + class_name + ' star {split:2}" value="3.0"/>';
        tmp_html += '<input name="'+class_name+'" type="radio" class="' + class_name + ' star {split:2}" value="3.5"/>';
        tmp_html += '<input name="'+class_name+'" type="radio" class="' + class_name + ' star {split:2}" value="4.0"/>';
        tmp_html += '<input name="'+class_name+'" type="radio" class="' + class_name + ' star {split:2}" value="4.5"/>';
        tmp_html += '<input name="'+class_name+'" type="radio" class="' + class_name + ' star {split:2}" value="5.0"/>';
        old_content = $(element).html();
        $(element).html(tmp_html+old_content)
        $(element).children('input[type=radio].'+class_name).rating();
        
    },
    update: function(element, valueAccessor) {
        //var target_score = valueAccessor();
        var observable = valueAccessor();
        var values = observable.split(" ",2);
        var target_score = values[0];
        var class_name = values[1];
        //console.log(observable);

        // depend on commonFunction.parseDataIntoNumber
        target_score = parseDataIntoNumber(target_score,maxRate);

        if (target_score != 0){

            rate_idx = parseFloat((target_score*2).toFixed(0))-1;
            $(element).children('input.'+class_name).rating("enable");
            $(element).children('input.'+class_name).rating('select',rate_idx);
            $(element).children('input.'+class_name).rating('disable')
        }
    }    
};

