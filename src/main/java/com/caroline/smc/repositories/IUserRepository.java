package com.caroline.smc.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.caroline.smc.entity.User;

@Repository
public interface IUserRepository extends CrudRepository<User, Long> {
	Optional<User> findByEmail(String email);

	Optional<User> findByName(String name);

	@Query("SELECT a FROM User a WHERE a.email=:email and a.confirmed=:confirmed")
	List<User> fetchUsers(@Param("email") String email, @Param("confirmed") boolean confirmed);
}
