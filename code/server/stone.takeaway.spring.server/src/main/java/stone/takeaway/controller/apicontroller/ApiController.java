package stone.takeaway.controller.apicontroller;

import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import stone.takeaway.controller.QueryResult;
import stone.takeaway.model.People;

@Controller
@RequestMapping(value="/api", produces = "application/json")
public class ApiController {
	@RequestMapping(value="/getNameList",method = RequestMethod.GET)
	@ResponseBody
    public QueryResult<People> getNameList() {		
		ArrayList<People> result = new ArrayList<People>();
		result.add(new People("Bin","Han"));
		result.add(new People("Yan","Li"));
		result.add(new People("Jun","Ze"));		
		return (QueryResult<People>) QueryResult.CreateFromSuccess(result);
    }
	
}
