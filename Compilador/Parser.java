package Compilador;

import java.io.IOException;

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
public final static short MENOR_IGUAL=262;
public final static short MAYOR_IGUAL=263;
public final static short DIST=264;
public final static short ASIGN=265;
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
    0,    1,    1,    1,    1,    1,    4,    5,    5,    5,
    5,    5,    5,    5,    2,    2,    2,    8,    7,    7,
    7,    9,    9,    9,    9,    3,    3,    3,    3,   13,
   13,   13,   13,   14,   14,   14,   14,   14,   14,   11,
   11,   11,   11,   11,   11,   11,   11,   11,   11,   11,
   11,   11,   11,   11,   11,   11,   12,   12,   12,   12,
   12,    6,    6,    6,   15,   15,   15,   16,   16,   10,
   10,
};
final static short yylen[] = {                            2,
    1,    2,    2,    1,    1,    2,    3,    3,    3,    3,
    3,    3,    3,    1,    2,    2,    9,    3,    1,    1,
    1,    4,    3,    4,    1,    1,    1,    1,    1,    4,
    3,    3,    3,    5,    4,    4,    4,    4,    4,    9,
    7,    6,    8,    8,    9,    8,    8,    8,    9,    8,
    6,    5,    5,    6,    7,    5,    5,    5,    4,    4,
    6,    3,    3,    1,    3,    3,    1,    1,    1,    1,
    1,
};
final static short yydefred[] = {                         0,
    0,    0,   19,   21,    0,    0,    0,    0,   20,    0,
    0,    0,    0,    4,    5,    0,   26,   27,   28,   29,
    6,    0,   68,   70,   71,    0,    0,   69,    0,   67,
    0,   14,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    2,    3,    0,   15,    0,   33,    0,
   32,    0,    0,    0,    0,   31,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   30,    0,    0,   65,   66,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   60,   59,   36,   37,    0,   38,    0,   35,
    0,    0,   18,   58,    0,    0,   53,    0,    0,   56,
    0,    0,   52,    0,   57,   34,    0,    0,    7,    0,
    0,   54,    0,    0,    0,    0,    0,    0,   61,    0,
   42,    0,    0,    0,   55,    0,    0,   41,    0,    0,
    0,    0,    0,   44,    0,   46,   48,    0,    0,   47,
   43,    0,    0,   45,   49,   40,    0,   23,    0,   17,
   22,   24,
};
final static short yydgoto[] = {                         12,
   13,   14,   15,   82,   34,   35,   16,   47,  143,   28,
   17,   18,   19,   20,   29,   30,
};
final static short yysindex[] = {                        73,
   13, -217,    0,    0, -232,  -20,   -1,  -35,    0, -210,
 -188,    0,   90,    0,    0, -183,    0,    0,    0,    0,
    0, -181,    0,    0,    0,    8,   48,    0,  -30,    0,
   54,    0,   21,  -26,  126, -181,   42,   53,  -31,  -34,
   69,   70,   74,    0,    0,   64,    0,   83,    0,   61,
    0, -232, -232, -232, -232,    0,   -8,  -10,   -8, -232,
 -232, -232, -232, -232, -232,  -33,   -8,   81,   22,   85,
   73,   87,   -8, -130,   -8,    0,  -30,  -30,    0,    0,
   73, -245,   -8, -117, -222,   11,   11,   11,   11,   11,
   11, -120,    0,    0,    0,    0,   88,    0,  -40,    0,
 -135,   64,    0,    0,  -22,   -8,    0, -203,   -8,    0,
 -119,   -8,    0,   91,    0,    0,   94,   95,    0, -128,
   -8,    0, -127,   98, -116,   99, -122, -115,    0,  107,
    0,  110, -113,  112,    0,  113, -242,    0,  114,  115,
  -38,   90,   97,    0,  116,    0,    0,  117,  120,    0,
    0,   26,   52,    0,    0,    0,  129,    0,  132,    0,
    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  178,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  -11,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    1,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  131,  140,    0,    0,
    0,    0,    0,    0,    0,  -14,   40,   43,   44,   45,
   66,    0,    0,    0,    0,    0,   19,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   37,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  138,    0,    0,    0,    0,    0,    0,   55,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,
};
final static short yygindex[] = {                         0,
  -60,    3,   23,  325,    2,   56,    0,  108,    0,   38,
    0,    0,    0,    0,   35,   46,
};
final static int YYTABLESIZE=450;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         11,
   16,  152,   81,   81,   39,   81,   81,   92,   37,   70,
   99,   54,   42,  148,   59,   44,   55,   11,   39,   33,
  105,  106,  107,   48,   23,  149,    8,   24,   25,   64,
   84,   64,   64,   64,   58,   45,   51,   66,   36,   23,
   16,   16,   24,   25,  112,  113,   40,   26,   64,   64,
   64,   49,   22,   52,   50,   53,   21,   27,   39,   39,
   31,   57,   97,  121,  122,   96,  158,   32,   23,  142,
   41,   24,   25,   46,   32,   23,   51,   51,   24,   25,
    9,   50,   67,   13,   10,   11,   77,   78,   71,   81,
   52,   51,   53,   68,   50,   50,   52,   56,   53,   79,
   80,   44,  119,   52,   76,   53,   12,   44,    8,   72,
   73,   64,   11,   22,   81,   86,   87,   88,   89,   90,
   91,   45,   74,   75,   95,   16,  102,   45,   98,   11,
  100,  116,  118,  130,  129,  114,  124,  153,  131,  132,
  134,  135,  138,   39,   44,  139,   11,  125,  126,  109,
  110,  136,  140,  144,  145,  146,  147,  150,  151,  154,
  155,   51,    9,  156,   45,   13,   10,   11,   52,  161,
   53,   62,  162,   62,   62,   62,  160,    1,   25,   50,
   63,  103,   63,   63,   63,   64,   63,   65,   12,  159,
   62,   62,   62,    0,    0,    0,   27,    0,    0,   63,
   63,   63,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   43,    2,    0,   23,    3,
    4,   24,   25,   38,    5,    6,   26,   69,    7,    8,
    9,   10,  117,   43,    2,   32,   23,    3,    4,   24,
   25,    8,    5,    6,   64,   83,    7,    8,    9,   10,
   64,   64,   64,   62,   32,   23,   16,   16,   24,   25,
   16,   16,   63,    0,   23,   16,   16,   24,   25,   16,
   16,   16,   16,   16,   39,   39,   32,   23,   39,   39,
   24,   25,  157,   39,   39,   24,   25,   39,   39,   39,
   39,   39,   51,   51,    0,    9,   51,   51,   13,   10,
   11,   51,   51,    0,    0,   51,   51,   51,   51,   51,
   50,   50,    0,    0,   50,   50,    0,    0,    0,   50,
   50,   12,    0,   50,   50,   50,   50,   50,    1,    2,
    0,    0,    3,    4,    0,    0,    0,    5,    6,    0,
    0,    7,    8,    9,   10,   43,    2,    0,    0,    3,
    4,    0,    0,    0,    5,    6,    0,    0,    7,    8,
    9,   10,    1,  141,    0,    0,    3,    4,    0,    0,
    0,    5,    6,    0,    0,    7,    8,    9,   10,    0,
    0,    0,    0,   85,    0,    0,   62,   60,   61,   62,
   93,   94,   62,   62,   62,   63,    0,  101,    0,  104,
    0,   63,   63,   63,    0,    0,    0,  108,  111,    0,
    0,    0,    0,    0,    0,    0,  115,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  120,    0,    0,  123,    0,  127,  128,    0,    0,    0,
    0,    0,    0,    0,    0,  133,    0,    0,    0,  137,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
    0,   40,  123,  123,   40,  123,  123,   41,    7,   41,
   71,   42,   11,  256,   41,   13,   47,   40,    0,   40,
   81,  267,  268,   22,  257,  268,   41,  260,  261,   41,
   41,   43,   44,   45,   33,   13,    0,   36,   40,  257,
   40,   41,  260,  261,  267,  268,  257,  265,   60,   61,
   62,   44,   40,   43,    0,   45,   44,    2,   40,   41,
    5,   41,   41,  267,  268,   44,   41,  256,  257,  130,
  259,  260,  261,  257,  256,  257,   40,   41,  260,  261,
   41,   26,   41,   41,   41,   41,   52,   53,  123,  123,
   43,   44,   45,   41,   40,   41,   43,   44,   45,   54,
   55,   99,  125,   43,   44,   45,   41,  105,  123,   41,
   41,  123,   40,   40,  123,   60,   61,   62,   63,   64,
   65,   99,   59,   41,   44,  125,  257,  105,   44,   40,
   44,   44,  268,   40,   44,  256,  256,   41,   44,  268,
  268,   44,   44,  125,  142,  268,   40,  267,  268,  267,
  268,  268,  268,   44,  268,   44,   44,   44,   44,   44,
   44,  125,  123,   44,  142,  123,  123,  123,   43,   41,
   45,   41,   41,   43,   44,   45,  125,    0,   41,  125,
   41,   74,   43,   44,   45,   60,   61,   62,  123,  152,
   60,   61,   62,   -1,   -1,   -1,  141,   -1,   -1,   60,
   61,   62,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  256,  257,   -1,  257,  260,
  261,  260,  261,  259,  265,  266,  265,  259,  269,  270,
  271,  272,  273,  256,  257,  256,  257,  260,  261,  260,
  261,  256,  265,  266,  256,  256,  269,  270,  271,  272,
  262,  263,  264,  123,  256,  257,  256,  257,  260,  261,
  260,  261,  123,   -1,  257,  265,  266,  260,  261,  269,
  270,  271,  272,  273,  256,  257,  256,  257,  260,  261,
  260,  261,  257,  265,  266,  260,  261,  269,  270,  271,
  272,  273,  256,  257,   -1,  256,  260,  261,  256,  256,
  256,  265,  266,   -1,   -1,  269,  270,  271,  272,  273,
  256,  257,   -1,   -1,  260,  261,   -1,   -1,   -1,  265,
  266,  256,   -1,  269,  270,  271,  272,  273,  256,  257,
   -1,   -1,  260,  261,   -1,   -1,   -1,  265,  266,   -1,
   -1,  269,  270,  271,  272,  256,  257,   -1,   -1,  260,
  261,   -1,   -1,   -1,  265,  266,   -1,   -1,  269,  270,
  271,  272,  256,  257,   -1,   -1,  260,  261,   -1,   -1,
   -1,  265,  266,   -1,   -1,  269,  270,  271,  272,   -1,
   -1,   -1,   -1,   59,   -1,   -1,  256,  262,  263,  264,
   66,   67,  262,  263,  264,  256,   -1,   73,   -1,   75,
   -1,  262,  263,  264,   -1,   -1,   -1,   83,   84,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   92,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  106,   -1,   -1,  109,   -1,  111,  112,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  121,   -1,   -1,   -1,  125,
};
}
final static short YYFINAL=12;
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
"MENOR_IGUAL","MAYOR_IGUAL","DIST","ASIGN","IF","ELSE","ENDIF","WHILE","PRINT",
"VOID","FUN","RETURN",
};
final static String yyrule[] = {
"$accept : programa",
"programa : sentencias",
"sentencias : sentencias declarativa",
"sentencias : sentencias ejecutable",
"sentencias : declarativa",
"sentencias : ejecutable",
"sentencias : error ','",
"bloque_de_sentencias : '{' sentencias '}'",
"condicion : exp MENOR_IGUAL exp",
"condicion : exp MAYOR_IGUAL exp",
"condicion : exp '=' exp",
"condicion : exp '<' exp",
"condicion : exp '>' exp",
"condicion : exp DIST exp",
"condicion : error",
"declarativa : tipo lista_de_variables",
"declarativa : tipo ID",
"declarativa : FUN ID '{' sentencias RETURN '(' retorno ')' '}'",
"lista_de_variables : ID ';' lista_de_variables",
"tipo : LINTEGER",
"tipo : VOID",
"tipo : USINTEGER",
"retorno : ID '(' ID ')'",
"retorno : ID '(' ')'",
"retorno : ID '(' constante ')'",
"retorno : sentencias",
"ejecutable : ejecutable_if",
"ejecutable : ejecutable_while",
"ejecutable : asig",
"ejecutable : print",
"asig : ID ASIGN exp ','",
"asig : ASIGN exp ','",
"asig : ID exp ','",
"asig : ID ASIGN ','",
"print : PRINT '(' CADENA ')' ','",
"print : '(' CADENA ')' ','",
"print : PRINT CADENA ')' ','",
"print : PRINT '(' CADENA ','",
"print : PRINT '(' ')' ','",
"print : PRINT '(' CADENA ')'",
"ejecutable_if : IF '(' condicion ')' bloque_de_sentencias ELSE bloque_de_sentencias ENDIF ','",
"ejecutable_if : IF '(' condicion ')' bloque_de_sentencias ENDIF ','",
"ejecutable_if : '(' condicion ')' bloque_de_sentencias ENDIF ','",
"ejecutable_if : IF condicion ')' bloque_de_sentencias ELSE bloque_de_sentencias ENDIF ','",
"ejecutable_if : IF '(' ')' bloque_de_sentencias ELSE bloque_de_sentencias ENDIF ','",
"ejecutable_if : IF '(' condicion error bloque_de_sentencias ELSE bloque_de_sentencias ENDIF ','",
"ejecutable_if : IF '(' condicion ')' ELSE bloque_de_sentencias ENDIF ','",
"ejecutable_if : IF '(' condicion ')' bloque_de_sentencias bloque_de_sentencias ENDIF ','",
"ejecutable_if : IF '(' condicion ')' bloque_de_sentencias ELSE ENDIF ','",
"ejecutable_if : IF '(' condicion ')' bloque_de_sentencias ELSE bloque_de_sentencias error ','",
"ejecutable_if : IF '(' condicion ')' bloque_de_sentencias ELSE bloque_de_sentencias ENDIF",
"ejecutable_if : IF '(' condicion ')' bloque_de_sentencias ENDIF",
"ejecutable_if : IF condicion ')' bloque_de_sentencias ENDIF",
"ejecutable_if : IF '(' ')' bloque_de_sentencias ENDIF",
"ejecutable_if : IF '(' condicion error bloque_de_sentencias ENDIF",
"ejecutable_if : IF '(' condicion ')' bloque_de_sentencias error ','",
"ejecutable_if : IF '(' condicion ')' ENDIF",
"ejecutable_while : WHILE '(' condicion ')' bloque_de_sentencias",
"ejecutable_while : error '(' condicion ')' bloque_de_sentencias",
"ejecutable_while : WHILE condicion ')' bloque_de_sentencias",
"ejecutable_while : WHILE '(' condicion bloque_de_sentencias",
"ejecutable_while : WHILE '(' condicion ')' error ','",
"exp : exp '+' termino",
"exp : exp '-' termino",
"exp : termino",
"termino : termino '*' factor",
"termino : termino '/' factor",
"termino : factor",
"factor : ID",
"factor : constante",
"constante : LINTEGER",
"constante : USINTEGER",
};

