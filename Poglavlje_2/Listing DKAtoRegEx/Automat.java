
import java.io.*;
import java.util.*;

public class Automat {
    static Map<String, Stanje> automat = new HashMap<>();
    static String start;
    static List<String> zavrsna;
    static List<String> svaStanja;
    String prelazi[][];

	Automat(String start, List<String> zavrsna, List<String> svaStanja, String prelazi[][]){
		this.start = start;
		this.zavrsna = zavrsna;
		this.svaStanja = svaStanja;
		this.prelazi = prelazi;
		for (String str : svaStanja) {
			 automat.put(str, new Stanje(str, zavrsna.contains(str), str.equals(start)));
		}
        for(int i = 0; i < prelazi.length; i++) {
            String od_stanja = prelazi[i][0];
            String vrednost = prelazi[i][1];
            String do_stanja = prelazi[i][2];
            if (od_stanja.equals(do_stanja)) {
                automat.get(od_stanja).dodajPovratniPrelaz(vrednost);
            } else {
                automat.get(od_stanja).dodajIzlazniPrelaz(do_stanja, vrednost);
                automat.get(do_stanja).dodajUlazniPrelaz(od_stanja, vrednost);
            }
        }
	}

	public String pokreni(){
        System.out.println("Automat ima " + automat.size() + " stanja ");
        System.out.println("--------------------------------------------------");
        prikaziAutomat();
        System.out.println("--------------------------------------------------");
        System.out.println("Dodaje se novo startno stanje (Si)");
        System.out.println("Dodaje se novo zavrsno stanje (Sf)");

        // Dodaj novo zavrsno stanje
        novoStanjePrihvatanja();
        // Dodaj novo startno stanje
        novoStartnoStanje();

        while (automat.size() > 2) {
            System.out.println("Automat ima " + automat.size() + " stanja ");
            prikaziAutomat();
            ukloniStanje();
        }
        System.out.println("Automat ima 2 stanja ");
        prikaziAutomat();
        System.out.println("--------------------------------------------------");
        //System.out.println("Regulrni izraz: " + automat.get("Si").izlazPrelaz.get("Sf").vrednost);
        //System.out.println("--------------------------------------------------");

        return automat.get("Si").izlazPrelaz.get("Sf").vrednost;
	}

    // dodaj novo startno stanje
    public void novoStartnoStanje() {
        Stanje novoStartnoStanje = new Stanje("Si", false, true);
        novoStartnoStanje.dodajIzlazniPrelaz(automat.get(start).oznaka, "e");
        automat.put(novoStartnoStanje.oznaka, novoStartnoStanje);
        automat.get(start).pocetnoStanje = false;
        automat.get(start).dodajUlazniPrelaz(novoStartnoStanje.oznaka, "e");
        start = novoStartnoStanje.oznaka;
        svaStanja.add(0, novoStartnoStanje.oznaka);
    }
    // Dodaj novo stanje prihvatanja
    public void novoStanjePrihvatanja() {
        Stanje novoStPrihvatanja = new Stanje("Sf", true, false);
        for (Stanje stanje : automat.values()) {
            if (zavrsna.contains(stanje.oznaka)) {
                novoStPrihvatanja.dodajUlazniPrelaz(stanje.oznaka, "e");
                stanje.dodajIzlazniPrelaz(novoStPrihvatanja.oznaka, "e");
                stanje.zavrsnoStanje = false;
                zavrsna.remove(stanje.oznaka);
            }
        }
        automat.put(novoStPrihvatanja.oznaka, novoStPrihvatanja);
        zavrsna.add(novoStPrihvatanja.oznaka);
        svaStanja.add(novoStPrihvatanja.oznaka);
    }

