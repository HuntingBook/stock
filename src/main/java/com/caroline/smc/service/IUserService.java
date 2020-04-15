package com.caroline.smc.service;

import java.util.List;
import java.util.Optional;

import com.caroline.smc.entity.User;

public interface IUserService {
	List<User> getAllUsers();

	Optional<User> getUserById(Long userId);

	Optional<User> getUserByEmail(String name);

	void addUser(User user);

	void updateUser(User user);

	void deleteUser(Long userId);
}
