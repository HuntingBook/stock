package com.caroline.smc.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "company")
@EntityListeners(AuditingEntityListener.class)
public class Company{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String name;

	private String turnover;

	private String ceo; 
	
	private String boardOfDirectors;

//	@Convert(converter = StockExchangeWrapperConverter.class)
//    @Column(columnDefinition = "text")
	private String stockExchangeIds;
	
	@Transient
	private List<StockExchange> stockExchanges;

	@NotBlank
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sectorId")
	private Sector sector;

	private String writeup;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTurnover() {
		return turnover;
	}

	public void setTurnover(String turnover) {
		this.turnover = turnover;
	}

	public String getCeo() {
		return ceo;
	}

	public void setCeo(String ceo) {
		this.ceo = ceo;
	}

	public String getBoardOfDirectors() {
		return boardOfDirectors;
	}

	public void setBoardOfDirectors(String boardOfDirectors) {
		this.boardOfDirectors = boardOfDirectors;
	}

	public String getStockExchangeIds() {
		return stockExchangeIds;
	}

	public void setStockExchangeIds(String stockExchangeIds) {
		this.stockExchangeIds = stockExchangeIds;
	}

	@Transient
	public List<StockExchange> getStockExchanges() {
		return stockExchanges;
	}

	@Transient
	public void setStockExchanges(List<StockExchange> stockExchanges) {
		this.stockExchanges = stockExchanges;
	}

	public Sector getSector() {
		return sector;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}

	public String getWriteup() {
		return writeup;
	}

	public void setWriteup(String writeup) {
		this.writeup = writeup;
	}
}

