package Compilador;
import java.util.ArrayList;
import java.util.HashMap;

public class AS_FinSimboloComp extends AccionSemantica{
	
	HashMap<String,Integer> TablaToken; 
	public AS_FinSimboloComp(HashMap<String,Integer> TablaToken){
		this.TablaToken = TablaToken;
	}
	
	public int execute(String Buffer, char c) {
		Buffer = Buffer + c ; 
		//System.out.println(TablaToken.get(Buffer));
		return TablaToken.get(Buffer);
	}
}
