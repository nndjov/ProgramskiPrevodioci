class Term{
	String br;
	Term(Factor f){
		br = f.br;
	}
	Term(Expr e){
		br = e.br;
	}
	Term(Term t, Factor f){
		Expr.redBr++;
		br = "p"+Expr.redBr;
		System.out.println(br+" mknode(*, "+t.br+", " + f.br+")");
	}
}