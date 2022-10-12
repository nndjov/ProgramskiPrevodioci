// import iskaz
import java_cup.runtime.Symbol;

%%
//jflex direktive
%cup

%eofval{
  return new Symbol(sym.EOF);  // izvrsava se kada se dodje do kraja ulaznog fajla
%eofval}

number = [0-9]+
identifier = [A-Za-z][A-Za-z0-9]*

%%
// Pravila za uparivanje

" " {}
\b {}
\t {}
\r\n {}
\f {}
"+"  { return new Symbol(sym.ADD); }
"-"   { return new Symbol(sym.SUB); }
"/"   { return new Symbol(sym.DIV); }
"*" { return new Symbol(sym.MUL); }
"("   { return new Symbol(sym.LPAREN); }
")"   { return new Symbol(sym.RPAREN); }
";"    { return new Symbol(sym.SEMI); }
":="    { return new Symbol(sym.ASSIG); }
{number}  { return new Symbol(sym.NUMBER, new Integer(yytext()) ); }
{identifier} { return new Symbol(sym.IDENTIFAER,yytext() ); }
.      {}
