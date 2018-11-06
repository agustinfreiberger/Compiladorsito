package Compilador;
public class AS_ErrorCaracter extends AccionSemantica{

	@Override
	public int execute(String Buffer, char c) {
		return -1;
	}
	public boolean acomodarLinea(){
		return false;
	}
}