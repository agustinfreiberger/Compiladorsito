package Compilador;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Analizador {
	
	static Matrix m;
	static String buffer = "";
	static int pos = 0;
	static Integer line = 0;
	FileReader fr;
	static BufferedReader br;
	static String linea;
	static int lexema;
	static ArrayList<String> Errores = new ArrayList<>();
	
	public Analizador(Matrix m) {
		this.m = m;
	}
	
	static public void mostrarTablaSimbolo(){
		System.out.println();
		System.out.println("---Tabla de simbolos--- ");
		ArrayList<String> TablaSimbolo = m.getTablaSimbolos();
		for(int i =0; i< TablaSimbolo.size();i++){
			System.out.println( i +  " ---> " + TablaSimbolo.get(i));
		}
	}
	
	static public void mostrarErrores(){
		System.out.println("----- Errores ------");
		for(int i =0 ; i< Errores.size();i++){
			System.out.println(Errores.get(i));
		}
	}
	
	public void cargarArchivo(String origen) throws IOException{
		fr = new FileReader(origen);
	    br = new BufferedReader(fr);
	}
	
	public int getLexema(){
		return this.lexema;
	}
	
	public static Token getToken() throws IOException   {
	    Token t = new Token(line);
	    int estado = 0;
	    char c = 0;
	    	while ((t.getToken() == 0 ) && (pos != linea.toCharArray().length)) {       // -2: token, -1: error
	    		 c = linea.toCharArray()[pos];
	    		 t.setToken(m.returnCasilla(estado, c).getAccion().execute(buffer, c)); 
	    		 buffer = m.returnCasilla(estado, c).getAccion().getBuffer();
	    		 lexema = m.returnCasilla(estado, c).getAccion().getLexema();
	    		 estado = m.returnCasilla(estado, c).getEstado();
	    		 if(m.returnCasilla(estado, c).getAccion().acomodarLinea() == true){
	    			 pos--;
	    		 }
	    		 pos++;
	    	}
	    	if(t.getToken() != 0){
	    		return t;
	    	}
	    	return t;
	   }
	    
	public void cerrarArchivo() throws IOException {
		this.fr.close();
	}
	
	public void setLinea(String line) {
		linea = line;
	}
		
	public static void main(String[] args) throws IOException{
		String error = "" ;
		Matrix m = new Matrix();
		Analizador a = new Analizador(m);
		System.out.println("Ingrese ruta del archivo");
		a.cargarArchivo("C:\\Users\\Agustin\\Desktop\\archivo.txt");
		int NumeroToken = 0;
		//int NumeroLinea = 0; ----------------------<
		//a.setLinea(br.readLine());
		linea = br.readLine();
		//System.out.println(linea);
		while( linea != null ){
			line++;
			while(pos < linea.length()){
				error = "";
				NumeroToken = getToken().getToken();
				if (NumeroToken != -4)  //NO ACTION
					System.out.print(NumeroToken + " ");
		    	if (NumeroToken == -1){ //TOKEN DE ERROR
		    		error = "Linea "+ getToken().getLinea() + " - Error : Caracter no valido ";
		    		Errores.add(error);
		    	}
		    	if (NumeroToken == -2){ //TOKEN DE FUERA DE RANGO
		    		error = "Linea "+ getToken().getLinea() + " - WARNING : Constante fuera de rango ";
		    		Errores.add(error);
		    	}
		    	if (NumeroToken == -3){ //IDENTIFICADOR MAYOR A 25
		    		error = "Linea "+ getToken().getLinea() + " - Error : Identificador mayor a 25 caracteres ";
		    		Errores.add(error);
		    	}
		    	
			}
		    //System.out.println();
			pos = 0;
			linea = br.readLine();

		}
		//impresion tabla simbolo // 
		mostrarTablaSimbolo();
		mostrarErrores();
		a.cerrarArchivo();
	}
}
