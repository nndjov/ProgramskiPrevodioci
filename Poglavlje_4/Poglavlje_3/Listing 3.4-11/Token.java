public class Token {
    public final byte tip;
    public final String leksema; // SADRZAJ ZA TOKEN IDENTIFIKATOR
	public final static byte
    IDENTIFIER =  1,
    NUMBER     =  2,
    LPAREN     =  3,
    RPAREN     = 4,
    OPERATOR   = 5,
    ASSIG      = 6,
    SEMI       = 7,
    EOT        = 8,
    LF         = 9;

	public Token(byte tip, String leksema) {
		this.tip = tip;
		this.leksema = leksema;
	}
	public String toString() {
		if(tip == IDENTIFIER) {
			return "IDENTIFIER<" + leksema + ">";
		}
		if(tip == NUMBER) {
			return "NUMBER<" + leksema + ">";
		}
		if(tip == OPERATOR) {
			return "OPERATOR<" + leksema + ">";
		}
		return vratiToken(tip);
    }
	String vratiToken(){
		return vratiToken(tip);
	}

	static String vratiToken(byte ki){
		String s = "";
		switch (ki){
		  case 1:
			s = "IDENTIFIER";
			break;
		  case 2:
			s = "NUMBER";
			break;
		  case 3:
			s = "LPAREN";
			break;
		  case 4:
			s = "RPAREN";
			break;
		  case 5:
			s = "OPERATOR";
			break;
		  case 6:
			s = "ASSIG";
			break;
		  case 7:
			s = "SEMI";
			break;
		  case 8:
			s = "EOT";
			break;
		  case 9:
			s = "LF";
			break;
		  default:
			s="";
		}
		return s;
	}
}
