
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
	String rezultat = "";
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

		//StringTokenizer stok = new StringTokenizer(ulaz, "\n");// razbija string na redove
		//while(stok.hasMoreTokens()){
		//	st = new StringTokenizer(stok.nextToken(), " :=+-*/(){},;",true);//uzima red po red
			//formirajTokene();
		//}
	}

	String vratiRezultat(){
		return rezultat;
	}

	void formirajTokene() {
		String s = new String();
		boolean ispravan = true;
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
								rezultat =rezultat + "Neispravan token: " + s + " " + "\n";
								ispravan = false;
							}
						}
					}
				}
			}
		}
		if(ispravan){
			System.out.println("Program je leksicki ispravan!");
			rezultat = rezultat + "Program je leksicki ispravan!\n";
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
			indeks = 0;
			return null;
		}
	}

    public static void main(String[] args) {
		try{
			File file = new File("test.txt");
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
}

