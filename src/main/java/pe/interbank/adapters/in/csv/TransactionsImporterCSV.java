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
		/*
		 * Se inicializa el reader con el File correspondiente al path ingresado por el usuario
		 * y se establece el charset UTF-8 para evitar problemas con los tildes
		 * */
		try(Reader reader = new InputStreamReader(new FileInputStream(dataFile), StandardCharsets.UTF_8)){
			CsvToBean<TransactionCsvDTO> transBean = new CsvToBeanBuilder<TransactionCsvDTO>(reader)//Se mapea cada transacción para obtener un bean por cada una de ellas
					.withType(TransactionCsvDTO.class)//Se establece la clase a la que perteneceran los objetos resultantes
					.withIgnoreLeadingWhiteSpace(true)//Se ignoran espacios en blancos al inicio de los campos
					.withThrowExceptions(false)//Evita que el parser se caiga si consigue una fila mal formateada
					.build();
			List<TransactionCsvDTO> transactionsDTO = transBean.parse();//Se aplica el parse y se obtiene una lista de objetos
			List<Transaction> transactions = transactionsDTO.stream()
					.map(TransactionsImporterCSV::mapperDTOtoTransaction)//Se obtiene una lista de objetos independientes del origen de los datos
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