    public void prikaziAutomat() {
        System.out.print("Sva stanja : ");
        for (String str : svaStanja) System.out.print(str + " ");
        System.out.println();       System.out.println("Startno stanje : " + start);
        System.out.print("Zavrsna stanja : ");
        for (String str : zavrsna) System.out.print(str + " ");
        System.out.println();

        System.out.println("Prelazi: ");
        for (Stanje stanje : automat.values()) {
            if (stanje.povratniPrelaz != null) {
                System.out.println("  " + stanje.povratniPrelaz.od_stanja + "  <" + stanje.povratniPrelaz.vrednost + ">  " + stanje.povratniPrelaz.do_stanja);
            }
            for (Prelaz tran : stanje.izlazPrelaz.values()) {
                System.out.println("  " + tran.od_stanja + "  <" + tran.vrednost + ">  " + tran.do_stanja);
            }
        }
    }
    public void ukloniStanje() {
        ukloniMrtvoStanje();
        String uklonjenoStanje = odaberiStanje();
        Stanje stanje = automat.get(uklonjenoStanje);
        ArrayList<String> ukloniUlazno = new ArrayList<>();
        ArrayList<String> ukloniIzlazno = new ArrayList<>();
        System.out.println("--------------------------------------------------");
        System.out.println("Uklanja se stanje " + stanje.oznaka );
        System.out.println("--------------------------------------------------");
        for (Prelaz prelazUl : stanje.ulazPrelaz.values()) {
            for (Prelaz prelazIz : stanje.izlazPrelaz.values()) {
                prelazUl.vrednost = prelazUl.vrednost.equals("e") ? "": prelazUl.vrednost;
                prelazIz.vrednost = prelazIz.vrednost.equals("e") ? "": prelazIz.vrednost;
                if (prelazUl.od_stanja.equals(prelazIz.do_stanja)) {
                    if (automat.get(stanje.oznaka).povratniPrelaz == null) {
                        automat.get(prelazUl.od_stanja).dodajPovratniPrelaz(prelazUl.vrednost + prelazIz.vrednost);
                    } else {
                        String povratnaPetljaVrednost = stanje.povratniPrelaz.vrednost.length() == 1 ? stanje.povratniPrelaz.vrednost : "(" + stanje.povratniPrelaz.vrednost + ")";
                        automat.get(prelazUl.od_stanja).dodajPovratniPrelaz(prelazUl.vrednost + povratnaPetljaVrednost + "*" + prelazIz.vrednost);
                    }
                } else {
                    if (automat.get(stanje.oznaka).povratniPrelaz == null) {
                        automat.get(prelazUl.od_stanja).dodajIzlazniPrelaz(prelazIz.do_stanja, prelazUl.vrednost + prelazIz.vrednost);
                        automat.get(prelazIz.do_stanja).dodajUlazniPrelaz(prelazUl.od_stanja, prelazUl.vrednost + prelazIz.vrednost);
                    } else {
                        String povratnaPetljaVrednost = stanje.povratniPrelaz.vrednost.length() == 1 ? stanje.povratniPrelaz.vrednost : "(" + stanje.povratniPrelaz.vrednost + ")";
                        automat.get(prelazUl.od_stanja).dodajIzlazniPrelaz(prelazIz.do_stanja, prelazUl.vrednost + povratnaPetljaVrednost + "*" + prelazIz.vrednost);
                        automat.get(prelazIz.do_stanja).dodajUlazniPrelaz(prelazUl.od_stanja, prelazUl.vrednost + povratnaPetljaVrednost + "*" + prelazIz.vrednost);
                    }
                }
                ukloniIzlazno.add(prelazIz.do_stanja);
            }
            ukloniUlazno.add(prelazUl.od_stanja);
        }
        svaStanja.remove(uklonjenoStanje);
        automat.remove(uklonjenoStanje);
        for (String str : ukloniIzlazno) {
            automat.get(str).ukloniUlazniPrelaz(uklonjenoStanje);
        }
        for (String str : ukloniUlazno) {
            automat.get(str).ukloniIzlazniPrelaz(uklonjenoStanje);
        }
    }

    public String odaberiStanje() {
        PriorityQueue<Map.Entry<String, Integer>> red = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        int zbir;
        for (Stanje stanje : automat.values()) {
            zbir = 0;
            if (!stanje.zavrsnoStanje && !stanje.pocetnoStanje) {
                if (stanje.povratniPrelaz != null) {
                    zbir++;
                }
                zbir += stanje.ulazPrelaz.size() + stanje.izlazPrelaz.size();
                red.offer(new AbstractMap.SimpleEntry<>(stanje.oznaka, zbir));
            }
        }
        String minStanje = null;
        for (Map.Entry<String, Integer> vrednost : red) {
            minStanje = vrednost.getKey();
        }
        return minStanje;
    }

    public void ukloniMrtvoStanje() {
        boolean uklonjeno = true;
        while (uklonjeno) {
            String oznaka = "";
            uklonjeno = false;
            for (Stanje stanje : automat.values()) {
                if (stanje.izlazPrelaz.size() == 0 && !stanje.zavrsnoStanje) {
                    for (Prelaz trans : stanje.ulazPrelaz.values()) {
                        automat.get(trans.od_stanja).izlazPrelaz.remove(trans.do_stanja);
                        uklonjeno = true;
                    }
                    oznaka = stanje.oznaka;
                    System.out.println("-------------------------------------------");
                    System.out.println("Mrtvo Stanje " + oznaka + " je mrtvo.");
                    System.out.println("-------------------------------------------");
                }
            }
            automat.remove(oznaka);
            svaStanja.remove(oznaka);
        }
    }
}