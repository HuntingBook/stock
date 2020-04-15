package com.caroline.smc.entity;

import org.springframework.stereotype.Component;

@Component
public class StockExchangeWrapperConverter {
//implements AttributeConverter<List<StockExchange>, String> {
//	@Autowired
//	protected IStockExchangeRepository stockExchangeRepository;
//	
//	@Override
//	public List<StockExchange> convertToEntityAttribute(String stockExchangeIds) {
//
//		List<Long> ids = Arrays.stream(stockExchangeIds.split(","))
//				.map(stockExchangeId -> Long.valueOf(stockExchangeId)).collect(Collectors.toList());
//		Iterable<StockExchange> stockExchanges = stockExchangeRepository.findAllById(ids);
//		if(stockExchanges == null) return null;
//		
////		List<StockExchange> stockExchanges = (List<StockExchange>) stockExchangeRepository.findAllById(ids);
//		return (List<StockExchange>)stockExchanges;
//	}
//
//	@Override
//	public String convertToDatabaseColumn(List<StockExchange> stockExchanges) {
//		// TODO Auto-generated method stub
//		List<String> stockExchangeIds = stockExchanges.stream().map(stockExchange -> stockExchange.getId().toString())
//				.collect(Collectors.toList());
//		return String.join(",", stockExchangeIds);
//	}
}