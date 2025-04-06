package pe.interbank.domain.models;

import java.math.BigDecimal;

public class Transaction {
	
	private Integer id;
	private String type;
	/*
	 * Dado el contexto de transacciones bancarias
	 * y que BigDecimal brinda mayor exactitud decimal
	 * se consideró más conveniente BigDecimal que double
	 * */
	private BigDecimal amount;
	
	
	public Transaction(Integer id, String tipo, BigDecimal amount) {
		super();
		this.id = id;
		this.type = tipo;
		this.amount = amount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	
	
}
