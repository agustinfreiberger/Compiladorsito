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
    0,    1,    1,    1,    1,    1,    1,    1,    5,    6,
    6,    6,    6,    6,    6,    6,    2,    2,    2,    2,
    9,    9,    8,    8,    8,   10,   10,   10,   10,    3,
    3,    3,    3,    3,    4,    4,    4,    4,    4,   14,
   14,   14,   14,   14,   14,   12,   12,   12,   12,   12,
   12,   12,   12,   12,   12,   12,   12,   12,   12,   12,
   12,   12,   13,   13,   13,   13,   13,    7,    7,    7,
   15,   15,   15,   16,   16,   11,   11,   11,   11,
};
final static short yylen[] = {                            2,
    1,    2,    2,    2,    1,    1,    1,    2,    3,    3,
    3,    3,    3,    3,    3,    1,    3,    2,    9,    2,
    3,    1,    1,    1,    1,    4,    3,    4,    1,    1,
    1,    1,    1,    2,    4,    3,    3,    3,    3,    5,
    4,    4,    4,    4,    4,    9,    7,    6,    8,    8,
    9,    8,    8,    8,    9,    8,    6,    5,    5,    6,
    7,    5,    5,    5,    4,    4,    6,    3,    3,    1,
    3,    3,    1,    1,    1,    1,    1,    1,    2,
};
final static short yydefred[] = {                         0,
    0,    0,   23,   25,    0,    0,    0,    0,   24,    0,
    0,    0,    0,    5,    6,    7,    0,   30,   31,   33,
    8,    0,    0,   74,   78,   76,   77,    0,    0,   75,
    0,   73,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    2,    3,    4,    0,
   20,    0,    0,    0,   38,    0,   37,    0,    0,    0,
    0,   79,   36,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   34,    0,   17,    0,   35,    0,    0,   71,   72,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   66,   65,   42,   43,    0,   44,    0,   41,    0,
    0,   21,   64,    0,    0,   59,    0,    0,   62,    0,
    0,   58,    0,   63,   40,    0,    0,    9,    0,    0,
   60,    0,    0,    0,    0,    0,    0,   67,    0,   48,
    0,    0,    0,   61,    0,    0,   47,    0,    0,    0,
    0,    0,   50,    0,   52,   54,    0,    0,   53,   49,
    0,    0,   51,   55,   46,    0,   27,    0,   19,   26,
   28,
};
final static short yydgoto[] = {                         12,
   13,   14,   15,   16,   91,   37,   38,   17,   52,  152,
   30,   18,   19,   20,   31,   32,
};
final static short yysindex[] = {                        91,
  -17, -177,    0,    0, -191,  175,  187,  -31,    0, -243,
 -138,    0,  108,    0,    0,    0,  -20,    0,    0,    0,
    0, -132,   14,    0,    0,    0,    0,  209,   46,    0,
  -14,    0,   18,   54,   18,  195,   34,  162, -132,   35,
   40,  -19,  -36,   51,   53,   -6,    0,    0,    0,   42,
    0,   58,   63,    0,    0,   89,    0, -191, -191, -191,
 -191,    0,    0,  -18,  -10,  -18, -191, -191, -191, -191,
 -191, -191,  -30,  -18,   65,    8,   71,   91,   76,  -18,
    0, -150,    0,  -18,    0,  -14,  -14,    0,    0,   91,
 -247,  -18, -121, -228,  -33,  -33,  -33,  -33,  -33,  -33,
 -115,    0,    0,    0,    0,   86,    0,  -40,    0, -131,
   42,    0,    0,  -22,  -18,    0, -217,  -18,    0, -117,
  -18,    0,   94,    0,    0,  100,   98,    0, -123,  -18,
    0, -113,  112, -116,  113, -110, -109,    0,  125,    0,
  116, -107,  119,    0,  120, -239,    0,  122,  123,   -5,
  108,  127,    0,  126,    0,    0,  128,  129,    0,    0,
  201,   44,    0,    0,    0,  130,    0,  133,    0,    0,
    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  176,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  142,    0,    0,    0,  -38,    0,    0,    0,    0,    0,
    0,    0,    0,  152,    0,    0,    0,    0,    0,   19,
    0,    0,    0,    1,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  358,  367,    0,    0,    0,
    0,    0,    0,    0,  -37,  -15,  -11,   12,   13,   26,
    0,    0,    0,    0,    0,   37,    0,    0,    0,    0,
  131,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   55,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  136,    0,    0,    0,    0,    0,    0,   73,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,
};
final static short yygindex[] = {                         0,
  -65,   -8,    2,    3,  442,   25,  435,    0,   96,    0,
   20,    0,    0,    0,   -2,   11,
};
final static int YYTABLESIZE=631;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         11,
   39,   90,   16,   10,   47,   90,   90,   90,   42,   58,
  101,   59,  108,   43,   48,   49,  157,   11,   18,  115,
  116,   77,   22,   51,  114,   11,   21,   60,  158,   15,
   93,   40,   61,   22,  161,   45,   45,   81,  121,  122,
   39,   39,   79,   79,   79,   79,   53,   79,  106,  130,
  131,  105,   12,   13,   57,   86,   87,   54,   18,   18,
   65,   62,   22,   73,   33,   24,   14,   25,   26,   27,
   88,   89,   56,  151,   66,   74,   45,   45,   23,   24,
   75,   25,   26,   27,   16,   10,   78,   28,   58,   57,
   59,   79,   90,   80,   57,   57,   58,   63,   59,   47,
   82,   83,  128,   84,   90,   47,  111,   11,  104,   48,
   49,   15,   56,   56,  107,   48,   49,   35,   24,  109,
   44,   26,   27,   35,   24,   39,   25,   26,   27,  125,
   11,   58,   85,   59,   12,   13,  127,  138,  133,  139,
  123,  140,   47,   18,  141,  118,  119,   11,   14,  134,
  135,  145,   48,   49,  143,  144,  147,  148,  149,  153,
  154,   45,  155,  156,   11,  159,  160,  162,  169,  163,
  170,  164,  165,  171,   22,    1,   29,  112,    0,   57,
  168,    0,   70,    0,   70,   70,   70,    0,    0,    0,
    0,    0,    0,   78,   78,    0,   78,   56,   78,    0,
    0,   70,   70,   70,   58,    0,   59,    0,    0,    0,
    0,   78,   78,   78,   36,   46,    2,   16,   10,    3,
    4,   71,   70,   72,    5,    6,   39,   41,    7,    8,
    9,   10,  126,   46,    2,   64,   50,    3,    4,   76,
   11,  167,    5,    6,   15,   92,    7,    8,    9,   10,
   23,   24,   55,   25,   26,   27,   39,   39,    0,   28,
   39,   39,    0,    0,   70,   39,   39,   12,   13,   39,
   39,   39,   39,   39,   18,   18,    0,    0,   18,   18,
    0,   14,    0,   18,   18,    0,    0,   18,   18,   18,
   18,   18,   45,   45,    0,    0,   45,   45,    0,    0,
    0,   45,   45,    0,    0,   45,   45,   45,   45,   45,
   57,   57,    0,    0,   57,   57,    0,    0,    0,   57,
   57,    0,    0,   57,   57,   57,   57,   57,   56,   56,
    0,    0,   56,   56,    0,    0,    0,   56,   56,    0,
    0,   56,   56,   56,   56,   56,    1,    2,    0,    0,
    3,    4,    0,    0,    0,    5,    6,    0,    0,    7,
    8,    9,   10,   46,    2,    0,    0,    3,    4,    0,
    0,    0,    5,    6,    0,    0,    7,    8,    9,   10,
    1,  150,    0,    0,    3,    4,    0,    0,    0,    5,
    6,    0,    0,    7,    8,    9,   10,   70,   68,    0,
   68,   68,   68,   70,   70,   70,    0,   69,    0,   69,
   69,   69,    0,   78,   78,   78,    0,   68,   68,   68,
    0,    0,    0,   67,   68,   69,   69,   69,   69,    0,
   35,   24,    0,   25,   26,   27,   29,    0,    0,   34,
    0,    0,   35,   24,    0,   25,   26,   27,    0,    0,
   35,   24,    0,   25,   26,   27,   33,  166,    0,   25,
   26,   27,   56,    0,   33,   24,    0,   25,   26,   27,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   68,    0,    0,    0,    0,    0,    0,    0,    0,   69,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   95,   96,   97,   98,   99,  100,   94,    0,    0,
    0,    0,    0,    0,  102,  103,    0,    0,    0,    0,
    0,  110,    0,    0,    0,  113,    0,    0,    0,    0,
    0,    0,    0,  117,  120,    0,    0,    0,    0,    0,
    0,    0,  124,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  129,    0,    0,  132,
    0,  136,  137,    0,    0,    0,    0,    0,    0,    0,
    0,  142,    0,    0,    0,  146,    0,    0,    0,    0,
    0,    0,    0,    0,   29,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   68,    0,    0,    0,    0,    0,   68,
   68,   68,   69,    0,    0,    0,    0,    0,   69,   69,
   69,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
    0,  123,   41,   41,   13,  123,  123,  123,   40,   43,
   41,   45,   78,  257,   13,   13,  256,   40,    0,  267,
  268,   41,   40,   44,   90,   41,   44,   42,  268,   41,
   41,    7,   47,   40,   40,   11,    0,   44,  267,  268,
   40,   41,   42,   43,   44,   45,   22,   47,   41,  267,
  268,   44,   41,   41,    0,   58,   59,   44,   40,   41,
   36,   44,   44,   39,  256,  257,   41,  259,  260,  261,
   60,   61,    0,  139,   41,   41,   40,   41,  256,  257,
   41,  259,  260,  261,  123,  123,  123,  265,   43,   44,
   45,   41,  123,   41,   40,   41,   43,   44,   45,  108,
   59,   44,  125,   41,  123,  114,  257,  123,   44,  108,
  108,  123,   40,   41,   44,  114,  114,  256,  257,   44,
  259,  260,  261,  256,  257,  125,  259,  260,  261,   44,
   40,   43,   44,   45,  123,  123,  268,   44,  256,   40,
  256,   44,  151,  125,  268,  267,  268,   40,  123,  267,
  268,  268,  151,  151,  268,   44,   44,  268,  268,   44,
  268,  125,   44,   44,   40,   44,   44,   41,  125,   44,
   41,   44,   44,   41,   44,    0,   41,   82,   -1,  125,
  161,   -1,   41,   -1,   43,   44,   45,   -1,   -1,   -1,
   -1,   -1,   -1,   42,   43,   -1,   45,  125,   47,   -1,
   -1,   60,   61,   62,   43,   -1,   45,   -1,   -1,   -1,
   -1,   60,   61,   62,   40,  256,  257,  256,  256,  260,
  261,   60,   61,   62,  265,  266,   40,  259,  269,  270,
  271,  272,  273,  256,  257,   41,  257,  260,  261,  259,
  256,   41,  265,  266,  256,  256,  269,  270,  271,  272,
  256,  257,   44,  259,  260,  261,  256,  257,   -1,  265,
  260,  261,   -1,   -1,  123,  265,  266,  256,  256,  269,
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
  256,  257,   -1,   -1,  260,  261,   -1,   -1,   -1,  265,
  266,   -1,   -1,  269,  270,  271,  272,  256,   41,   -1,
   43,   44,   45,  262,  263,  264,   -1,   41,   -1,   43,
   44,   45,   -1,  262,  263,  264,   -1,   60,   61,   62,
   -1,   -1,   -1,  262,  263,  264,   60,   61,   62,   -1,
  256,  257,   -1,  259,  260,  261,    2,   -1,   -1,    5,
   -1,   -1,  256,  257,   -1,  259,  260,  261,   -1,   -1,
  256,  257,   -1,  259,  260,  261,  256,  257,   -1,  259,
  260,  261,   28,   -1,  256,  257,   -1,  259,  260,  261,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  123,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  123,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   67,   68,   69,   70,   71,   72,   66,   -1,   -1,
   -1,   -1,   -1,   -1,   73,   74,   -1,   -1,   -1,   -1,
   -1,   80,   -1,   -1,   -1,   84,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   92,   93,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  101,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  115,   -1,   -1,  118,
   -1,  120,  121,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  130,   -1,   -1,   -1,  134,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  150,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  256,   -1,   -1,   -1,   -1,   -1,  262,
  263,  264,  256,   -1,   -1,   -1,   -1,   -1,  262,  263,
  264,
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
"sentencias : sentencias asig",
"sentencias : declarativa",
"sentencias : ejecutable",
"sentencias : asig",
"sentencias : error ','",
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

