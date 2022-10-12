class Term{
	int val;
	String ime;
	String kod="";
	Term(Factor f){
		val = f.val;
		ime = f.ime;
		kod = f.kod;
	}
	Term(Term t, Factor f){
		ime="t_"+Expr.brojac++;
		val = t.val*f.val;
		kod = t.kod+f.kod+ime+"="+t.ime+"*"+f.ime+"\n";
	}
}