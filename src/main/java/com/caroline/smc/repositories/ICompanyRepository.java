package com.caroline.smc.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.caroline.smc.entity.Company;

@Repository
public interface ICompanyRepository extends CrudRepository<Company, Long> {

	Optional<Company> findByName(String name);
	
	@Query("SELECT c FROM Company c, StockExchange s WHERE s.id in (c.stockExchangeIds) and (lower(c.name) like lower(concat(?1, '%')) or lower(s.name) like lower(concat(?1, '%')))")
	List<Company> fetchCompanies(@Param("keyword") String keyword);
}