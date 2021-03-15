package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.data.MyPojo;

@RestController
@RequestMapping("/serviceTwo")
public class SecondServiceController {

	@GetMapping("/heartbeat")
	public String heartbeat() {
		System.out.println("Hi heartbeat");
		return new String("heartbeat");
	}

	@GetMapping("/displayB")
	public String displayB() {
		String str = "Working from serviceB";
		return str;
	}

	@GetMapping(value = "/createPojo")
	public MyPojo createPojo() {
		System.out.println("Hi");
		MyPojo myPojo = new MyPojo();
		myPojo.setNum(124);
		myPojo.setName("Swati");
		return myPojo;
	}
	
	@PostMapping(value="/actualCreatePojo",consumes = "application/json")
	public MyPojo actualCreatePojo(@RequestBody MyPojo pojo) {
		MyPojo myPojo = new MyPojo();
		myPojo.setNum(pojo.getNum());
		myPojo.setName(pojo.getName());
		return myPojo;
	}
}
