package com.caroline.smc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caroline.smc.entity.IPO;
import com.caroline.smc.exception.NotExistException;
import com.caroline.smc.repositories.IIPORepository;
import com.caroline.smc.service.IIPOService;

@Service
public class IPOService implements IIPOService {
	@Autowired
	protected IIPORepository ipoRepository;

	@Override
	public Optional<IPO> getIPOById(Long ipoId) {
		Optional<IPO> ipo = ipoRepository.findById(ipoId);
		return ipo;
	}

	@Override
	public List<IPO> getAllIPOs(Optional<String> keyword) {
		return (List<IPO>) ipoRepository.findAll();
	}

	@Override
	public void addIPO(IPO ipo) {
		ipoRepository.save(ipo);
	}

	@Override
	public void updateIPO(IPO ipo) {
		if (getIPOById(ipo.getId()).isEmpty()) {
			throw new NotExistException("IPO does not exist! You are not allowed to update it.");
		}
		ipoRepository.save(ipo);
	}

	@Override
	public void deleteIPO(Long ipoId) {
		if (ipoRepository.existsById(ipoId)) {
			throw new NotExistException("IPO does not exist! You are not allowed to delete it.");
		}
		ipoRepository.deleteById(ipoId);
	}
}
