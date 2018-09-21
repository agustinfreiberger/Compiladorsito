package compilator;

import java.util.ArrayList;

public class AS_FinCadena extends AccionSemantica {
	
	ArrayList<String> TablaSimbolo;
	
	public AS_FinCadena(ArrayList<String> TablaSimbolo) {
		this.TablaSimbolo = TablaSimbolo;
	}
	
	@Override
	public int execute(String Buffer, char c) {
			this.TablaSimbolo.add(Buffer);
		return 0;
	}

}
