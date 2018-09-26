//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 2 "gramatica.y"
/*------------------------------------------------------------------
 * yacc-able Java 1 grammar
 * see notes at end
 */
//#line 22 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short ID=257;
public final static short CTE=258;
public final static short CADENA=259;
public final static short LINTEGER=260;
public final static short USINTEGER=261;
public final static short IF=266;
public final static short ELSE=267;
public final static short ENDIF=268;
public final static short WHILE=269;
public final static short PRINT=270;
public final static short VOID=271;
public final static short FUN=272;
public final static short RETURN=273;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    2,    2,    1,    1,    5,    6,    6,    6,    6,
    6,    6,    4,    4,    9,    9,    8,    8,    8,    8,
   10,   10,   10,   10,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    7,    7,    7,   11,
   11,   11,   12,   12,   12,
};
final static short yylen[] = {                            2,
    1,    2,    2,    2,    1,    3,    3,    3,    3,    3,
    3,    3,    2,    9,    3,    1,    1,    1,    1,    1,
    4,    3,    4,    1,    8,    8,    8,    8,    6,    6,
    5,    5,    4,    1,    3,    1,    3,    3,    1,    3,
    3,    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
   36,    0,    0,    0,    0,    0,    0,   19,    0,    0,
    1,    0,    0,    0,    0,    0,    0,   42,    0,    0,
    0,    0,    0,    4,    2,    3,    0,    0,    0,   13,
    0,    0,   43,   44,   45,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   40,   41,    0,    0,    0,    0,
    0,    0,    0,    0,   33,    0,   15,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   32,   31,    0,    0,
    0,   30,    0,   29,    0,    6,    0,    0,    0,    0,
    0,   24,    0,   26,   28,   27,   25,    0,    0,    0,
    0,   22,   14,   21,   23,
};
final static short yydgoto[] = {                         10,
   11,   12,   13,   14,   60,   37,   15,   16,   30,   83,
   17,   18,
};
final static short yysindex[] = {                      -188,
    0, -259,    0,    0,  -24,  -12,   -7,    0, -214,    0,
    0, -188,   31,   33,   11, -158,  -15,    0, -181, -181,
 -181, -157,  -19,    0,    0,    0, -181, -181,   46,    0,
 -181, -181,    0,    0,    0,   11,   70,  -23,   71,   72,
 -188,  -15,  -15, -158,    0,    0, -120, -181, -181, -181,
 -181, -181, -181, -120,    0, -159,    0, -188, -197, -182,
   11,   11,   11,   11,   11,   11,    0,    0,   75,   -9,
 -120,    0, -120,    0, -163,    0, -151, -150, -149, -148,
  -40,    0,   80,    0,    0,    0,    0,  -39,   -3,   82,
   83,    0,    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,   19,    2,    8,    0,    0,    0,    0,    0,    0,
    0,    1,    0,    0,   66,    0,  -36,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   81,    0,
    0,    0,    0,    0,    0,   84,    0,    0,    0,    0,
    0,  -31,  -26,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   86,   88,   89,   90,   91,   92,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   19,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
   -1,   -6,    0,    0,  -50,  113,   39,    0,   94,    0,
   68,   69,
};
final static int YYTABLESIZE=274;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         88,
    5,   92,   58,   68,   39,   19,   39,   39,   39,   37,
   24,   37,   37,   37,   38,   20,   38,   38,   38,   27,
   78,   28,   80,   39,   39,   39,   31,   21,   37,   37,
   37,   32,   22,   38,   38,   38,   51,   53,   52,   56,
   59,    5,   23,   44,   44,   44,   44,   67,   44,   45,
   45,   45,   45,   27,   45,   28,   70,   36,   38,   38,
   43,   43,   43,   43,   77,   43,   79,    1,    2,   71,
   72,    3,    4,   82,   25,   33,   26,    5,   34,   35,
    6,    7,    8,    9,   73,   74,   61,   62,   63,   64,
   65,   66,    1,   81,   42,   43,    3,    4,   29,   45,
   46,   40,    5,   41,   44,    6,    7,    8,    9,   34,
   47,   54,   55,   69,   75,   76,   84,   85,   86,   87,
   89,   93,   94,   95,   16,    5,    7,   35,    8,   12,
   10,   11,    9,   39,    0,    1,    2,   57,    0,    3,
    4,    0,    0,    0,    0,    5,    0,    0,    6,    7,
    8,    9,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   90,   91,    0,
    0,    0,    0,    0,   19,   39,   39,   39,    0,    0,
   37,   37,   37,    0,    0,   38,   38,   38,   48,   49,
   50,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   17,    0,
    0,    0,    0,    0,   20,    0,    0,    0,    0,    0,
    0,    0,    0,    5,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
    0,   41,  123,   54,   41,  265,   43,   44,   45,   41,
   12,   43,   44,   45,   41,   40,   43,   44,   45,   43,
   71,   45,   73,   60,   61,   62,   42,   40,   60,   61,
   62,   47,   40,   60,   61,   62,   60,   61,   62,   41,
   47,   41,  257,   42,   43,   44,   45,   54,   47,   42,
   43,   44,   45,   43,   47,   45,   58,   19,   20,   21,
   42,   43,   44,   45,   71,   47,   73,  256,  257,  267,
  268,  260,  261,   75,   44,  257,   44,  266,  260,  261,
  269,  270,  271,  272,  267,  268,   48,   49,   50,   51,
   52,   53,  256,  257,   27,   28,  260,  261,  257,   31,
   32,  259,  266,  123,   59,  269,  270,  271,  272,   44,
   41,   41,   41,  273,   40,  125,  268,  268,  268,  268,
   41,  125,   41,   41,   44,  125,   41,   44,   41,   41,
   41,   41,   41,   21,   -1,  256,  257,   44,   -1,  260,
  261,   -1,   -1,   -1,   -1,  266,   -1,   -1,  269,  270,
  271,  272,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,   -1,
   -1,   -1,   -1,   -1,  265,  262,  263,  264,   -1,   -1,
  262,  263,  264,   -1,   -1,  262,  263,  264,  262,  263,
  264,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,   -1,
   -1,   -1,   -1,   -1,  257,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  273,
};
}
final static short YYFINAL=10;
final static short YYMAXTOKEN=273;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,"'('","')'","'*'","'+'","','",
"'-'",null,"'/'",null,null,null,null,null,null,null,null,null,null,null,"';'",
"'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,"ID","CTE","CADENA","LINTEGER","USINTEGER",
"\"<=\"","\">=\"","\"!=\"","\":=\"","IF","ELSE","ENDIF","WHILE","PRINT","VOID",
"FUN","RETURN",
};
final static String yyrule[] = {
"$accept : programa",
"programa : sentencias",
"sentencia : ejecutable ','",
"sentencia : declarativa ','",
"sentencias : sentencia sentencias",
"sentencias : sentencia",
"bloque_de_sentencias : '{' sentencias '}'",
"condicion : exp \"<=\" exp",
"condicion : exp \">=\" exp",
"condicion : exp '=' exp",
"condicion : exp '<' exp",
"condicion : exp '>' exp",
"condicion : exp \"!=\" exp",
"declarativa : tipo lista_de_variables",
"declarativa : FUN ID '{' sentencias RETURN '(' retorno ')' '}'",
"lista_de_variables : ID ';' lista_de_variables",
"lista_de_variables : ID",
"tipo : LINTEGER",
"tipo : FUN",
"tipo : VOID",
"tipo : USINTEGER",
"retorno : ID '(' ID ')'",
"retorno : ID '(' ')'",
"retorno : ID '(' CTE ')'",
"retorno : sentencias",
"ejecutable : IF '(' condicion ')' bloque_de_sentencias ELSE bloque_de_sentencias ENDIF",
"ejecutable : IF '(' condicion ')' sentencia ELSE sentencia ENDIF",
"ejecutable : IF '(' condicion ')' bloque_de_sentencias ELSE sentencia ENDIF",
"ejecutable : IF '(' condicion ')' sentencia ELSE bloque_de_sentencias ENDIF",
"ejecutable : IF '(' condicion ')' bloque_de_sentencias ENDIF",
"ejecutable : IF '(' condicion ')' sentencia ENDIF",
"ejecutable : WHILE '(' condicion ')' bloque_de_sentencias",
"ejecutable : WHILE '(' condicion ')' sentencia",
"ejecutable : PRINT '(' CADENA ')'",
"ejecutable : exp",
"ejecutable : ID \":=\" exp",
"ejecutable : error",
"exp : exp '+' termino",
"exp : exp '-' termino",
"exp : termino",
"termino : termino '*' factor",
"termino : termino '/' factor",
"termino : factor",
"factor : ID",
"factor : LINTEGER",
"factor : USINTEGER",
};

