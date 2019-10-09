%{

%}

NUMBER  [0-9]{1,6}[ \t\n]+
NEWCOUNTY	C|CE|CN|CW|D|DL|G|KE|KK|KY|L|LD|LH|LM|LS|MH|MN|MO|OY|RN|SO|T|W|WH|WX|WW
COUNTY		C|CE|CN|CW|D|DL|G|KE|KK|KY|L|LK|LD|LH|LM|LS|MH|MN|MO|OY|RN|SO|TN|TS|W|WD|WH|WX|WW
YEAR  [0-9]{2}
NEWYEAR [0-9]{2}[12]

%%
{YEAR}-{COUNTY}-{NUMBER} 	{
					char y[2];
					strncpy(y, yytext, 2);
					int yInt = atoi(y);
					if(yInt < 13) yInt = yInt + 2000;
					else yInt = yInt + 1900;

					printf("%d\n", 2019-yInt);
				}
{NEWYEAR}-{COUNTY}-{NUMBER}	{
					char y[2];
					strncpy(y, yytext, 2);
					int yInt = atoi(y);
					
					printf("%d\n", 19-yInt);
				}

.+[ \t\n]			{ printf("INVALID\n"); }
\n				{}
%%

int main()
{
	yylex();
	return 0;
}
