package com.caroline.smc.service;

import java.util.List;
import java.util.Optional;

import com.caroline.smc.entity.IPO;

public interface IIPOService {

	List<IPO> getAllIPOs(Optional<String> keyword);

	Optional<IPO> getIPOById(Long ipoId);

	void addIPO(IPO ipo);

	void updateIPO(IPO ipo);

	void deleteIPO(Long ipoId);
}