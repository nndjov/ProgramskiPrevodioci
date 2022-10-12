
import java.util.*;
import java.util.regex.*;
import java.io.*;
import java.util.*;

class Kompajler {
	static String ulaz;
	static List<String> listaTokena;
	static String[] tokeni;
	static int ptr;
	static List<String> floatPromenljive;
	static List<String> intPromenljive;
	static Map<Integer, String> promenljiveMap;
	static int tipIzraza;
	static int ctr;
	String rezultat;

	List<TabelaSimbolaStavka> tabelaSimbola = new LinkedList<>();

	//public static void main(String args[]) throws Exception {
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

	void leksickaAnaliza() {
		/*
		listaTokena:
		***********************************
		TYP1 : kljucna rec int
		TYP2 : kljucna rec float
		PRINT : kljucna rec print
		IDE : identifikator, ime promenljive, moze biti int ili float
		OPR : operator =,+,-,*,/
		LPAREN : simbol '('
		RPAREN : simbol ')'
		SEMICOLON : simbol ';'
		LBRACE : simbol '{'
		RBRACE : simbol '}'
		COMMA : simbol ','
		CON1 : int konstanta
		CON2 : float konstanta
		*************************************
		*/
		listaTokena = new LinkedList<>();
		StringTokenizer st = new StringTokenizer(ulaz, " =+-*/(){},;", true);// razbija string na tokene
		String s = new String();
		while(st.hasMoreTokens()) {
			s = st.nextToken();
			if(s.equals(" ")) {
				continue;
			}
			if(!kljucnaRec(s)) {
				if(!operator(s)) {
					if(!simbol(s)) {
						try {
							//Provera da li je konstanta int:
							int x = Integer.parseInt(s);
							listaTokena.add("CON1");
///////
							String leksema = s;
							TabelaSimbolaStavka stavka = new TabelaSimbolaStavka(leksema, "CON1", "int");
							tabelaSimbola.add(stavka);
/////////
						} catch(NumberFormatException e1) {
							try {
								//Provera da li je konstanta float:
								float y = Float.parseFloat(s);
								listaTokena.add("CON2");
/////////
								String leksema = s;
								TabelaSimbolaStavka stavka = new TabelaSimbolaStavka(leksema, "CON2", "float");
								tabelaSimbola.add(stavka);
////////
							} catch(NumberFormatException e2) {
// Definisinje IDENTIFIKATORA  [A-Za-z_][A-Za-z0-9]*
                                String slovo =  "[A-Za-z]";
                                String izraz = "["+slovo+"_]["+slovo+"0-9]*";
								//if(Pattern.matches("[A-Za-z_][A-Za-z0-9]*", s)) {
								if(Pattern.matches(izraz, s)) {
									// validni identifikator
									if(promenljiveMap.containsValue(s)) {
										Iterator<Integer> iterator = promenljiveMap.keySet().iterator();
										while(iterator.hasNext()) {
											int x = iterator.next();
											if(promenljiveMap.get(x).equals(s)) {
												listaTokena.add("IDE" + x);
											}
										}
									} else {
										// ctr sledeci identifikator
										listaTokena.add("IDE" + (++ctr));
										promenljiveMap.put(ctr, s);
									}
								} else {
									System.out.println("Neispravan token: '" + s + "'");
									rezultat = "Neispravan token: " + s + " " + "\n";
								}
							}
						}
					}
				}
			}
		}
	}

	boolean kljucnaRec(String s) {
		if(s.equals("int")) {
			listaTokena.add("TYP1");
			return true;
		} else if(s.equals("float")) {
			listaTokena.add("TYP2");
			return true;
		}else if(s.equals("print")) {
			listaTokena.add("PRINT");
			return true;
		}
		return false;
	}

	boolean operator(String s) {
		if(s.equals("=")) {
			listaTokena.add("OPR1");
			return true;
		} else if(s.equals("+")) {
			listaTokena.add("OPR2");
			return true;
		}else if(s.equals("-")) {
			listaTokena.add("OPR3");
			return true;
		}else if(s.equals("*")) {
			listaTokena.add("OPR4");
			return true;
		}else if(s.equals("/")) {
			listaTokena.add("OPR5");
			return true;
		}
		return false;
	}

	boolean simbol(String s) {
		if(s.equals("(")) {
			listaTokena.add("LPAREN");
			return true;
		}else if(s.equals(")")) {
			listaTokena.add("RPAREN");
			return true;
		}else if(s.equals(";")) {
			listaTokena.add("SEMICOLON");
			return true;
		}else if(s.equals("{")) {
			listaTokena.add("LBRACE");
			return true;
		}else if(s.equals("}")) {
			listaTokena.add("RBRACE");
			return true;
		}else if(s.equals(",")) {
			listaTokena.add("COMMA");
			return true;
		}
		return false;
	}

