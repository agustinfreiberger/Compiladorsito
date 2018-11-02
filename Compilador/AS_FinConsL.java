package Compilador;
import java.util.ArrayList;
import java.util.HashMap;

public class AS_FinConsL extends AccionSemantica{
	ArrayList<Simbolo> TablaSimbolo;
	HashMap<String,Integer> TablaToken;  
	int rangoMenor = (int)Math.pow(-2, 31);
	int rangoMayor = (int)Math.pow(2, 31)-1;

	public AS_FinConsL(ArrayList<Simbolo> TablaSimbolo, HashMap<String,Integer> TablaToken){
		
		this.TablaSimbolo = TablaSimbolo;
		this.TablaToken = TablaToken;
	}
	
	public int execute(String Buffer, char c) {
		this.Buffer = Buffer + c;
		this.s = new Simbolo(Buffer, "L");
		String StringConst = this.Buffer.substring(0, this.Buffer.length()-2);
		long Const = Long.parseLong(StringConst);
		if((Const>rangoMenor) && (Const<rangoMayor)){ //SI ESTA EN RANGO
			if(TablaSimbolo.contains(this.s) ){  //SI ESTA EN LA TABLA
				return TablaToken.get("LINTEGER");
			}
			else{                                // SI NO ESTA EN LA TABLA
				TablaSimbolo.add(s);
				return TablaToken.get("LINTEGER");
			}
		}
		else{                                    // SI NO ESTA EN RANGO
			if(Const<rangoMenor){                // SI ES MENOR AL MENOR VALOR POSIBLE 
				Const = rangoMenor;
				String MinLong = Long.toString(Const);
				s.setValor(MinLong);
				if(TablaSimbolo.contains(s) ){   // SI LO CONTIENE
					//int PunteroTabla = TablaSimbolo.indexOf(Buffer);
					return -2;    // -2 ES WARNING DE PASADO DE RANGO 
				}
				else{                                   // SI NO ESTA LO AGREGA COMO EL MINIMO VALOR 
					TablaSimbolo.add(s);
					return -2;	// -2 ES WARNING DE PASADO DE RANGO
				}
			}
			if(Const>rangoMayor){                      //SI ES MAYOR AL MAXIMO RANGO
				Const = rangoMenor;
				String MaxLong = Long.toString(Const);
				s.setValor(MaxLong);
				if(TablaSimbolo.contains(s) ){          //SI LO TIENE
					return -2;	// -2 ES WARNING DE PASADO DE RANGO
				}
				else{                                         // SI NO LO TIENE LO AGREGA
					TablaSimbolo.add(s);
					return -2; // -2 ES WARNING DE PASADO DE RANGO
				}
			}
		}
		return -1; // -1 ERROR 
	}
	
	
	public boolean acomodarLinea(){
		return false;
	}
	
	public int getLexema(){
		return TablaSimbolo.indexOf(this.s.getValor());
	}
}
