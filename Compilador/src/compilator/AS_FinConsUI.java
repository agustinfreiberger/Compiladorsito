package compilator;
import java.util.ArrayList;

public class AS_FinConsUI extends AccionSemantica{
	ArrayList<String> TablaSimbolo;
	int constante = 420;  
	int rangoMenor = 0;
	int rangoMayor = (int)Math.pow(2, 16)-1;
	
	
	
	
	public AS_FinConsUI(ArrayList<String> TablaSimbolo){
		
		this.TablaSimbolo = TablaSimbolo;
		
	}
	
	public int execute(String Buffer, char c) {
		String StringConst = Buffer.substring(0, Buffer.length()-3);
		int Const = Integer.parseInt(StringConst);
		if((Const>rangoMenor) && (Const<rangoMayor)){
			if(TablaSimbolo.contains(Buffer) ){
				return constante;
			}
			else{
				TablaSimbolo.add(Buffer);
				return constante;
			}
		}
		else{                                    // SI NO ESTA EN RANGO
			if(Const<rangoMenor){                // SI ES MENOR AL MENOR VALOR POSIBLE 
				Const = rangoMenor;
				String MinConst = Integer.toString(Const);
				if(TablaSimbolo.contains(MinConst) ){   // SI LO CONTIENE
					return -2;    // -2 ES WARNING DE PASADO DE RANGO 
				}
				else{                                   // SI NO ESTA LO AGREGA COMO EL MINIMO VALOR 
					TablaSimbolo.add(MinConst);
					return -2;	// -2 ES WARNING DE PASADO DE RANGO
				}
			}
			if(Const>rangoMayor){                      //SI ES MAYOR AL MAXIMO RANGO
				Const = rangoMenor;
				String MaxConst = Integer.toString(Const);
				if(TablaSimbolo.contains(MaxConst) ){          //SI LO TIENE
					return -2;	// -2 ES WARNING DE PASADO DE RANGO
				}
				else{                                         // SI NO LO TIENE LO AGREGA
					TablaSimbolo.add(MaxConst);
					return -2; // -2 ES WARNING DE PASADO DE RANGO
				}
			}
		}
		return -1; // -1 ERROR 
	}
}