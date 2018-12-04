package com.wirebrain.friends;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
//import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.wirebrain.friends.model.Friend;

public class SystemTests {

	
//	@Test
	public void testCreateReadDelete() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		
		String url = "http://localhost:8080/friend";
		
		Friend friend = new Friend("Gordon", "More");
		ResponseEntity<Friend> entity = restTemplate.postForEntity(url, friend, Friend.class);
		ResponseEntity<Friend[]> friends = restTemplate.getForEntity(url, Friend[].class);
		
		Assertions.assertThat(friends.getBody())
			.extracting(Friend::getFirstName).containsOnly("Gordon");
		
		restTemplate.delete(url + "/" + entity.getBody().getId());
		
		Assertions.assertThat(
				restTemplate.getForEntity(url, Friend[].class)
				.getBody()).isEmpty();
		
	}
	
//	  @Test
	  public void testErrorHandlingReturnsBadRequest() {
	    RestTemplate restTemplate = new RestTemplate();
	    String url = "http://localhost:8080/wrong";
	    try {
	      restTemplate.getForEntity(url, String.class);
	    } catch (HttpClientErrorException e) {
	      Assert.assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
	    }
	  }
}
