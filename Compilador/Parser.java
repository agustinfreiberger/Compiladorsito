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
package Compilador;

import java.io.IOException;
import java.util.ArrayList;

//#line 19 "Parser.java"




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
    5,    5,    5,    5,    2,    2,    2,    2,    8,    7,
    7,    7,    9,    9,    9,    9,    3,    3,    3,    3,
   13,   13,   13,   13,   13,   14,   14,   14,   14,   14,
   11,   11,   11,   11,   11,   11,   11,   11,   11,   11,
   11,   11,   11,   11,   11,   12,   12,   12,   12,   12,
    6,    6,    6,   15,   15,   15,   16,   16,   10,   10,
   10,
};
final static short yylen[] = {                            2,
    1,    3,    3,    2,    2,    2,    3,    3,    3,    3,
    3,    3,    3,    1,    2,    2,    9,    1,    3,    1,
    1,    1,    4,    3,    4,    1,    1,    1,    1,    1,
    3,    2,    2,    2,    2,    4,    3,    3,    3,    3,
    8,    6,    5,    7,    7,    8,    7,    7,    7,    8,
    5,    5,    6,    6,    5,    5,    5,    4,    4,    5,
    3,    3,    1,    3,    3,    1,    1,    1,    1,    1,
    1,
};
final static short yydefred[] = {                         0,
    0,    0,   20,   22,    0,    0,    0,    0,   21,    0,
    0,    0,    0,    0,    0,    0,   27,   28,   29,   30,
    6,    0,   35,   67,   71,   69,   70,    0,    0,   68,
    0,   66,    0,   14,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    4,    5,    0,
   15,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   38,    0,
   40,    0,   37,    0,    2,    3,    0,    0,    0,    0,
   64,   65,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   59,   58,   36,    0,    0,    0,
   19,   57,    0,    0,   52,    0,    0,   55,    0,    0,
   51,   60,   56,    0,   43,    7,    0,    0,   53,    0,
   54,    0,   42,    0,    0,    0,   45,    0,   47,   49,
    0,   48,   44,    0,    0,    0,   46,   50,   41,    0,
    0,    0,   24,    0,   17,   23,   25,
};
final static short yydgoto[] = {                         12,
   13,   14,   15,   84,   36,   37,   16,   51,  136,   30,
   17,   18,   19,   20,   31,   32,
};
final static short yysindex[] = {                        -5,
  -23, -184,    0,    0, -216,   75,   95,  -37,    0, -243,
 -223,    0,   12,  -25,  -20, -229,    0,    0,    0,    0,
    0, -202,    0,    0,    0,    0,    0, -216,    6,    0,
  -27,    0,    6,    0,  101,   -2,   86, -202,    5,    9,
  -36,  -61,   33,   39,   45,   48,   55,    0,    0,   30,
    0,   49,    6, -216, -216, -216, -216,  -18,  -28,  -18,
 -216, -216, -216, -216, -216, -216,  -39,  -18,    0,   65,
    0,   -5,    0,  -18,    0,    0, -141,  -18,  -27,  -27,
    0,    0,   -5, -236,  -18, -117, -207,    6,    6,    6,
    6,    6,    6, -122,    0,    0,    0,  -40, -146,   30,
    0,    0,  -22,  -18,    0, -189,  -18,    0, -115,  -18,
    0,    0,    0,   93,    0,    0, -125,  -18,    0, -124,
    0, -113,    0, -123, -114,   29,    0, -111,    0,    0,
 -245,    0,    0,   46,   12,  117,    0,    0,    0,  108,
   35,  118,    0,  121,    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  163,    0,    0,  120,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  122,  123,    0,
   52,    0,  124,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   78,    0,    0,    0,    0,    0,    0,  126,
    0,    0,  127,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  128,
    0,    0,    0,    0,    0,    0,    0,    0,   57,   66,
    0,    0,    0,    0,    0,    0,    0,  -32,  -29,  -19,
  -15,    1,    7,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  132,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
  -56,   34,   58,  303,   18,    2,    0,   88,    0,   36,
    0,    0,    0,    0,   28,   31,
};
final static int YYTABLESIZE=425;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         11,
   83,   94,   41,   29,   71,   83,   33,   83,    8,   83,
  138,    9,   86,   42,   56,   98,   22,   11,   48,   57,
   21,   13,  139,   49,   39,   10,  103,   50,   44,   53,
  104,  105,   34,   24,   11,   43,   26,   27,   60,   52,
   24,   11,   25,   26,   27,   68,   46,   12,   54,   69,
   55,   11,   59,   34,   24,   67,   25,   26,   27,  110,
  111,   72,   88,   89,   90,   91,   92,   93,   11,  135,
   47,   23,   24,   73,   25,   26,   27,  118,  119,   74,
   28,   79,   80,   83,   22,  140,   81,   82,   77,   78,
    8,   75,   63,    9,   63,   63,   63,   61,   76,   61,
   61,   61,  116,   13,   83,   97,   62,   10,   62,   62,
   62,   63,   63,   63,   35,  100,   61,   61,   61,   71,
   71,  115,   71,   11,   71,   62,   62,   62,   54,   12,
   55,   46,  126,  112,   38,   29,   46,   71,   71,   71,
  121,   58,  127,  129,  132,   65,   64,   66,  143,  107,
  108,  122,  123,  133,  130,   47,  137,  141,  146,  145,
   47,  147,    1,   18,  101,   34,   33,   32,   46,   16,
   31,   39,   26,    0,   63,  144,    0,    0,    0,   61,
    0,    0,    0,    0,    0,    0,    0,    0,   62,    0,
    0,    0,   47,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   45,    2,    0,    0,    3,
    4,   40,   70,    8,    5,    6,    9,   85,    7,    8,
    9,   10,  114,   45,    2,    0,   13,    3,    4,    0,
   10,    0,    5,    6,    0,    0,    7,    8,    9,   10,
    1,    2,    0,    0,    3,    4,   11,    0,    0,    5,
    6,    0,   12,    7,    8,    9,   10,   45,    2,    0,
    0,    3,    4,    0,    0,    0,    5,    6,    0,    0,
    7,    8,    9,   10,    1,  134,    0,    0,    3,    4,
    0,    0,    0,    5,    6,    0,    0,    7,    8,    9,
   10,   23,   24,    0,   25,   26,   27,   63,    0,    0,
   28,    0,   61,   63,   63,   63,    0,    0,   61,   61,
   61,   62,    0,    0,    0,    0,    0,   62,   62,   62,
   34,   24,    0,   25,   26,   27,    0,    0,    0,   71,
   71,   71,    0,    0,    0,    0,    0,   61,   62,   63,
   34,   24,    0,   25,   26,   27,   34,   24,    0,   25,
   26,   27,   87,    0,  142,    0,   25,   26,   27,   95,
   96,    0,    0,    0,    0,    0,   99,    0,    0,    0,
  102,    0,    0,    0,    0,    0,    0,  106,  109,    0,
    0,    0,    0,    0,    0,    0,  113,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  117,    0,    0,  120,
    0,  124,  125,    0,    0,    0,    0,    0,    0,    0,
  128,    0,    0,    0,  131,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
  123,   41,   40,    2,   41,  123,    5,  123,   41,  123,
  256,   41,   41,  257,   42,   72,   40,   40,   44,   47,
   44,   41,  268,   44,    7,   41,   83,  257,   11,   28,
  267,  268,  256,  257,   40,  259,  260,  261,   41,   22,
  257,   41,  259,  260,  261,   41,   13,   41,   43,   41,
   45,   40,   35,  256,  257,   38,  259,  260,  261,  267,
  268,  123,   61,   62,   63,   64,   65,   66,   40,  126,
   13,  256,  257,   41,  259,  260,  261,  267,  268,   41,
  265,   54,   55,  123,   40,   40,   56,   57,   59,   41,
  123,   44,   41,  123,   43,   44,   45,   41,   44,   43,
   44,   45,  125,  123,  123,   41,   41,  123,   43,   44,
   45,   60,   61,   62,   40,  257,   60,   61,   62,   42,
   43,  268,   45,  123,   47,   60,   61,   62,   43,  123,
   45,   98,   40,  256,   40,  134,  103,   60,   61,   62,
  256,   41,  268,  268,  268,   60,   61,   62,   41,  267,
  268,  267,  268,  268,  268,   98,  268,   41,   41,  125,
  103,   41,    0,   44,   77,   44,   44,   44,  135,   44,
   44,   44,   41,   -1,  123,  140,   -1,   -1,   -1,  123,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  123,   -1,
   -1,   -1,  135,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  256,  257,   -1,   -1,  260,
  261,  259,  259,  256,  265,  266,  256,  256,  269,  270,
  271,  272,  273,  256,  257,   -1,  256,  260,  261,   -1,
  256,   -1,  265,  266,   -1,   -1,  269,  270,  271,  272,
  256,  257,   -1,   -1,  260,  261,  256,   -1,   -1,  265,
  266,   -1,  256,  269,  270,  271,  272,  256,  257,   -1,
   -1,  260,  261,   -1,   -1,   -1,  265,  266,   -1,   -1,
  269,  270,  271,  272,  256,  257,   -1,   -1,  260,  261,
   -1,   -1,   -1,  265,  266,   -1,   -1,  269,  270,  271,
  272,  256,  257,   -1,  259,  260,  261,  256,   -1,   -1,
  265,   -1,  256,  262,  263,  264,   -1,   -1,  262,  263,
  264,  256,   -1,   -1,   -1,   -1,   -1,  262,  263,  264,
  256,  257,   -1,  259,  260,  261,   -1,   -1,   -1,  262,
  263,  264,   -1,   -1,   -1,   -1,   -1,  262,  263,  264,
  256,  257,   -1,  259,  260,  261,  256,  257,   -1,  259,
  260,  261,   60,   -1,  257,   -1,  259,  260,  261,   67,
   68,   -1,   -1,   -1,   -1,   -1,   74,   -1,   -1,   -1,
   78,   -1,   -1,   -1,   -1,   -1,   -1,   85,   86,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   94,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  104,   -1,   -1,  107,
   -1,  109,  110,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  118,   -1,   -1,   -1,  122,
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
"sentencias : sentencias declarativa ','",
"sentencias : sentencias ejecutable ','",
"sentencias : declarativa ','",
"sentencias : ejecutable ','",
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
"declarativa : tipo",
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
"asig : ID ASIGN exp",
"asig : ASIGN exp",
"asig : ID exp",
"asig : ID ASIGN",
"asig : ID error",
"print : PRINT '(' CADENA ')'",
"print : '(' CADENA ')'",
"print : PRINT CADENA ')'",
"print : PRINT '(' CADENA",
"print : PRINT '(' ')'",
"ejecutable_if : IF '(' condicion ')' bloque_de_sentencias ELSE bloque_de_sentencias ENDIF",
"ejecutable_if : IF '(' condicion ')' bloque_de_sentencias ENDIF",
"ejecutable_if : '(' condicion ')' bloque_de_sentencias ENDIF",
"ejecutable_if : IF condicion ')' bloque_de_sentencias ELSE bloque_de_sentencias ENDIF",
"ejecutable_if : IF '(' ')' bloque_de_sentencias ELSE bloque_de_sentencias ENDIF",
"ejecutable_if : IF '(' condicion error bloque_de_sentencias ELSE bloque_de_sentencias ENDIF",
"ejecutable_if : IF '(' condicion ')' ELSE bloque_de_sentencias ENDIF",
"ejecutable_if : IF '(' condicion ')' bloque_de_sentencias bloque_de_sentencias ENDIF",
"ejecutable_if : IF '(' condicion ')' bloque_de_sentencias ELSE ENDIF",
"ejecutable_if : IF '(' condicion ')' bloque_de_sentencias ELSE bloque_de_sentencias error",
"ejecutable_if : IF condicion ')' bloque_de_sentencias ENDIF",
"ejecutable_if : IF '(' ')' bloque_de_sentencias ENDIF",
"ejecutable_if : IF '(' condicion error bloque_de_sentencias ENDIF",
"ejecutable_if : IF '(' condicion ')' bloque_de_sentencias error",
"ejecutable_if : IF '(' condicion ')' ENDIF",
"ejecutable_while : WHILE '(' condicion ')' bloque_de_sentencias",
"ejecutable_while : error '(' condicion ')' bloque_de_sentencias",
"ejecutable_while : WHILE condicion ')' bloque_de_sentencias",
"ejecutable_while : WHILE '(' condicion bloque_de_sentencias",
"ejecutable_while : WHILE '(' condicion ')' error",
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
"constante : CADENA",
};

