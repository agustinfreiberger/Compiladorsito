package Compilador;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public class Matrix {
	
	Casilla matrix [][];
	ArrayList<String> TablaSimbolo;
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
		HashMap<String,Integer> TablaToken = new HashMap<>();
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
		
		AccionSemantica AS1 = new AS_AgregarCaracter();
		AccionSemantica AS2 = new AS_NoAction();
		AccionSemantica AS3 = new AS_FinConsL(TablaSimbolo, TablaToken);
		AccionSemantica AS4 = new AS_FinConsUI(TablaSimbolo, TablaToken);
		AccionSemantica AS5 = new AS_FinId(TablaSimbolo, TablaToken);
		AccionSemantica AS6 = new AS_FinSimbolo();
		AccionSemantica AS7 = new AS_FinSimboloComp(TablaToken);
		AccionSemantica AS8 = new AS_PReservada(TablaToken);
		AccionSemantica AS9 = new AS_ErrorCaracter();
		AccionSemantica AS10 = new AS_IniciarBuffer();
		AccionSemantica AS11 = new AS_FinCadena(TablaSimbolo, TablaToken);
	
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
		diccionario.put(300, 26); // 300 = otro
		
		//Estado 13 = FINAL
		Casilla c1 = new Casilla (10, AS1); //Agregar Caracter a Buffer
		Casilla c2 = new Casilla (3, AS1); 
		Casilla c3 = new Casilla (13, AS8); //buscarTPR 
		Casilla c4 = new Casilla (13, AS6); //Fin Simbolo Simple
		Casilla c5 = new Casilla (6, AS2);  //No Action
		Casilla c6 = new Casilla (8 , AS10);
		Casilla c7 = new Casilla (9, AS10);
		Casilla c8 = new Casilla (13, AS2);
		Casilla c9 = new Casilla (1, AS10);
		Casilla c10 = new Casilla (7 , AS10);
		Casilla c11 = new Casilla (13, AS9);  //Error // Era -1 puse 13
		Casilla c12 = new Casilla (2, AS1);
		Casilla c13 = new Casilla (13, AS7);  //Fin Simbolo Comp   //LA CAMBIE ERA -1 PUSE 13
		Casilla c14 = new Casilla (11, AS1);
		Casilla c20 = new Casilla (13, AS5);  //FinID
		Casilla c26 = new Casilla (-1, AS2);
		Casilla c27 = new Casilla (7, AS1);
		Casilla c28 = new Casilla (13, AS11);
		Casilla c35 = new Casilla (5, AS1);
		Casilla c36 = new Casilla (13, AS4);
		Casilla c37 = new Casilla (13, AS3);
		Casilla c38 = new Casilla (4, AS1);
		Casilla c39 = new Casilla (12, AS1);
		Casilla c0  = new Casilla (0, AS2);
		
		                                //1 , 2 , 3 ,   4  , 5   , 6 ,  7  , 8 ,  9 , 10  , 11 , 12 , 13 , 14 , 15 , 16 , 17 , 18 , 19 , 20 , 21 , 22 , 23 , 24 , 25 , 26                                          
		this.matrix = new Casilla[][] { {c1 , c2 , c3 , c3 , c14 , c4 , c4 , c4 , c5 , c6 , c6 , c4 , c7 , c7 , c4 , c4 , c4 , c4 , c10 , c9 , c8, c0, c1 , c1 , c1 , c11},
									    {c12, c12, c4 , c4 , c4 , c4 , c13 , c13 , c4 , c4 , c4 , c4 , c11, c13, c13, c13, c13, c13, c13, c11, c26, c13, c12, c12, c12, c13},
									    {c12, c12, c20, c20, c20, c20, c20, c20, c20, c20, c20, c20, c20, c20, c20, c20, c20, c20, c20, c20, c20, c20, c12, c12, c12, c20},
									    {c11, c2, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c38, c11, c11, c11, c11, c11, c11},
									    {c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c35, c11, c37, c11},
									    {c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c5 , c11, c36, c11, c11},
									    {c5 , c5 , c5 , c5 , c5 , c5 , c5 , c5 , c5 , c0 , c5 , c5 , c5 , c5 , c5 , c5 , c5 , c5 , c5 , c5 , c5 , c5 , c5 , c5 , c5 , c5 },
									    {c27, c27, c27, c27, c27, c27, c27, c27, c27, c27, c27, c27, c27, c27, c27, c27, c27, c27, c28, c27, c11, c27, c27, c27, c27, c27},
									    {c4 , c4 , c4 , c4 , c4 , c4 , c4 , c4 , c4 , c4 , c4 , c3 , c4 , c4 , c4 , c4 , c4 , c4 , c4 , c4 , c11, c4 , c4 , c4 , c4 , c4 },
									    {c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c13, c11, c11, c11, c11 , c11, c11, c11, c11, c11, c11, c11, c11, c11, c11 },
									    {c1 , c3 , c3 , c3 ,c3 ,c3 ,c3 ,c3 ,c3 ,c3 ,c3 ,c3 ,c3 , c3 , c3 , c3 , c3 , c3 ,c3 , c3 , c3 , c3 , c1 , c1 , c1 ,c3},
									    {c4 , c39 , c4 , c4 , c4 , c4 , c4 , c4 , c4 , c4 , c4 , c4 , c4 , c4 , c4 , c4 , c4 , c4 , c4 , c4 , c4 , c4 , c4 , c4 , c4 , c4 },
									    {c11 , c39 , c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c38, c11, c11, c11, c11, c11, c11 },
									    {c0 , c0 , c0 , c0 , c0 , c0 , c0 , c0 , c0 , c0 , c0 , c0 , c0 , c0 , c0 , c0 , c0 , c0 , c0 , c0 , c0 , c0 , c0 , c0 , c0 , c0}
									};	
	}

	
	public Casilla returnCasilla(int estado, char c) {
		//System.out.println(matrix[estado][diccionario.get(charToInt(c))]);
		int columna = diccionario.get(charToInt(c))-1;
		return (this.matrix[estado][columna]);
	}
	
	public ArrayList<String> getTablaSimbolos(){
		return this.TablaSimbolo;
	}
}