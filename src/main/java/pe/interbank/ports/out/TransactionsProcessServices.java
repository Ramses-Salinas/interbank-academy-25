package pe.interbank.ports.out;

import java.util.List;

import pe.interbank.domain.models.Transaction;

public interface TransactionsProcessServices {
	public String resultOfProcess(String path) throws Exception;
}