//#line 123 "gramatica.y"

Matrix m = new Matrix();
Analizador a = new Analizador(m);
ArrayList<String> errores;
Token t;

public Parser (Analizador a) {
	this.a = a;
	this.errores = new ArrayList<>();
}

public Token yylex() throws IOException{
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

public void yyerror(int l, String s){
    this.errores.add("Sintax Error: Line " + l + " - " + s);

}
public void yyerror(String e){
	//System.out.print(e);
}
//#line 446 "Parser.java"
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
    	t = yylex();
        yychar = t.getToken();  //get next token
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
{print("Reconoce bien el lenguaje");}
break;
case 6:
//#line 23 "gramatica.y"
{yyerror(t.getLinea(), "Error ");}
break;
case 8:
//#line 31 "gramatica.y"
{yyval = new ParserVal (menorIgual(val_peek(2).ival,val_peek(1).ival));}
break;
case 9:
//#line 32 "gramatica.y"
{yyval = new ParserVal (mayorIgual(val_peek(2).ival,val_peek(1).ival));}
break;
case 10:
//#line 33 "gramatica.y"
{yyval = new ParserVal (igual(val_peek(2).ival,val_peek(1).ival));}
break;
case 11:
//#line 34 "gramatica.y"
{yyval = new ParserVal (mayor(val_peek(2).ival,val_peek(1).ival));}
break;
case 12:
//#line 35 "gramatica.y"
{yyval = new ParserVal (menor(val_peek(2).ival,val_peek(1).ival));}
break;
case 13:
//#line 36 "gramatica.y"
{yyval = new ParserVal (distinto(val_peek(2).ival,val_peek(1).ival));}
break;
case 14:
//#line 37 "gramatica.y"
{print("Condicion mal escrita");}
break;
case 15:
//#line 40 "gramatica.y"
{print("Sintaxis: Declaracion multiple");}
break;
case 17:
//#line 42 "gramatica.y"
{print("Sintaxis: Closure");}
break;
case 18:
//#line 43 "gramatica.y"
{yyerror(t.getLinea(), "Faltan nombres de variables");}
break;
case 31:
//#line 66 "gramatica.y"
{print("Sintaxis: Asignacion");}
break;
case 32:
//#line 67 "gramatica.y"
{yyerror(t.getLinea(), "Asignacion sin id del lado izq");}
break;
case 33:
//#line 68 "gramatica.y"
{yyerror(t.getLinea(), "Asignacion sin :=");}
break;
case 34:
//#line 69 "gramatica.y"
{yyerror(t.getLinea(), "Falta expresion del lado derecho del :=");}
break;
case 35:
//#line 70 "gramatica.y"
{yyerror(t.getLinea(), "Error en la asignación");}
break;
case 36:
//#line 73 "gramatica.y"
{print("Sintaxis: Sentencia print");}
break;
case 37:
//#line 74 "gramatica.y"
{yyerror(t.getLinea(), "'PRINT' faltante en la sentencia de impresion");}
break;
case 38:
//#line 75 "gramatica.y"
{yyerror(t.getLinea(), "Falta '('");}
break;
case 39:
//#line 76 "gramatica.y"
{yyerror(t.getLinea(), "Falta ')'");}
break;
case 40:
//#line 77 "gramatica.y"
{print("Sintaxis: Sentencia print");}
break;
case 41:
//#line 80 "gramatica.y"
{print("Sintaxis: Sentencia IF-ELSE");}
break;
case 42:
//#line 81 "gramatica.y"
{print("Sintaxis: Sentencia IF");}
break;
case 43:
//#line 82 "gramatica.y"
{yyerror(t.getLinea(), "'ELSE' sin 'IF'");}
break;
case 44:
//#line 83 "gramatica.y"
{yyerror(t.getLinea(), "Falta '(' en condicion de sentencia condicional");}
break;
case 45:
//#line 84 "gramatica.y"
{yyerror(t.getLinea(), "condicion faltante en sentencia condicional");}
break;
case 46:
//#line 85 "gramatica.y"
{yyerror(t.getLinea(), "Falta ')' en la sentencia condicional");}
break;
case 47:
//#line 86 "gramatica.y"
{yyerror(t.getLinea(), "Sentencia/s faltante/s luego de 'IF'");}
break;
case 48:
//#line 87 "gramatica.y"
{yyerror(t.getLinea(), "Falta 'ELSE' o sobran llaves en la declaracion de bloque");}
break;
case 49:
//#line 88 "gramatica.y"
{yyerror(t.getLinea(), "Bloque faltante luego de 'ELSE'");}
break;
case 50:
//#line 89 "gramatica.y"
{yyerror(t.getLinea(), "Falta 'ENDIF'");}
break;
case 51:
//#line 90 "gramatica.y"
{yyerror(t.getLinea(), "Falta '('");}
break;
case 52:
//#line 91 "gramatica.y"
{yyerror(t.getLinea(), "Falta condicion en la sentencia condicional");}
break;
case 53:
//#line 92 "gramatica.y"
{yyerror(t.getLinea(), "Falta ')'");}
break;
case 54:
//#line 93 "gramatica.y"
{yyerror(t.getLinea(), "Falta 'ENDIF' en sentencia condicional");}
break;
case 55:
//#line 94 "gramatica.y"
{yyerror(t.getLinea(), "Falta bloque de sentencias en sentencia condicional");}
break;
case 56:
//#line 97 "gramatica.y"
{print("Sintaxis: Sentencia While");}
break;
case 57:
//#line 98 "gramatica.y"
{yyerror(t.getLinea(), "'SENTENCIA DE CONTROL' desconocida");}
break;
case 58:
//#line 99 "gramatica.y"
{yyerror(t.getLinea(), "Falta'('");}
break;
case 59:
//#line 100 "gramatica.y"
{yyerror(t.getLinea(), "Falta ')'");}
break;
case 60:
//#line 101 "gramatica.y"
{yyerror(t.getLinea(), "Sentencia de control sin bloque de sentencias");}
break;
//#line 763 "Parser.java"
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
        yychar = t.getToken();        //get next character
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