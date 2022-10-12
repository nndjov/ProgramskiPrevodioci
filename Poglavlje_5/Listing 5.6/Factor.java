class Factor{
	int val;
	String ime;
	String kod="";
	Factor(Expr e){
		val = e.val;
		ime = e.ime;
		kod = e.kod;
	}
	Factor(Integer n){
		val = n;
		ime = String.valueOf(n);
		kod="";
	}
	Factor(String id, int val){
		this.val = val;
		ime = id;
		kod="";
	}
}