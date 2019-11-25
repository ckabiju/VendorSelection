package com.ntt.Controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.ntt.config.JwtAuthenticationEntryPoint;
import com.ntt.config.JwtTokenUtil;
import com.ntt.controller.VendorSelectionController;
import com.ntt.service.JwtUserDetailsService;

@RunWith(SpringRunner.class)
@WebMvcTest(VendorSelectionController.class)
public class VendorSelectionControllerTest {

	@Autowired
	public MockMvc mvc;

	@MockBean
	public JwtUserDetailsService jwtUserDetailsService;
	@MockBean
	public JwtTokenUtil jwtTokenUtil;
	@MockBean
	public JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Test
	public void getInit() throws Exception {
		URI myURI = new URI("/api/init");
		mvc.perform(get(myURI)).andExpect(status().isOk());
	}
}
