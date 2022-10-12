/*
E → T R
R → ‘+’ T { print(‘+’) } R1
R → ‘*’ T { print(‘*’) } R1
R →  є
T → (E)
T → digit{ print(digit.val) }
*/



class Parser {
    char[] ulaz;
    int pok;
    public Parser(char[] ulaz){
		pok = 0;
		this.ulaz = ulaz;
    }
    boolean E()   {
        T();
        R();
        return true;
    }
    boolean R()  {
        while (true) {
            if( simbol() == '+' ) {
                sledeci('+'); T(); System.out.print('+');
            } else if( simbol() == '*' ) {
                sledeci('*'); T(); System.out.print('*');
            } else if( simbol() == '-' ) {
                sledeci('-'); T(); System.out.print('-');
            }
            else return false;
        }
    }

    boolean T() {
          if( Character.isDigit(simbol()) ) {
              System.out.write(simbol()) ;
              sledeci(simbol()) ;
              return true;
          } else {
			  if( simbol() != '(' ) {
				  return false;
			  } else{
				  sledeci('(');
			  }
			 if(E()== false){
				 return false;
			 }
		     if ( simbol() != ')' ) {
				 return false;
			 } else{
				 sledeci(')') ;
			 }
		     return true;
		  }
    }
    char simbol(){
		return ulaz[pok];
	}
    void sledeci(int t) {
        if( simbol() == t  ){
			if(pok < (ulaz.length-1))
			    pok++;
		}
        else  System.out.println("syntax error") ;
    }
}

