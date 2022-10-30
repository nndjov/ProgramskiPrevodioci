	// Korisnicki kod
	import java_cup.runtime.*;

%%
	//jflex direktive

%cup

%eofval{
	return new Symbol(sym.EOF);  // izvrsava se kada se dodje do kraja ulaznog fajla
%eofval}

	// Sabloni	
	slovo          = [A-Za-z]
	broj           = [0-9]
	alfanumeric    = {slovo}|{broj}
	crta           = [_]
	identifier     = {slovo}({alfanumeric}|{crta})*

%%
	// Leksicka pravila

	" "  {}
	\b   {}
	\t   {}
	\r\n {}
	\f   {}
	"+"     { return new Symbol(sym.ADD); }
	"-"     { return new Symbol(sym.SUB); }
	"/"     { return new Symbol(sym.DIV); }
	"*"     { return new Symbol(sym.MUL); }
	"("     { return new Symbol(sym.LPAREN); }
	")"     { return new Symbol(sym.RPAREN); }
	";"     { return new Symbol(sym.SEMI); }
	":="    { return new Symbol(sym.ASSIG); }
	<<EOF>> { return new Symbol(sym.EOF); }
	[0-9]+  { return new Symbol(sym.NUMBER, new Integer(yytext()) ); }
	{identifier}    { return new Symbol(sym.IDENT, yytext()); }
	. {}