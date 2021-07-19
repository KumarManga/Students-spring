package com.venkat.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "feign",url = "http://localhost:3333/na")
public interface FeignInterface {
	@GetMapping(path = "/nani",produces = "application/json")
	public String getDetails();

}
