package pe.interbank.adapters.in;

import pe.interbank.application.TransactionsProcessCSV;
import pe.interbank.ports.out.TransactionsProcessServices;

public class Main {
	
	public static void main(String [] args) {
		if(args.length == 1) {
			TransactionsProcessServices process = new TransactionsProcessCSV();
			try {
				String result = process.resultOfProcess(args[0]);
				System.out.println(result);
			} catch (Exception e) {
				System.out.println("Error: "+e.getMessage());
				//e.printStackTrace();
			}
		}else {
			System.out.println("Cantidad de argumentos invalido, debe ingresar como argumento una ruta que apunte a un CSV");
			System.out.println("Consejo: coloque la ruta dentro de comillas");
		}
	}
}
