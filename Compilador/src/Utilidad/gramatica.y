%{
/*------------------------------------------------------------------
 * yacc-able Java 1 grammar
 * see notes at end
 */
%}

%token ID CTE CADENA LINTEGER USINTEGER '<=' '>=' '!=' ':=' '<' '>' IF ELSE ENDIF WHILE PRINT VOID FUN RETURN

%nonassoc '<=' '>=' '!=' ':=' '<' '>' '='

%start programa


%%

programa: sentencias {print("reconoce bien el lenguaje");}
;

sentencia: ejecutable ','
		 | declarativa ','
		 ;
		  

sentencias: sentencia sentencias
		  | sentencia 
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
		   | FUN ID '{' sentencias RETURN '('retorno')''}'
		   ;
			
lista_de_variables: ID ';' lista_de_variables
				  | ID
				  ;
				   
tipo: LINTEGER
	| FUN
	| VOID
	| USINTEGER
	;

retorno: ID '('ID')'
		|ID '('')'
		|ID '('CTE')'
		|sentencias
		;
		
ejecutable: IF '('condicion')'bloque_de_sentencias ELSE bloque_de_sentencias ENDIF
		   |IF '('condicion')'sentencia ELSE sentencia ENDIF
		   |IF '('condicion')'bloque_de_sentencias ELSE sentencia ENDIF
		   |IF '('condicion')'sentencia ELSE bloque_de_sentencias ENDIF
		   |IF '('condicion')'bloque_de_sentencias ENDIF
		   |IF '('condicion')'sentencia ENDIF
		   |WHILE '('condicion')'bloque_de_sentencias
		   |WHILE '('condicion')'sentencia
		   |PRINT'('CADENA')'
		   |exp
		   |ID ':=' exp 
		   |error
		   ;

exp: exp '+' termino {$$ = ($1.ival '+' $2.ival);}
   | exp '-' termino {$$ = ($1.ival '-' $2.ival);}
   | termino
   ;
   
termino: termino '*' factor {($$ = $1.ival '*' $2.ival);} 
	   | termino '/' factor {($$ = $1.ival '/' $2.ival);}
	   | factor
	   ;

factor: ID
	  | LINTEGER
	  | USINTEGER		
	  ;
	  
%%

Matrix m = new Matrix();
Analizador a = new Analizador(m);

public Parser(){

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

