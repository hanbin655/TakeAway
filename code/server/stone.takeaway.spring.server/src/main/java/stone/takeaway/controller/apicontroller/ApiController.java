package stone.takeaway.controller.apicontroller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import stone.takeaway.controller.ResultWrapper;
import stone.takeaway.model.People;

@Controller
@RequestMapping(value="/api", produces = "application/json")
public class ApiController {
	@RequestMapping(value="/getNameList",method = RequestMethod.GET)
	@ResponseBody
    public Map<String,Object> getNameList() {		
		ArrayList<People> result = new ArrayList<People>();
		result.add(new People("Bin","Han"));
		result.add(new People("Yan","Li"));
		result.add(new People("Jun","Ze"));		
		return ResultWrapper.CreateFromSuccess(result);
    }
	
}
