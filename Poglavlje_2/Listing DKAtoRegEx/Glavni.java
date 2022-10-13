import java.util.*;

class Glavni{
    public static void main(String[] args)  {
		//DEfinisanje automata
		String start = "A";
		List<String> zavrsna = new ArrayList<String>(Arrays.asList("D"));
		List<String> svaStanja = new ArrayList<String>(Arrays.asList("A","B","C","D"));
		// Prelazi
        String prelazi[][] = { {"A","a","B"},  // iz stanja A, na simbol a se prelazi u stanje B
					    	   {"B","b","C"},
							   {"C","c","D"},
							   {"D","d","D"}
					  		 };
		Automat a = new Automat(start, zavrsna, svaStanja, prelazi);
		String regIzraz = a.pokreni();

		System.out.println("Regularni izraz : " + regIzraz);
    }
}