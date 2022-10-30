import java.io.*;
import java_cup.runtime.*;
public class Primer {
	public static void main(String[] args) {
		try{
			File file = new File("test.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			Yylex lexer = new Yylex(br);
			Symbol s = lexer.next_token();// yylex()  next_token() because the specification uses the %cup switch

            while(s.sym!= sym.EOF){
				 System.out.println(sym.vratiToken(s.sym));
				 s = lexer.next_token();
			}
		} catch (Exception e) {
		  System.out.println("Greska!!!");
		  e.printStackTrace();
		}
	}
}
