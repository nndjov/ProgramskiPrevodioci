class Expr_part {
	int val;
	String ime;
	String kod="";
	Expr_part(Expr e){
		val = e.val;
		ime = e.ime;
		kod = e.kod;
		System.out.println(kod);
		System.out.println(" = " +val);
	}
}