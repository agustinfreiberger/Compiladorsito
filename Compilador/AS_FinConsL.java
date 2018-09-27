package Compilador;
import java.util.ArrayList;
import java.util.HashMap;

public class AS_FinConsL extends AccionSemantica{
	ArrayList<String> TablaSimbolo;
	HashMap<String,Integer> TablaToken;  
	int rangoMenor = (int)Math.pow(-2, 31);
	int rangoMayor = (int)Math.pow(2, 31)-1;

	public AS_FinConsL(ArrayList<String> TablaSimbolo, HashMap<String,Integer> TablaToken){
		
		this.TablaSimbolo = TablaSimbolo;
		this.TablaToken = TablaToken;
	}
	
	public int execute(String Buffer, char c) {
		this.Buffer = Buffer + c;
		String StringConst = this.Buffer.substring(0, this.Buffer.length()-2);
		long Const = Long.parseLong(StringConst);
		if((Const>rangoMenor) && (Const<rangoMayor)){ //SI ESTA EN RANGO
			if(TablaSimbolo.contains(this.Buffer) ){  //SI ESTA EN LA TABLA
				return TablaToken.get("LINTEGER");
			}
			else{                                // SI NO ESTA EN LA TABLA
				TablaSimbolo.add(this.Buffer);
				return TablaToken.get("LINTEGER");
			}
		}
		else{                                    // SI NO ESTA EN RANGO
			if(Const<rangoMenor){                // SI ES MENOR AL MENOR VALOR POSIBLE 
				Const = rangoMenor;
				String MinLong = Long.toString(Const);
				if(TablaSimbolo.contains(MinLong) ){   // SI LO CONTIENE
					//int PunteroTabla = TablaSimbolo.indexOf(Buffer);
					return -2;    // -2 ES WARNING DE PASADO DE RANGO 
				}
				else{                                   // SI NO ESTA LO AGREGA COMO EL MINIMO VALOR 
					TablaSimbolo.add(MinLong);
					return -2;	// -2 ES WARNING DE PASADO DE RANGO
				}
			}
			if(Const>rangoMayor){                      //SI ES MAYOR AL MAXIMO RANGO
				Const = rangoMenor;
				String MaxLong = Long.toString(Const);
				if(TablaSimbolo.contains(MaxLong) ){          //SI LO TIENE
					return -2;	// -2 ES WARNING DE PASADO DE RANGO
				}
				else{                                         // SI NO LO TIENE LO AGREGA
					TablaSimbolo.add(MaxLong);
					return -2; // -2 ES WARNING DE PASADO DE RANGO
				}
			}
		}
		return -1; // -1 ERROR 
	}
	
	public int getLexema(){
		return TablaSimbolo.indexOf(this.Buffer);
	}
}
