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
		if(!path.endsWith(".csv")) throw new Exception("La ruta especificada no apunta a un CSV");// Si el path recibido no se identifica como un CSV no se puede continuar
		
		File csv = new File(path);
		TransactionsImporter importer = new TransactionsImporterCSV();
		List<Transaction> trans = importer.importer(csv);//Se obtiene una lista con todas las transacciones que se encuentran en el CSV
		
		return TransactionsProcess.reportOfTransactions(trans);//Se obtiene el mensaje que se imprimirá como respuesta en consola con los resultados de las operaciones
	}
	
}
