class TabelaSimbolaStavka{
	String leksema;
	String token;
	String atribut;
	public TabelaSimbolaStavka(String leksema, String token, String atribut){
		this.leksema = leksema;
		this.token = token;
		this.atribut = atribut;
	}
	public String toString(){
		return leksema + "   " + token + "   " + atribut;
	}
}