import java.io.*;
import java_cup.runtime.*;
class Racunaj {
	static int rez = 0;
   public static void main(String args[])  {
	   try {
		  InputStreamReader in = new InputStreamReader(System.in);
		  BufferedReader input = new BufferedReader(in);
		  Reader r = new StringReader("2+3;");
		  parser p = new parser( new Yylex(r));

		  //p.parse();
		  //p.debug_parse();
        Symbol s = p.parse();
        Integer result = (Integer)(s.value);
        System.out.println("rez="+Racunaj.rez);

		  //System.out.println();
  	  } catch (Exception e) {
			e.printStackTrace();
	  }
  }
}


//  ili debug_parse() ako hocemo ispis
//  parserskih akcija na System.err