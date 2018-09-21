package compilator;

public class AS_AgregarCaracter extends AccionSemantica {
	
	public AS_AgregarCaracter(){
		
	}
	
	public int execute(String Buffer, char c) {
		Buffer = Buffer + c ;
		
	return 0;
	}
	
}
