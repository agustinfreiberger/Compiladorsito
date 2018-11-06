package Compilador;
import java.util.HashMap;

public class AS_FinSimboloComp extends AccionSemantica{
	
	HashMap<String,Integer> TablaToken; 
	
	public AS_FinSimboloComp(HashMap<String,Integer> TablaToken){
		this.TablaToken = TablaToken;
	}
	
	public int execute(String Buffer, char c) {
		this.Buffer = Buffer + c ;
		return TablaToken.get(this.Buffer);
	}
	
	public boolean acomodarLinea(){
		return false;
	}

}