	void sintaksnaAnaliza() {
		/*
		Grammar:
		linijaKoda -> LBRACE deklaracija RBRACE
		linijaKoda -> TYP1 IDE OPR1 izraz SEMICOLON
		linijaKoda -> TYP2 IDE OPR1 izraz SEMICOLON
		linijaKoda -> PRINT IDE SEMICOLON
		linijaKoda -> izraz SEMICOLON
		deklaracija -> TYP1 identifikator
		deklaracija -> TYP2 identifikator
		identifikator -> IDE
		identifikator -> IDE COMMA identifikator
		izraz -> terminal
		izraz -> izraz OPR1 izraz
		izraz -> izraz OPR2 izraz
		izraz -> izraz OPR3 izraz
		izraz -> izraz OPR4 izraz
		izraz -> izraz OPR5 izraz
		izraz -> LPAREN izraz RPAREN
		izraz -> LPAREN izraz RPAREN OPR2 izraz
		izraz -> LPAREN izraz RPAREN OPR3 izraz
		izraz -> LPAREN izraz RPAREN OPR4 izraz
		izraz -> LPAREN izraz RPAREN OPR5 izraz
		terminal -> IDE
		terminal -> CON
		*/
		ptr = 0;
		tokeni = listaTokena.toArray(new String[0]);
		if(linijaKoda() == true) {
			System.out.println("Ispravna sintaksa.");
			rezultat = rezultat + "Ispravna sintaksa.\n\n";
		} else {
			System.out.println("Neispravna sintaksa.");
			rezultat = rezultat + "Neispravna sintaksa.\n\n";
		}
	}

	boolean linijaKoda() {
		/*
		Grammar:
		linijaKoda -> LBRACE deklaracija RBRACE
		linijaKoda -> TYP1 IDE OPR1 izraz SEMICOLON
		linijaKoda -> TYP2 IDE OPR1 izraz SEMICOLON
		linijaKoda -> PRINT IDE SEMICOLON
	    linijaKoda -> IDE OPR1 CON SEMICOLON  // treba dodati
		*/
		tipIzraza = 0;
		if((!tokeni[ptr].equals("TYP1")) && (!tokeni[ptr].equals("TYP2")) && (!tokeni[ptr].equals("PRINT")) && (!tokeni[ptr].contains("IDE"))) {
			if(!tokeni[ptr].equals("LBRACE")) {
				return false;
			} else {
				ptr++;
				if(deklaracija() == false) {
					return false;
				}
				if(!tokeni[ptr++].equals("RBRACE")) {
					return false;
				}
				return true;
			}
		}
		if( (tokeni[ptr].equals("PRINT"))) {
			ptr++;
			if(!tokeni[ptr++].contains("IDE")) {
				return false;
			}

			try {
				if(!tokeni[ptr++].equals("SEMICOLON")) {
					return false;
				}
			} catch(ArrayIndexOutOfBoundsException e) {
				System.out.println("Nedostaje tacka i zarez.");
				rezultat = rezultat + "Nedostaje tacka i zarez.\n";
				return false;
			}
			return true;
		}
////////////////  float x = 5.5;  ili int x = 5;
        if( tokeni[ptr].equals("TYP1") | tokeni[ptr].equals("TYP2")){
			int ptr1 = 0;
			ptr++;
			if(!tokeni[ptr++].contains("IDE")) {
				return false;
			}
			if(!tokeni[ptr++].equals("OPR1")) {
				return false;
			}
			if(izraz() == false) {
				return false;
			}
			try {
				if(!tokeni[ptr++].equals("SEMICOLON")) {
					return false;
				}
			} catch(ArrayIndexOutOfBoundsException e) {
				System.out.println("Nedostaje tacka i zarez.");
				rezultat = rezultat + "Nedostaje tacka i zarez.\n";
				return false;
			}
/////
			if (tokeni[ptr1].equals("TYP1")){
				//System.out.println("XXXXXXINTXXXX");
				String leksema = promenljiveMap.get(Integer.parseInt(tokeni[ptr1+1].substring(tokeni[ptr1+1].length()-1, tokeni[ptr1+1].length())));
				TabelaSimbolaStavka stavka = new TabelaSimbolaStavka(leksema, tokeni[ptr1+1], "int");
				tabelaSimbola.add(stavka);
			} else if (tokeni[ptr1].equals("TYP2")){
				//System.out.println("XXXXXFLOATXXXX");
				String leksema = promenljiveMap.get(Integer.parseInt(tokeni[ptr1+1].substring(tokeni[ptr1+1].length()-1, tokeni[ptr1+1].length())));
				TabelaSimbolaStavka stavka = new TabelaSimbolaStavka(leksema, tokeni[ptr1+1], "float");
				//System.out.println("AAA postavljena " + leksema + "  " + tokeni[ptr1]);
				tabelaSimbola.add(stavka);
			}
/////
            return true;
		}
////////////////  x = 5 ili x = 5.5
        if(tokeni[ptr].contains("IDE")){
			int ptr1 = ptr;
			ptr++;
			if(!tokeni[ptr++].equals("OPR1")) {
				return false;
			}

			if(izraz() == false) {
				return false;
			}
			try {
				if(!tokeni[ptr++].equals("SEMICOLON")) {
					return false;
				}
			} catch(ArrayIndexOutOfBoundsException e) {
				System.out.println("Nedostaje tacka i zarez.");
				rezultat = rezultat + "Nedostaje tacka i zarez.\n";
				return false;
			}

            return true;
		}

         return true;

	}

