package com.example.demo.reader;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;

public class PayForMybatis 
{
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
	
    private Long id;
    private Long amount;
    private String tx_Name;
    private LocalDateTime tx_Date_Time;

    public PayForMybatis() {}
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getTxName() {
		return tx_Name;
	}

	public void setTxName(String txName) {
		this.tx_Name = txName;
	}

	public LocalDateTime getTxDateTime() {
		return tx_Date_Time;
	}

	public void setTxDateTime(LocalDateTime txDateTime) {
		this.tx_Date_Time = txDateTime;
	}
}
