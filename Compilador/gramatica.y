%{
 *------------------------------------------------------------------
 * Compilador
 * Yacc v2.0
 *
%}


%token ID CTE CADENA LINTEGER USINTEGER
%token '<=' '>=' '!=' ':=' 
%token IF ELSE ENDIF WHILE PRINT
%token VOID FUN RETURN

%start programa


%%

programa: sentencias {print("reconoce bien el lenguaje");}
;

sentencia: ejecutable ','
		 | declarativa ','
		 ;
		  

sentencias: sentencia sentencias
		  ;
		   
bloque_de_sentencias: '{'sentencias'}'
					;


condicion: exp '<=' exp {$$ = ($1.ival '<=' $2.ival);} 
		 | exp '>=' exp {$$ = ($1.ival '>=' $2.ival);} 
		 | exp '=' exp  {$$ = ($1.ival '=' $2.ival);}
		 | exp '<' exp  {$$ = ($1.ival '<' $2.ival);}
		 | exp '>' exp  {$$ = ($1.ival '>' $2.ival);}
		 | exp '!=' exp {$$ = ($1.ival '!=' $2.ival);}
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
		
ejecutable: IF '('condicion')'bloque_de_sentencias ELSE bloque_de_sentencias ENDIF
		   |IF '('condicion')'sentencia ELSE sentencia ENDIF
		   |IF '('condicion')'bloque_de_sentencias ELSE sentencia ENDIF
		   |IF '('condicion')'sentencia ELSE bloque_de_sentencias ENDIF
		   |IF '('condicion')'bloque_de_sentencias
		   |IF '('condicion')'sentencia
		   |WHILE '('condicion')'bloque_de_sentencias
		   |WHILE '('condicion')'sentencia
		   |PRINT'('CADENA')'
		   |ID ':=' exp
		   ;

exp: exp '+' termino {$$ = ($1.ival + $2.ival);}
   | exp '-' termino {$$ = ($1.ival - $2.ival);}
   | termino
   ;
   
termino: termino '*' factor {($$ = $1.ival * $2.ival);} 
	   | termino '/' factor {($$ = $1.ival / $2.ival);}
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

public void yyerror(String e){
	System.out.print(e);
}