	boolean deklaracija() {
		/*
		Grammar:
		deklaracija -> TYP1 identifikator
		deklaracija -> TYP2 identifikator
		*/

		if((!tokeni[ptr].equals("TYP1")) && (!tokeni[ptr].equals("TYP2"))) {
		//if((!tokeni[ptr].equals("TYP1")) && (!tokeni[ptr].equals("TYP2")) && (!tokeni[ptr].equals("PRINT"))) {
			return false;
		}
		ptr++;
		if(identifikator(Integer.parseInt(tokeni[ptr-1].substring(tokeni[ptr].length()-1, tokeni[ptr].length()))) == false) {
			return false;
		}
		return true;
	}

	boolean identifikator(int type) {
		/*
		Grammar:
		identifikator -> IDE
		identifikator -> IDE COMMA identifikator
		*/
		if(tokeni[ptr].contains("IDE")) {
			if(type == 1) {
				intPromenljive.add(promenljiveMap.get(Integer.parseInt(tokeni[ptr].substring(tokeni[ptr].length()-1, tokeni[ptr].length()))));
				String leksema = promenljiveMap.get(Integer.parseInt(tokeni[ptr].substring(tokeni[ptr].length()-1, tokeni[ptr].length())));
				TabelaSimbolaStavka stavka = new TabelaSimbolaStavka(leksema, tokeni[ptr], "int");
				tabelaSimbola.add(stavka);
			} else {
				floatPromenljive.add(promenljiveMap.get(Integer.parseInt(tokeni[ptr].substring(tokeni[ptr].length()-1, tokeni[ptr].length()))));
				String leksema = promenljiveMap.get(Integer.parseInt(tokeni[ptr].substring(tokeni[ptr].length()-1, tokeni[ptr].length())));
				TabelaSimbolaStavka stavka = new TabelaSimbolaStavka(leksema, tokeni[ptr], "float");
				tabelaSimbola.add(stavka);
			}
			ptr++;
			int fallback = ptr;
			if(tokeni[ptr++].equals("COMMA")) {
				if(identifikator(type) == true) {
					return true;
				}
			} else {
				ptr = fallback;
			}
			return true;
		}
		return false;
	}

	boolean izraz() {
		/*
		Grammar:
		izraz -> terminal
		izraz -> terminal OPR1 izraz
		izraz -> terminal OPR2 izraz
		izraz -> terminal OPR3 izraz
		izraz -> terminal OPR4 izraz
		izraz -> terminal OPR5 izraz
		izraz -> LPAREN izraz RPAREN
		izraz -> LPAREN izraz RPAREN OPR2 izraz
		izraz -> LPAREN izraz RPAREN OPR3 izraz
		izraz -> LPAREN izraz RPAREN OPR4 izraz
		izraz -> LPAREN izraz RPAREN OPR5 izraz
		*/
		int fallback = ptr;
		if(terminal()) {
//System.out.println("tokeni[ptr] = " +tokeni[ptr]);
			if(aritmetickiOperator()) {
				if(izraz()) {
					return true;
				}
			}
///////////////////////////////////
			if(operatorJednako()) {
				if(izraz()) {
					return true;
				}
			}

			return true;
		} else {
			if(tokeni[ptr++].equals("LPAREN")) {
				if(izraz()) {
					if(tokeni[ptr++].equals("RPAREN")) {
						if(aritmetickiOperator()) {
							if(izraz()) {
								return true;
							}
						}
						return true;
					}
				}
			} else {
				ptr = fallback;
			}
		}
		return false;
	}

