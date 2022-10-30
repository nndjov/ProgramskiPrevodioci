/*
	Gramatika:
	E -> x + T
	T -> (E)
	T -> x
*/
import java.util.*;
class RekurzivniSpust {
	private int pok;
	private char[] ulaz;

	RekurzivniSpust(int pok, char[] ulaz){
		this.pok = pok;
		this.ulaz = ulaz;
	}

	char tekuci(){
		char s = ulaz[pok];
		return s;
	}
	public void parsiranje(){
		if((E()) & (pok == ulaz.length)) {
			System.out.println("String se prihvata!");
		} else {
			System.out.println("String se ne prihvata!");
		}
	}


	public boolean E() {
		// Provera  da li je ulaz 'x + T'
		if(tekuci() != 'x') {
			return false;
		}else{
			pok++;
		}
		if(tekuci() != '+') {
			return false;
		}else{
			pok++;
		}
		if(T() == false) {
			return false;
		}
		return true;
	}


	public boolean T() {
		// Provera da li je ulaz 'x' ili '(E)'
		if(tekuci() == 'x') {
			pok++;
			return true;
		}
		else {
			if(tekuci() != '(') {
				return false;
			}else{
				pok++;
			}
			if(E() == false) {
				return false;
			}
			if(tekuci() != ')') {
				return false;
			}else{
				pok++;
			}
			return true;
		}
	}
}