//#line 123 "gramatica.y"

Matrix m = new Matrix();
Analizador a = new Analizador(m);

public Parser (Analizador a) {
	this.a = a;
}

public int yylex() throws IOException{
	int NumeroToken = 0;
	NumeroToken = a.getToken().getToken();
	yylval = new ParserVal();
	return NumeroToken;
}

public void yyerror(int l, String s){
    System.out.println("Sintax Error: Line " + l + " - " + s);

}


public void yyerror(String e){
	//System.out.println(e);
}
//#line 425 "Parser.java"
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
int yyparse() throws IOException
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
//#line 16 "gramatica.y"
{print("reconoce bien el lenguaje");}
break;
case 8:
//#line 31 "gramatica.y"
{yyval = new ParserVal(menorIgual(val_peek(2).ival,val_peek(1).ival));}
break;
case 9:
//#line 32 "gramatica.y"
{yyval = new ParserVal(mayorIgual(val_peek(2).ival,val_peek(1).ival));}
break;
case 10:
//#line 33 "gramatica.y"
{yyval = new ParserVal(val_peek(2).ival = val_peek(1).ival);}
break;
case 11:
//#line 34 "gramatica.y"
{yyval = new ParserVal(val_peek(2).ival < val_peek(1).ival);}
break;
case 12:
//#line 35 "gramatica.y"
{yyval = new ParserVal(val_peek(2).ival > val_peek(1).ival);}
break;
case 13:
//#line 36 "gramatica.y"
{yyval = new ParserVal (distinto(val_peek(2).ival,val_peek(1).ival));}
break;
case 14:
//#line 37 "gramatica.y"
{print("condicion mal escrita");}
break;
case 30:
//#line 65 "gramatica.y"
{print("Sintaxis: Asignacion");}
break;
case 31:
//#line 66 "gramatica.y"
{yyerror(((Token) val_peek(0).obj).getLinea(), "Asignacion sin id del lado izq");}
break;
case 32:
//#line 67 "gramatica.y"
{yyerror(((Token) val_peek(2).obj).getLinea(), "Asignacion sin :=");}
break;
case 33:
//#line 68 "gramatica.y"
{yyerror(((Token) val_peek(0).obj).getLinea(), "Falta expresion del lado derecho del :=");}
break;
case 34:
//#line 71 "gramatica.y"
{print("Sintaxis: Sentencia print");}
break;
case 35:
//#line 72 "gramatica.y"
{yyerror(((Token) val_peek(3).obj).getLinea(), "'PRINT' faltante en la sentencia de impresion");}
break;
case 36:
//#line 73 "gramatica.y"
{yyerror(((Token) val_peek(2).obj).getLinea(), "Falta '('");}
break;
case 37:
//#line 74 "gramatica.y"
{yyerror(((Token) val_peek(1).obj).getLinea(), "Falta ')'");}
break;
case 38:
//#line 75 "gramatica.y"
{print("Sintaxis: Sentencia print");}
break;
case 39:
//#line 76 "gramatica.y"
{yyerror(((Token) val_peek(0).obj).getLinea(), "Falta ','");}
break;
case 40:
//#line 79 "gramatica.y"
{print("Sintaxis: Sentencia IF-ELSE");}
break;
case 41:
//#line 80 "gramatica.y"
{print("Sintaxis: Sentencia IF");}
break;
case 42:
//#line 81 "gramatica.y"
{yyerror(((Token) val_peek(5).obj).getLinea(), "'ELSE' sin 'IF'");}
break;
case 43:
//#line 82 "gramatica.y"
{yyerror(((Token) val_peek(7).obj).getLinea(), "Falta '(' en condicion de sentencia condicional");}
break;
case 44:
//#line 83 "gramatica.y"
{yyerror(((Token) val_peek(6).obj).getLinea(), "condicion faltante en sentencia condicional");}
break;
case 45:
//#line 84 "gramatica.y"
{yyerror(((Token) val_peek(7).obj).getLinea(), "Falta ')' en la sentencia condicional");}
break;
case 46:
//#line 85 "gramatica.y"
{yyerror(((Token) val_peek(4).obj).getLinea(), "Sentencia/s faltante/s luego de 'IF'");}
break;
case 47:
//#line 86 "gramatica.y"
{yyerror(((Token) val_peek(4).obj).getLinea(), "Falta 'ELSE' o sobran llaves en la declaracion de bloque");}
break;
case 48:
//#line 87 "gramatica.y"
{yyerror(((Token) val_peek(2).obj).getLinea(), "Bloque faltante luego de 'ELSE'");}
break;
case 49:
//#line 88 "gramatica.y"
{yyerror(((Token) val_peek(3).obj).getLinea(), "Falta 'ENDIF'");}
break;
case 50:
//#line 89 "gramatica.y"
{yyerror(((Token) val_peek(2).obj).getLinea(), "Falta ','");}
break;
case 51:
//#line 90 "gramatica.y"
{yyerror(((Token) val_peek(0).obj).getLinea(), "Falta ','");}
break;
case 52:
//#line 91 "gramatica.y"
{yyerror(((Token) val_peek(3).obj).getLinea(), "Falta '('");}
break;
case 53:
//#line 92 "gramatica.y"
{yyerror(((Token) val_peek(3).obj).getLinea(), "Falta condicion en la sentencia condicionicional");}
break;
case 54:
//#line 93 "gramatica.y"
{yyerror(((Token) val_peek(4).obj).getLinea(), "Falta ')'");}
break;
case 55:
//#line 94 "gramatica.y"
{yyerror(((Token) val_peek(1).obj).getLinea(), "Falta 'ENDIF' en sentencia condicionicional");}
break;
case 56:
//#line 95 "gramatica.y"
{yyerror(((Token) val_peek(1).obj).getLinea(), "Falta bloque de sentencias en sentencia condicional");}
break;
case 57:
//#line 98 "gramatica.y"
{print("Sintaxis: Sentencia While");}
break;
case 58:
//#line 99 "gramatica.y"
{yyerror(((Token) val_peek(3).obj).getLinea(), "'SENTENCIA DE CONTROL' desconocida");}
break;
case 59:
//#line 100 "gramatica.y"
{yyerror(((Token) val_peek(3).obj).getLinea(), "Falta'('");}
break;
case 60:
//#line 101 "gramatica.y"
{yyerror(((Token) val_peek(2).obj).getLinea(), "Falta ')'");}
break;
case 61:
//#line 102 "gramatica.y"
{yyerror(((Token) val_peek(2).obj).getLinea(), "Sentencia de control sin bloque de sentencias");}
break;
//#line 738 "Parser.java"
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
private void print(String string) {
	System.out.println(string);
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 * @throws IOException 
 */
public void run() throws IOException
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