	boolean terminal() {
		/*
		Grammar:
		terminal -> IDE
		terminal -> CON1
		terminal -> CON2
		*/
		if(tokeni[ptr].contains("IDE")) {
			String variable = promenljiveMap.get(Integer.parseInt(tokeni[ptr].substring(tokeni[ptr].length()-1, tokeni[ptr].length())));
			if((intPromenljive.contains(variable)) && (tipIzraza <= 1)) {
				tipIzraza = 1;

			} else {
				if(tipIzraza == 1) {
					System.out.println("Konverzija tipa na float--.");
					rezultat = rezultat + "Konverzija tipa na float.\n";
				}
				tipIzraza = 2;
			}
			ptr++;
			return true;
		} else if(tokeni[ptr].equals("CON1")) {
			ptr++;
			if(tipIzraza < 1) {
				tipIzraza = 1;
			}
			return true;
		} else if(tokeni[ptr].equals("CON2")) {
			ptr++;
			if(tipIzraza == 1) {
				System.out.println("Konverzija tipa na float---.");
				rezultat = rezultat + "Konverzija tipa na float.\n";
			}
			tipIzraza = 2;
			return true;
		}
		return false;
	}

	boolean aritmetickiOperator() {
		if(tokeni[ptr].equals("OPR2")) {
			ptr++;
			return true;
		}
		if(tokeni[ptr].equals("OPR3")) {
			ptr++;
			return true;
		}
		if(tokeni[ptr].equals("OPR4")) {
			ptr++;
			return true;
		}
		if(tokeni[ptr].equals("OPR5")) {
			ptr++;
			return true;
		}
		return false;
	}
	boolean operatorJednako() {
		if(tokeni[ptr].equals("OPR1")) {
			ptr++;
			return true;
		}
		return false;
	}


	void semantickaAnaliza() {
		if(tokeni[0].contains("TYP") && !tokeni[0].equals("PRINT")) {
			int type = Integer.parseInt(tokeni[0].substring(tokeni[0].length()-1, tokeni[0].length()));
			String s1, s2;
			if(type == 1) {
				s1 = "int";
			} else {
				s1 = "float";
			}
			if(tipIzraza == 1) {
				s2 = "int";
			} else {
				s2 = "float";
			}
			System.out.println("Pokusaj dodele " + s2 + " na " + s1 + ".");
			rezultat = rezultat + "Pokusaj dodele " + s2 + " na " + s1 + ".\n";
			if(type == tipIzraza) {
				System.out.println("Semanticki ispravno.");
				rezultat = rezultat + "Semanticki ispravno.\n\n";
			} else if(type < tipIzraza) {
				System.out.println("Semanticki neispravno.");
				rezultat = rezultat + "Semanticki neispravno.\n\n";
			} else {
				System.out.println("Svodjenje tipa " + s2 + " na " + s1 + ".");
				rezultat = rezultat + "Svodjenje tipa " + s2 + " na " + s1 + ".\n\n";
			}
		} else  if (tokeni[0].contains("IDE")) {
			//System.out.println();
			//treba odrediti tipove

//System.out.println("ulaz: " + ulaz);
//System.out.println("listaTokena: " +listaTokena);

			StringTokenizer st = new StringTokenizer(ulaz, " =+-*/(){},;", true);
			String s = new String();
			String a="";
			while(st.hasMoreTokens()) {
				s = st.nextToken();
				if(s.equals(" ")) {
					continue;
				}
				if(Pattern.matches("[A-Za-z][A-Za-z0-9_]", s)) {  //[a-zA-Z]+
					a = s;
				}
			}
			// Ispitati tip od a
            int type=0;
			String s1 = "";
			String s2 = "";
			if(intPromenljive.contains("a")){
				type = 1;
				s1 = "int";
			}
			if(floatPromenljive.contains("a")){
				type = 2;
				s1 = "float";
			}
			if(tipIzraza == 1) {
				s2 = "int";
			} else {
				s2 = "float";
			}
			System.out.println("Pokusaj dodele " + s2 + " to " + s1 + ".");
			rezultat = rezultat + "Pokusaj dodele " + s2 + " to " + s1 + ".\n";
			if(type == tipIzraza) {
				System.out.println("Semanticki ispravno.");
				rezultat = rezultat + "Semanticki ispravno.\n\n";
			} else if(type < tipIzraza) {
				System.out.println("Semanticki neispravno.");
				rezultat = rezultat + "Semanticki neispravno.\n\n";
			} else {
				System.out.println("Svodjenje tipa " + s2 + " to " + s1 + ".");
				rezultat = rezultat + "Svodjenje tipa " + s2 + " to " + s1 + ".\n\n";
			}
/////////////********************
		}
	}

	public String tabelaSimbola(){

    	//for(Map.Entry m:promenljiveMap.entrySet()){
		//   System.out.println(m.getKey()+" "+m.getValue());
        //}

        String s = "";
        s = s + "Tabela simbola" +"\n";
        s = s + "-----------------" +"\n";
        for(TabelaSimbolaStavka ts:tabelaSimbola){
			System.out.println(ts);
			s = s + ts +"\n";
		}
		return s;
    }

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