//#line 131 "gramatica.y"

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

public void yyerror(String e){
	//System.out.print(e);
}
//#line 505 "Parser.java"
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
//#line 16 "gramatica.y"
{print("reconoce bien el lenguaje");}
break;
case 10:
//#line 33 "gramatica.y"
{yyval = menorIgual(val_peek(2).ival,val_peek(1).ival);}
break;
case 11:
//#line 34 "gramatica.y"
{yyval = mayorIgual(val_peek(2).ival,val_peek(1).ival);}
break;
case 12:
//#line 35 "gramatica.y"
{yyval = igual(val_peek(2).ival,val_peek(1).ival);}
break;
case 13:
//#line 36 "gramatica.y"
{yyval = mayor(val_peek(2).ival,val_peek(1).ival);}
break;
case 14:
//#line 37 "gramatica.y"
{yyval = menor(val_peek(2).ival,val_peek(1).ival);}
break;
case 15:
//#line 38 "gramatica.y"
{yyval = distinto(val_peek(2).ival,val_peek(1).ival);}
break;
case 16:
//#line 39 "gramatica.y"
{print("condicion mal escrita");}
break;
case 17:
//#line 42 "gramatica.y"
{print("Sintaxis: Declaracion multiple");}
break;
case 19:
//#line 44 "gramatica.y"
{print("Sintaxis: Closure");}
break;
case 20:
//#line 45 "gramatica.y"
{yyerror(t.getLinea(), "Faltan nombres de variables");}
break;
case 34:
//#line 67 "gramatica.y"
{print("Error");}
break;
case 35:
//#line 70 "gramatica.y"
{print("Sintaxis: Asignacion");}
break;
case 36:
//#line 71 "gramatica.y"
{yyerror(t.getLinea(), "Asignacion sin id del lado izq");}
break;
case 37:
//#line 72 "gramatica.y"
{yyerror(t.getLinea(), "Asignacion sin :=");}
break;
case 38:
//#line 73 "gramatica.y"
{yyerror(t.getLinea(), "Falta expresion del lado derecho del :=");}
break;
case 39:
//#line 74 "gramatica.y"
{print("Error");}
break;
case 40:
//#line 77 "gramatica.y"
{print("Sintaxis: Sentencia print");}
break;
case 41:
//#line 78 "gramatica.y"
{yyerror(t.getLinea(), "'PRINT' faltante en la sentencia de impresion");}
break;
case 42:
//#line 79 "gramatica.y"
{yyerror(t.getLinea(), "Falta '('");}
break;
case 43:
//#line 80 "gramatica.y"
{yyerror(t.getLinea(), "Falta ')'");}
break;
case 44:
//#line 81 "gramatica.y"
{print("Sintaxis: Sentencia print");}
break;
case 45:
//#line 82 "gramatica.y"
{yyerror(t.getLinea(), "Falta ','");}
break;
case 46:
//#line 85 "gramatica.y"
{print("Sintaxis: Sentencia IF-ELSE");}
break;
case 47:
//#line 86 "gramatica.y"
{print("Sintaxis: Sentencia IF");}
break;
case 48:
//#line 87 "gramatica.y"
{yyerror(t.getLinea(), "'ELSE' sin 'IF'");}
break;
case 49:
//#line 88 "gramatica.y"
{yyerror(t.getLinea(), "Falta '(' en condicion de sentencia condicional");}
break;
case 50:
//#line 89 "gramatica.y"
{yyerror(t.getLinea(), "condicion faltante en sentencia condicional");}
break;
case 51:
//#line 90 "gramatica.y"
{yyerror(t.getLinea(), "Falta ')' en la sentencia condicional");}
break;
case 52:
//#line 91 "gramatica.y"
{yyerror(t.getLinea(), "Sentencia/s faltante/s luego de 'IF'");}
break;
case 53:
//#line 92 "gramatica.y"
{yyerror(t.getLinea(), "Falta 'ELSE' o sobran llaves en la declaracion de bloque");}
break;
case 54:
//#line 93 "gramatica.y"
{yyerror(t.getLinea(), "Bloque faltante luego de 'ELSE'");}
break;
case 55:
//#line 94 "gramatica.y"
{yyerror(t.getLinea(), "Falta 'ENDIF'");}
break;
case 56:
//#line 95 "gramatica.y"
{yyerror(t.getLinea(), "Falta ','");}
break;
case 57:
//#line 96 "gramatica.y"
{yyerror(t.getLinea(), "Falta ','");}
break;
case 58:
//#line 97 "gramatica.y"
{yyerror(t.getLinea(), "Falta '('");}
break;
case 59:
//#line 98 "gramatica.y"
{yyerror(t.getLinea(), "Falta condicion en la sentencia condicional");}
break;
case 60:
//#line 99 "gramatica.y"
{yyerror(t.getLinea(), "Falta ')'");}
break;
case 61:
//#line 100 "gramatica.y"
{yyerror(t.getLinea(), "Falta 'ENDIF' en sentencia condicional");}
break;
case 62:
//#line 101 "gramatica.y"
{yyerror(t.getLinea(), "Falta bloque de sentencias en sentencia condicional");}
break;
case 63:
//#line 104 "gramatica.y"
{print("Sintaxis: Sentencia While");}
break;
case 64:
//#line 105 "gramatica.y"
{yyerror(t.getLinea(), "'SENTENCIA DE CONTROL' desconocida");}
break;
case 65:
//#line 106 "gramatica.y"
{yyerror(t.getLinea(), "Falta'('");}
break;
case 66:
//#line 107 "gramatica.y"
{yyerror(t.getLinea(), "Falta ')'");}
break;
case 67:
//#line 108 "gramatica.y"
{yyerror(t.getLinea(), "Sentencia de control sin bloque de sentencias");}
break;
case 79:
//#line 128 "gramatica.y"
{print("error");}
break;
//#line 838 "Parser.java"
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
