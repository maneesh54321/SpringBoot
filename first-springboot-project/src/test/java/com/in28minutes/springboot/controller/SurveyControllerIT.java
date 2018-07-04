package com.in28minutes.springboot.controller;

import static org.junit.Assert.assertTrue;

import java.nio.charset.Charset;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.test.context.junit4.SpringRunner;

import com.in28minutes.springboot.Application;
import com.in28minutes.springboot.model.Question;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SurveyControllerIT {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();
	
	HttpHeaders headers = new HttpHeaders();

	@Before
	public void before() {
		headers.add("Authorization", createHttpHeaders("user1","secret1"));
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	}

	@Test
	public void retrieveQuestion() {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/surveys/Survey1/questions/Question1"), HttpMethod.GET, entity, String.class);
		String expected = "{id:Question1,description:Largest Country in the World,correctAnswer:Russia}";
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	@Test
	public void addQuestion() {
		Question question = new Question("doesntMatter", "TestQuestion", "tes1",
				Arrays.asList("test1", "test2", "test3", "test4"));
		HttpEntity<Question> entity = new HttpEntity<Question>(question, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/surveys/Survey1/questions"),
				HttpMethod.POST, entity, String.class);
		String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
		assertTrue(actual.contains("/surveys/Survey1/questions/"));
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
	
	private String createHttpHeaders(String userID, String password) {
		String auth=userID+":"+password;
		byte[] encodedAuth=Base64.encode(auth.getBytes(Charset.forName("US-ASCII")));
		String headerValue="Basic "+new String(encodedAuth);
		return headerValue;
	}

}
