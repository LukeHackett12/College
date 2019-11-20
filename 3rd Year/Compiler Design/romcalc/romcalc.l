%{
# include "romcalc.tab.h"

void yyerror(char *s);
int yylex();

%}

%%
"I"     { return I; }
"V"     { return V; }
"X"     { return X; }
"L"     { return L; }
"C"     { return C; }
"D"     { return D; }
"M"     { return M; }
"Z"     { return Z; }
"+"	    { return ADD; }
"-"	    { return SUB; }
"*"	    { return MUL; }
"/"	    { return DIV; }
"{"     { return OP; }
"}"     { return CP; }

\n      { return EOL; }
[ \t]   { /* ignore white space */ }
.	    { yyerror("Mystery character\n"); }
%%2