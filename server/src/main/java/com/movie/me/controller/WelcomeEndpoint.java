package com.movie.me.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class WelcomeEndpoint {
	@RequestMapping("/rest")
	public Object greeting() {
		Map<String, String> returnMap = new HashMap<String, String>();
		
		returnMap.put("first", "hello");
		returnMap.put("second", "world");
		return returnMap;
	}
}
