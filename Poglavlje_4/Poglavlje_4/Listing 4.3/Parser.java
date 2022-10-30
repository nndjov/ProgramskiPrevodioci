import java.io.*;
public class Parser {
	private Token tekuciToken;
	private Skener skener;
	static int programInt;
	String stablo;
	String parser;
	String ocekivaniToken;

	public Parser(Skener skener){
		this.skener = skener;
		stablo = "";
		parser = "";
	}

	public void parsiranje() {
		tekuciToken = skener.skeniraj();
		if (!expr_list() && tekuciToken!=null  ){
			System.out.println("TOKEN " + tekuciToken.leksema);
			Glavni.REZULTAT = "Sintaksna greska: Suvisni karakter <"+
			                  tekuciToken.vratiToken()+"> u programu!";
			new Error("Sintaksna greska: Suvisni karakter u programu!");
		} else{
			Glavni.REZULTAT = "Sintaksa izvornog programa je korektna!";
            System.out.println("Sintaksa izvornog programa je korektna!");
		}
	}

	private boolean expr_list(){
		programInt = 0;
		  System.out.println(vratiPrazno(programInt)+"Enter expr_list");
		  stablo = stablo + "\n" + vratiPrazno(programInt)+"Enter expr_list";
		  programInt++;
		  while(tekuciToken!=null &&  tekuciToken.tip != Token.EOT) {   //stmt --> expr_part*
			  if(!expr_part()){
				  return false;
			  }
		  }
		  programInt--;
		  System.out.println(vratiPrazno(programInt)+"Exit expr_list");
		  stablo = stablo + "\n" + vratiPrazno(programInt)+"Exit expr_list";
		  return true;
	}

	private boolean expr_part(){
		if(tekuciToken.tip != Token.IDENTIFIER){
		  return false;
		}else{
		  System.out.println(vratiPrazno(programInt)+"Enter expr_part");
		  stablo = stablo + "\n" + vratiPrazno(programInt)+"Enter expr_part";
		  programInt++;
		  stablo = stablo + "\n" + vratiPrazno(programInt)+"IDENTIFAER " + tekuciToken.leksema;
		  //programInt++;
		  prihvati(Token.IDENTIFIER);
		}
		if(tekuciToken.tip != Token.ASSIG){
		  return false;
		}else{
		  prihvati(Token.ASSIG);
		  stablo = stablo + "\n" + vratiPrazno(programInt)+"ASSIGN";
		  //programInt--;
		  if(expr()){
			  if(tekuciToken.tip != Token.SEMI){
				return false;
			  }
			  prihvati(Token.SEMI);
			  stablo = stablo + "\n" + vratiPrazno(programInt)+"SEMI";
			  if(tekuciToken!=null && tekuciToken.tip == Token.LF){
				  prihvati(Token.LF);
			  }
			  programInt--;
			  System.out.println(vratiPrazno(programInt)+"Exit expr_part");
			  stablo = stablo + "\n" + vratiPrazno(programInt)+"Exit expr_part";
			  return true;
		  }else{
			  return false;
		  }
		}
	}

	private boolean expr(){  //  <expr> -> <term> <term_add>
		System.out.println(vratiPrazno(programInt)+"Enter expr");
		stablo = stablo + "\n" + vratiPrazno(programInt)+"Enter expr";
		programInt++;
		if(term()){
			if(term_add()){
				programInt--;
				System.out.println(vratiPrazno(programInt)+"Exit expr");
				stablo = stablo + "\n" + vratiPrazno(programInt)+"Exit expr";
				return true;
			}
		}
		return false;
	}

	//<term_add> -> <add_op> <term> <term_add> | e
	public boolean term_add(){
		System.out.println(vratiPrazno(programInt)+"Enter term_add");
		stablo = stablo + "\n" + vratiPrazno(programInt)+"Enter term_add";
		programInt++;
		if(tekuciToken!=null && (tekuciToken.leksema.equals("+") || tekuciToken.leksema.equals("-"))){
			if(add_op()){
				if(term()){
					if(term_add()){
						programInt--;
						System.out.println(vratiPrazno(programInt)+"Exit term_add");
						stablo = stablo + "\n" + vratiPrazno(programInt)+"Exit term_add";
						return true;
					}
				}
			}
			return false;
		}
		programInt--;
		System.out.println(vratiPrazno(programInt)+"Exit term_add");
		stablo = stablo + "\n" + vratiPrazno(programInt)+"Exit term_add";
		return true; // epsilon
	}

    // <term> -> <factor> <factor_add>
	private boolean term(){
		System.out.println(vratiPrazno(programInt)+"Enter term");
		stablo = stablo + "\n" + vratiPrazno(programInt)+"Enter term";
		programInt++;
		if(factor()){
			if(factor_add()){
				programInt--;
				System.out.println(vratiPrazno(programInt)+"Exit term");
				stablo = stablo + "\n" + vratiPrazno(programInt)+"Exit term";
				return true;
			}
		}
		return false;
	}

