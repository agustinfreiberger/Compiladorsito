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
    0,    1,    1,    1,    1,    4,    5,    5,    5,    5,
    5,    5,    5,    2,    2,    2,    2,    8,    8,    7,
    7,    7,    9,    9,    9,    9,    3,    3,    3,    3,
    3,   13,   13,   13,   13,   13,   14,   14,   14,   14,
   14,   14,   11,   11,   11,   11,   11,   11,   11,   11,
   11,   11,   11,   11,   11,   11,   11,   11,   11,   12,
   12,   12,   12,   12,    6,    6,    6,   15,   15,   15,
   16,   16,   10,   10,   10,   10,
};
final static short yylen[] = {                            2,
    1,    2,    2,    1,    1,    3,    3,    3,    3,    3,
    3,    3,    1,    3,    2,    9,    2,    3,    1,    1,
    1,    1,    4,    3,    4,    1,    1,    1,    1,    1,
    2,    4,    3,    3,    3,    3,    5,    4,    4,    4,
    4,    4,    9,    7,    6,    8,    8,    9,    8,    8,
    8,    9,    8,    6,    5,    5,    6,    7,    5,    5,
    5,    4,    4,    6,    3,    3,    1,    3,    3,    1,
    1,    1,    1,    1,    1,    2,
};
final static short yydefred[] = {                         0,
    0,    0,   20,   22,    0,    0,    0,    0,   21,    0,
    0,    0,    0,    4,    5,    0,   27,   28,   29,   30,
   31,    0,    0,   71,   75,   73,   74,    0,    0,   72,
    0,   70,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    2,    3,    0,   17,    0,
    0,    0,   35,    0,   34,    0,    0,    0,    0,   76,
   33,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   14,
    0,   32,    0,    0,   68,   69,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   63,   62,
   39,   40,    0,   41,    0,   38,    0,    0,   18,   61,
    0,    0,   56,    0,    0,   59,    0,    0,   55,    0,
   60,   37,    0,    0,    6,    0,    0,   57,    0,    0,
    0,    0,    0,    0,   64,    0,   45,    0,    0,    0,
   58,    0,    0,   44,    0,    0,    0,    0,    0,   47,
    0,   49,   51,    0,    0,   50,   46,    0,    0,   48,
   52,   43,    0,   24,    0,   16,   23,   25,
};
final static short yydgoto[] = {                         12,
   13,   14,   15,   88,   37,   38,   16,   50,  149,   30,
   17,   18,   19,   20,   31,   32,
};
final static short yysindex[] = {                        91,
  -11, -141,    0,    0, -185,  171,  178,  -31,    0, -243,
 -100,    0,   91,    0,    0,  -42,    0,    0,    0,    0,
    0,  -93,  -20,    0,    0,    0,    0,  201,   39,    0,
  -27,    0,   -4,   57,   -4,  186,  -24,  162,  -93,    6,
    8,  -19,  -71,   17,   25,    0,    0,  -29,    0,   20,
   27,    0,    0,   78,    0, -185, -185, -185, -185,    0,
    0,  -53,  -10,  -53, -185, -185, -185, -185, -185, -185,
  -38,  -53,   35,  -28,   43,   91,   54,  -53, -140,    0,
  -53,    0,  -27,  -27,    0,    0,   91, -229,  -53, -115,
 -211,   -9,   -9,   -9,   -9,   -9,   -9, -118,    0,    0,
    0,    0,   81,    0,  -40,    0, -139,  -29,    0,    0,
  -22,  -53,    0, -206,  -53,    0, -117,  -53,    0,   83,
    0,    0,   88,   86,    0, -135,  -53,    0, -134,   93,
 -113,   96, -127, -125,    0,  108,    0,  101, -122,  110,
    0,  114, -245,    0,  121,  125,   -5,   91,  131,    0,
  132,    0,    0,  133,  134,    0,    0,  195,   61,    0,
    0,    0,  138,    0,  141,    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  187,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  130,    0,    0,    0,  -37,    0,    0,    0,    0,    0,
    0,    0,    0,  152,    0,    0,    0,   19,    0,    0,
    0,    1,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  140,  346,    0,    0,    0,    0,    0,    0,
    0,  -15,  -14,    9,   12,   13,   26,    0,    0,    0,
    0,    0,   37,    0,    0,    0,    0,  144,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   55,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  148,    0,    0,
    0,    0,    0,    0,   73,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
  -55,   -6,   -1,  395,   58,   23,    0,  117,    0,   45,
    0,    0,    0,    0,   50,   53,
};
final static int YYTABLESIZE=610;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         11,
   36,   49,   98,   13,   87,   87,   46,   87,   42,   87,
  154,   47,  103,   43,   58,  102,   64,   11,   15,   59,
  105,   75,  155,   52,   29,    7,    8,   34,   22,   79,
   90,  111,   21,   56,  158,   57,   42,  112,  113,   60,
   36,   36,   76,   76,   76,   76,   72,   76,   73,   12,
   54,   76,    9,   10,   54,  118,  119,   77,   15,   15,
  127,  128,   19,   80,   40,   78,   11,   81,   45,   87,
   33,   24,   53,   25,   26,   27,   42,   42,  101,   51,
  148,   56,   55,   57,   87,   13,  104,   92,   93,   94,
   95,   96,   97,   63,   54,   54,   71,  106,   46,   56,
   61,   57,  125,   47,   46,   83,   84,    7,    8,   47,
   85,   86,   53,   53,   23,   24,  108,   25,   26,   27,
   56,   82,   57,   28,  122,   36,  135,  136,  124,  137,
   11,   12,  138,  140,    9,   10,  141,  120,  130,  144,
  145,   46,  146,   15,  150,  151,   47,   11,   11,  131,
  132,  115,  116,  152,  142,   35,   24,  153,   44,   26,
   27,   42,   35,   24,  156,   25,   26,   27,  157,   29,
   67,  159,   67,   67,   67,  160,  161,  162,  167,   54,
   65,  168,   65,   65,   65,  166,    1,   19,   26,   67,
   67,   67,    0,   75,   75,  109,   75,   53,   75,   65,
   65,   65,  165,    0,   56,    0,   57,    0,    0,    0,
   36,   75,   75,   75,   48,    1,    2,   39,   13,    3,
    4,   69,   68,   70,    5,    6,   62,   41,    7,    8,
    9,   10,  123,    1,    2,  164,    0,    3,    4,   74,
    7,    8,    5,    6,   53,   89,    7,    8,    9,   10,
   23,   24,   67,   25,   26,   27,   36,   36,    0,   28,
   36,   36,   65,    0,   12,   36,   36,    9,   10,   36,
   36,   36,   36,   36,   15,   15,    0,    0,   15,   15,
    0,   11,    0,   15,   15,    0,    0,   15,   15,   15,
   15,   15,   42,   42,    0,    0,   42,   42,    0,    0,
    0,   42,   42,    0,    0,   42,   42,   42,   42,   42,
   54,   54,    0,    0,   54,   54,    0,    0,    0,   54,
   54,    0,    0,   54,   54,   54,   54,   54,   53,   53,
    0,    0,   53,   53,    0,    0,    0,   53,   53,    0,
    0,   53,   53,   53,   53,   53,    1,    2,    0,    0,
    3,    4,    0,    0,    0,    5,    6,    0,    0,    7,
    8,    9,   10,    1,  147,    0,    0,    3,    4,    0,
    0,    0,    5,    6,    0,    0,    7,    8,    9,   10,
    0,    0,    0,    0,    0,   67,   66,    0,   66,   66,
   66,   67,   67,   67,    0,   65,    0,    0,    0,    0,
    0,   65,   65,   65,    0,   66,   66,   66,    0,    0,
    0,    0,    0,   75,   75,   75,    0,    0,    0,    0,
    0,    0,    0,   65,   66,   67,   35,   24,    0,   25,
   26,   27,    0,   35,   24,    0,   25,   26,   27,    0,
    0,   35,   24,    0,   25,   26,   27,    0,    0,    0,
   33,  163,    0,   25,   26,   27,   33,   24,   91,   25,
   26,   27,    0,    0,    0,   99,  100,    0,   66,    0,
    0,    0,  107,    0,    0,  110,    0,    0,    0,    0,
    0,    0,    0,  114,  117,    0,    0,    0,    0,    0,
    0,    0,  121,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  126,    0,    0,  129,
    0,  133,  134,    0,    0,    0,    0,    0,    0,    0,
    0,  139,    0,    0,    0,  143,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   66,    0,    0,    0,    0,    0,   66,   66,   66,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
    0,   44,   41,   41,  123,  123,   13,  123,   40,  123,
  256,   13,   41,  257,   42,   44,   41,   40,    0,   47,
   76,   41,  268,   44,    2,   41,   41,    5,   40,   59,
   41,   87,   44,   43,   40,   45,    0,  267,  268,   44,
   40,   41,   42,   43,   44,   45,   41,   47,   41,   41,
   28,  123,   41,   41,    0,  267,  268,   41,   40,   41,
  267,  268,   44,   44,    7,   41,   41,   41,   11,  123,
  256,  257,    0,  259,  260,  261,   40,   41,   44,   22,
  136,   43,   44,   45,  123,  123,   44,   65,   66,   67,
   68,   69,   70,   36,   40,   41,   39,   44,  105,   43,
   44,   45,  125,  105,  111,   56,   57,  123,  123,  111,
   58,   59,   40,   41,  256,  257,  257,  259,  260,  261,
   43,   44,   45,  265,   44,  125,   44,   40,  268,   44,
   40,  123,  268,  268,  123,  123,   44,  256,  256,   44,
  268,  148,  268,  125,   44,  268,  148,   40,  123,  267,
  268,  267,  268,   44,  268,  256,  257,   44,  259,  260,
  261,  125,  256,  257,   44,  259,  260,  261,   44,  147,
   41,   41,   43,   44,   45,   44,   44,   44,   41,  125,
   41,   41,   43,   44,   45,  125,    0,   44,   41,   60,
   61,   62,   -1,   42,   43,   79,   45,  125,   47,   60,
   61,   62,  158,   -1,   43,   -1,   45,   -1,   -1,   -1,
   40,   60,   61,   62,  257,  256,  257,   40,  256,  260,
  261,   60,   61,   62,  265,  266,   41,  259,  269,  270,
  271,  272,  273,  256,  257,   41,   -1,  260,  261,  259,
  256,  256,  265,  266,   44,  256,  269,  270,  271,  272,
  256,  257,  123,  259,  260,  261,  256,  257,   -1,  265,
  260,  261,  123,   -1,  256,  265,  266,  256,  256,  269,
  270,  271,  272,  273,  256,  257,   -1,   -1,  260,  261,
   -1,  256,   -1,  265,  266,   -1,   -1,  269,  270,  271,
  272,  273,  256,  257,   -1,   -1,  260,  261,   -1,   -1,
   -1,  265,  266,   -1,   -1,  269,  270,  271,  272,  273,
  256,  257,   -1,   -1,  260,  261,   -1,   -1,   -1,  265,
  266,   -1,   -1,  269,  270,  271,  272,  273,  256,  257,
   -1,   -1,  260,  261,   -1,   -1,   -1,  265,  266,   -1,
   -1,  269,  270,  271,  272,  273,  256,  257,   -1,   -1,
  260,  261,   -1,   -1,   -1,  265,  266,   -1,   -1,  269,
  270,  271,  272,  256,  257,   -1,   -1,  260,  261,   -1,
   -1,   -1,  265,  266,   -1,   -1,  269,  270,  271,  272,
   -1,   -1,   -1,   -1,   -1,  256,   41,   -1,   43,   44,
   45,  262,  263,  264,   -1,  256,   -1,   -1,   -1,   -1,
   -1,  262,  263,  264,   -1,   60,   61,   62,   -1,   -1,
   -1,   -1,   -1,  262,  263,  264,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  262,  263,  264,  256,  257,   -1,  259,
  260,  261,   -1,  256,  257,   -1,  259,  260,  261,   -1,
   -1,  256,  257,   -1,  259,  260,  261,   -1,   -1,   -1,
  256,  257,   -1,  259,  260,  261,  256,  257,   64,  259,
  260,  261,   -1,   -1,   -1,   71,   72,   -1,  123,   -1,
   -1,   -1,   78,   -1,   -1,   81,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   89,   90,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   98,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  112,   -1,   -1,  115,
   -1,  117,  118,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  127,   -1,   -1,   -1,  131,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  256,   -1,   -1,   -1,   -1,   -1,  262,  263,  264,
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
"bloque_de_sentencias : '{' sentencias '}'",
"condicion : exp MENOR_IGUAL exp",
"condicion : exp MAYOR_IGUAL exp",
"condicion : exp '=' exp",
"condicion : exp '<' exp",
"condicion : exp '>' exp",
"condicion : exp DIST exp",
"condicion : error",
"declarativa : tipo lista_de_variables ','",
"declarativa : tipo ID",
"declarativa : FUN ID '{' sentencias RETURN '(' retorno ')' '}'",
"declarativa : tipo ','",
"lista_de_variables : ID ';' lista_de_variables",
"lista_de_variables : ID",
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
"ejecutable : error ','",
"asig : ID ASIGN exp ','",
"asig : ASIGN exp ','",
"asig : ID exp ','",
"asig : ID ASIGN ','",
"asig : ID error ','",
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
"constante : CADENA",
"constante : error ','",
};

