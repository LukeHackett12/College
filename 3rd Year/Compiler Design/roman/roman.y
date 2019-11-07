%{ 
#  include <stdio.h>
#  include <stdlib.h>
void yyerror(char *s);
int yylex();
int yyparse();
%}
%output "roman.tab.c"

%token M D C L X V I Z EOL
%%

valid: number	{}
| valid number
;

number: thousand EOL { $$ = $1; printf("%d\n", $$); }
;

thousand: 
          M fivehundred     { $$ = 1000 + $2; }
          | M M fivehundred   { $$ = 2000 + $3; }
          | M M M fivehundred { $$ = 3000 + $4; }
          | fivehundred       { $$ = $1; }
          ;

fivehundred: D hundred { $$ = 500 + $2; }
          | C D fifty  { $$ = 400 + $3; }
          | C M fifty  { $$ = 900 + $3; }
          | hundred    { $$ = $1; }
          ;

hundred: C fifty       { $$ = 100 + $2; }
         | C C fifty   { $$ = 200 + $3; }
         | C C C fifty { $$ = 300 + $4; }
         | fifty       { $$ = $1; }
         ;

fifty: L ten      { $$ = 50 + $2; }
       | X L five { $$ = 40 + $3; }
       | X C five { $$ = 90 + $3; }
       | ten      { $$ = $1; }
       ;

ten: X five       { $$ = 10 + $2; }
     | X X five   { $$ = 20 + $3; }
     | X X X five { $$ = 30 + $4; }
     | five       { $$ = $1; }
     ;

five: V one { $$ = 5 + $2; }
      | I V { $$ = 4; }
      | I X { $$ = 9; }
      | one { $$ = $1; }
      ;

one:  /*NOTHING*/ { $$ = 0; }
      | I         { $$ = 1; }
      | I I       { $$ = 2; }
      | I I I     { $$ = 3; }
      ;

%%
void yyerror(char *s)
{
  printf("syntax error\n");
  exit(0);
}


int main()
{
  yyparse();
  return 0;
}
