package Compilador;

import java.util.ArrayList;
import java.util.HashMap;

public class AS_FinCadena extends AccionSemantica {
	
	ArrayList<Simbolo> TablaSimbolo;
	HashMap<String,Integer> TablaToken;
	
	public AS_FinCadena(ArrayList<Simbolo> TablaSimbolo, HashMap<String,Integer> TablaToken) {
		this.TablaSimbolo = TablaSimbolo;
		this.TablaToken = TablaToken;
	}
	
	@Override
	public int execute(String Buffer, char c) {
		this.Buffer = Buffer + c;
		Simbolo s = new Simbolo(Buffer);
		if(!TablaSimbolo.contains(s)){
			this.TablaSimbolo.add(s);
		}
		this.Buffer = "";
		return TablaToken.get("CADENA");
	}
	
	public int getLexema(){
		return TablaSimbolo.indexOf(this.Buffer);
	}
	public boolean acomodarLinea(){
		return false;
	}
	public int getRetroceso() {
		return 1;
	}

}