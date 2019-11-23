package com.ntt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VendorSelectionController {

	@RequestMapping({ "/hello" })
	public String firstPage() {
		return "Hello World";
	}
}
