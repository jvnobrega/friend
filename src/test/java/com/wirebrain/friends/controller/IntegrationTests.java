package com.wirebrain.friends.controller;

import javax.validation.ValidationException;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wirebrain.friends.model.Friend;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IntegrationTests {

	@Autowired
	private FriendController friendController;
	
	@Test
	public void testCreateReadDelete() {
		Friend friend = new Friend("Gordon", "More");
		Friend friendResult = friendController.create(friend).getBody();
		
		Iterable<Friend> friends = friendController.read().getBody();
		Assertions.assertThat(friends).first().hasFieldOrPropertyWithValue("FirstName", "Gordon");
		
		friendController.delete(friendResult.getId());
		Assertions.assertThat(friendController.read().getBody()).isEmpty();
	}
	
	  @Test(expected = ValidationException.class)
	  public void errorHandlingValidationExceptionThrown() {
	    friendController.somethingIsWrong();

	  }
}
