package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.data.MyPojo;
import com.netflix.discovery.shared.Application;

@RestController
@RequestMapping("/serviceOne")
public class HelloController {
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/display")
	public String displayServiceA() {
		// return "Service A working";
		String result = restTemplate.getForObject("http://serviceB/serviceTwo/displayB", String.class);
		System.out.println(result);
		return result;

	}
	
	/* This service gets the object from service 2 */
	@GetMapping(value = "/createPojoFromA")
	public MyPojo getPojo() {
		
		
		MyPojo result = restTemplate.getForObject("http://serviceB/serviceTwo/createPojo", MyPojo.class);
		System.out.println(result);
		return result;

	}

	
	/* This service posts the object to service 2  and at service2 it creates new object by copying the contents from the input we provided from service1*/
	@PostMapping(value = "/createPojo", consumes = "application/json")
	public MyPojo createPojo(@RequestBody MyPojo pojo) {
		MyPojo result = restTemplate.postForObject("http://serviceB/serviceTwo/actualCreatePojo",pojo,
				  MyPojo.class);
		System.out.println(result);
		return result;
		
	}
	
	
}
