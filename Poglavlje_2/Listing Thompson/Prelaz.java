class Prelaz{
	private int stanjeOd;
	private int stanjeDo;
	private char simbolTranzicije;

	public Prelaz(int stanjeOd, int stanjeDo, char simbolTranzicije){
		this.stanjeOd = stanjeOd;
		this.stanjeDo = stanjeDo;
		this.simbolTranzicije = simbolTranzicije;
	}

	public void postaviStanjeOd(int stanjeOd){
		this.stanjeOd = stanjeOd;
	}
	public int stanjeOd(){
		return stanjeOd;
	}
	public void postaviStanjeDo(int stanjeDo){
		this.stanjeDo = stanjeDo;
	}
	public int stanjeDo(){
		return stanjeDo;
	}
	public void postaviSimbolTranzicije(char simbolTranzicije){
		this.simbolTranzicije = simbolTranzicije;
	}
	public char simbolTranzicije(){
		return simbolTranzicije;
	}
}

