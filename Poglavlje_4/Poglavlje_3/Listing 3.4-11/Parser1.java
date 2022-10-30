import java.io.*;
public class Parser1 {
	private Token tekuciToken;
	private Skener skener;
	static int programInt = 0;

	public Parser1(Skener skener){
		this.skener = skener;
	}

	public void parsiranje() {
		tekuciToken = skener.skeniraj();
		if (!expr_list() && tekuciToken!=null && tekuciToken.tip != Token.EOT){
			new Error("Syntax error: Redundant characters at the end of program.");
		} else{
            System.out.println("The syntax of the source program is correct.");
		}
	}

	private boolean expr_list(){
		  while(tekuciToken!=null &&  tekuciToken.tip != Token.EOT) {   //stmt --> expr_part*
			  if(!expr_part()){
				  return false;
			  }
		  }
		  return true;
	}

	private boolean expr_part(){
		String iden="";
		if(tekuciToken.tip != Token.IDENTIFIER){
		  return false;
		}else{
		  iden = tekuciToken.leksema;
		  prihvati(Token.IDENTIFIER);
		}
		if(tekuciToken.tip != Token.ASSIG){
		  return false;
		}else{
		  prihvati(Token.ASSIG);
		  int rez = expr();
		  if(tekuciToken.tip != Token.SEMI){
			   return false;
		  }
		  else{
			  prihvati(Token.SEMI);
			  if(tekuciToken!=null && tekuciToken.tip == Token.LF){
				  prihvati(Token.LF);
			  }
			  System.out.println();
			  System.out.println("REZULTAT = " + rez);
			  Glavni.REZULTAT_ = Glavni.REZULTAT_ + " = " + rez+"\n";
			  TabelaSimbolaStavka stavka = new TabelaSimbolaStavka(iden, "IDENTIFIER", ""+rez);
			  skener.tabelaSimbola.dodaj(stavka);
			  System.out.println();
			  return true;
		  }
		}
	}
	private int expr(){  //  <expr> -> <term> <term_add>
		int subtotal = term();
		int rez = term_add(subtotal);
		return 	rez;
	}
	// <term> -> <factor> <factor_add>
	private int term(){
		int subtotal = factor();
		int rez = factor_add(subtotal);
		return rez;
	}

	//<term_add> -> <add_op> <term> <term_add> | e
	public int term_add(int subtotal){
		if(tekuciToken!=null && tekuciToken.leksema.equals("+")){
			Glavni.REZULTAT_ = Glavni.REZULTAT_ +"+";
			prihvati(Token.OPERATOR);
			int termvalue = term();
			int rez = term_add(subtotal + termvalue);
			return rez;
		} else if(tekuciToken!=null && tekuciToken.leksema.equals("-")){
			Glavni.REZULTAT_ = Glavni.REZULTAT_ +"-";
			prihvati(Token.OPERATOR);
			int termvalue = term();
			int rez = term_add(subtotal - termvalue);
			return rez;
		} else{
			return subtotal;
		}
	}
	// <factor> -> ( <expr> ) | - <factor> | IDENTIFIER | NUMBER
	private int factor(){
		if( tekuciToken.tip == Token.LPAREN ){
			Glavni.REZULTAT_ = Glavni.REZULTAT_ +"(";
			prihvati(Token.LPAREN);
			int value = expr();
			if( tekuciToken.tip == Token.RPAREN ){
				Glavni.REZULTAT_ = Glavni.REZULTAT_ +")";
				prihvati(Token.RPAREN);
			}
			return value;
		} else if( tekuciToken.tip == Token.NUMBER ){
			int num = Integer.parseInt(tekuciToken.leksema);
			Glavni.REZULTAT_ = Glavni.REZULTAT_ +tekuciToken.leksema;
			prihvati(Token.NUMBER);
			return num;
		} else if( tekuciToken.tip == Token.IDENTIFIER){
			int num = skener.tabelaSimbola.pretrazi(tekuciToken.leksema);
			Glavni.REZULTAT_ = Glavni.REZULTAT_ +tekuciToken.leksema;
			prihvati(Token.IDENTIFIER);
			return num;
		} else {
			return 0;
		}
	}

	//<factor_add> -> <mult_op> <factor> <factor_add> | e
	private int factor_add(int subtotal){
		if(tekuciToken != null && tekuciToken.leksema.equals("*") ){
			Glavni.REZULTAT_ = Glavni.REZULTAT_ +"*";
			//prihvati(Token.OPERATOR);
			add_op();
			int factorvalue = factor();
			int rez = factor_add(subtotal * factorvalue);
			return rez;
		}else if(tekuciToken != null && tekuciToken.leksema.equals("/")){
			Glavni.REZULTAT_ = Glavni.REZULTAT_ +"/";
			//prihvati(Token.OPERATOR);
			mult_op();
			int factorvalue = factor();
			int rez = factor_add(subtotal / factorvalue);
			return rez;
		} else{
			return subtotal;
		}
	}

	private void add_op(){
		 prihvati(Token.OPERATOR);
	}
	private void mult_op(){
		 prihvati(Token.OPERATOR);
	}

	private void prihvati(byte ocekivaniTip) {
		if(tekuciToken!=null ){
			Token tt = tekuciToken;
			if(tekuciToken.tip == ocekivaniTip){
				System.out.println(vratiPrazno(programInt)+"Tekuci token = "+tekuciToken.vratiToken()+ "["+tekuciToken.leksema+"]" +" Ocekivani = "+Token.vratiToken(ocekivaniTip));
				tekuciToken = skener.skeniraj();
			}else
				new Error("Syntax error: " + tekuciToken.tip + " is not expected.");
		}
	}
	String vratiPrazno(int n){
	  String s = "";
	  for(int i =0; i<n; i++)
		 s = s+"-";
	  return s;
	}
}
