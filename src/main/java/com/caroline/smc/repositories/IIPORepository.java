package com.caroline.smc.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.caroline.smc.entity.IPO;

@Repository
public interface IIPORepository extends CrudRepository<IPO, Long> {

	Optional<IPO> findByCode(String code);

	@Query("SELECT i FROM IPO i, Company c, StockExchange s WHERE i.companyId = c.id and i.stockExchangeId = s.id and (lower(c.name) like lower(concat(?1, '%')) or lower(s.name) like lower(concat(?1, '%')))")
	List<IPO> fetchIPOs(@Param("keyword") String keyword);
}