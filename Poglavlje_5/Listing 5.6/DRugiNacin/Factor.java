class Factor{
	int val;
	String ime;
	Factor(Integer n){
		val = n;
		ime = String.valueOf(n);
	}
	Factor(Expr e){
		val = e.val;
		ime = e.ime;
	}
	Factor(String id, int val){
		this.val = val;
		ime = id;
	}
}