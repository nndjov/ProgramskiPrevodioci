//CRTA STABLO
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
			new Error("Sintaksna greska: Pogresan token!");
		} else{
            System.out.println("Program je sintaksno ispravan.");
		}
	}

	private boolean expr_list(){
		  while(tekuciToken!=null &&  tekuciToken.tip != Token.EOT) {
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
		  DFACvorr c1 = new DFACvorr();
		  c1.setLabel(":=");
		  DFACvorr c2 = new DFACvorr();
		  c2.setLabel(iden);
		  c1.setLeftChild(c2);
		  DFACvorr c3 = expr();
		  c1.setRightChild(c3);
		  stablo = new DFASintaksnoStablo(c1);
          stablo.uredi(c1);
          stablo.urediXY(c1);
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
	private DFACvorr expr(){
		DFACvorr subtotal = term();
		DFACvorr rez = term_add(subtotal);
		return 	rez;
	}
	private DFACvorr term(){
		DFACvorr subtotal = factor();
		DFACvorr rez = factor_add(subtotal);
		return rez;
	}
	public DFACvorr term_add(DFACvorr medjuRez){
		if(tekuciToken!=null && tekuciToken.leksema.equals("+")){
            prihvati(Token.OPERATOR);
			DFACvorr c = new DFACvorr();
			c.setLabel("+");
			DFACvorr c2 = term();
			DFACvorr c1 = medjuRez;
			c.setLeftChild(c1);
			c.setRightChild(c2);
			return c;
		} else if(tekuciToken!=null && tekuciToken.leksema.equals("-")){
			prihvati(Token.OPERATOR);
			DFACvorr c = new DFACvorr();
			c.setLabel("-");
			DFACvorr c2 = term();
			DFACvorr c1 = medjuRez;
			c.setLeftChild(c1);
			c.setRightChild(c2);
			return c;
		} else{
			return medjuRez;
		}
	}
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

	private DFACvorr factor_add(DFACvorr medjuRez){
		if(tekuciToken != null && tekuciToken.leksema.equals("*") ){
			prihvati(Token.OPERATOR);
			DFACvorr c = new DFACvorr();
			c.setLabel("*");
			DFACvorr c2 = factor();
			DFACvorr c1 = medjuRez;
			c.setLeftChild(c1);
			c.setRightChild(c2);
			return c;

		}else if(tekuciToken != null && tekuciToken.leksema.equals("/")){
			prihvati(Token.OPERATOR);
			DFACvorr c = new DFACvorr();
			c.setLabel("/");
			DFACvorr c2 = factor();
			DFACvorr c1 = medjuRez;
			c.setLeftChild(c1);
			c.setRightChild(c2);
			return c;
		} else{
			return medjuRez;
		}
	}

	private void prihvati(byte ocekivaniTip) {
		if(tekuciToken!=null ){
			Token tt = tekuciToken;
			if(tekuciToken.tip == ocekivaniTip){
				System.out.println(vratiPrazno(programInt)+"Tekuci token = "+tekuciToken.vratiToken()+ "["+tekuciToken.leksema+"]" +" Ocekivani = "+Token.vratiToken(ocekivaniTip));
				tekuciToken = skener.skeniraj();
			}else
				new Error("Sintaksna greska: " + tekuciToken.tip + " nije ocekivan.");
		}
	}
	String vratiPrazno(int n){
	  String s = "";
	  for(int i =0; i<n; i++)
		 s = s+"-";
	  return s;
	}
}
