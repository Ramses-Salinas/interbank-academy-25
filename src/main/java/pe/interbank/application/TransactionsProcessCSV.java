package pe.interbank.application;

import java.io.File;
import java.util.List;

import pe.interbank.adapters.in.csv.TransactionsImporterCSV;
import pe.interbank.domain.TransactionsProcess;
import pe.interbank.domain.models.Transaction;
import pe.interbank.ports.out.TransactionsProcessServices;
import pe.interbank.ports.in.TransactionsImporter;

public class TransactionsProcessCSV implements TransactionsProcessServices{

	@Override
	public String resultOfProcess(String path) throws Exception {
		if(!path.endsWith(".csv")) throw new Exception("La ruta especificada no apunta a un CSV");
		
		File csv = new File(path);
		TransactionsImporter importer = new TransactionsImporterCSV();
		List<Transaction> trans = importer.importer(csv);
		
		return TransactionsProcess.reportOfTransactions(trans);
	}
	
}
