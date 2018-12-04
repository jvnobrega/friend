package com.wirebrain.friends.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wirebrain.friends.model.Friend;

@Repository
public interface FriendRepository extends CrudRepository<Friend, Integer> {

	Iterable<Friend> findByFirstNameAndLastName(String firstName, String lastName);
	Iterable<Friend> findByFirstName(String firstName);
	Iterable<Friend> findByLastName(String lastName);

	
}
