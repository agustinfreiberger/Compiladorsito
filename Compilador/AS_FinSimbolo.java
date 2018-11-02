package Compilador;

public class AS_FinSimbolo extends AccionSemantica {
	
	public int execute(String Buffer, char c) {
		int ascii = (int) c; //cambio c a codigo ascii
		return ascii;
	}
	public boolean acomodarLinea(){
		return false;
	}
	public int getRetroceso() {
		return 0;
	}

	
}
