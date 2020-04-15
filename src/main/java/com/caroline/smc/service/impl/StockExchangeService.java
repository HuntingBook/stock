package com.caroline.smc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caroline.smc.entity.StockExchange;
import com.caroline.smc.exception.AlreadyExistException;
import com.caroline.smc.exception.NotExistException;
import com.caroline.smc.repositories.IStockExchangeRepository;
import com.caroline.smc.service.IStockExchangeService;

@Service
public class StockExchangeService implements IStockExchangeService {
	@Autowired
	protected IStockExchangeRepository stockExchangeRepository;

	@Override
	public Optional<StockExchange> getStockExchangeById(Long stockExchangeId) {
		Optional<StockExchange> stockExchange = stockExchangeRepository.findById(stockExchangeId);
		return stockExchange;
	}

	@Override
	public List<StockExchange> getAllStockExchanges(Optional<String> keyword) {
		List<StockExchange> stockExchanges = null;
		if (keyword.isPresent()) {
			stockExchanges = stockExchangeRepository.fetchStockExchanges(keyword.get());
		} else {
			stockExchanges = (List<StockExchange>) stockExchangeRepository.findAll();
		}
		return stockExchanges;
	}

	@Override
	public Optional<StockExchange> getStockExchangeByName(String name) {
		Optional<StockExchange> stockExchange = stockExchangeRepository.findByName(name);
		return stockExchange;
	}

	@Override
	public void addStockExchange(StockExchange stockExchange) {
		if (getStockExchangeByName(stockExchange.getName()).isPresent()) {
			throw new AlreadyExistException(
					"StockExchange name already exist! Please choose another stockExchange name.");
		}
		stockExchangeRepository.save(stockExchange);
	}

	@Override
	public void updateStockExchange(StockExchange stockExchange) {
		if (getStockExchangeById(stockExchange.getId()).isEmpty()) {
			throw new NotExistException("StockExchange does not exist! You are not allowed to update it.");
		}
		stockExchangeRepository.save(stockExchange);
	}

	@Override
	public void deleteStockExchange(Long stockExchangeId) {
		if (stockExchangeRepository.existsById(stockExchangeId)) {
			throw new NotExistException("StockExchange does not exist! You are not allowed to delete it.");
		}
		stockExchangeRepository.deleteById(stockExchangeId);
	}
}
