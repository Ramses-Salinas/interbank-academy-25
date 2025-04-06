package pe.interbank.domain;

import java.math.BigDecimal;
import java.util.List;

import pe.interbank.domain.models.Transaction;
import pe.interbank.domain.utils.TransactionsProcessUtil;

public class TransactionsProcess {
	
	public static String reportOfTransactions(List<Transaction> trans) throws Exception {
		//Se obtiene el resultado de cada operación
		BigDecimal finalBalance = TransactionsProcessUtil.finalBalance(trans);
		String highestAmount = TransactionsProcessUtil.highestAmount(trans);
		String countTotalAmounts = TransactionsProcessUtil.countTotalAmounts(trans);
		
		
		return String.format("Reporte de Transacciones"
				+ "\n---------------------------------------------"
				+ "\nBalance Final: %s"
				+ "\nTransacción de Mayor Monto: %s"
				+ "\nConteo de Transacciones: %s"
				, finalBalance.toString(), highestAmount, countTotalAmounts);
	}
}
