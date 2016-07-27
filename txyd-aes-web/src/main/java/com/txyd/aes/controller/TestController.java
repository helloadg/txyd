

package com.txyd.aes.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
public class TestController{	

	@RequestMapping(value="/get", method=RequestMethod.POST)
	public Map<String, Object> get( @RequestBody Map<String,Object>  map){		
		return map;
	}	
}

