import java.io.*;
import java.util.*;
class TroadresniKod{
	Token[] niz;
	int indeks;
	String kod;

	TroadresniKod(String ulaz){
		Skener s = new Skener(ulaz);
		List<Token> listaTokena = s.vratiListu();
		niz = new Token[listaTokena.size()];
		niz = listaTokena.toArray(niz);
		indeks = 1;
		kod="";
	}

    void generatorKoda(){
		int lp = 0;
		int p = 0;
		for(int i = 0; i < niz.length; i++){
			if(niz[i].tip==Token.LPAREN){
				lp++;
				p = i;// cuva se pozicija otv. zagrade
			}
			if(niz[i]!=null && niz[i].tip==Token.RPAREN){
				lp--;
				if(lp == 0){// (...)
					niz[p]=null;// prva zagrada se brise
					niz[i]=null;// druga zagrada se brise
					troadresniKod(p+1,i);
				}
			}
		}
		if(imaZagrada()){
			generatorKoda();
		} else{
			troadresniKod(0, niz.length);
			kod = kod.replaceAll("t_"+(indeks-1), niz[0].leksema);
		}
	}

	void troadresniKod(int poc, int kraj){
        for(int i = poc; i < kraj; i++){
			if(niz[i].tip==Token.OPERATOR){
				if(niz[i].leksema.equals("*") || niz[i].leksema.equals("/")){
					//System.out.println("  t_"+indeks+" = "+ niz[i-1].leksema +niz[i].leksema+niz[i+1].leksema);
					kod = kod+"t_"+indeks+" = "+ niz[i-1].leksema +niz[i].leksema+niz[i+1].leksema+"\n";
					//System.out.println("   Izlazu se dodaje: " +"t_"+indeks+" = "+ niz[i-1].leksema +niz[i].leksema+niz[i+1].leksema);
					niz[i-1] = null;
					niz[i] = null;
					niz[i+1] = new Token(Token.IDENTIFIER, "t_"+indeks);
					indeks++;
				}
			}
		}
		List<Token> pom1 = new LinkedList<Token>();
		for(int i = 0; i < niz.length; i++){
			if(niz[i] != null){
				pom1.add(niz[i]);
			}
		}
		Token[] nizP = new Token[pom1.size()];
        nizP = pom1.toArray(nizP);
        int null_broj = niz.length - nizP.length;
        int kraj1 = kraj-null_broj;

        for(int i = poc; i < kraj1 ; i++){
			if(nizP[i].tip==Token.OPERATOR){
				if(nizP[i].leksema.equals("+") || nizP[i].leksema.equals("-")){
					//System.out.println("  t_"+indeks+" = "+ nizP[i-1].leksema +nizP[i].leksema+nizP[i+1].leksema);
					kod = kod+"t_"+indeks+" = "+ nizP[i-1].leksema +nizP[i].leksema+nizP[i+1].leksema+"\n";
					//System.out.println("     Izlazu se dodaje:" + "t_"+indeks+" = "+ nizP[i-1].leksema +nizP[i].leksema+nizP[i+1].leksema);
					nizP[i-1] = null;
					nizP[i] = null;
					nizP[i+1] = new Token(Token.IDENTIFIER, "t_"+indeks);
					indeks++;
				}
			}
		}

		List<Token> pom2 = new LinkedList<Token>();
		for(int i = 0; i < nizP.length; i++){
			if(nizP[i] != null){
				pom2.add(nizP[i]);
			}
		}
		Token[] nizP1 = new Token[pom2.size()];
        nizP1 = pom2.toArray(nizP1);
        niz = nizP1;
        return;
	}
    boolean imaZagrada(){
		for(int i=0; i<niz.length;i++){
			if(niz[i].tip==Token.LPAREN)
				return true;
		}
		return false;
	}
	String vratiKod(){
		return kod;
	}
}