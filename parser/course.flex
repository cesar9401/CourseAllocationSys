package com.cesar31.system.parser;

import static com.cesar31.system.parser.CourseParserSym.*;
import java_cup.runtime.*;

%%

%class CourseLex
%type java_cup.runtime.Symbol
%public
%unicode
%cup
%line
%column

%{
		private StringBuffer string = new StringBuffer();
		private boolean isSc = false;
		private int len = 0;

		private Symbol symbol setString(int type, String s) {
			if(isSc && s.length() == len) {
				isSc = false;
				len = 0;
				return symbol(INTERVAL, s);
			}

			switch (s)
				case "lunes"
				case "martes"
				case "miercoles"
				case "jueves"
				case "viernes"
				case "sabado"
				case "domingo"
					return symbol(DAY, s);

				default:
					return symbol(type, s)
		}

		private Symbol symbol(int type) {
			return new Symbol(type, yyline + 1, yycolumn + 1);
		}

		private Symbol symbol(int type, Object object) {
			return new Symbol(type, yyline + 1, yycolumn + 1, object);
		}
%}

%eofval{
	return symbol(EOF);
%eofval}
%eofclose

LineTerminator = \r|\n|\r\n
WhiteSpace = {LineTerminator} | [ \t\f]

Integer = 0|[1-9][0-9]*
Hour = \d{1,2} ":" \d{2} ("am"|"pm")
Schedule = {Hour} "-" {Hour}
Input = \w+

%state STRING

%%

<YYINITIAL> {

	"Estudiante"
	{ return symbol(STUDENT); }

	"Usuario"
	{ return symbol(USER); }

	"colaborador"
	{ return symbol(COLLABORATOR); }

	"estudiante"
	{ return symbol(STUDENT_); }

	"Edificio"
	{ return symbol(BUILDING); }

	"Salon"
	{ return symbol(CLASSROOM); }

	"Catedratico"
	{ return symbol(PROFESSOR); }

	"Curso"
	{ return symbol(COURSE); }

	"Horario"
	{ return symbol(SCHEDULE); }

	"Asignar"
	{ return symbol(ASSIGNMENT); }

	{Integer}
	{ return symbol(INTEGER); }

	"("
	{ return symbol(LPAREN); }

	")"
	{ return symbol(RPAREN); }

	";"
	{ return symbol(SEMI); }

	","
	{ return symbol(COMMA); }

	{Input}
	{ return symbol(INPUT, yytext()); }

	\"
	{
		string.setLength(0);
		yybegin(STRING);
	}

	{WhiteSpace}
	{ /* Ignore */ }

}

<STRING> {

  \"
	{
		yybegin(YYINITIAL);
		return setString(STRING, string.toString());
	}

	{Schedule}
	{
		string.append(yytext());
		len = yytext().length();
		isSc = true;
	}

	[^\n\r\"\\]+
	{ string.append( yytext() ); }

	\\t
	{ string.append('\t'); }

  \\n
	{ string.append('\n'); }

  \\r
	{ string.append('\r'); }

  \\\"
	{ string.append('\"'); }

  \\
	{ string.append('\\'); }

}

[^]
{
	System.out.println("Error: < " + yytext() + " >");
	return symbol(ERROR, yytext());
}
