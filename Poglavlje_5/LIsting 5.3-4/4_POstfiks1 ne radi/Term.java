class Term{
	Term(Term t, char c, Factor f){
		if(c == '*'){
			Izraz.izraz = Izraz.izraz + " * ";
		}
		if(c == '/'){
			Izraz.izraz = Izraz.izraz + " / ";
		}
	}
}