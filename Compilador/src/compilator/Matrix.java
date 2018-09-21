package compilator;

import java.util.Hashtable;

public class Matrix {
	Casilla matrix [][];// = new Casilla[10][26];
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
		diccionario.put(0, 0); // /n : salto de linea ?
		diccionario.put(32, 21); 
		diccionario.put(117, 22); //u
		diccionario.put(105, 23); // i
		diccionario.put(108, 24); // l
		diccionario.put(300, 25); // 300 = otro
		
		 //Estado 11 = FINAL
		Casilla c1 = new Casilla (10,as1); //agregar buffer = as1
		Casilla c2 = new Casilla (3,as1); 
		Casilla c3 = new Casilla (11,AS_PReserva); //buscarTPR = as6
		Casilla c4 = new Casilla (11,finsimbolo);
		Casilla c5 = new Casilla (6,noaction);
		Casilla c6 = new Casilla (7,inicbuffer);
		Casilla c7 = new Casilla (9,inicbuffer);
		Casilla c71 = new Casilla (-1,as6);
		Casilla c8 = new Casilla (6,inicbuffer);
		Casilla c9 = new Casilla (1,inicbuffer);
		Casilla c10 = new Casilla (0,noaction);
		Casilla c11 = new Casilla (-1,error);
		Casilla c12 = new Casilla (2,agregarBuffer);
		Casilla c13 = new Casilla (-1,buscarTS);
		Casilla c14 = new Casilla (1,AgregaraBuffer);
		Casilla c15 = new Casilla (2,error);
		Casilla c17 = new Casilla (11,error);
		Casilla c18 = new Casilla (11,noaction);
		Casilla c19 = new Casilla (3,error);
		Casilla c20 = new Casilla (11,finconsimbolo);
		Casilla c21 = new Casilla (4,error);
		Casilla c22 = new Casilla (2,noaction);
		Casilla c23 = new Casilla (2,finlong);
		Casilla c24 = new Casilla (5,error);
		Casilla c25 = new Casilla (-1,findunsigned);
		Casilla c26 = new Casilla (-1,noaction);
		Casilla c27 = new Casilla (7,AgregarCadena);
		Casilla c28 = new Casilla (11,fincadena);
		Casilla c29 = new Casilla (5,error);
		Casilla c30 = new Casilla (6,finconsimbolo);
		Casilla c31 = new Casilla (6,as6);
		Casilla c32 = new Casilla (6,error);
		Casilla c33= new Casilla (-1,finconsimbolo);
		Casilla c34 = new Casilla (-1,as1);
		Casilla c35 = new Casilla (5,AgregarBuffer);
		Casilla c36 = new Casilla (11, finUnsigned);
		Casilla c37 = new Casilla (11,finLong);
		Casilla c38 = new Casilla (4,noAction);
		Casilla c0  = new Casilla (0,noAction);
		
		
		Casilla [][] matrix = { {c1 , c2 , c3 , c3 , c3 , c3 , c3 , c3 , c5 , c6 , c6 , c4 , c7 , c7 , c4 , c4 , c4 , c4 , c8 , c9 , c10, c10, c1 , c1 , c1 , c11},
								{c12, c12, c4 , c4 , c4 , c4 , c4 , c4 , c4 , c4 , c4 , c4 , c11, c13, c13, c13, c13, c13, c13, c11, c26, c13, c12, c12, c12, c13},
								{c12, c12, c20, c20, c20, c20, c20, c20, c20, c20, c20, c20, c20, c20, c20, c20, c20, c20, c20, c20, c20, c20, c12, c12, c12, c20},
								{c11, c19, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c38, c11, c11, c11, c11, c11, c11},
								{c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c35, c11, c37, c11},
								{c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c5 , c11, c36, c11, c11},
								{c5 , c5 , c5 , c5 , c5 , c5 , c5 , c5 , c5 , c5 , c5 , c5 , c5 , c5 , c5 , c5 , c5 , c5 , c5 , c5 , c5 , c11 , c5 , c5 , c5 , c5 },
								{c27, c27, c27, c27, c27, c27, c27, c27, c27, c27, c27, c27, c27, c27, c27, c27, c27, c27, c28, c27, c11, c27, c27, c27, c27, c27},
								{c20, c20, c20, c20, c20, c20, c20, c20, c20, c20, c20, c3 , c20, c20, c20, c20, c20, c20, c20, c20, c11, c3 , c20, c20, c20, c20},
								{c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c3 , c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11, c11},
								{c1 , c20, c33, c33, c33, c33, c33, c33, c33, c33, c33, c33, c33, c33, c33, c33, c33, c33, c33, c33, c20, c20, c1 , c1 , c1 , c33},
								{c0 , c0 , c0 , c0 , c0 , c0 , c0 , c0 , c0 , c0 ,c0 , c0 , c0 , c0 , c0 , c0 , c0 , c0 , c0 , c0 , c0 , c0 , c0 , c0 , c0 , c0}
							};	
	}
	
	public Casilla returnCasilla(int estado, char c) {
		return matrix[estado][diccionario.get(charToInt(c))];
	}
}
