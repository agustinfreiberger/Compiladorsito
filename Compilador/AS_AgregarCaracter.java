package Compilador;
public class AS_AgregarCaracter extends AccionSemantica {
	
	public AS_AgregarCaracter(){
		
	}
	
	public int execute(String Buffer, char c) {
		this.Buffer = Buffer + c ;
	return 0;
	}
	
	public String getBuffer(){
		return this.Buffer;
	}
	
}
