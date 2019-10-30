%{
# include "roman.h"

void yyerror(char *s);
int yylex();

%}

%%
"I" { return I; }
"V" { return V; }
"X" { return X; }
"L" { return L; }
"C" { return C; }
"D" { return D; }
"M" { return M; }
"Z" { return Z; }
\n  { return EOL; }
.	{ yyerror("Mystery character\n"); }
%%