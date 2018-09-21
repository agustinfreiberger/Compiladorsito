package compilator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Analizador {
	
	static Matrix m;
	static int pos = 0;
	static Integer line = 0;
	FileReader fr;
	static BufferedReader br;
	static String linea;
	HashMap<String, Integer> tokenTable = new HashMap<String, Integer>();
	
	public Analizador() {
		tokenTable.put("ID", 421);
		tokenTable.put("Long", 422);
		tokenTable.put("CTE", 420);
		tokenTable.put("<=", 290);
		tokenTable.put(">=", 291);
		tokenTable.put("!=", 292);
		tokenTable.put(":=", 293);
		
	}
	
	public void cargarArchivo(String origen) throws IOException{
		fr = new FileReader(origen);
	    br = new BufferedReader(fr);
	}
	
	public static int getToken () throws IOException   {
		String buffer = "";
	    int hayToken = 0;
	    int estado = 0;
	    	while ((hayToken != -1) || (hayToken != -2) || (pos != linea.toCharArray().length)) {       // -2: token, -1: error
	    		 char c = linea.toCharArray()[pos];
	    		 hayToken = m.returnCasilla(estado, c).getAccion().execute(buffer, c);                   
	    		 estado = m.returnCasilla(estado, c).getEstado();
	    		 pos++;
	    	}
	    	if (hayToken == -1)
	    		// System.out.println(m.returnCasilla(estado, c).getAccion().imprimirError(line)); <-esta accion devolvería el error
	    	
	    	if ((pos == linea.toCharArray().length+1) || (hayToken == 290)) { // salto de linea = 290
	    		linea = br.readLine();
	    		line++;
	    	}
	    	return hayToken;
	   }
	    
	
		public void setLinea(String line) {
			linea = line;
		}
		

		
		public static void main(String[] args) throws IOException{
		
		Analizador a = new Analizador();
		a.cargarArchivo("archivo.txt");
		int Contador = 0;
		int NumeroToken = 0;
		int NumeroLinea = 0;
		a.setLinea(br.readLine());
		File archivo = new File ("C:\\archivo.txt");                        // PRUEBA ABRIR EL ARCHIVO
		while( linea != null ){
			NumeroLinea++;
			Contador = 0;
			while(Contador < linea.length()){
				NumeroToken = getToken();
				System.out.println(NumeroToken);
			}
		}
		
	}
	}

	

	
