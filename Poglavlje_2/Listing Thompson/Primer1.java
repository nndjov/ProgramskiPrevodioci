class Primer1{

    public static void main(String[] args){
		RegularanIzraz regIzraz = new RegularanIzraz("(a|b)*c");
		System.out.println("Regularni izaraz: " + regIzraz.vrati());

        NKA nka = new NKA(regIzraz);
        System.out.println("NKA:");
        System.out.println(nka);
        System.out.println("Konacno stanje = " + nka.vratiKonacnoStanje());
    }
}