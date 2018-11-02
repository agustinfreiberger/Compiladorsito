package Compilador;

public class AS_FinSimboloBuffer extends AccionSemantica {
	
	public int execute(String Buffer, char c) {
		int ascii = (int) Buffer.charAt(0); 
		return ascii;
	}
	
	public boolean acomodarLinea(){
		return true;
	}
	
}