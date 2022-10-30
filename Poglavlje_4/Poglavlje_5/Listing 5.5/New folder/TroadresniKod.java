import java.io.*;
import java.util.*;
class TroadresniKod{
	List<Token> listaTokena;
	Token[] niz;
	int indeks = 1;
	String izlaz="";
	TroadresniKod(File file){
		try{
			BufferedReader br = new BufferedReader(new FileReader(file));
			Skener s = new Skener(br);
			listaTokena = s.vratiListu();
			niz = new Token[listaTokena.size()];
			niz = listaTokena.toArray(niz);
		}catch (FileNotFoundException e) {}
	}
	TroadresniKod(String ulaz){
		Skener s = new Skener(ulaz);
		listaTokena = s.vratiListu();
		niz = new Token[listaTokena.size()];
		niz = listaTokena.toArray(niz);
	}

    void eliminacijazagrada(){
        boolean tacno = true;
        while(tacno){
			int lp = 0;
			int p = 0;
			boolean imaZagrada = false;
			for(int i = 0; i < niz.length; i++){
				if(niz[i].kind==Token.LPAREN){
					imaZagrada = true;
					lp++;
					p = i;// cuva se pozicija otv. zagrade
				}
				if(niz[i]!=null && niz[i].kind==Token.RPAREN){
					imaZagrada = true;
					lp--;
					if(lp == 0){
						niz[p]=null;// prva zagrada se brise
						troadresniKod(p+1,i);
						break;
					}
				}
				if( i == niz.length-1 && imaZagrada == false){
					troadresniKod(0, niz.length);
				    String iz = izlaz.replaceAll("t_"+(indeks-1), niz[0].c);
				    System.out.println(iz);
					tacno = false;
				}
			}
		}
	}

	void troadresniKod(int poc, int kraj){
        for(int i = poc; i < kraj; i++){
			if(niz[i].kind==Token.OPERATOR){
				if(niz[i].c.equals("*") || niz[i].c.equals("/")){
					izlaz = izlaz+"t_"+indeks+" = "+ niz[i-1].c +niz[i].c+niz[i+1].c+"\n";
					//System.out.println("t_"+indeks+" = "+ niz[i-1].c +niz[i].c+niz[i+1].c);
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
        int poz = poc;
        int kraj1 = nizP.length-1;
        for(int i = poc; i<nizP.length; i++){
			if(nizP[i].kind==Token.RPAREN){
				kraj1 = i;
				break;
			}
		}
        for(int i = poc; i < kraj1 ; i++){
			if(nizP[i].kind==Token.OPERATOR){
				if(nizP[i].c.equals("+") || nizP[i].c.equals("-")){
					izlaz = izlaz+"t_"+indeks+" = "+ nizP[i-1].c +nizP[i].c+nizP[i+1].c+"\n";
					//System.out.println("t_"+indeks+" = "+ nizP[i-1].c +nizP[i].c+nizP[i+1].c);
					nizP[i-1] = null;
					nizP[i] = null;
					nizP[i+1] = new Token(Token.IDENTIFIER, "t_"+indeks);
					indeks++;
				}
			}
		}
		if(nizP[kraj1].kind==Token.RPAREN)
		    nizP[kraj1]=null;
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
}