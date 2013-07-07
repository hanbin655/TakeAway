package stone.takeaway.controller;

import java.util.HashMap;
import java.util.Map;

public class ResultWrapper {	
	public static Map<String,Object> CreateFromSuccess(Object result){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("data", result);
		return map;		
	}
	
}
