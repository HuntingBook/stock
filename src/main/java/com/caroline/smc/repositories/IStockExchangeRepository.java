package com.caroline.smc.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.caroline.smc.entity.StockExchange;

@Repository
public interface IStockExchangeRepository extends CrudRepository<StockExchange, Long> {

	Optional<StockExchange> findByName(String name);
	
	@Query("SELECT s FROM StockExchange s WHERE lower(s.name) like lower(concat(?1, '%'))")
	List<StockExchange> fetchStockExchanges(@Param("keyword") String keyword);
}