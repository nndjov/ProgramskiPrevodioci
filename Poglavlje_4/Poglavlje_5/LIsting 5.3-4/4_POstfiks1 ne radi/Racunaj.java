import java.io.*;
import java_cup.runtime.*;
class Racunaj {
   public static void main(String args[])  {
	   try {
		  InputStreamReader in = new InputStreamReader(System.in);
		  BufferedReader input = new BufferedReader(in);
		  Reader r = new StringReader("a:=b*(c+d/(e+f));");
		  parser p = new parser(new Yylex(r));
		  p.parse();
		  System.out.println("Izraz.izraz = "+Izraz.izraz);
  	  } catch (Exception e) {
			//e.printStackTrace();
	  }

  }
}



//  ili debug_parse() ako hocemo ispis
//  parserskih akcija na System.err