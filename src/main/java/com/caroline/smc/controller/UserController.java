package com.caroline.smc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.caroline.smc.entity.User;
import com.caroline.smc.service.impl.UserService;

@RestController
@RequestMapping("${smc.api.prefix}/users")
public class UserController {

	@Autowired
	UserService userService;

//    @PreAuthorize("hasAnyRole('ROLE_NORMAL','ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> readAll() {
		List<User> users = userService.getAllUsers();
		return ResponseEntity.ok().body(users);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> read(@PathVariable("id") Long userId) {
		Optional<User> user = userService.getUserById(userId);
		return ResponseEntity.ok().body(user.get());
	}

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<User> create(@RequestBody User user) {
		userService.addUser(user);
		return ResponseEntity.ok().build();
	}

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<User> update(@RequestBody User user) {
		userService.updateUser(user);
		return ResponseEntity.ok().build();
	}

	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> delete(@PathVariable("id") Long userId) {
		userService.deleteUser(userId);
		return ResponseEntity.ok().build();
	}

}
