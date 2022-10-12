import java.io.*;
public class Parser2 {
	private Token tekuciToken;
	private Skener skener;
	static int programInt = 0;
	DFASintaksnoStablo stablo;

	public Parser2(Skener skener){
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
		  DFACvorr koren = expr();
		  stablo = new DFASintaksnoStablo(koren);
          stablo.uredi(koren);
          stablo.urediXY(koren);
		  stablo.pokreni();
		  if(tekuciToken.tip != Token.SEMI){
			   return false;
		  }
		  else{
			  prihvati(Token.SEMI);
			  if(tekuciToken!=null && tekuciToken.tip == Token.LF){
				  prihvati(Token.LF);
			  }
			  return true;
		  }
		}
	}
	private DFACvorr expr(){  //  <expr> -> <term> <term_add>
		DFACvorr subtotal = term();
		DFACvorr rez = term_add(subtotal);
		return 	rez;
	}
	// <term> -> <factor> <factor_add>
	private DFACvorr term(){
		DFACvorr subtotal = factor();
		DFACvorr rez = factor_add(subtotal);
		return rez;
	}

	//<term_add> -> <add_op> <term> <term_add> | e
	public DFACvorr term_add(DFACvorr subtotal){
		if(tekuciToken!=null && tekuciToken.leksema.equals("+")){
            prihvati(Token.OPERATOR);
			DFACvorr c = new DFACvorr();
			c.setLabel("+");
			DFACvorr c2 = term();
			DFACvorr c1 = subtotal;
			c.setLeftChild(c1);
			c.setRightChild(c2);
			return c;
		} else if(tekuciToken!=null && tekuciToken.leksema.equals("-")){
			prihvati(Token.OPERATOR);
			DFACvorr c = new DFACvorr();
			c.setLabel("-");
			DFACvorr c2 = term();
			DFACvorr c1 = subtotal;
			c.setLeftChild(c1);
			c.setRightChild(c2);
			return c;
		} else{
			return subtotal;
		}
	}
	// <factor> -> ( <expr> ) | - <factor> | IDENTIFIER | NUMBER
	private DFACvorr factor(){
		if( tekuciToken.tip == Token.LPAREN ){
			prihvati(Token.LPAREN);
			DFACvorr value = expr();
			if( tekuciToken.tip == Token.RPAREN ){
				prihvati(Token.RPAREN);
			}
			return value;
		} else if( tekuciToken.tip == Token.NUMBER ){
			String broj = tekuciToken.leksema;
			prihvati(Token.NUMBER);
			DFACvorr c = new DFACvorr();
			c.setLabel(broj);
			return c;
		} else if( tekuciToken.tip == Token.IDENTIFIER){
			String id = tekuciToken.leksema;
			prihvati(Token.IDENTIFIER);
			DFACvorr c = new DFACvorr();
			c.setLabel(id);
			return c;
		} else {
			return null;
		}
	}

	//<factor_add> -> <mult_op> <factor> <factor_add> | e
	private DFACvorr factor_add(DFACvorr subtotal){
		if(tekuciToken != null && tekuciToken.leksema.equals("*") ){
			//add_op();
			prihvati(Token.OPERATOR);
			DFACvorr c = new DFACvorr();
			c.setLabel("*");
			DFACvorr c2 = factor();
			DFACvorr c1 = subtotal;
			c.setLeftChild(c1);
			c.setRightChild(c2);
			return c;

		}else if(tekuciToken != null && tekuciToken.leksema.equals("/")){
			//mult_op();
			prihvati(Token.OPERATOR);
			DFACvorr c = new DFACvorr();
			c.setLabel("/");
			DFACvorr c2 = factor();
			DFACvorr c1 = subtotal;
			c.setLeftChild(c1);
			c.setRightChild(c2);
			return c;
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
