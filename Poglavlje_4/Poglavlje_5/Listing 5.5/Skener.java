
import java.util.*;
import java.util.regex.*;
import java.io.*;
import java.util.*;

class Skener {
	String ulaz;
	BufferedReader datoteka;
	int indeks = 0;
	List<Token> listaTokena;
	Map<Integer, String> promenljiveMap;
	int ctr;
	String rezultat;
	//List<TabelaSimbolaStavka> tabelaSimbola = new LinkedList<>();
	TabelaSimbola tabelaSimbola;
	StringTokenizer st ;//

	public Skener(BufferedReader datoteka){
		listaTokena = new LinkedList<Token>();
		tabelaSimbola = new TabelaSimbola();
		promenljiveMap = new HashMap<>();
		this.datoteka = datoteka;
		ulaz = "";
		try {
		  Scanner s = new Scanner(datoteka);
		  while (s.hasNextLine()) {
			  String data = s.nextLine();
			  ulaz = ulaz+data;
		  }
		  st = new StringTokenizer(ulaz, " :=+-*/(){},;",true);// razbija string na tokene
		  System.out.println("");
		  s.close();
		} catch (Exception e) {
		  System.out.println("Greska!!!");
		  e.printStackTrace();
		}
		formirajTokene();
	}

	public Skener(String dat){
		listaTokena = new LinkedList<Token>();
		tabelaSimbola = new TabelaSimbola();
		promenljiveMap = new HashMap<>();
		this.datoteka = datoteka;
		ulaz = dat;
		st = new StringTokenizer(ulaz, " :=+-*/(){},; \n",true);// razbija string na tokene
		formirajTokene();
	}

	List<Token> vratiListu(){
		return listaTokena;
	}

	void formirajTokene() {
		String s = new String();
		while(st.hasMoreTokens()) {
			s = st.nextToken();
			if(s.equals(" ")) {
				continue;
			}
			if(!kljucnaRec(s)) {
				if(!operator(s)) {
					if(!simbol(s)) {
						if(!brojnaVrednost(s)){
							if(!identifikator(s)){
								System.out.println("Neispravan token: '" + s + "'");
								rezultat = "Neispravan token: " + s + " " + "\n";
							}
						}
					}
				}
			}
		}
	}

	boolean brojnaVrednost(String s) {
		try {
			//Provera da li je konstanta int:
			int x = Integer.parseInt(s);
			String leksema = s;
			listaTokena.add(new Token(Token.NUMBER, leksema));
			TabelaSimbolaStavka stavka = new TabelaSimbolaStavka(leksema, "NUMBER", "int");
			tabelaSimbola.dodaj(stavka);
			return true;
		} catch(NumberFormatException e1) {
			try {
				//Provera da li je konstanta float:
				float y = Float.parseFloat(s);
				String leksema = s;
				listaTokena.add(new Token(Token.NUMBER, leksema));
				TabelaSimbolaStavka stavka = new TabelaSimbolaStavka(leksema, "NUMBER", "float");
				tabelaSimbola.dodaj(stavka);
				return true;
			} catch(NumberFormatException e2){

			}
		}
		return false;
	}

	boolean identifikator(String s) {
		// IDENTIFIKATOR  [A-Za-z_][A-Za-z0-9]*
		//String slovo =  "[A-Za-z]";
		//String izraz = "["+slovo+"_]["+slovo+"0-9]*";
		String izraz = "[A-Za-z_][A-Za-z0-9]*";
		if(Pattern.matches(izraz, s)) {
			// validni identifikator
			listaTokena.add(new Token(Token.IDENTIFIER, s));
			return true;
			/*
			if(promenljiveMap.containsValue(s)) {
				Iterator<Integer> iterator = promenljiveMap.keySet().iterator();
				while(iterator.hasNext()) {
					int x = iterator.next();
					if(promenljiveMap.get(x).equals(s)) {
						listaTokena.add(new Token(Token.IDENTIFIER, s));
						return true;
					}
				}
			} else {
				// ctr sledeci identifikator
				++ctr;
				listaTokena.add(new Token(Token.IDENTIFIER, s));
				promenljiveMap.put(ctr, s);
				return true;
			}
	   */
		}
		return false;
	}

	boolean kljucnaRec(String s) {
		if(s.equals("print")) {
			//listaTokena.add(new Token(Token.PRINT, "print"));
			return true;
		}
		return false;
	}

	boolean operator(String s) {
		if(s.equals("=")) {
			listaTokena.add(new Token(Token.OPERATOR, "="));
			return true;
		} else if(s.equals("+")) {
			listaTokena.add(new Token(Token.OPERATOR, "+"));
			return true;
		}else if(s.equals("-")) {
			listaTokena.add(new Token(Token.OPERATOR, "-"));
			return true;
		}else if(s.equals("*")) {
			listaTokena.add(new Token(Token.OPERATOR, "*"));
			return true;
		}else if(s.equals("/")) {
			listaTokena.add(new Token(Token.OPERATOR, "/"));
			return true;
		}
		return false;
	}

	boolean simbol(String s) {
		if(s.equals("(")) {
			listaTokena.add(new Token(Token.LPAREN, "("));
			return true;
		}else if(s.equals(")")) {
			listaTokena.add(new Token(Token.RPAREN, ")"));
			return true;
		}else if(s.equals(";")) {
			listaTokena.add(new Token(Token.SEMI, ";"));
			return true;
		} else if(s.equals(":")) {
			if( st.nextToken().equals("=")){
				listaTokena.add(new Token(Token.ASSIG, ":="));
				return true;
			}
		} else if(s.equals("\n")) {
			listaTokena.add(new Token(Token.LF, "novi red"));
			return true;
		}
		return false;
	}

	public String tabelaSimbola(){
		return tabelaSimbola.tabelaSimbola();
    }

	public Token skeniraj(){
		if (indeks < listaTokena.size()){
			Token t = listaTokena.get(indeks);
			indeks++;
			return t;
		}else{
			return null;
		}
	}

    public static void main(String[] args) {
		try{
			File file = new File("ulaz.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			Skener s = new Skener(br);
			System.out.println("ulaz: "+s.ulaz);
			System.out.println();

			Token t = s.skeniraj();
			System.out.print("[");
			while(t!=null){
				System.out.print(t);
				t = s.skeniraj();
				if(t!=null)
				   System.out.print(", ");
			}
			System.out.print("]");
			System.out.println("\n\n"+s.tabelaSimbola());

		}catch (FileNotFoundException e) {

		}

    }

/*
	public String start(String program){
		rezultat = "";
		Scanner br = new Scanner(program);
		floatPromenljive = new ArrayList<>(); // LinkedList
		intPromenljive = new ArrayList<>();  // LinkedList
		promenljiveMap = new HashMap<>();
		ctr = 0;
		try{
		while((ulaz = br.nextLine()) != null) {
			System.out.println(ulaz);
			rezultat = rezultat + "" +ulaz + "\n";
			leksickaAnaliza();
			System.out.println("listaTokena = " + listaTokena);
			rezultat = rezultat + "listaTokena = " + listaTokena + "\n";
			System.out.println();
			sintaksnaAnaliza();
			System.out.println();
			semantickaAnaliza();
			System.out.println();
		}
		tabelaSimbola();
	    } catch(Exception e) {}
		return rezultat;
	}
*/

}

/*
OUTPUT:

{ int a,b }
Ispravna sintaksa.
int x = (a/10.0)+(b/10);
Konverzija tipa na float.
Ispravna sintaksa.
We are trying to assign float to int.
Semanticki neispravno.

*/