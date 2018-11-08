%{
package Compilador;
%}


%token ID CTE CADENA LINTEGER USINTEGER
%token MENOR_IGUAL MAYOR_IGUAL DIST ASIGN 
%token IF ELSE ENDIF WHILE PRINT
%token VOID FUN RETURN

%start programa


%%

programa: sentencias {print("Reconoce bien el lenguaje");}
;

sentencias:   sentencias declarativa ','
          |   sentencias ejecutable ','
          |   declarativa ','
          |   ejecutable  ','
		  |   error ',' 				{yyerror(t.getLinea(), "Error ");};
          ;
		  
		   
bloque_de_sentencias: '{'sentencias'}'
					;


condicion: exp MENOR_IGUAL exp {$$ = menorIgual($1.ival,$2.ival);} 
		 | exp MAYOR_IGUAL exp {$$ = mayorIgual($1.ival,$2.ival);} 
		 | exp '=' exp  {$$ = igual($1.ival,$2.ival);}
		 | exp '<' exp  {$$ = mayor($1.ival,$2.ival);}
		 | exp '>' exp  {$$ = menor($1.ival,$2.ival);}
		 | exp DIST exp {$$ = distinto($1.ival,$2.ival);}
		 | error {print("Condición mal escrita");}
		 ;
					
declarativa: tipo lista_de_variables                        {print("Sintaxis: Declaracion multiple");}
		   | tipo ID
		   | FUN ID '{' sentencias RETURN '('retorno ')''}'     {print("Sintaxis: Closure");}
		   | tipo                                            {yyerror(t.getLinea(), "Faltan nombres de variables");};
		   ;
			
lista_de_variables: ID ';' lista_de_variables
				  ;
				   
tipo: LINTEGER
	| VOID
	| USINTEGER
	;

retorno: ID '('ID')'
		|ID '('')'
		|ID '('constante')'
		|sentencias
		;
		
ejecutable: ejecutable_if
		  | ejecutable_while
		  | asig
		  | print
		  ;

asig: ID ASIGN exp       {print("Sintaxis: Asignacion");}
    | ASIGN exp        	{yyerror(t.getLinea(), "Asignacion sin id del lado izq");}
    | ID exp          	{yyerror(t.getLinea(), "Asignacion sin :=");}
    | ID ASIGN           {yyerror(t.getLinea(), "Falta expresion del lado derecho del :=");}
	| ID error 			{yyerror(t.getLinea(), "Error en la asignación");}
    ;

print: PRINT'('CADENA')'      {print("Sintaxis: Sentencia print");}
     | '(' CADENA ')'       {yyerror(t.getLinea(), "'PRINT' faltante en la sentencia de impresion");}
     | PRINT CADENA ')'     {yyerror(t.getLinea(), "Falta '('");}
     | PRINT '('CADENA      {yyerror(t.getLinea(), "Falta ')'");}
     | PRINT'(' ')'         {print("Sintaxis: Sentencia print");}
     ;
	 
