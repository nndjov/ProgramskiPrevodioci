// import iskaz
import java_cup.runtime.Symbol;

%%
//jflex direktive
%cup

%eofval{
  return new Symbol(sym.EOF);  // izvrsava se kada se dodje do kraja ulaznog fajla
%eofval}

broj = [0-9]+
ident = [A-Za-z][A-Za-z0-9]*

%%
// Pravila za uparivanje


"+"  { return new Symbol(sym.PLUS); }
"-"   { return new Symbol(sym.MINUS); }
"*"   { return new Symbol(sym.MUL); }
"/"   { return new Symbol(sym.DIV); }
"("   { return new Symbol(sym.LPAREN); }
")"   { return new Symbol(sym.RPAREN); }
";"    { return new Symbol(sym.SEMI); }
":="    { return new Symbol(sym.ASSIG); }
[0-9]+  { return new Symbol(sym.NUMBER, new Integer(yytext()) ); }
{ident} { return new Symbol(sym.IDENTIFAER,yytext() ); }
.      {}
