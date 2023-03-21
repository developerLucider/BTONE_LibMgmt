package com.jincomp.jintest.web.jin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/swagger/api")
public class SwaggerController {

	
	@GetMapping("/hello")
	public String Hello() {
		return "hello";
	}
}
