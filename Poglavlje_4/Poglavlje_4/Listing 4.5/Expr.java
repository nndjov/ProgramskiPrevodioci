class Expr{
	static int redBr = 0;
	String br;
	Expr(Term t){
		br = t.br;
	}
	Expr(Expr e, Term t){
		Expr.redBr++;
		br = "p"+Expr.redBr;
	}
	Expr(char c, Expr e, Term t){
		Expr.redBr++;
		br = "p"+Expr.redBr;
		if(c == '+'){
			System.out.println(br+" mknode(+, "+e.br+", " + t.br+")");
		}
		if(c == '-'){
            System.out.println(br+" mknode(-, "+e.br+", " + t.br+")");
		}
	}
}