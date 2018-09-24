
import java.util.ArrayList;

public class AS_FinCadena extends AccionSemantica {
	
	ArrayList<String> TablaSimbolo;
	int cadena = 423 ;
	public AS_FinCadena(ArrayList<String> TablaSimbolo) {
		this.TablaSimbolo = TablaSimbolo;
	}
	
	@Override
	public int execute(String Buffer, char c) {
		this.Buffer = Buffer + c;
		if(!TablaSimbolo.contains(this.Buffer)){
			this.TablaSimbolo.add(this.Buffer);
		}
		this.Buffer = "";
		return cadena;
	}

}