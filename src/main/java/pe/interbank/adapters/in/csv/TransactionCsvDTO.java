package pe.interbank.adapters.in.csv;

import java.math.BigDecimal;

import com.opencsv.bean.CsvBindByName;

public class TransactionCsvDTO {
	
	@CsvBindByName(column = "id")
	private Integer id;
	
	@CsvBindByName(column = "tipo")
	private String type;
	
	@CsvBindByName(column = "monto")
	private BigDecimal amount;

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
