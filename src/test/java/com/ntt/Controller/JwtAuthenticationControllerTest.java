package com.ntt.Controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;
import com.ntt.config.JwtAuthenticationEntryPoint;
import com.ntt.config.JwtTokenUtil;
import com.ntt.hibernate.Entity.DAOUser;
import com.ntt.hibernate.dao.UserDao;
import com.ntt.model.JwtRequest;
import com.ntt.model.UserDTO;
import com.ntt.service.JwtUserDetailsService;

@ActiveProfiles("test")

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class JwtAuthenticationControllerTest {

	@MockBean
	private UserDao userDao;

	@Autowired
	public MockMvc mvc;

	@Autowired
	public JwtUserDetailsService jwtUserDetailsService;
	@MockBean
	public JwtTokenUtil jwtTokenUtil;
	@MockBean
	public JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Test
	public void createAuthenticationTokenTest() throws Exception {
		DAOUser user = new DAOUser();
		user.setUsername("nttuser");
		user.setPassword("$2a$10$HwR8J.ssfQw07AezBAJYMe1OOkAFXYwRMBg2uD0qQSJ0wVA4p6O4m");

		JwtRequest jwtRequest = new JwtRequest();
		jwtRequest.setUsername("nttuser");
		jwtRequest.setPassword("password");
		Gson gson = new Gson();
		String json = gson.toJson(jwtRequest);

		given(userDao.findByUsername(Mockito.anyString())).willReturn(user);
		URI myURI = new URI("/authenticate");
		mvc.perform(post(myURI).with(SecurityMockMvcRequestPostProcessors.user("nttuser").password("password"))
				.contentType(MediaType.APPLICATION_JSON).content(json)).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void createAuthenticationTokenUnauthorizedTest() throws Exception {

		JwtRequest jwtRequest = new JwtRequest();
		jwtRequest.setUsername("nttuser");
		jwtRequest.setPassword("password1");
		Gson gson = new Gson();
		String json = gson.toJson(jwtRequest);
		URI myURI = new URI("/authenticate");

		given(userDao.findByUsername(Mockito.anyString())).willReturn(null);
		mvc.perform(post(myURI).with(SecurityMockMvcRequestPostProcessors.user("nttuser").password("password"))
				.contentType(MediaType.APPLICATION_JSON).content(json)).andDo(print())
				.andExpect(status().isUnauthorized());

	}

	@SuppressWarnings("deprecation")
	@Test
	public void saveUserTest() throws Exception {
		UserDTO userdto = new UserDTO();
		userdto.setUsername("nttuser");
		userdto.setPassword("password");

		DAOUser user = new DAOUser();
		user.setUsername("nttuser");
		user.setPassword("$2a$10$HwR8J.ssfQw07AezBAJYMe1OOkAFXYwRMBg2uD0qQSJ0wVA4p6O4m");
		Gson gson = new Gson();
		String json = gson.toJson(userdto);
		given(userDao.save(Mockito.anyObject())).willReturn(user);

		given(userDao.findByUsername(Mockito.anyString())).willReturn(null);
		URI myURI = new URI("/register");

		mvc.perform(post(myURI).with(SecurityMockMvcRequestPostProcessors.user("nttuser").password("password"))
				.contentType(MediaType.APPLICATION_JSON).content(json)).andDo(print()).andExpect(status().isOk());

		given(userDao.findByUsername(Mockito.anyString())).willReturn(user);

		mvc.perform(post(myURI).with(SecurityMockMvcRequestPostProcessors.user("nttuser").password("password"))
				.contentType(MediaType.APPLICATION_JSON).content(json)).andDo(print()).andExpect(status().isOk());
	}
}
