package Compilador;

public class AS_NoAction extends AccionSemantica {

	public int execute(String Buffer, char c) {
		return 0;
	}
	
	public boolean acomodarLinea(){
		return false;
	}
}