	//<factor_add> -> <mult_op> <factor> <factor_add> | e
	private boolean factor_add(){
		System.out.println(vratiPrazno(programInt)+"Enter factor_add");
		stablo = stablo + "\n" + vratiPrazno(programInt)+"Enter factor_add";
		programInt++;
		if(tekuciToken != null && (tekuciToken.leksema.equals("*") || tekuciToken.leksema.equals("/"))){
			if(mult_op()){
				if(factor()){
					if(factor_add()){
						programInt--;
						System.out.println(vratiPrazno(programInt)+"Exit factor_add");
						stablo = stablo + "\n" + vratiPrazno(programInt)+"Exit factor_add";
						return true;
					}
				}
			}
			return false;
		} // epsilon
		programInt--;
		System.out.println(vratiPrazno(programInt)+"Exit factor_add");
		stablo = stablo + "\n" + vratiPrazno(programInt)+"Exit factor_add";
		return true;// epsilon
	}

	// <factor> -> ( <expr> ) | - <factor> | IDENTIFIER | NUMBER
	private boolean factor(){
		System.out.println(vratiPrazno(programInt)+"Enter factor");
		stablo = stablo + "\n" + vratiPrazno(programInt)+"Enter factor";
		programInt++;
		if( tekuciToken.tip == Token.LPAREN ){
			stablo = stablo + "\n" + vratiPrazno(programInt)+"LPAREN " + tekuciToken.leksema;
			prihvati(Token.LPAREN);
			if(!expr()){
				return false;
			} else if( tekuciToken.tip == Token.RPAREN ){
				stablo = stablo + "\n" + vratiPrazno(programInt)+"RPAREN " + tekuciToken.leksema;
				prihvati(Token.RPAREN);
				programInt--;
				System.out.println(vratiPrazno(programInt)+"Exit factor");
				stablo = stablo + "\n" + vratiPrazno(programInt)+"Exit factor";
				return true;
			}
			return false;
		} else if( tekuciToken.tip == Token.NUMBER ){
			stablo = stablo + "\n" + vratiPrazno(programInt)+"NUMBER = "+tekuciToken.leksema;
			prihvati(Token.NUMBER);
			programInt--;
			System.out.println(vratiPrazno(programInt)+"Exit factor");
			stablo = stablo + "\n" + vratiPrazno(programInt)+"Exit factor";
			return true;
		} else if( tekuciToken.tip == Token.IDENTIFIER){
			stablo = stablo + "\n" + vratiPrazno(programInt)+"IDENTIFIER "+tekuciToken.leksema;
			prihvati(Token.IDENTIFIER);
			programInt--;
			System.out.println(vratiPrazno(programInt)+"Exit factor");
			stablo = stablo + "\n" + vratiPrazno(programInt)+"Exit factor";
			return true;
		}
		return false;
	}

    // <add_op> -> + | -
	private boolean add_op(){
		if(tekuciToken!=null && (tekuciToken.leksema.equals("+") || tekuciToken.leksema.equals("-"))){
		  System.out.println(vratiPrazno(programInt)+"Enter AddOperation");
		  stablo = stablo + "\n" + vratiPrazno(programInt)+"Enter AddOperation";
		  programInt++;
		  stablo = stablo + "\n" + vratiPrazno(programInt)+"OPERATOR "+tekuciToken.leksema;
		  prihvati(Token.OPERATOR);
		  programInt--;
		  System.out.println(vratiPrazno(programInt)+"Exit AddOperation");
		  stablo = stablo + "\n" + vratiPrazno(programInt)+"Exit AddOperation";
		  return true;
		}
		return false;
	}
	private boolean mult_op(){
		if(tekuciToken!=null && (tekuciToken.leksema.equals("*") || tekuciToken.leksema.equals("/"))){
		  System.out.println(vratiPrazno(programInt)+"Enter MultOperation");
		  stablo = stablo + "\n" + vratiPrazno(programInt)+"Enter MultOperation";
		  programInt++;
		  stablo = stablo + "\n" + vratiPrazno(programInt)+"OPERATOR "+tekuciToken.leksema;
		  prihvati(Token.OPERATOR);
		  programInt--;
		  System.out.println(vratiPrazno(programInt)+"Exit MultOperation");
		  stablo = stablo + "\n" + vratiPrazno(programInt)+"Exit MultOperation";
		  return true;
		}
		return false;
	}

	private void prihvati(byte ocekivaniTip) {
		if(tekuciToken!=null ){
			if(tekuciToken.tip == ocekivaniTip){
				System.out.println(vratiPrazno(programInt)+"Tekuci token = "+tekuciToken.vratiToken()+ "["+tekuciToken.leksema+"]" +" Ocekivani = "+Token.vratiToken(ocekivaniTip));
				ocekivaniToken = Token.vratiToken(ocekivaniTip);
				parser = parser + "\n" + "Tekuci token = "+tekuciToken.vratiToken()+ "["+tekuciToken.leksema+"]" +" Ocekivani = "+Token.vratiToken(ocekivaniTip);
				tekuciToken = skener.skeniraj();
			}else
				new Error("Sintaksna greska!!!: " + tekuciToken.tip + " se ne ocekuje.");
		}
	}

	String vratiPrazno(int n){
	  String s = "";
	  for(int i =0; i<n; i++)
		 s = s+"-";
	  return s;
	}
	String stablo(){
		return stablo;
	}
	String parser(){
		return parser;
	}
}
