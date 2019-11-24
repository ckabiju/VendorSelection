package com.ntt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class VendorSelectionController {

	@RequestMapping({ "/hello" })
	public String firstPage() {
		return "Hello World First Code commit";
	}
	
	@RequestMapping({ "/test" })
	public String testPage() {
		return "Testing Hello World";
	}

	@GetMapping("/init")
	public ResponseEntity<?> init() {

		return  new ResponseEntity<>(null, HttpStatus.OK);

	}

}
