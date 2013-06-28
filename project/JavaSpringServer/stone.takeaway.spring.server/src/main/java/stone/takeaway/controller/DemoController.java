package stone.takeaway.controller;

import java.lang.annotation.Inherited;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import stone.takeaway.service.AddService;

@Controller
public class DemoController {
	@Autowired
	AddService addService;
	public DemoController() {
		System.out.println("DemoController construct");
	}
	@RequestMapping(value="/index2")
	@ResponseBody
    public String home() {
		return "hello this is index2";
    }
	@RequestMapping(value="/demo1", produces = "application/json")
	@ResponseBody
    public Map<String,String> demo1() {
		Map<String,String> map = new HashMap<String,String>();
		map.put("result", "demo1");
		return map;
    }
	@RequestMapping(value="/demo2", produces = "application/json")
	@ResponseBody
    public Map<String,Object> demo2(int a, int b) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("result", addService.add(a, b));
		return map;
    }

		
}
