package Compilador;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public class AS_FinConsUI extends AccionSemantica{
	ArrayList<Simbolo> TablaSimbolo;
	HashMap<String,Integer> TablaToken; 
	int rangoMenor = 0;
	int rangoMayor = (int)Math.pow(2, 16)-1;
	
	
	public AS_FinConsUI(ArrayList<Simbolo> TablaSimbolo, HashMap<String,Integer> TablaToken){
		
		this.TablaSimbolo = TablaSimbolo;
		this.TablaToken = TablaToken;
	}
	
	
	public int execute(String Buffer, char c) {
		this.Buffer = Buffer + c;
		String StringConst = this.Buffer.substring(0, (this.Buffer.length()-3));
		long Const = Long.parseLong(StringConst);
		this.s = new Simbolo(StringConst, "UI");
		if((Const>=rangoMenor) && (Const<=rangoMayor)){
			if(TablaSimbolo.contains(this.s) ){
				return TablaToken.get("USINTEGER");
			}
			else{
				TablaSimbolo.add(this.s);
				//System.out.println("Agrego a TS : " + StringConst);
				return TablaToken.get("USINTEGER");
			}
		}
		else{                                    // SI NO ESTA EN RANGO
			if(Const<rangoMenor){                // SI ES MENOR AL MENOR VALOR POSIBLE 
				Const = rangoMenor;
				String MinConst = Long.toString(Const);
				this.s.setValor(MinConst);
				if(TablaSimbolo.contains(s) ){   // SI LO CONTIENE
					return -2;    // -2 ES WARNING DE PASADO DE RANGO 
				}
				else{                                   // SI NO ESTA LO AGREGA COMO EL MINIMO VALOR 
					TablaSimbolo.add(s);
					return -2;	// -2 ES WARNING DE PASADO DE RANGO
				}
			}
			if(Const>rangoMayor){                      //SI ES MAYOR AL MAXIMO RANGO
				Const = rangoMayor;
				String MaxConst = Long.toString(Const);
				this.s.setValor(MaxConst);
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