import java.io.*;
import java_cup.runtime.*;
class Racunaj {
   public static void main(String args[])  {
	   try {
		  InputStreamReader in = new InputStreamReader(System.in);
		  BufferedReader input = new BufferedReader(in);
		  //String s = "a:=x1*x+(2+x*(y+y))*y;";
		  //String ulaz = "x:=1*2;y:=2+x;a:=2*x+(2+4*(5+5))*y;";
		  String ulaz = "a:=2*3+(2+4*(5+5))*2;";
		  //String niz[] = ulaz.split(";");
		  //for(String s:niz)
			//  System.out.println(s);
		  System.out.println(ulaz);
		  Reader r = new StringReader(ulaz);
		  parser p = new parser(new Yylex(r));
		  p.parse();
  	  } catch (Exception e) {
			e.printStackTrace();
	  }
  }
}
