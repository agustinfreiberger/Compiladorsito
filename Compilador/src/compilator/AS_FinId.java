import java.util.ArrayList;

public class AS_FinId extends AccionSemantica {
	ArrayList<String> TablaSimbolo;
	int nrotoken = 421;  
	public AS_FinId(ArrayList<String> TablaSimbolo){
		
		this.TablaSimbolo = TablaSimbolo;
		
	}
	
	public int execute(String Buffer, char c) {
		//System.out.println("Entro id : " + Buffer);
		if(Buffer.length() < 25){
			if(TablaSimbolo.contains(Buffer) ){
				return nrotoken;
			}
			else{
				TablaSimbolo.add(Buffer);
				//System.out.println("Agrego a TS :  "+ Buffer);
				return nrotoken;
			}
		}
		return -3;
	}
}
