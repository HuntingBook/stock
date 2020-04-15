package com.caroline.smc.service;

import java.util.List;
import java.util.Optional;

import com.caroline.smc.entity.Sector;

public interface ISectorService {

	List<Sector> getAllSectors();

	Optional<Sector> getSectorById(Long sectorId);

	Optional<Sector> getSectorByName(String name);

	void addSector(Sector sector);

	void updateSector(Sector sector);

	void deleteSector(Long sectorId);
}