package com.caroline.smc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.caroline.smc.entity.Company;
import com.caroline.smc.service.impl.CompanyService;

@RestController
@RequestMapping("${smc.api.prefix}/companies")
public class CompanyController {

	@Autowired
	CompanyService companyService;

	/**
	 * 
	 * @param keyword  to search companies by key word
	 * @return companies
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Company>> readAll(@RequestParam Optional<String> keyword) {
		List<Company> companies = companyService.getAllCompanies(keyword);
		return ResponseEntity.ok().body(companies);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Company> read(@PathVariable("id") Long companyId) {
		Optional<Company> company = companyService.getCompanyById(companyId);
		return ResponseEntity.ok().body(company.get());
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Company> create(@RequestBody Company company) {
		companyService.addCompany(company);
		return ResponseEntity.ok().build();
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Company> update(@RequestBody Company company) {
		companyService.updateCompany(company);
		return ResponseEntity.ok().build();
	}

	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Company> delete(@PathVariable("id") Long companyId) {
		companyService.deleteCompany(companyId);
		return ResponseEntity.ok().build();
	}

}
