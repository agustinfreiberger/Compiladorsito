package Compilador;
import java.util.ArrayList;
import java.util.HashMap;

public class AS_FinId extends AccionSemantica {
	ArrayList<Simbolo> TablaSimbolo;
	HashMap<String,Integer> TablaToken; 
	
	public AS_FinId(ArrayList<Simbolo> TablaSimbolo, HashMap<String,Integer> TablaToken){
		this.TablaToken = TablaToken;
		this.TablaSimbolo = TablaSimbolo;
		
	}
	
	public int execute(String Buffer, char c) {
		this.s = new Simbolo (Buffer);
		if(Buffer.length() < 25){
			if(TablaSimbolo.contains(s) ){
				return TablaToken.get("ID");
			}
			else{
				TablaSimbolo.add(s);
				return TablaToken.get("ID");
			}
		}
		return -3;
	}
	
	public boolean acomodarLinea(){
		return true;
	}
	
	public int getLexema(){
		return TablaSimbolo.indexOf(this.s.getValor());
	}
	
}
