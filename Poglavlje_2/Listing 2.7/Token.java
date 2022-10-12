public class Token {
    public final byte kind;
    public final String c; // SADRZAJ ZA TOKEN IDENTIFIKATOR
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

	public Token(byte kind, String c) {
		this.kind = kind;
		this.c = c;
	}
	public String toString() {
		if(kind == IDENTIFIER) {
			return "IDENTIFIER<" + c + ">";
		}
		if(kind == NUMBER) {
			return "NUMBER<" + c + ">";
		}
		if(kind == OPERATOR) {
			return "OPERATOR<" + c + ">";
		}
		return vratiToken(kind);
    }
	String vratiToken(){
		return vratiToken(kind);
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
