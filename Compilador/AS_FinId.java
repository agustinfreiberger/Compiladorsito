package Compilador;
import java.util.ArrayList;
import java.util.HashMap;

public class AS_FinId extends AccionSemantica {
	ArrayList<String> TablaSimbolo;
	HashMap<String,Integer> TablaToken; 
	public AS_FinId(ArrayList<String> TablaSimbolo, HashMap<String,Integer> TablaToken){
		
		this.TablaToken = TablaToken;
		this.TablaSimbolo = TablaSimbolo;
		
	}
	
	public int execute(String Buffer, char c) {
		//System.out.println("Entro id : " + Buffer);
		this.Buffer = Buffer;
		if(Buffer.length() < 25){
			if(TablaSimbolo.contains(Buffer) ){
				return TablaToken.get("ID");
			}
			else{
				TablaSimbolo.add(Buffer);
				//System.out.println("Agrego a TS :  "+ Buffer);
				return TablaToken.get("ID");
			}
		}
		return -3;
	}
	
	public int getLexema(){
		return TablaSimbolo.indexOf(this.Buffer);
	}
}
