import java.io.*;
public class Main {
	public static void main(String[] args) {
		try{
			File file = new File("test.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			Skener skener = new Skener(br);
			Parser p = new Parser(skener);
			p.parsiranje();
		} catch (Exception e) {
		  System.out.println("Greska!!!");
		  e.printStackTrace();
		}
	}
}
