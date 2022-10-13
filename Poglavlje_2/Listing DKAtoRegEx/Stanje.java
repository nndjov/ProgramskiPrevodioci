import java.util.HashMap;
import java.util.Map;

public class Stanje {
    String oznaka;
    Boolean pocetnoStanje;
    Boolean zavrsnoStanje;
    Map<String, Prelaz> ulazPrelaz;
    Map<String, Prelaz> izlazPrelaz;
    Prelaz povratniPrelaz;

    Stanje(String oznaka, Boolean zavrsnoStanje, Boolean pocetnoStanje) {
        this.oznaka = oznaka;
        this.zavrsnoStanje = zavrsnoStanje;
        this.pocetnoStanje = pocetnoStanje;
        ulazPrelaz = new HashMap<>();
        izlazPrelaz = new HashMap<>();
    }

    public void dodajPovratniPrelaz(String vrednost) {
        if (povratniPrelaz == null) {
            this.povratniPrelaz = new Prelaz(oznaka, oznaka, vrednost);
        } else {
            this.povratniPrelaz.vrednost = "(" + this.povratniPrelaz.vrednost + "|" + vrednost + ")";
            //this.povratniPrelaz.vrednost = "(" + this.povratniPrelaz.vrednost + "+" + vrednost + ")";
        }
    }

    public void dodajUlazniPrelaz(String od, String vrednost) {
        if (!ulazPrelaz.containsKey(od)) {
            this.ulazPrelaz.put(od, new Prelaz(od, oznaka, vrednost));
        } else {
            //this.ulazPrelaz.get(from).vrednost = "(" + this.ulazPrelaz.get(from).vrednost + "+" + vrednost + ")";
            this.ulazPrelaz.get(od).vrednost = "(" + this.ulazPrelaz.get(od).vrednost + "|" + vrednost + ")";
        }
    }

    public void dodajIzlazniPrelaz(String prema, String vrednost) {
        if (!izlazPrelaz.containsKey(prema)) {
            this.izlazPrelaz.put(prema, new Prelaz(oznaka, prema, vrednost));
        } else {
            //this.izlazPrelaz.get(to).vrednost  = "(" + vrednost + "+" + this.izlazPrelaz.get(to).vrednost + ")";
            this.izlazPrelaz.get(prema).vrednost  = "(" + vrednost + "|" + this.izlazPrelaz.get(prema).vrednost + ")";
        }
    }
    public void ukloniUlazniPrelaz(String dest){
        this.ulazPrelaz.remove(dest);
    }
    public void ukloniIzlazniPrelaz(String dest){
        this.izlazPrelaz.remove(dest);
    }
}