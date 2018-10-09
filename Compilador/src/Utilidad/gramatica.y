%{
import Compilador.Token;
%}


%token ID CTE CADENA LINTEGER USINTEGER
%token MENOR_IGUAL MAYOR_IGUAL DIST ASIGN 
%token IF ELSE ENDIF WHILE PRINT
%token VOID FUN RETURN

%start programa


%%

programa: sentencias {print("reconoce bien el lenguaje");}
;

sentencias:   sentencias declarativa
          |   sentencias ejecutable
          |   declarativa
          |   ejecutable
          |   error','    {yyerror(((Token) $2.obj).getLinea(), "");}
          ;
		  
		   
bloque_de_sentencias: '{'sentencias'}'
					;


condicion: exp MENOR_IGUAL exp {$$ = menorIgual($1.ival,$2.ival);} 
		 | exp MAYOR_IGUAL exp {$$ = mayorIgual($1.ival,$2.ival);} 
		 | exp '=' exp  {$$ = ($1.ival '=' $2.ival);}
		 | exp '<' exp  {$$ = ($1.ival '<' $2.ival);}
		 | exp '>' exp  {$$ = ($1.ival '>' $2.ival);}
		 | exp DIST exp {$$ = distinto($1.ival,$2.ival);}
		 | error {print("condicion mal escrita");}
		 ;
					
declarativa: tipo lista_de_variables
		   | tipo ID
		   | FUN ID '{' sentencias RETURN '('retorno ')''}'
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

asig: ID ASIGN exp ','       {print("Sintaxis: Asignacion");}
    | ASIGN exp','        {yyerror(((Token) $3.obj).getLinea(), "Asignacion sin id del lado izq");}
    | ID exp','          {yyerror(((Token) $1.obj).getLinea(), "Asignacion sin :=");}
    | ID ASIGN ','          {yyerror(((Token) $3.obj).getLinea(), "Falta expresion del lado derecho del :=");}
    ;

print: PRINT'('CADENA')' ','     {print("Sintaxis: Sentencia print");}
     | '(' CADENA ')'','       {yyerror(((Token) $1.obj).getLinea(), "'PRINT' faltante en la sentencia de impresion");}
     | PRINT CADENA ')'','     {yyerror(((Token) $2.obj).getLinea(), "Falta '('");}
     | PRINT '('CADENA','      {yyerror(((Token) $3.obj).getLinea(), "Falta ')'");}
     | PRINT'(' ')'','         {print("Sintaxis: Sentencia print");}
     | PRINT'(' CADENA')'      {yyerror(((Token) $4.obj).getLinea(), "Falta ','");}
     ;
	 
ejecutable_if: IF '('condicion')'bloque_de_sentencias ELSE bloque_de_sentencias ENDIF ','               {print("Sintaxis: Sentencia IF-ELSE");} 
		     | IF '('condicion')'bloque_de_sentencias ENDIF ','                                         {print("Sintaxis: Sentencia IF");}
			 | '('condicion')'bloque_de_sentencias ENDIF ','							  		         {yyerror(((Token) $1.obj).getLinea(), "'ELSE' sin 'IF'");}
             | IF condicion')'bloque_de_sentencias ELSE bloque_de_sentencias ENDIF','                   {yyerror(((Token) $1.obj).getLinea(), "Falta '(' en condicionicion de sentencia condicionicional");}
			 | IF '(' ')' bloque_de_sentencias ELSE bloque_de_sentencias ENDIF','                          {yyerror(((Token) $2.obj).getLinea(), "condicionicion faltante en sentencia condicionicional");}
			 | IF '(' condicion error bloque_de_sentencias ELSE bloque_de_sentencias ENDIF','              {yyerror(((Token) $2.obj).getLinea(), "Falta ')' en la sentencia condicionicional");}
			 | IF '(' condicion ')' ELSE bloque_de_sentencias ENDIF','                                     {yyerror(((Token) $4.obj).getLinea(), "Sentencia/s faltante/s luego de 'IF'");}
			 | IF '(' condicion ')' bloque_de_sentencias bloque_de_sentencias ENDIF','                     {yyerror(((Token) $4.obj).getLinea(), "Falta 'ELSE' o sobran llaves en la declaracion de bloque");}
			 | IF '(' condicion ')' bloque_de_sentencias ELSE ENDIF','                                     {yyerror(((Token) $6.obj).getLinea(), "Bloque faltante luego de 'ELSE'");}
			 | IF '(' condicion ')' bloque_de_sentencias ELSE bloque_de_sentencias error','                {yyerror(((Token) $6.obj).getLinea(), "Falta 'ENDIF'");}
			 | IF '(' condicion ')' bloque_de_sentencias ELSE bloque_de_sentencias ENDIF                   {yyerror(((Token) $6.obj).getLinea(), "Falta ','");}
			 | IF '(' condicion ')' bloque_de_sentencias ENDIF                                                       {yyerror(((Token) $6.obj).getLinea(), "Falta ','");}
			 | IF condicion ')' bloque_de_sentencias ENDIF                                                           {yyerror(((Token) $2.obj).getLinea(), "Falta '('");}
			 | IF '(' ')' bloque_de_sentencias ENDIF                                                            {yyerror(((Token) $2.obj).getLinea(), "Falta condicionicion en la sentencia condicionicional");}
			 | IF '(' condicion error bloque_de_sentencias ENDIF                                                     {yyerror(((Token) $2.obj).getLinea(), "Falta ')'");}
			 | IF '(' condicion ')' bloque_de_sentencias error','                                                    {yyerror(((Token) $6.obj).getLinea(), "Falta 'ENDIF' en sentencia condicionicional");}
			 | IF '(' condicion ')' ENDIF                                                                 {yyerror(((Token) $4.obj).getLinea(), "Falta bloque de sentencias en sentencia condicionicional");}
			 ;
			 
ejecutable_while: WHILE '('condicion')'bloque_de_sentencias                      {print("Sintaxis: Sentencia While");}
				| error '(' condicion ')' bloque_de_sentencias                   {yyerror(((Token) $2.obj).getLinea(), "'SENTENCIA DE CONTROL' desconocida");}
				| WHILE condicion ')' bloque_de_sentencias                       {yyerror(((Token) $1.obj).getLinea(), "Falta'('");}
				| WHILE '(' condicion bloque_de_sentencias                       {yyerror(((Token) $2.obj).getLinea(), "Falta ')'");}
				| WHILE '(' condicion ')' error','                               {yyerror(((Token) $4.obj).getLinea(), "Sentencia de control sin bloque de sentencias");}
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
		  ;
%%

Matrix m = new Matrix();
Analizador a = new Analizador(m);

public Parser(){

}

public int yylex(){
	int NumeroToken = 0;
	NumeroToken = a.getToken().getToken();
	return NumeroToken;
	yylval = new ParserVal();
}

public int yylex(){
	int NumeroToken = 0;
	NumeroToken = a.getToken();
	yylval = newParserVal(a.getLexema());
	return NumeroToken;
}

public void yyerror(int l, String s){

    System.out.println("Sintax Error: Line " + l + " - " + s);

}


public void yyerror(String e){
	System.out.print(e);
}
