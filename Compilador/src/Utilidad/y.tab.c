#ifndef lint
static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";
#endif
#define YYBYACC 1
#line 2 "gramatica.y"
/*------------------------------------------------------------------
 * yacc-able Java 1 grammar
 * see notes at end
 */
#line 11 "y.tab.c"
#define ID 257
#define CTE 258
#define CADENA 259
#define LINTEGER 260
#define USINTEGER 261
#define IF 266
#define ELSE 267
#define ENDIF 268
#define WHILE 269
#define PRINT 270
#define VOID 271
#define FUN 272
#define RETURN 273
#define YYERRCODE 256
short yylhs[] = {                                        -1,
    0,    2,    2,    1,    1,    5,    6,    6,    6,    6,
    6,    6,    4,    4,    9,    9,    8,    8,    8,    8,
   10,   10,   10,   10,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    7,    7,    7,   11,
   11,   11,   12,   12,   12,
};
short yylen[] = {                                         2,
    1,    2,    2,    2,    1,    3,    3,    3,    3,    3,
    3,    3,    2,    9,    3,    1,    1,    1,    1,    1,
    4,    3,    4,    1,    8,    8,    8,    8,    6,    6,
    5,    5,    4,    1,    3,    1,    3,    3,    1,    3,
    3,    1,    1,    1,    1,
};
short yydefred[] = {                                      0,
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
short yydgoto[] = {                                      10,
   11,   12,   13,   14,   60,   37,   15,   16,   30,   83,
   17,   18,
};
short yysindex[] = {                                   -188,
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
short yyrindex[] = {                                      0,
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
short yygindex[] = {                                      0,
   -1,   -6,    0,    0,  -50,  113,   39,    0,   94,    0,
   68,   69,
};
#define YYTABLESIZE 274
short yytable[] = {                                      88,
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
short yycheck[] = {                                      40,
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
#define YYFINAL 10
#ifndef YYDEBUG
#define YYDEBUG 0
#endif
#define YYMAXTOKEN 273
#if YYDEBUG
char *yyname[] = {
"end-of-file",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,"'('","')'","'*'","'+'","','","'-'",0,"'/'",0,0,0,0,0,0,0,0,0,0,0,
"';'","'<'","'='","'>'",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"'{'",0,"'}'",0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,"ID","CTE","CADENA","LINTEGER","USINTEGER","\"<=\"",
"\">=\"","\"!=\"","\":=\"","IF","ELSE","ENDIF","WHILE","PRINT","VOID","FUN",
"RETURN",
};
char *yyrule[] = {
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
#endif
#ifndef YYSTYPE
typedef int YYSTYPE;
#endif
#define yyclearin (yychar=(-1))
#define yyerrok (yyerrflag=0)
#ifdef YYSTACKSIZE
#ifndef YYMAXDEPTH
#define YYMAXDEPTH YYSTACKSIZE
#endif
#else
#ifdef YYMAXDEPTH
#define YYSTACKSIZE YYMAXDEPTH
#else
#define YYSTACKSIZE 500
#define YYMAXDEPTH 500
#endif
#endif
int yydebug;
int yynerrs;
int yyerrflag;
int yychar;
short *yyssp;
YYSTYPE *yyvsp;
YYSTYPE yyval;
YYSTYPE yylval;
short yyss[YYSTACKSIZE];
YYSTYPE yyvs[YYSTACKSIZE];
#define yystacksize YYSTACKSIZE
#line 91 "gramatica.y"

Matrix m = new Matrix();
Analizador a = new Analizador(m);

public Parser(){

}

public int yylex(){
	int NumeroToken = 0;
	NumeroToken = a.getToken();
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

#line 267 "y.tab.c"
#define YYABORT goto yyabort
#define YYACCEPT goto yyaccept
#define YYERROR goto yyerrlab
int
yyparse()
{
    register int yym, yyn, yystate;
#if YYDEBUG
    register char *yys;
    extern char *getenv();

    if (yys = getenv("YYDEBUG"))
    {
        yyn = *yys;
        if (yyn >= '0' && yyn <= '9')
            yydebug = yyn - '0';
    }
#endif

    yynerrs = 0;
    yyerrflag = 0;
    yychar = (-1);

    yyssp = yyss;
    yyvsp = yyvs;
    *yyssp = yystate = 0;

yyloop:
    if (yyn = yydefred[yystate]) goto yyreduce;
    if (yychar < 0)
    {
        if ((yychar = yylex()) < 0) yychar = 0;
#if YYDEBUG
        if (yydebug)
        {
            yys = 0;
            if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
            if (!yys) yys = "illegal-symbol";
            printf("yydebug: state %d, reading %d (%s)\n", yystate,
                    yychar, yys);
        }
#endif
    }
    if ((yyn = yysindex[yystate]) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
    {
#if YYDEBUG
        if (yydebug)
            printf("yydebug: state %d, shifting to state %d (%s)\n",
                    yystate, yytable[yyn],yyrule[yyn]);
#endif
        if (yyssp >= yyss + yystacksize - 1)
        {
            goto yyoverflow;
        }
        *++yyssp = yystate = yytable[yyn];
        *++yyvsp = yylval;
        yychar = (-1);
        if (yyerrflag > 0)  --yyerrflag;
        goto yyloop;
    }
    if ((yyn = yyrindex[yystate]) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
    {
        yyn = yytable[yyn];
        goto yyreduce;
    }
    if (yyerrflag) goto yyinrecovery;
#ifdef lint
    goto yynewerror;
#endif
yynewerror:
    yyerror("syntax error");
#ifdef lint
    goto yyerrlab;
#endif
yyerrlab:
    ++yynerrs;
yyinrecovery:
    if (yyerrflag < 3)
    {
        yyerrflag = 3;
        for (;;)
        {
            if ((yyn = yysindex[*yyssp]) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
#if YYDEBUG
                if (yydebug)
                    printf("yydebug: state %d, error recovery shifting\
 to state %d\n", *yyssp, yytable[yyn]);
#endif
                if (yyssp >= yyss + yystacksize - 1)
                {
                    goto yyoverflow;
                }
                *++yyssp = yystate = yytable[yyn];
                *++yyvsp = yylval;
                goto yyloop;
            }
            else
            {
#if YYDEBUG
                if (yydebug)
                    printf("yydebug: error recovery discarding state %d\n",
                            *yyssp);
#endif
                if (yyssp <= yyss) goto yyabort;
                --yyssp;
                --yyvsp;
            }
        }
    }
    else
    {
        if (yychar == 0) goto yyabort;
#if YYDEBUG
        if (yydebug)
        {
            yys = 0;
            if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
            if (!yys) yys = "illegal-symbol";
            printf("yydebug: state %d, error recovery discards token %d (%s)\n",
                    yystate, yychar, yys);
        }
#endif
        yychar = (-1);
        goto yyloop;
    }
yyreduce:
#if YYDEBUG
    if (yydebug)
        printf("yydebug: state %d, reducing by rule %d (%s)\n",
                yystate, yyn, yyrule[yyn]);
#endif
    yym = yylen[yyn];
    yyval = yyvsp[1-yym];
    switch (yyn)
    {
case 1:
#line 17 "gramatica.y"
{print("reconoce bien el lenguaje");}
break;
case 7:
#line 33 "gramatica.y"
{yyval = (yyvsp[-2].ival '<=' yyvsp[-1].ival);}
break;
case 8:
#line 34 "gramatica.y"
{yyval = (yyvsp[-2].ival '>=' yyvsp[-1].ival);}
break;
case 9:
#line 35 "gramatica.y"
{yyval = (yyvsp[-2].ival '=' yyvsp[-1].ival);}
break;
case 10:
#line 36 "gramatica.y"
{yyval = (yyvsp[-2].ival '<' yyvsp[-1].ival);}
break;
case 11:
#line 37 "gramatica.y"
{yyval = (yyvsp[-2].ival '>' yyvsp[-1].ival);}
break;
case 12:
#line 38 "gramatica.y"
{yyval = (yyvsp[-2].ival '!=' yyvsp[-1].ival);}
break;
case 37:
#line 75 "gramatica.y"
{yyval = (yyvsp[-2].ival + yyvsp[-1].ival);}
break;
case 38:
#line 76 "gramatica.y"
{yyval = (yyvsp[-2].ival - yyvsp[-1].ival);}
break;
case 40:
#line 80 "gramatica.y"
{(yyval = yyvsp[-2].ival * yyvsp[-1].ival);}
break;
case 41:
#line 81 "gramatica.y"
{(yyval = yyvsp[-2].ival / yyvsp[-1].ival);}
break;
#line 451 "y.tab.c"
    }
    yyssp -= yym;
    yystate = *yyssp;
    yyvsp -= yym;
    yym = yylhs[yyn];
    if (yystate == 0 && yym == 0)
    {
#if YYDEBUG
        if (yydebug)
            printf("yydebug: after reduction, shifting from state 0 to\
 state %d\n", YYFINAL);
#endif
        yystate = YYFINAL;
        *++yyssp = YYFINAL;
        *++yyvsp = yyval;
        if (yychar < 0)
        {
            if ((yychar = yylex()) < 0) yychar = 0;
#if YYDEBUG
            if (yydebug)
            {
                yys = 0;
                if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
                if (!yys) yys = "illegal-symbol";
                printf("yydebug: state %d, reading %d (%s)\n",
                        YYFINAL, yychar, yys);
            }
#endif
        }
        if (yychar == 0) goto yyaccept;
        goto yyloop;
    }
    if ((yyn = yygindex[yym]) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn];
    else
        yystate = yydgoto[yym];
#if YYDEBUG
    if (yydebug)
        printf("yydebug: after reduction, shifting from state %d \
to state %d\n", *yyssp, yystate);
#endif
    if (yyssp >= yyss + yystacksize - 1)
    {
        goto yyoverflow;
    }
    *++yyssp = yystate;
    *++yyvsp = yyval;
    goto yyloop;
yyoverflow:
    yyerror("yacc stack overflow");
yyabort:
    return (1);
yyaccept:
    return (0);
}
