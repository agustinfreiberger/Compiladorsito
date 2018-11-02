package Compilador;

public abstract class AccionSemantica {

	String Buffer = "";
	Simbolo s;
	
	
	public abstract int execute(String Buffer, char c);
	
	public String getBuffer(){
		return this.Buffer;
	}
	
	public abstract boolean acomodarLinea();
	
	public int getLexema(){
		return -1;
	}
}