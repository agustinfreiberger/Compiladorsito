package Compilador;

import java.util.ArrayList;
import java.util.HashMap;

public class AS_FinCadena extends AccionSemantica {
	
	ArrayList<String> TablaSimbolo;
	HashMap<String,Integer> TablaToken;
	public AS_FinCadena(ArrayList<String> TablaSimbolo, HashMap<String,Integer> TablaToken) {
		this.TablaSimbolo = TablaSimbolo;
		this.TablaToken = TablaToken;
	}
	
	@Override
	public int execute(String Buffer, char c) {
		this.Buffer = Buffer + c;
		if(!TablaSimbolo.contains(this.Buffer)){
			this.TablaSimbolo.add(this.Buffer);
		}
		this.Buffer = "";
		return TablaToken.get("CADENA");
	}
	
	public int getLexema(){
		return TablaSimbolo.indexOf(this.Buffer);
	}

}