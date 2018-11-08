package Compilador;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public class Matrix {
	
	Casilla matrix [][];
	ArrayList<Simbolo> TablaSimbolo;
	HashMap<String, Integer> TablaToken;
	Hashtable<Integer, Integer> diccionario= new Hashtable<Integer, Integer>();
	
	private int charToInt(char c) {
		int ascii=  c;
		if((ascii >= 65 && ascii <= 90)||(ascii>= 97 && ascii <= 122)) {
			if(ascii == 105) return 105;
			if(ascii == 108) return 108;
			if(ascii == 117) return 117;
			return 1;
		}	
		if (ascii >= 48 && ascii <= 57)
			return 2;
		return ascii;
	}
	
	public Matrix() {
		
		TablaSimbolo = new ArrayList<>();
		TablaToken = new HashMap<String,Integer>();
		
		//Tokens
		TablaToken.put("ID", 257);
		TablaToken.put("LINTEGER", 260);
		TablaToken.put("USINTEGER", 261);
		TablaToken.put("CTE", 258);
		TablaToken.put("CADENA", 259);
		TablaToken.put("<=", 262);
		TablaToken.put(">=", 263);
		TablaToken.put("!=", 264);
		TablaToken.put(":=", 265);
		TablaToken.put("IF", 266);
		TablaToken.put("ELSE", 267);
		TablaToken.put("ENDIF", 268);
		TablaToken.put("PRINT", 270);
		TablaToken.put("WHILE", 269);
		TablaToken.put("VOID", 271);
		TablaToken.put("FUN", 272);
		TablaToken.put("RETURN", 273);		
		
		//Acciones Semánticas
		AccionSemantica AS_AgregarCaracter = new AS_AgregarCaracter();
		AccionSemantica AS_NoAction = new AS_NoAction();
		AccionSemantica AS_FinConsL = new AS_FinConsL(TablaSimbolo, TablaToken);
		AccionSemantica AS_FinConsUI = new AS_FinConsUI(TablaSimbolo, TablaToken);
		AccionSemantica AS_FinId = new AS_FinId(TablaSimbolo, TablaToken);
		AccionSemantica AS_FinSimbolo = new AS_FinSimbolo();
		AccionSemantica AS_FinSimboloComp = new AS_FinSimboloComp(TablaToken);
		AccionSemantica AS_PReservada = new AS_PReservada(TablaToken);
		AccionSemantica AS_ErrorCaracter = new AS_ErrorCaracter();
		AccionSemantica AS_IniciarBuffer = new AS_IniciarBuffer();
		AccionSemantica AS_FinCadena = new AS_FinCadena(TablaSimbolo, TablaToken);
		AccionSemantica AS_FinSimboloBuffer = new AS_FinSimboloBuffer();
	
		//Ascii a Columnas
		diccionario.put(1, 1);
		diccionario.put(2, 2);
		diccionario.put(47, 3);
		diccionario.put(42, 4);
		diccionario.put(45, 5);
		diccionario.put(43, 6);
		diccionario.put(44, 7);
		diccionario.put(59, 8);
		diccionario.put(35, 9);
		diccionario.put(60, 10);
		diccionario.put(62, 11);
		diccionario.put(61, 12);
		diccionario.put(33, 13);
		diccionario.put(58, 14);
		diccionario.put(123, 15);
		diccionario.put(125, 16);
		diccionario.put(40, 17);
		diccionario.put(41, 18);
		diccionario.put(39, 19);
		diccionario.put(95, 20);
		diccionario.put(290, 21); // /n : salto de linea 
		diccionario.put(32, 22); 
		diccionario.put(117, 23); //u
		diccionario.put(105, 24); // i
		diccionario.put(108, 25); // l
		
		//Casillas
		Casilla c1 = new Casilla (10, AS_AgregarCaracter);       
		Casilla c2 = new Casilla (3, AS_AgregarCaracter); 
		Casilla c3 = new Casilla (13, AS_PReservada);            
		Casilla c4 = new Casilla (13, AS_FinSimbolo);         
		Casilla c5 = new Casilla (6, AS_NoAction);             
		Casilla c6 = new Casilla (8 , AS_IniciarBuffer);
		Casilla c7 = new Casilla (9, AS_IniciarBuffer);
		Casilla c8 = new Casilla (13, AS_NoAction);
		Casilla c9 = new Casilla (1, AS_IniciarBuffer);
		Casilla c10 = new Casilla (7 , AS_IniciarBuffer);
		Casilla c11 = new Casilla (13, AS_ErrorCaracter);      
		Casilla c12 = new Casilla (2, AS_AgregarCaracter);
		Casilla c13 = new Casilla (13, AS_FinSimboloComp);      
		Casilla c14 = new Casilla (11, AS_AgregarCaracter);
		Casilla c15 = new Casilla (13, AS_FinSimboloBuffer);   // PARA EL CASO DEL "-"
		Casilla c16 = new Casilla (13, AS_FinId);              		
		Casilla c17 = new Casilla (-1, AS_NoAction);
		Casilla c18 = new Casilla (7, AS_AgregarCaracter);
		Casilla c19 = new Casilla (13, AS_FinCadena);
		Casilla c20 = new Casilla (5, AS_AgregarCaracter);
		Casilla c21 = new Casilla (13, AS_FinConsUI);
		Casilla c22 = new Casilla (13, AS_FinConsL);
		Casilla c23 = new Casilla (4, AS_AgregarCaracter);
		Casilla c24 = new Casilla (12, AS_AgregarCaracter);
		Casilla c0  = new Casilla (0, AS_NoAction);
		
		
		                                //1 , 2 , 3 ,   4  , 5   , 6 ,  7  , 8 ,  9 , 10  , 11 , 12 , 13 , 14 , 15 , 16 , 17 , 18 , 19 , 20 , 21 , 22 , 23 , 24 , 25 , 26                                          
		this.matrix = new Casilla[][] { {c1 , c2 , c3 , c3 , c14 , c4 , c4 , c4 , c5 , c6 , c6 , c3 , c7 , c7 , c4 , c4 , c4 , c4 , c10 , c9 , c8, c0, c1 , c1 , c1 , c11},
							/*1*/	    {c12, c12, c4 , c4 , c4 , c4 , c13 , c13 , c4 , c4 , c4 , c4 , c11, c13, c13, c13, c13, c13, c13, c11, c17, c13, c12, c12, c12, c13},
							/*2*/       {c12, c12, c16, c16, c16, c16, c16, c16, c16, c16, c16, c16, c16, c16, c16, c16, c16, c16, c16, c11, c16, c16, c12, c12, c12, c16},
							/*3*/	    {c11, c2, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c23, c11, c11, c11, c11, c11, c11},
							/*4*/		{c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c20, c11, c22, c11},
							/*5*/		{c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c5 , c11, c21, c11, c11},
							/*6*/		{c5 , c5 , c5 , c5 , c5 , c5 , c5 , c5 , c5 , c0 , c5 , c5 , c5 , c5 , c5 , c5 , c5 , c5 , c5 , c5 , c5 , c5 , c5 , c5 , c5 , c5 },
							/*7*/		{c18, c18, c18, c18, c18, c18, c18, c18, c18, c18, c18, c18, c18, c18, c18, c18, c18, c18, c19, c18, c11, c18, c18, c18, c18, c18},
							/*8*/		{c15 , c15 , c15 , c15 , c15,c15 ,c15 ,c15 ,c15 , c15,c15 , c3 , c15 ,c15 ,c15 ,c15 ,c15 , c15 ,c15 ,c15 , c11, c15 ,c15 ,c15 ,c15 ,c15 },
							/*9*/		{c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c13, c11, c11, c11, c11 , c11, c11, c11, c11, c11, c11, c11, c11, c11, c11 },
							/*10*/	    {c1 , c3 , c3 , c3 ,c3 ,c3 ,c3 ,c3 ,c3 ,c3 ,c3 ,c3 ,c3 , c3 , c3 , c3 , c3 , c3 ,c3 , c3 , c3 , c3 , c1 , c1 , c1 ,c3},
							/*11*/	    {c15 , c24 , c15 , c15 , c15 , c15 , c15 , c15 , c15 , c15 , c15 , c15 , c15 , c15 , c15 , c15 , c15 , c15 , c15 , c15 , c15 , c15 , c15 , c15 , c15 , c15 },
							/*12*/		{c11 , c24 , c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c23, c11, c11, c11, c11, c11, c11 },
							/*13*/		{c0 , c0 , c0 , c0 , c0 , c0 , c0 , c0 , c0 , c0 , c0 , c0 , c0 , c0 , c0 , c0 , c0 , c0 , c0 , c0 , c0 , c0 , c0 , c0 , c0 , c0}
									};	
	}

	public Casilla returnCasilla(int estado, char c) {
		int columna = 0;
		if (!diccionario.containsKey(charToInt(c))) {
			columna = 25;
		}else { 
			columna = diccionario.get(charToInt(c))-1;
		}
		return (this.matrix[estado][columna]);
	}
	
	public String translateToken(int token) {   //Convierto numero en Token
		if(token == 0) 
			return "Error";
		for (String s : this.TablaToken.keySet()) {
			if (TablaToken.get(s) == token)
				return s;
		}
		return null;
    }
	
	public ArrayList<Simbolo> getTablaSimbolos(){
		return this.TablaSimbolo;
	}
}