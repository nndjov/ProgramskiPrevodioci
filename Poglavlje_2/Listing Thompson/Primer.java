class Primer{

    public static void main(String[] args){
		RegularanIzraz regIzraz = new RegularanIzraz("(a|b)*c");
		System.out.println("Regularni izaraz: " + regIzraz.vrati());

		NKA nka = new NKA();
        NKA nka1 = nka.tompson(regIzraz);
        System.out.println("NKA:");
        System.out.println(nka1);
        System.out.println("Konacno stanje = " + nka1.vratiKonacnoStanje());

        String stanja[][] = nka1.vratiStanja();
        for(String[] s: stanja){
			System.out.println(s[0] + " --- "+ s[1]+" --- "+s[2]);
		}
    }
}