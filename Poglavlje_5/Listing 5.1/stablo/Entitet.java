import java.io.*;
class Entitet{

    protected String tip;
    protected Svojstvo svojstva;

    protected Entitet sledeciTip;
    protected Entitet prethodniTip;
    protected Entitet sledeciEntitet;
    protected Entitet prethodniEntitet;

    Entitet(){
		svojstva = new Svojstvo();

	}

    Entitet(String tip){
		this.tip = tip;
		svojstva = new Svojstvo();
	}

	public void dodajSvojstvo(String ime, String vrednost){
	    	Svojstvo tekuci = svojstva;

		    while (true) {
		        if (tekuci.sledeci == null){
			       Svojstvo novi = new Svojstvo(ime, vrednost);
			       tekuci.sledeci = novi;
			       break;
		        }
		        tekuci = tekuci.sledeci;
		    }
    }

	public Svojstvo nadjiSvojstvo(String ime){
		Svojstvo tekuci = svojstva;

		while (tekuci != null) {
			if (tekuci.ime.equals(ime)){
				return tekuci;

			}
			tekuci = tekuci.sledeci;
		}
		return null;
	}

	public void promeniVrednostSvojstvu(String ime, String vrednost){
		Svojstvo tekuci = svojstva;
		while (tekuci != null) {
			if (tekuci.ime.equals(ime)){
				tekuci.vrednost = vrednost;
				break;
			}
			tekuci = tekuci.sledeci;
		}
	}

	public String nadjiVrednostSvojstva(String ime){
		Svojstvo tekuci = svojstva;
		while (tekuci != null) {
			if (tekuci.ime.equals(ime)){
				return tekuci.vrednost;

			}
			tekuci = tekuci.sledeci;
		}
		return null;
	}

    public void upisiXML(PrintWriter ulaz){
       ulaz.println("<ENTITET>");
       ulaz.println("<tip>");
       ulaz.println(tip);
       ulaz.println("</tip>");

       Svojstvo t = svojstva.sledeci;
       ulaz.println("<SVOJSTVA>");
       while (t != null){
           ulaz.println("<"+ t.ime + ">");
           ulaz.println(t.vrednost );
           ulaz.println("</" + t.ime + ">");
           t=t.sledeci;
	   }
	   ulaz.println("</SVOJSTVA>");

       if (prethodniTip!=null){
           ulaz.println("<PRETHODNITIP>");
           ulaz.println("<tip>");
           ulaz.println(prethodniTip.tip);
           ulaz.println("</tip>");
           ulaz.println("<ime>");
           ulaz.println(prethodniTip.nadjiVrednostSvojstva("ime"));
           ulaz.println("</ime>");
           ulaz.println("</PRETHODNITIP>");

       }

       if (sledeciTip!=null){
           ulaz.println("<SLEDECITIP>");
           ulaz.println("<tip>");
           ulaz.println(sledeciTip.tip);
           ulaz.println("</tip>");
           ulaz.println("<ime>");
           ulaz.println(sledeciTip.nadjiVrednostSvojstva("ime"));
           ulaz.println("</ime>");
           ulaz.println("</SLEDECITIP>");
       }
       ulaz.println("</ENTITET>");
	}
}