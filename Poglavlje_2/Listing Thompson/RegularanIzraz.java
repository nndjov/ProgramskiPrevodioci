class RegularanIzraz{
	String izraz;
	RegularanIzraz(String izraz){
		this.izraz = izraz;
	}
	public String vrati(){
		return izraz;
	}

    public boolean slovo(char c){
		return c >= 'a' && c <= 'z';
	}
    public boolean azbuka(char c){
		return slovo(c) || c == 'E';
	}
    public boolean operatorRegIz(char c){
        return c == '(' || c == ')' || c == '*' || c == '|';
    }
    public  boolean validniZnak(char c){
        return azbuka(c) || operatorRegIz(c);
    }

    public boolean validanIzraz(){
        if (izraz.isEmpty())
            return false;
        for (char c: izraz.toCharArray()){
            if (!validniZnak(c)){
				return false;
			}
		}
        return true;
    }
}