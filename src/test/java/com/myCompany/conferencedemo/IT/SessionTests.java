package com.myCompany.conferencedemo.IT;

import com.myCompany.conferencedemo.ConferenceDemoApplication;
import org.springframework.http.HttpHeaders;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ConferenceDemoApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class SessionTests {

  @LocalServerPort
  private int port;

  TestRestTemplate restTemplate = new TestRestTemplate();

  HttpHeaders headers = new HttpHeaders();
  //headers.set("X-COM-PERSIST", "true");

  @Test
  public void testRetrieveSession() throws JSONException {

    HttpEntity<String> entity = new HttpEntity<String>(null, headers);

    ResponseEntity<String> response = restTemplate.exchange(
        createURLWithPort("http://localhost:8080/api/v1/sessions/2"),
        HttpMethod.GET, entity, String.class);

    String expected = "{\n"
        + "    \"session_id\": 2,\n"
        + "    \"session_name\": \"A Better Way to Access Data with Spring Data\",\n"
        + "    \"session_description\": \"\",\n"
        + "    \"session_length\": 60,\n"
        + "    \"speakers\": [\n"
        + "        {\n"
        + "            \"speaker_id\": 4,\n"
        + "            \"first_name\": \"Lori\",\n"
        + "            \"last_name\": \"Vanhoose\",\n"
        + "            \"title\": \"Java Technical Lead\",\n"
        + "            \"company\": \"National Bank\",\n"
        + "            \"speaker_bio\": \"Test\",\n"
        + "            \"speaker_photo\": null\n"
        + "        }\n"
        + "    ]\n"
        + "}";
    JSONAssert.assertEquals(expected, response.getBody(), false);
    assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
  }

  private String createURLWithPort(String url) {
    return url;
  }

}
