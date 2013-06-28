package stone.takeaway.controller.subcontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

@RequestMapping(value="/sub")
public class SubDemoController {
	public SubDemoController() {
		System.out.println("SubDemoController construct");
	}
	@RequestMapping(value="/index2")
	@ResponseBody
    public String home() {
		return "hello this is index2";
    }
	@RequestMapping(value="/demo1", produces = "application/json")
	@ResponseBody
    public Map<String,Object> demo1() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("result", "demo1");
		return map;
    }

		
}
