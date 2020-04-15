package com.caroline.smc.service;

import java.util.List;
import java.util.Optional;

import com.caroline.smc.entity.Company;

public interface ICompanyService {
	List<Company> getAllCompanies(Optional<String> keyword);

	Optional<Company> getCompanyById(Long companyId);

	void addCompany(Company company);

	void updateCompany(Company company);

	void deleteCompany(Long companyId);
}
