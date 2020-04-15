package com.caroline.smc.service;

import java.util.List;
import java.util.Optional;

import com.caroline.smc.entity.StockExchange;

public interface IStockExchangeService {

	List<StockExchange> getAllStockExchanges(Optional<String> keyword);

	Optional<StockExchange> getStockExchangeById(Long stockExchangeId);

	Optional<StockExchange> getStockExchangeByName(String name);

	void addStockExchange(StockExchange stockExchange);

	void updateStockExchange(StockExchange stockExchange);

	void deleteStockExchange(Long stockExchangeId);
}