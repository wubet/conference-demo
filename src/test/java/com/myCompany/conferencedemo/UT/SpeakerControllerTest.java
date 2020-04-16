package com.myCompany.conferencedemo.UT;

import com.myCompany.conferencedemo.controllers.SpeakersController;
import com.myCompany.conferencedemo.services.SpeakerService;
import java.util.Collections;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(SpeakersController.class)
public class SpeakerControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private SpeakerService speakerService;

  @Test
  public void getAllSpeakers() throws Exception{

    Mockito.when(speakerService.listAllSpeakers()).thenReturn(
        Collections.emptyList()
    );
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/speakers")
        .accept(MediaType.APPLICATION_JSON))
        .andReturn();

    System.out.println(mvcResult.getResponse());

    Mockito.verify(speakerService).listAllSpeakers();
  }

  @Test
  public void contextLoads() {
  }


}
