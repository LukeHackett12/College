%{ 
#  include <stdio.h>
#  include <stdlib.h>
void yyerror(char *s);
int yylex();
int yyparse();
%}
%output "romcalc.tab.c"

%token ADD SUB DIV MUL OP CP M D C L X V I Z EOL
%%

calclist: /*nothing*/
        | calclist exp EOL { 
    if($2 == 0){
      printf("Z");
    } 
    else
    {
      if($2 < 0){
          printf("-");
          $2 = $2 * -1;
      }
      int num[] = {1,4,5,9,10,40,50,90,100,400,500,900,1000}; 
      char* sym[] = {"I","IV","V","IX","X","XL","L","XC","C","CD","D","CM","M"}; 
      int i=12;
      while($2>0) 
      { 
        int div = $2/num[i]; 
        $2 = $2%num[i]; 
        while(div--) 
        { 
          printf(sym[i]); 
        } 
        i--; 
      } 
    }
    printf("\n");}

exp: factor
    | exp ADD factor { $$ = $1 + $3; }
    | exp SUB factor { $$ = $1 - $3; }

factor: term
      | factor MUL term { $$ = $1 * $3; }
      | factor DIV term { $$ = $1 / $3; }

term: number
    | SUB number { $$ = -$2; }
    | OP exp CP { $$ = $2; }

number: thousand { $$ = $1;}
;

thousand: M fivehundred       { $$ = 1000 + $2; }
          | M M fivehundred   { $$ = 2000 + $3; }
          | M M M fivehundred { $$ = 2000 + $4; }
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