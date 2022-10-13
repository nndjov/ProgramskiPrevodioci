import java.util.*;

public class NKA{
	private ArrayList <Integer> stanja;
	private ArrayList <Prelaz> prelazi;
	private int konacnoStanje;

	public NKA(){
		this.stanja = new ArrayList <Integer> ();
		this.prelazi = new ArrayList <Prelaz> ();
		this.konacnoStanje = 0;
	}

	public NKA(int br){
		this.stanja = new ArrayList <Integer> ();
		this.prelazi = new ArrayList <Prelaz> ();
		this.konacnoStanje = 0;
		this.postaviStanja(br);
	}

	public NKA(char simbolPrelaza){
		this.stanja = new ArrayList<Integer> ();
		this.prelazi = new ArrayList <Prelaz> ();
		this.postaviStanja(2);
		this.konacnoStanje = 1;
		this.prelazi.add(new Prelaz(0, 1, simbolPrelaza));
	}

	public NKA(RegularanIzraz reg){
		NKA n = tompson(reg);
		this.stanja = n.stanja;
		this.prelazi = n.prelazi;
		this.konacnoStanje = n.konacnoStanje;
	}

	public ArrayList <Integer> stanja(){
		return stanja;
	}
	public void dodajStanje(Integer s){
		stanja.add(s);
	}
	public ArrayList <Prelaz> prelazi(){
		return prelazi;
	}

	public void dodajPrelaz(Prelaz p){
		prelazi.add(p);
	}

	public void postaviStanja(int broj){
		for (int i = 0; i < broj; i++)
			this.stanja.add(i);
	}
	public void postaviKonacnoStanje(int konacnoStanje){
		this.konacnoStanje = konacnoStanje;
	}

	public int vratiKonacnoStanje(){
		return konacnoStanje;
	}

	public String toString(){
		String s="";
		for (Prelaz t: prelazi){
			s = s+"("+ t.stanjeOd() +", "+ t.simbolTranzicije() +", "+ t.stanjeDo() +")" + "\n";
		}
		return s;
	}

	public String[][] vratiStanja(){
		String[][] stanja = new String[prelazi.size()][3];
		int i = 0;
		for (Prelaz t: prelazi){
			stanja[i][0] = ""+t.stanjeOd();
			stanja[i][1] = ""+t.stanjeDo();
			stanja[i][2] = ""+t.simbolTranzicije();
			i++;
		}
		return stanja;
	}

    public NKA zvezda(NKA n){
        NKA rezultat = new NKA(n.stanja().size()+2);
        rezultat.prelazi.add(new Prelaz(0, 1, 'E'));
        for (Prelaz t: n.prelazi()){
            rezultat.prelazi.add(new Prelaz(t.stanjeOd() + 1,t.stanjeDo() + 1, t.simbolTranzicije()));
        }
        rezultat.prelazi.add(new Prelaz(n.stanja.size(), n.stanja.size() + 1, 'E'));
        rezultat.prelazi.add(new Prelaz(n.stanja.size(), 1, 'E'));
        rezultat.prelazi.add(new Prelaz(0, n.stanja.size() + 1, 'E'));
        rezultat.konacnoStanje = n.stanja.size() + 1;
        return rezultat;
    }

    public NKA nadovezivanje(NKA n, NKA m){
        m.stanja.remove(0); //
        for (Prelaz t: m.prelazi){
            n.prelazi.add(new Prelaz(t.stanjeOd() + n.stanja.size()-1,t.stanjeDo() + n.stanja.size() - 1, t.simbolTranzicije()));
        }
        for (Integer s: m.stanja){
            n.stanja.add(s + n.stanja.size() + 1);
        }
        n.konacnoStanje = n.stanja.size() + m.stanja.size() - 2;
        return n;
    }

