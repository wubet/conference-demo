package com.myCompany.conferencedemo.UT;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.myCompany.conferencedemo.controllers.SessionsController;
import com.myCompany.conferencedemo.services.SessionService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SessionsController.class)
class SessionControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SessionService sessionService;

	@Test
	public void getAllSessions() throws Exception{
		mockMvc.perform(get("/api/v1/sessions"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(content().json("[]"));

		verify(sessionService, times(1)).listAllSessions();
	}

	@Test
	public void contextLoads() {
	}

}
