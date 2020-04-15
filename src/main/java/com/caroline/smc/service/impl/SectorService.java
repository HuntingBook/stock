package com.caroline.smc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caroline.smc.entity.Sector;
import com.caroline.smc.exception.NotExistException;
import com.caroline.smc.exception.AlreadyExistException;
import com.caroline.smc.repositories.ISectorRepository;
import com.caroline.smc.service.ISectorService;

@Service
public class SectorService implements ISectorService {
	@Autowired
	protected ISectorRepository sectorRepository;

	@Override
	public Optional<Sector> getSectorById(Long sectorId) {
		Optional<Sector> sector = sectorRepository.findById(sectorId);
		return sector;
	}

	@Override
	public Optional<Sector> getSectorByName(String name) {
		Optional<Sector> sector = sectorRepository.findByName(name);
		return sector;
	}

	@Override
	public List<Sector> getAllSectors() {
		return (List<Sector>) sectorRepository.findAll();
	}

	@Override
	public void addSector(Sector sector) {
		if (getSectorByName(sector.getName()).isPresent()) {
			throw new AlreadyExistException("Sector name already exist! Please choose another sector name.");
		}
		sectorRepository.save(sector);
	}

	@Override
	public void updateSector(Sector sector) {
		if (getSectorById(sector.getId()).isEmpty()) {
			throw new NotExistException("Sector does not exist! You are not allowed to update it.");
		}
		sectorRepository.save(sector);
	}

	@Override
	public void deleteSector(Long sectorId) {
		if (sectorRepository.existsById(sectorId)) {
			throw new NotExistException("Sector does not exist! You are not allowed to delete it.");
		}
		sectorRepository.deleteById(sectorId);
	}
}
