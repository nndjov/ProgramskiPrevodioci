class Expr{
	Expr(Expr e, char c, Term t){
		if(c == '+'){
			Izraz.izraz = Izraz.izraz + " + ";
		}
		if(c == '-'){
			Izraz.izraz = Izraz.izraz + " - ";;
		}
	}
}