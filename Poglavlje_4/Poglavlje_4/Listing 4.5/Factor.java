class Factor{
	String br;
	Factor(Integer n){
		Expr.redBr++;
		br = "p"+Expr.redBr;
		System.out.println(br+" mkleaf(num, "+n+")");
	}
	Factor(String id){
		Expr.redBr++;
		br = "p"+Expr.redBr;
		System.out.println(br+" mkleaf(id, entry-"+id+")");
	}
}