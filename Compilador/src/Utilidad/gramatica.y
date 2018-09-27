%{
/*------------------------------------------------------------------
 * yacc-able Java 1 grammar
 * see notes at end
 */
%}

%token ID CTE CADENA LINTEGER USINTEGER '<=' '>=' '!=' ':=' '<' '>' IF ELSE ENDIF WHILE PRINT VOID FUN RETURN '<=' '>=' '!=' ':=' '<' '>' '='

%start programa


%%

programa: sentencias {print("reconoce bien el lenguaje");}
;

sentencia: ejecutable
		 | declarativa
		 ;
		  

sentencias: sentencia ',' sentencias
		  ;

ejecutable: IF '('condicion')'sentencia ENDIF
		   |WHILE '('condicion')'sentencia
		   |PRINT'('CADENA')'
		   |ID ':=' exp
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
		   | FUN ID '{' sentencias RETURN '('retorno')''}'
		   ;
			
lista_de_variables: ID ',' lista_de_variables
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

