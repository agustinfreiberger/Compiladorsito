package compilator;
import java.util.HashMap;

public class AS_PReservada extends AccionSemantica{
	
	HashMap<String,Integer> TablaToken;

	public AS_PReservada(HashMap<String,Integer> TablaToken){
		
		this.TablaToken = TablaToken;
		
	}
	
	public int execute(String Buffer, char c) {
		if(TablaToken.containsKey(Buffer)){
			return TablaToken.get(Buffer);
		}
		return -1;  // Si no existe la palabra reservada , tira error;
	}

	
}
