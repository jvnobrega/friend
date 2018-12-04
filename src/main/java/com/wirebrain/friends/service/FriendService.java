package com.wirebrain.friends.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wirebrain.friends.model.Friend;
import com.wirebrain.friends.repository.FriendRepository;

@Service
public class FriendService {

	@Autowired
	private FriendRepository friendRepository;

	public Friend save(Friend friend) {
		return friendRepository.save(friend);
	}

	public Iterable<Friend> findAll() {
		return friendRepository.findAll();
	}

	public void deleteById(Integer id) {
		friendRepository.deleteById(id);
	}

	public Optional<Friend> findById(Integer id) {
		return friendRepository.findById(id);
	}

	public Iterable<Friend> findByFirstNameOrLastName(String firstName, String lastName) {
		if (firstName != null && lastName != null) {
			return friendRepository.findByFirstNameAndLastName(firstName, lastName);
		} else if (firstName != null) {
			return friendRepository.findByFirstName(firstName);
		} else if (lastName != null) {
			return friendRepository.findByLastName(lastName);
		} else {
			return friendRepository.findAll();
		}
	}

	public void deleteAll() {
		friendRepository.deleteAll();
	}
}
