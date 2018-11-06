package Compilador;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
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
	static BufferedWriter bw;
	static String linea;
	static int lexema;
	static ArrayList<String> Errores = new ArrayList<>();
	
	public Analizador(Matrix m) {
		this.m = m;
	}
	
	static public void mostrarTablaSimbolo() throws IOException{                           //Muestro Simbolos por pantalla
		System.out.println();
		System.out.println("---Tabla de simbolos--- ");
		imprimirArchivo("---Tabla de simbolos--- ");
		ArrayList<Simbolo> TablaSimbolo = m.getTablaSimbolos();
		for(int i =0; i< TablaSimbolo.size();i++){
			System.out.println( i +  " ---> " + ((Simbolo)TablaSimbolo.get(i)).getValor());
			imprimirArchivo(i +  " ---> " + ((Simbolo)TablaSimbolo.get(i)).getValor());
		}
	}
	
	static public void mostrarErrores(ArrayList<String> eSintactico) throws IOException{    //Muestro errores por pantalla
		System.out.println("----- Errores ------");
		imprimirArchivo("----- Errores ------");
		for(int i =0 ; i< Errores.size();i++){
			System.out.println(Errores.get(i));
			imprimirArchivo(Errores.get(i));
		}
		for (String s : eSintactico) {
			System.out.println(s);
			imprimirArchivo(s);
		}
	}
	
	public void cargarArchivo(String origen) throws IOException{
		fr = new FileReader(origen);
	    br = new BufferedReader(fr);
	}
	
	public int getLexema(){                                             //Devuelvo el lexema
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
	    	
	    	if (t.getToken() != 0) {   //HAY TOKEN
				if (t.getToken() >= 257) {
					System.out.print("["+ m.translateToken(t.getToken()) +","+ line +"]"+ " ");   //Token
					imprimirArchivo("["+ m.translateToken(t.getToken()) +","+ line +"]"+ " ");
				}
	    		if (t.getToken() == -1){ //TOKEN DE ERROR
	    			System.out.print("["+ m.translateToken(t.getToken()) +","+ line +"]"+ " ");   //Token
					imprimirArchivo("["+ m.translateToken(t.getToken()) +","+ line +"]"+ " ");
	    			error = "Linea "+ line + " - Error : Caracter no valido ";
	    			Errores.add(error);
	    		}
	    		if (t.getToken() == -2){ //TOKEN DE FUERA DE RANGO
	    			t.setToken(261);
	    			System.out.print("["+ m.translateToken(t.getToken()) +","+ line +"]"+ " ");   //Token
					imprimirArchivo("["+ m.translateToken(t.getToken()) +","+ line +"]"+ " ");
	    			error = "Linea "+ line + " - WARNING : Constante fuera de rango ";
	    			Errores.add(error);
		    	}
	    		if (t.getToken() == -3){ //IDENTIFICADOR MAYOR A 25
		    		error = "Linea "+ line + " - Error : Identificador mayor a 25 caracteres ";
		    		Errores.add(error);
		    	}
		    	if (t.getToken() == -4){ //TOKEN DE FUERA DE RANGO
		    		t.setToken(260);
		    		System.out.print("["+ m.translateToken(t.getToken()) +","+ line +"]"+ " ");   //Token
					imprimirArchivo("["+ m.translateToken(t.getToken()) +","+ line +"]"+ " ");
		    		error = "Linea "+ line + " - WARNING : Constante fuera de rango ";
		    		Errores.add(error);
		    	}
		    	if((t.getToken()) < 257 && (t.getToken()>0)) { 						//ASCII
				System.out.print("["+ t.getToken() +","+ line +"]"+ " ");                     
				imprimirArchivo("["+ t.getToken() +","+ line +"]"+ " ");
		    	}
			}
	
	    	if (pos == linea.toCharArray().length) {     //Pido otra linea.
	    		linea = br.readLine();
	    		if (estado == 7) {
	    			t.setToken(-1);
	    			System.out.print("["+ m.translateToken(t.getToken()) +","+ line +"]"+ " ");   //Token
					imprimirArchivo("["+ m.translateToken(t.getToken()) +","+ line +"]"+ " ");
	    			error = "Linea "+ line + " - Error : Cadena no válida ";
	    			Errores.add(error);
	    			estado = 0;
	    		}
	    		line++;
	    		pos = 0;
	    	}
	    }
		buffer = "";
	    return t;
	   }
	    
	public void cerrarArchivo() throws IOException {   //Cierro archivo
		this.fr.close();
		this.bw.close();
	}
	
	public static void crearSalida(String salida) throws IOException{
		bw = new BufferedWriter(new FileWriter(salida));
	}
	
	public static void imprimirArchivo(String cadena) throws IOException {
		bw.write(cadena);
		bw.newLine();
	}
	
	public void setLinea(String line) {
		linea = line;
	}
	
	public static void main(String[] args) throws IOException{
		Matrix m = new Matrix();
		Analizador a = new Analizador(m);
		String file = args[0];
		
		//String file = "closure_bien.txt";
		a.cargarArchivo(file);
		String filesalida = "salida_" + file ;
		crearSalida(filesalida);
		linea = br.readLine();
		Parser p = new Parser(a);
		p.yyparse();
		
		//Impresion tabla simbolo //
		mostrarTablaSimbolo();
		mostrarErrores(p.getErrores());
		a.cerrarArchivo();
	}
}
