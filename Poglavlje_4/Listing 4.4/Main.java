import java.io.*;
public class Main {
	public static void main(String[] args) {
		try{
			File file = new File("test.txt");
			Yylex lexer = new Yylex( new FileReader( file ) );
			parser p = new parser(lexer);
			p.parse();
			//p.debug_parse();
		} catch (Exception e) {
		  System.out.println("Greska!!!");
		  e.printStackTrace();
		}
	}
}
