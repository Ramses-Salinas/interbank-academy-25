package pe.interbank.ports.in;

import java.io.File;
import java.util.List;

import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import pe.interbank.domain.models.Transaction;

public interface TransactionsImporter {
	public List<Transaction> importer(File dataFile) throws Exception;
}
