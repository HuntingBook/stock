package com.caroline.smc.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.caroline.smc.entity.Price;

@Repository
public interface IPriceRepository extends CrudRepository<Price, Long> {

	Optional<Price> findByCode(String code);
}