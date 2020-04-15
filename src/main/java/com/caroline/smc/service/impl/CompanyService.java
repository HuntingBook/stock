package com.caroline.smc.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caroline.smc.entity.Company;
import com.caroline.smc.entity.StockExchange;
import com.caroline.smc.exception.AlreadyExistException;
import com.caroline.smc.exception.NotExistException;
import com.caroline.smc.repositories.ICompanyRepository;
import com.caroline.smc.repositories.IStockExchangeRepository;
import com.caroline.smc.service.ICompanyService;

@Service
public class CompanyService implements ICompanyService {
	@Autowired
	protected ICompanyRepository companyRepository;

	@Autowired
	protected IStockExchangeRepository stockExchangeRepository;

	@Override
	public Optional<Company> getCompanyById(Long companyId) {
		Optional<Company> company = companyRepository.findById(companyId);
		return company;
	}

	@Override
	public List<Company> getAllCompanies(Optional<String> keyword) {
		List<Company> companies = null;
		Stream<Company> companyStream = null;
		if (keyword.isPresent()) {
			companyStream = companyRepository.fetchCompanies(keyword.get()).stream();
		} else {
			companyStream = StreamSupport.stream(companyRepository.findAll().spliterator(), false);
		}
		
		companies = companyStream.map(company -> {
			if (Strings.isNotEmpty(company.getStockExchangeIds())) {
				List<Long> ids = Arrays.stream(company.getStockExchangeIds().split(","))
						.map(stockExchangeId -> Long.valueOf(stockExchangeId)).collect(Collectors.toList());
				company.setStockExchanges((List<StockExchange>) stockExchangeRepository.findAllById(ids));
				return company;
			}
			return null;
		}).collect(Collectors.toList());
		
		return companies;
	}

	@Override
	public void addCompany(Company company) {
		if (companyRepository.findByName(company.getName()).isPresent()) {
			throw new AlreadyExistException("Company name already exist! Please choose another company name.");
		}
		companyRepository.save(company);
	}

	@Override
	public void updateCompany(Company company) {
		if (getCompanyById(company.getId()).isEmpty()) {
			throw new NotExistException("Company does not exist! You are not allowed to update it.");
		}
		companyRepository.save(company);
	}

	@Override
	public void deleteCompany(Long companyId) {
		if (companyRepository.existsById(companyId)) {
			throw new NotExistException("Company does not exist! You are not allowed to delete it.");
		}
		companyRepository.deleteById(companyId);
	}
}
