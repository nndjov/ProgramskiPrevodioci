class Expr{
	int val;
	String ime;
	String kod="";
	static int brojac=1;
	Expr(Term t){
		val = t.val;
		ime = t.ime;
		kod = t.kod;
	}
	Expr(Expr e, Term t){
		ime="t_"+brojac++;
		val = e.val+t.val;
		kod = e.kod+t.kod+ime+"="+e.ime+"+"+t.ime+"\n";
	}
}