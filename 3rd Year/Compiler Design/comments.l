%{
int comment = 0;
%}

%%
"**".*		{}
\".*\"		{printf("%s", yytext);}
"{"		{comment = 1;}
"}"		{if(comment == 0){printf("syntax error\n"); return 0;} comment = 0;}
.		{if(comment == 0){printf("%s", yytext);}}
\n		{if(comment == 0){printf("%s", yytext);}}
%%

int main()
{
	yylex();
	if(comment == 1) printf("syntax error\n");
	return 0;
}
