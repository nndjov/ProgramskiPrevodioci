import java.io.*;
import java_cup.runtime.*;
class Racunaj {
   public static void main(String args[])  {
	   try {
		  InputStreamReader in = new InputStreamReader(System.in);
		  BufferedReader input = new BufferedReader(in);
		  String s = "a+5-b";
		  System.out.println(s);
		  Reader r = new StringReader(s);
		  parser p = new parser( new Yylex(r));
		  p.parse();
  	  } catch (Exception e) {
			e.printStackTrace();
	  }
  }
}



//  ili debug_parse() ako hocemo ispis
//  parserskih akcija na System.err