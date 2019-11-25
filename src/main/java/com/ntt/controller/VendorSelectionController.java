package com.ntt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class VendorSelectionController {

	private static final String INIT = "Initialized";

	/**
	 * {@code GET  Checks the Initialization of API's
	 *
	 * @return the {@link ResponseEntity} with status {@code 200 (ok)} and with body the String as Initialized
	 *
	 */
	@GetMapping("/init")
	public ResponseEntity<String> init() {

		return  new ResponseEntity<>(INIT, HttpStatus.OK);

	}

}
