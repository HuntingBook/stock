package com.caroline.smc.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.caroline.smc.entity.Sector;

@Repository
public interface ISectorRepository extends CrudRepository<Sector, Long> {
	Optional<Sector> findByName(String name);
}