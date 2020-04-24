package com.springmvc.controllor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Wang danning
 * @create: 2020-02-10 20:46
 **/
@Controller
public class IndexController {

	@RequestMapping("/index/{id}")
	@ResponseBody
	public Map<String, String> index() {
		Map<String, String> map = new HashMap<>();
		System.out.println("IndexController index()");
		map.put("index", "index");
		return map;
	}
}
