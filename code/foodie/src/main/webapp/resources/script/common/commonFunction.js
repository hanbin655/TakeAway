//This functioni will parse data into float number. The return value
//will be bound inside the intervel [0, max_value]
//in_value: the data to be parsed
//max_value: the maximum value that the in_value can reach
//           if greater than max_value then value is set to be zero
function parseDataIntoNumber(in_value,max_value) {
    // set the default value
    var result = 0;

    // check the type if in_value is not a number
    if (typeof(in_value) != typeof(1)) {
        try{
            // if succeed
            result = parseFloat(in_value);
        }catch(err) {
            //otherwise set the result to 0
            result = 0;
        }
    }else {
        // if it is already a number
        result = in_value;
    }
    
    // if the number is greater than max_value
    if (result > max_value) {
        // 0 is set as the return value
        result = 0;
    }else if (result < 0) {
        // if the number is less than 0 
        result = 0;
    }

    return result;

}