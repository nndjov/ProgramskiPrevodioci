class Expr{
	int val;
	String ime;
	static String kod="";
	static int brojac=1;
	Expr(Term t){
		val = t.val;
		ime = t.ime;
	}
	Expr(Expr e, Term t){
		ime="t_"+brojac++;
		val = e.val+t.val;
		String instrukcija = ime+"="+e.ime+"+"+t.ime;
		kod = kod + instrukcija +"\n";
	}
}