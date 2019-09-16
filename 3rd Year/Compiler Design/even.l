%{
int evens = 0;
%}

%%
[0-9]*[02468][ \t\n\r\f\v]+	{ evens++; }
[a-zA-Z]+	{}
\n		{}
.		{}
%%

int main()
{
	yylex();
	printf("%d\n", evens);
	return 0;
}
