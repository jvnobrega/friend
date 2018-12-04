package com.wirebrain.friends.controller;

import java.util.Optional;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wirebrain.friends.model.Friend;
import com.wirebrain.friends.service.FriendService;

@RestController
public class FriendController {

	@Autowired
	private FriendService friendService;

	@GetMapping("/friend")
	public ResponseEntity<Iterable<Friend>> read() {
		return new ResponseEntity<>(
				friendService.findAll(), 
				HttpStatus.OK);
	}
	
	@GetMapping("/friend/{id}")
	public ResponseEntity<Optional<Friend>> findById(@PathVariable Integer id) {
		return new ResponseEntity<>(
				friendService.findById(id), 
				HttpStatus.OK);
	}
	
	@GetMapping("/friend/search")
	public ResponseEntity<Iterable<Friend>> findByQuery(
			@RequestParam(value = "first", required=false) String firstName, 
			@RequestParam(value = "last", required=false) String lastName) {
		
		return new ResponseEntity<>(
				friendService.findByFirstNameOrLastName(firstName, lastName), 
				HttpStatus.OK);
	}
	
	@PostMapping("/friend")
	public ResponseEntity<Friend> create(@Valid @RequestBody Friend friend) {
			return new ResponseEntity<>(
					friendService.save(friend), 
					HttpStatus.CREATED);
	}
	
	@PutMapping("/friend")
	public ResponseEntity<Friend> update(@RequestBody Friend friend) {
		if(friendService.findById(friend.getId()).isPresent()) {
			return new ResponseEntity<>(friendService.save(friend), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(friend, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/friend/{id}")
	public void delete(@PathVariable Integer id) {
		friendService.deleteById(id);	
	}

	  @GetMapping("/wrong")
	  Friend somethingIsWrong() {
	    throw new ValidationException("Something is wrong");
	  }

	  @ResponseStatus(HttpStatus.BAD_REQUEST)
	  @ExceptionHandler(ValidationException.class)
	  String exceptionHandler(ValidationException e) {
	    return e.getMessage();
	  }
}