//#line 91 "gramatica.y"

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

//#line 324 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 17 "gramatica.y"
{print("reconoce bien el lenguaje");}
break;
case 7:
//#line 33 "gramatica.y"
{yyval = (val_peek(2).ival '<=' val_peek(1).ival);}
break;
case 8:
//#line 34 "gramatica.y"
{yyval = (val_peek(2).ival '>=' val_peek(1).ival);}
break;
case 9:
//#line 35 "gramatica.y"
{yyval = (val_peek(2).ival '=' val_peek(1).ival);}
break;
case 10:
//#line 36 "gramatica.y"
{yyval = (val_peek(2).ival '<' val_peek(1).ival);}
break;
case 11:
//#line 37 "gramatica.y"
{yyval = (val_peek(2).ival '>' val_peek(1).ival);}
break;
case 12:
//#line 38 "gramatica.y"
{yyval = (val_peek(2).ival '!=' val_peek(1).ival);}
break;
case 37:
//#line 75 "gramatica.y"
{yyval = (val_peek(2).ival '+' val_peek(1).ival);}
break;
case 38:
//#line 76 "gramatica.y"
{yyval = (val_peek(2).ival '-' val_peek(1).ival);}
break;
case 40:
//#line 80 "gramatica.y"
{(yyval = val_peek(2).ival '*' val_peek(1).ival);}
break;
case 41:
//#line 81 "gramatica.y"
{(yyval = val_peek(2).ival '/' val_peek(1).ival);}
break;
//#line 517 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
