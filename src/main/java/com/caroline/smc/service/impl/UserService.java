package com.caroline.smc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caroline.smc.entity.User;
import com.caroline.smc.exception.NotExistException;
import com.caroline.smc.exception.AlreadyExistException;
import com.caroline.smc.repositories.IUserRepository;
import com.caroline.smc.service.IUserService;

@Service
public class UserService implements IUserService {
	@Autowired
	protected IUserRepository userRepository;

	@Override
	public Optional<User> getUserById(Long userId) {
		Optional<User> user = userRepository.findById(userId);
		return user;
	}

	@Override
	public Optional<User> getUserByEmail(String email) {
		Optional<User> user = userRepository.findByEmail(email);
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public void addUser(User user) {
		if (getUserByEmail(user.getEmail()).isPresent()) {
			throw new AlreadyExistException("User email already exist! Please choose another user email.");
		}
		userRepository.save(user);
	}

	@Override
	public void updateUser(User user) {
		if (getUserById(user.getId()).isEmpty()) {
			throw new NotExistException("User does not exist! You are not allowed to update it.");
		}
		userRepository.save(user);
	}

	@Override
	public void deleteUser(Long userId) {
		if (userRepository.existsById(userId)) {
			throw new NotExistException("User does not exist! You are not allowed to delete it.");
		}
		userRepository.deleteById(userId);
	}
}