    public NKA unija(NKA n, NKA m){
        NKA rezultat = new NKA(n.stanja.size() + m.stanja.size() + 2);
        rezultat.prelazi.add(new Prelaz(0, 1, 'E'));
        for (Prelaz t: n.prelazi){
            rezultat.prelazi.add(new Prelaz(t.stanjeOd() + 1,t.stanjeDo() + 1, t.simbolTranzicije()));
        }
        rezultat.prelazi.add(new Prelaz(n.stanja.size(),n.stanja.size() + m.stanja.size() + 1, 'E'));
        rezultat.prelazi.add(new Prelaz(0, n.stanja.size() + 1, 'E'));
        for (Prelaz t: m.prelazi){
            rezultat.prelazi.add(new Prelaz(t.stanjeOd() + n.stanja.size()+ 1, t.stanjeDo() + n.stanja.size() + 1, t.simbolTranzicije()));
        }
        rezultat.prelazi.add(new Prelaz(m.stanja.size() + n.stanja.size(),n.stanja.size() + m.stanja.size() + 1, 'E'));
        rezultat.konacnoStanje = n.stanja.size() + m.stanja.size() + 1;
        return rezultat;
    }

    public NKA tompson(RegularanIzraz regIzraz){
        if (!regIzraz.validanIzraz()){
            System.out.println("Pogresan regularan izraz.");
            return new NKA();
        }

        String regularniIzraz = regIzraz.vrati();

        Stack <Character> operatori = new Stack <Character> ();
        Stack <NKA> operandi = new Stack <NKA> ();
        Stack <NKA> stek = new Stack <NKA> ();
        boolean ccflag = false;
        char op, c;
        int brojacZagrada = 0;
        NKA nfa1, nfa2;
        for (int i = 0; i < regularniIzraz.length(); i++){
            c = regularniIzraz.charAt(i);
            if (regIzraz.azbuka(c)){
                operandi.push(new NKA(c));
                if (ccflag){
                    operatori.push('.');// operator za nadovezivanje
                }else{
					ccflag = true;
				}
            }else{
                if (c == ')'){
                    ccflag = false;
                    if (brojacZagrada == 0){
                        System.out.println("Greska: Neuparene zagrade!!!");
                        System.exit(1);
                    }else{
						brojacZagrada--;
					}
                    while (!operatori.empty() && operatori.peek() != '('){
                        op = operatori.pop();
                        if (op == '.'){
                            nfa2 = operandi.pop();
                            nfa1 = operandi.pop();
                            operandi.push(nadovezivanje(nfa1, nfa2));
                        }
                        else if (op == '|'){
                            nfa2 = operandi.pop();
                            if(!operatori.empty() && operatori.peek() == '.'){
                                stek.push(operandi.pop());
                                while (!operatori.empty() && operatori.peek() == '.'){
                                    stek.push(operandi.pop());
                                    operatori.pop();
                                }
                                nfa1 = nadovezivanje(stek.pop(), stek.pop());
                                while (stek.size() > 0){
                                   nfa1 =  nadovezivanje(nfa1, stek.pop());
                                }
                            }else{
                                nfa1 = operandi.pop();
                            }
                            operandi.push(unija(nfa1, nfa2));
                        }
                    }
                }else if (c == '*'){
                    operandi.push(zvezda(operandi.pop()));
                    ccflag = true;
                }else if (c == '('){
                    operatori.push(c);
                    brojacZagrada++;
                }else if (c == '|'){
                    operatori.push(c);
                    ccflag = false;
                }
            }
        }
        while (operatori.size() > 0){
            if (operandi.empty()){
                System.out.println("Greska: neuravnotezeni operatori i operandi");
                System.exit(1);
            }
            op = operatori.pop();
            if (op == '.'){
                nfa2 = operandi.pop();
                nfa1 = operandi.pop();
                operandi.push(nadovezivanje(nfa1, nfa2));
            }else if (op == '|'){
                nfa2 = operandi.pop();
                if( !operatori.empty() && operatori.peek() == '.'){
                    stek.push(operandi.pop());
                    while (!operatori.empty() && operatori.peek() == '.'){
                        stek.push(operandi.pop());
                        operatori.pop();
                    }
                    nfa1 = nadovezivanje(stek.pop(),stek.pop());
                    while (stek.size() > 0){
                       nfa1 =  nadovezivanje(nfa1, stek.pop());
                    }
                }else{
                    nfa1 = operandi.pop();
                }
                operandi.push(unija(nfa1, nfa2));
            }
        }
        return operandi.pop();
    }
}