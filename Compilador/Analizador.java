package Compilador;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
		ArrayList<Simbolo> TablaSimbolo = m.getTablaSimbolos();
		for(int i =0; i< TablaSimbolo.size();i++){
			System.out.println( i +  " ---> " + ((Simbolo)TablaSimbolo.get(i)).getValor());
		}
	}
	
	static public void mostrarErrores(ArrayList<String> eSintactico){
		System.out.println("----- Errores ------");
		for(int i =0 ; i< Errores.size();i++){
			System.out.println(Errores.get(i));
		}
		for (String s : eSintactico)
			System.out.println(s);
	}
	
	public void cargarArchivo(String origen) throws IOException{
		fr = new FileReader(origen);
	    br = new BufferedReader(fr);
	}
	
	public int getLexema(){
		return this.lexema;
	}
	
	public Token getToken() throws IOException {
	    Token t = new Token(line);
	    String error = "";
	    int estado = 0;
	    char c = 0;
		if( linea != null ){
	    	while ((t.getToken() == 0 ) && (pos != linea.toCharArray().length)) {     
	    		 c = linea.toCharArray()[pos];
	    		 t.setToken(m.returnCasilla(estado, c).getAccion().execute(buffer, c)); 
	    		 buffer = m.returnCasilla(estado, c).getAccion().getBuffer();
	    		 lexema = m.returnCasilla(estado, c).getAccion().getLexema();
	    		 if(m.returnCasilla(estado, c).getAccion().acomodarLinea() == true){     //Acomodo la posición en caso de necesitarlo
	    			 pos--;
	    		 }
	    		 pos++;
	    		 estado = m.returnCasilla(estado, c).getEstado();
	    		 
	    	}
	    	
	    	if (t.getToken() != 0)   //HAY TOKEN
				System.out.print("["+t.getToken()+","+ line +"]"+ " ");
	    	if (t.getToken() == -1){ //TOKEN DE ERROR
	    		error = "Linea "+ line + " - Error : Caracter no valido ";
	    		Errores.add(error);
	    	}
	    	if (t.getToken() == -2){ //TOKEN DE FUERA DE RANGO
	    		error = "Linea "+ line + " - WARNING : Constante fuera de rango ";
	    		Errores.add(error);
	    	}
	    	if (t.getToken() == -3){ //IDENTIFICADOR MAYOR A 25
	    		error = "Linea "+ line + " - Error : Identificador mayor a 25 caracteres ";
	    		Errores.add(error);
	    	}
	    	if (pos == linea.toCharArray().length) {
	    		linea = br.readLine();
	    		line++;
	    		pos = 0;
	    	}
	    }
		buffer = "";
	    return t;
	   }
	    
	public void cerrarArchivo() throws IOException {
		this.fr.close();
	}
	
	public void setLinea(String line) {
		linea = line;
	}
		
	public static void main(String[] args) throws IOException{
		Matrix m = new Matrix();
		Analizador a = new Analizador(m);
		//String file = args[0];
		String file = "D:\\archivo.txt";
		a.cargarArchivo(file);
		linea = br.readLine();
		Parser p = new Parser(a);
		p.yyparse();
		
		//Impresion tabla simbolo //
		mostrarTablaSimbolo();
		mostrarErrores(p.getErrores());
		a.cerrarArchivo();
	}
}
