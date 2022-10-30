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
		  System.out.println(vratiPrazno(programInt)+"Enter expr_list");
		  programInt++;
		  while(tekuciToken!=null &&  tekuciToken.tip != Token.EOT) {   //stmt --> expr_part*
			  if(!expr_part()){
				  return false;
			  }
		  }
		  programInt--;
		  System.out.println(vratiPrazno(programInt)+"Exit expr_list");
		  return true;
	}

	private boolean expr_part(){
		String iden="";
		if(tekuciToken.tip != Token.IDENTIFIER){
		  return false;
		}else{
		  System.out.println(vratiPrazno(programInt)+"Enter expr_part");
		  programInt++;
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
			  programInt--;
			  System.out.println(vratiPrazno(programInt)+"Exit expr_part");
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
		System.out.println(vratiPrazno(programInt)+"Enter expr");
		programInt++;
		int subtotal = term();
		int rez = term_add(subtotal);
		programInt--;
		System.out.println(vratiPrazno(programInt)+"Exit expr");
		return 	rez;
	}
    // <term> -> <factor> <factor_add>
    private int term(){
		System.out.println(vratiPrazno(programInt)+"Enter term");
		programInt++;
	  	int subtotal = factor();
	  	int rez = factor_add(subtotal);
		programInt--;
		System.out.println(vratiPrazno(programInt)+"Exit term");
		return rez;
	}

	//<term_add> -> <add_op> <term> <term_add> | e
	public int term_add(int subtotal){
		System.out.println(vratiPrazno(programInt)+"Enter term_add");
		programInt++;
		if(tekuciToken!=null && tekuciToken.leksema.equals("+")){
			Glavni.REZULTAT_ = Glavni.REZULTAT_ +"+";
			prihvati(Token.OPERATOR);
			int termvalue = term();
			int rez = term_add(subtotal + termvalue);
			programInt--;
			System.out.println(vratiPrazno(programInt)+"Exitterm_add");
			return rez;
		} else if(tekuciToken!=null && tekuciToken.leksema.equals("-")){
			Glavni.REZULTAT_ = Glavni.REZULTAT_ +"-";
			prihvati(Token.OPERATOR);
			int termvalue = term();
			int rez = term_add(subtotal - termvalue);
			programInt--;
			System.out.println(vratiPrazno(programInt)+"Exit term_add");
			return rez;
		} else{
			programInt--;
			System.out.println(vratiPrazno(programInt)+"Exit term_add");
			return subtotal;
		}
	}
	// <factor> -> ( <expr> ) | - <factor> | IDENTIFIER | NUMBER
    private int factor(){
		System.out.println(vratiPrazno(programInt)+"Enter factor");
		programInt++;
		if( tekuciToken.tip == Token.LPAREN ){
			Glavni.REZULTAT_ = Glavni.REZULTAT_ +"(";
			prihvati(Token.LPAREN);
			int value = expr();
			if( tekuciToken.tip == Token.RPAREN ){
				Glavni.REZULTAT_ = Glavni.REZULTAT_ +")";
				prihvati(Token.RPAREN);
				programInt--;
				System.out.println(vratiPrazno(programInt)+"Exit factor");
			}
			return value;
		} else if( tekuciToken.tip == Token.NUMBER ){
			int num = Integer.parseInt(tekuciToken.leksema);
			Glavni.REZULTAT_ = Glavni.REZULTAT_ +tekuciToken.leksema;
			prihvati(Token.NUMBER);
			programInt--;
			System.out.println(vratiPrazno(programInt)+"Exit factor");
			return num;
		} else if( tekuciToken.tip == Token.IDENTIFIER){
			int num = skener.tabelaSimbola.pretrazi(tekuciToken.leksema);
		    Glavni.REZULTAT_ = Glavni.REZULTAT_ +tekuciToken.leksema;
			prihvati(Token.IDENTIFIER);
			programInt--;
			System.out.println(vratiPrazno(programInt)+"Exit factor");
			return num;
		} else {
			programInt--;
			System.out.println(vratiPrazno(programInt)+"Exit factor");
			return 0;
		}
	}

	//<factor_add> -> <mult_op> <factor> <factor_add> | e
	private int factor_add(int subtotal){
		System.out.println(vratiPrazno(programInt)+"Enter factor_add");
		programInt++;
		if(tekuciToken != null && tekuciToken.leksema.equals("*") ){
			Glavni.REZULTAT_ = Glavni.REZULTAT_ +"*";
			//prihvati(Token.OPERATOR);
			add_op();
	        int factorvalue = factor();
	        int rez = factor_add(subtotal * factorvalue);
			programInt--;
			System.out.println(vratiPrazno(programInt)+"Exit factor_add");
	        return rez;
		}else if(tekuciToken != null && tekuciToken.leksema.equals("/")){
			Glavni.REZULTAT_ = Glavni.REZULTAT_ +"/";
			//prihvati(Token.OPERATOR);
			mult_op();
	        int factorvalue = factor();
	        int rez = factor_add(subtotal / factorvalue);
			programInt--;
			System.out.println(vratiPrazno(programInt)+"Exit factor_add");
	        return rez;
		} else{
			programInt--;
			System.out.println(vratiPrazno(programInt)+"Exit factor_add");
			return subtotal;
		}
	}

    // <add_op> -> + | -

	private void add_op(){
		//if(tekuciToken!=null && (tekuciToken.leksema.equals("+") || tekuciToken.leksema.equals("-"))){
		  System.out.println(vratiPrazno(programInt)+"Enter AddOperation");
		  programInt++;
		  prihvati(Token.OPERATOR);
		  programInt--;
		  System.out.println(vratiPrazno(programInt)+"Exit AddOperation");
		//  return true;
		//} else{
		//	return false;
		//}
	}
	private void mult_op(){
		//if(tekuciToken!=null && (tekuciToken.leksema.equals("*") || tekuciToken.leksema.equals("/"))){
		  System.out.println(vratiPrazno(programInt)+"Enter MultOperation");
		  programInt++;
		  prihvati(Token.OPERATOR);
		  programInt--;
		  System.out.println(vratiPrazno(programInt)+"Exit MultOperation");
		  //return true;
		//} else{
		  //return false;
		//}
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
