class Term{
	int val;
	String ime;
	Term(Factor f){
		val = f.val;
		ime = f.ime;
	}
	Term(Term t, Factor f){
		ime="t_"+Expr.brojac++;
		val = t.val*f.val;
		Expr.kod = Expr.kod + ime+"="+t.ime+"*"+f.ime+"\n";
	}
}