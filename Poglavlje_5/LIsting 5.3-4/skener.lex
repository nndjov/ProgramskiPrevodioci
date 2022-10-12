// import iskaz
import java_cup.runtime.Symbol;

%%
//jflex direktive
%cup

%eofval{
  return new Symbol(sym.EOF);  // izvrsava se kada se dodje do kraja ulaznog fajla
%eofval}

%%
// Pravila za uparivanje


"+"  { return new Symbol(sym.PLUS); }
"-"   { return new Symbol(sym.MINUS); }
"*"   { return new Symbol(sym.MUL); }
"("   { return new Symbol(sym.LPAREN); }
")"   { return new Symbol(sym.RPAREN); }
";"    { return new Symbol(sym.SEMI); }
[0-9]+  { return new Symbol(sym.NUMBER, new Integer(yytext()) ); }
.      {}