//#line 128 "gramatica.y"

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
//#line 493 "Parser.java"
//###############################################################
//method: yylexdebug : check lexer state
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
//method: yyparse : parse input and execute indicated items
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
      yychar = t.getToken(); //get next token
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
case 7:
//#line 30 "gramatica.y"
{yyval = new ParserVal(menorIgual(val_peek(2).ival,val_peek(1).ival));}
break;
case 8:
//#line 31 "gramatica.y"
{yyval = new ParserVal(mayorIgual(val_peek(2).ival,val_peek(1).ival));}
break;
case 9:
//#line 32 "gramatica.y"
{yyval = new ParserVal(igual(val_peek(2).ival,val_peek(1).ival));}
break;
case 10:
//#line 33 "gramatica.y"
{yyval = new ParserVal( mayor(val_peek(2).ival,val_peek(1).ival));}
break;
case 11:
//#line 34 "gramatica.y"
{yyval = new ParserVal(menor(val_peek(2).ival,val_peek(1).ival));}
break;
case 12:
//#line 35 "gramatica.y"
{yyval = new ParserVal(distinto(val_peek(2).ival,val_peek(1).ival));}
break;
case 13:
//#line 36 "gramatica.y"
{print("condicion mal escrita");}
break;
case 14:
//#line 39 "gramatica.y"
{print("Sintaxis: Declaracion multiple");}
break;
case 16:
//#line 41 "gramatica.y"
{print("Sintaxis: Closure");}
break;
case 17:
//#line 42 "gramatica.y"
{yyerror(t.getLinea(), "Faltan nombres de variables");}
break;
case 31:
//#line 64 "gramatica.y"
{print("Error");}
break;
case 32:
//#line 67 "gramatica.y"
{print("Sintaxis: Asignacion");}
break;
case 33:
//#line 68 "gramatica.y"
{yyerror(t.getLinea(), "Asignacion sin id del lado izq");}
break;
case 34:
//#line 69 "gramatica.y"
{yyerror(t.getLinea(), "Asignacion sin :=");}
break;
case 35:
//#line 70 "gramatica.y"
{yyerror(t.getLinea(), "Falta expresion del lado derecho del :=");}
break;
case 37:
//#line 74 "gramatica.y"
{print("Sintaxis: Sentencia print");}
break;
case 38:
//#line 75 "gramatica.y"
{yyerror(t.getLinea(), "'PRINT' faltante en la sentencia de impresion");}
break;
case 39:
//#line 76 "gramatica.y"
{yyerror(t.getLinea(), "Falta '('");}
break;
case 40:
//#line 77 "gramatica.y"
{yyerror(t.getLinea(), "Falta ')'");}
break;
case 41:
//#line 78 "gramatica.y"
{print("Sintaxis: Sentencia print");}
break;
case 42:
//#line 79 "gramatica.y"
{yyerror(t.getLinea(), "Falta ','");}
break;
case 43:
//#line 82 "gramatica.y"
{print("Sintaxis: Sentencia IF-ELSE");}
break;
case 44:
//#line 83 "gramatica.y"
{print("Sintaxis: Sentencia IF");}
break;
case 45:
//#line 84 "gramatica.y"
{yyerror(t.getLinea(), "'ELSE' sin 'IF'");}
break;
case 46:
//#line 85 "gramatica.y"
{yyerror(t.getLinea(), "Falta '(' en condicion de sentencia condicional");}
break;
case 47:
//#line 86 "gramatica.y"
{yyerror(t.getLinea(), "condicion faltante en sentencia condicional");}
break;
case 48:
//#line 87 "gramatica.y"
{yyerror(t.getLinea(), "Falta ')' en la sentencia condicional");}
break;
case 49:
//#line 88 "gramatica.y"
{yyerror(t.getLinea(), "Sentencia/s faltante/s luego de 'IF'");}
break;
case 50:
//#line 89 "gramatica.y"
{yyerror(t.getLinea(), "Falta 'ELSE' o sobran llaves en la declaracion de bloque");}
break;
case 51:
//#line 90 "gramatica.y"
{yyerror(t.getLinea(), "Bloque faltante luego de 'ELSE'");}
break;
case 52:
//#line 91 "gramatica.y"
{yyerror(t.getLinea(), "Falta 'ENDIF'");}
break;
case 53:
//#line 92 "gramatica.y"
{yyerror(t.getLinea(), "Falta ','");}
break;
case 54:
//#line 93 "gramatica.y"
{yyerror(t.getLinea(), "Falta ','");}
break;
case 55:
//#line 94 "gramatica.y"
{yyerror(t.getLinea(), "Falta '('");}
break;
case 56:
//#line 95 "gramatica.y"
{yyerror(t.getLinea(), "Falta condicion en la sentencia condicional");}
break;
case 57:
//#line 96 "gramatica.y"
{yyerror(t.getLinea(), "Falta ')'");}
break;
case 58:
//#line 97 "gramatica.y"
{yyerror(t.getLinea(), "Falta 'ENDIF' en sentencia condicional");}
break;
case 59:
//#line 98 "gramatica.y"
{yyerror(t.getLinea(), "Falta bloque de sentencias en sentencia condicional");}
break;
case 60:
//#line 101 "gramatica.y"
{print("Sintaxis: Sentencia While");}
break;
case 61:
//#line 102 "gramatica.y"
{yyerror(t.getLinea(), "'SENTENCIA DE CONTROL' desconocida");}
break;
case 62:
//#line 103 "gramatica.y"
{yyerror(t.getLinea(), "Falta'('");}
break;
case 63:
//#line 104 "gramatica.y"
{yyerror(t.getLinea(), "Falta ')'");}
break;
case 64:
//#line 105 "gramatica.y"
{yyerror(t.getLinea(), "Sentencia de control sin bloque de sentencias");}
break;
case 76:
//#line 125 "gramatica.y"
{print("error");}
break;
//#line 822 "Parser.java"
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