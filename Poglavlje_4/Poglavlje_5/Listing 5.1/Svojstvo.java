class Svojstvo{
    String ime;
    String vrednost;
    Svojstvo sledeci;

    Svojstvo(){
		this.ime = "";
		this.vrednost = "";
	}

    Svojstvo(String ime, String vrednost){
		this.ime = ime;
		this.vrednost = vrednost;
	}
	public String getVrednost(){
		return vrednost;
	}
}