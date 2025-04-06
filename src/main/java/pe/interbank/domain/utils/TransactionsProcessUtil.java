package pe.interbank.domain.utils;

import java.math.BigDecimal;
import java.util.List;

import pe.interbank.domain.models.Transaction;

public class TransactionsProcessUtil {
	
	public static BigDecimal finalBalance(List<Transaction> trans) throws Exception {
		BigDecimal credit = BigDecimal.ZERO;
		BigDecimal debit = BigDecimal.ZERO;
		BigDecimal total = BigDecimal.ZERO;
		
		try {
			for(Transaction tran : trans) {
				
				switch(tran.getType().toLowerCase()) {
					case "débito":
						debit = debit.add(tran.getAmount());
						break;
					case "crédito":
						credit = credit.add(tran.getAmount());
						break;
					default:
						break;
				}		
					
			}
			
			total = credit.subtract(debit);
			
			return total;
		} catch(NullPointerException e) {
			throw new Exception("El CSV no pudo ser leído correctamente o su estructura es incorrecta");
		}
		
	}
	
	public static String highestAmount(List<Transaction> trans) throws Exception {
		try {
			BigDecimal highestValue = trans.get(0).getAmount();
			Integer id = trans.get(0).getId();
			
			for(Transaction tran : trans) {
				if(tran.getAmount().compareTo(highestValue) > 0 ) {
					highestValue = tran.getAmount();
					id = tran.getId();
				}
			}
			return String.format("ID %d - %s", id, highestValue.toString());
			
		} catch(ArrayIndexOutOfBoundsException e) {
			throw new Exception("El CSV tiene 0 transacciones o su estructura es incorrecta");
		} catch(NullPointerException e) {
			throw new Exception("El CSV no pudo ser leído correctamente o su estructura es incorrecta");
		}		
	}
	
	public static String countTotalAmounts(List<Transaction> trans) throws Exception {
		int credit = 0;
		int debit = 0;
		
		try {
			for(Transaction tran : trans) {
				
				switch(tran.getType().toLowerCase()) {
					case "débito":
						debit++;
						break;
					case "crédito":
						credit++;
						break;
					default:
						break;
				}		
					
			}
			
			return String.format("Crédito: %d Débito: %d", credit, debit);
		} catch(NullPointerException e) {
			throw new Exception("El CSV no pudo ser leído correctamente o su estructura es incorrecta");
		}
		
	}
	
}
