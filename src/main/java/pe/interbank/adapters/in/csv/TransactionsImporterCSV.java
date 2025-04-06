package pe.interbank.adapters.in.csv;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import pe.interbank.adapters.in.csv.TransactionCsvDTO;
import pe.interbank.domain.models.Transaction;
import pe.interbank.ports.in.TransactionsImporter;

public class TransactionsImporterCSV implements TransactionsImporter{

	@Override
	public List<Transaction> importer(File dataFile) throws Exception {
		try(Reader reader = new InputStreamReader(new FileInputStream(dataFile), StandardCharsets.UTF_8)){
			CsvToBean<TransactionCsvDTO> transBean = new CsvToBeanBuilder<TransactionCsvDTO>(reader)
					.withType(TransactionCsvDTO.class)
					.withIgnoreLeadingWhiteSpace(true)
					.withThrowExceptions(false)
					.build();
			List<TransactionCsvDTO> transactionsDTO = transBean.parse();
			List<Transaction> transactions = transactionsDTO.stream()
					.map(TransactionsImporterCSV::mapperDTOtoTransaction)
					.toList();
			return transactions;
		} catch (FileNotFoundException e) {
			throw new Exception("No se encontró el CSV indicado");
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	private static Transaction mapperDTOtoTransaction(TransactionCsvDTO transDTO) {
		return new Transaction(
				transDTO.getId(),
				transDTO.getType(),
				transDTO.getAmount()
				);
	}
	
}