ejecutable_if: IF '('condicion')'bloque_de_sentencias ELSE bloque_de_sentencias ENDIF                {print("Sintaxis: Sentencia IF-ELSE");} 
		     | IF '('condicion')'bloque_de_sentencias ENDIF                                          {print("Sintaxis: Sentencia IF");}
			 | '('condicion')'bloque_de_sentencias ENDIF 							  		         {yyerror(t.getLinea(), "'ELSE' sin 'IF'");}
             | IF condicion')'bloque_de_sentencias ELSE bloque_de_sentencias ENDIF                   {yyerror(t.getLinea(), "Falta '(' en condicion de sentencia condicional");}
			 | IF '(' ')' bloque_de_sentencias ELSE bloque_de_sentencias ENDIF                          {yyerror(t.getLinea(), "condicion faltante en sentencia condicional");}
			 | IF '(' condicion error bloque_de_sentencias ELSE bloque_de_sentencias ENDIF              {yyerror(t.getLinea(), "Falta ')' en la sentencia condicional");}
			 | IF '(' condicion ')' ELSE bloque_de_sentencias ENDIF                                 {yyerror(t.getLinea(), "Sentencia/s faltante/s luego de 'IF'");}
			 | IF '(' condicion ')' bloque_de_sentencias bloque_de_sentencias ENDIF                     {yyerror(t.getLinea(), "Falta 'ELSE' o sobran llaves en la declaracion de bloque");}
			 | IF '(' condicion ')' bloque_de_sentencias ELSE ENDIF                                     {yyerror(t.getLinea(), "Bloque faltante luego de 'ELSE'");}
			 | IF '(' condicion ')' bloque_de_sentencias ELSE bloque_de_sentencias error                {yyerror(t.getLinea(), "Falta 'ENDIF'");}
			 | IF condicion ')' bloque_de_sentencias ENDIF                                                           {yyerror(t.getLinea(), "Falta '('");}
			 | IF '(' ')' bloque_de_sentencias ENDIF                                                            {yyerror(t.getLinea(), "Falta condicion en la sentencia condicional");}
			 | IF '(' condicion error bloque_de_sentencias ENDIF                                                     {yyerror(t.getLinea(), "Falta ')'");}
			 | IF '(' condicion ')' bloque_de_sentencias error                                                    {yyerror(t.getLinea(), "Falta 'ENDIF' en sentencia condicional");}
			 | IF '(' condicion ')' ENDIF                                                                 {yyerror(t.getLinea(), "Falta bloque de sentencias en sentencia condicional");}  
			 ;
			 
ejecutable_while: WHILE '('condicion')'bloque_de_sentencias                      {print("Sintaxis: Sentencia While");}
				| error '(' condicion ')' bloque_de_sentencias                   {yyerror(t.getLinea(), "'SENTENCIA DE CONTROL' desconocida");}
				| WHILE condicion ')' bloque_de_sentencias                       {yyerror(t.getLinea(), "Falta'('");}
				| WHILE '(' condicion bloque_de_sentencias                       {yyerror(t.getLinea(), "Falta ')'");}
				| WHILE '(' condicion ')' error                               {yyerror(t.getLinea(), "Sentencia de control sin bloque de sentencias");}
				;

exp: exp '+' termino
   | exp '-' termino
   | termino
   ;
   
termino: termino '*' factor
	   | termino '/' factor
	   | factor
	   ;
	   
factor: ID
	  | constante
	  ;
	  
constante : LINTEGER
		  | USINTEGER
		  | CADENA
		  ;
%%

Matrix m = new Matrix();
Analizador a = new Analizador(m);
ArrayList<String> errores;
Token t;

public Parser (Analizador a) {
	this.a = a;
	this.errores = new ArrayList<>();
}

public Token yylex(){
	Token Token = a.getToken();
	yylval = new ParserVal(a.getLexema());
	return Token;
}

private void print(String string) throws IOException {
	System.out.println(string);
	a.imprimirArchivo(string);
}

public ArrayList<String> getErrores(){
	return this.errores;
}

private int distinto(int ival, int ival2) {
	if (ival != ival2)
		return 1;
	return 0;
}
private int mayorIgual(int ival, int ival2) {
	if (ival >= ival2)
		return 1;
	return 0;
}
private int menorIgual(int ival, int ival2) {
	if (ival <= ival2)
		return 1;
	return 0;
}

private int menor(int ival, int ival2) {
	if (ival < ival2)
		return 1;
	return 0;
}

private int mayor(int ival, int ival2) {
	if (ival > ival2)
		return 1;
	return 0;
}

private int igual(int ival, int ival2) {
	if (ival == ival2)
		return 1;
	return 0;
}

public void yyerror(int i, String e){
sys
}

public void yyerror(String e){
	//System.out.print(e);
}
