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

import com.caroline.smc.entity.IPO;
import com.caroline.smc.service.impl.IPOService;

@RestController
@RequestMapping("${smc.api.prefix}/ipos")
public class IPOController {

	@Autowired
	IPOService ipoService;

	/**
	 * 
	 * @param keyword  to search ipos by key word
	 * @return ipos
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<IPO>> readAll(@RequestParam Optional<String> keyword) {
		List<IPO> ipos = ipoService.getAllIPOs(keyword);
		return ResponseEntity.ok().body(ipos);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<IPO> read(@PathVariable("id") Long ipoId) {
		Optional<IPO> ipo = ipoService.getIPOById(ipoId);
		return ResponseEntity.ok().body(ipo.get());
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<IPO> create(@RequestBody IPO ipo) {
		ipoService.addIPO(ipo);
		return ResponseEntity.ok().build();
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<IPO> update(@RequestBody IPO ipo) {
		ipoService.updateIPO(ipo);
		return ResponseEntity.ok().build();
	}

	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<IPO> delete(@PathVariable("id") Long ipoId) {
		ipoService.deleteIPO(ipoId);
		return ResponseEntity.ok().build();
	}

}
