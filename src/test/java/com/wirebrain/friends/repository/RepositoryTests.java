package com.wirebrain.friends.repository;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wirebrain.friends.model.Friend;
import com.wirebrain.friends.repository.FriendRepository;

@RunWith(SpringRunner.class)

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RepositoryTests {

	@Autowired
	private FriendRepository friendRepository;

	@Test
	public void testCreateReadDelete() {
		Friend friend = new Friend("Gordon", "Moore");

		friendRepository.save(friend);

		Iterable<Friend> friends = friendRepository.findAll();
		Assertions.assertThat(friends).extracting(Friend::getFirstName).containsOnly("Gordon");

		friendRepository.deleteAll();
		Assertions.assertThat(friendRepository.findAll()).isEmpty();
	}
}
