import java.util.*;
class TabelaSimbola{
	List<TabelaSimbolaStavka> stavke = new LinkedList<>();
	public void dodaj(TabelaSimbolaStavka stavka){
		stavke.add(stavka);
	}
	public String tabelaSimbola(){
        String s = "";
        s = s + "Tabela simbola" +"\n";
        s = s + "-----------------" +"\n";
        for(TabelaSimbolaStavka ts:stavke){
			s = s + ts +"\n";
		}
		return s;
    }

    public int pretrazi(String s){
		for(TabelaSimbolaStavka st:stavke){
			if(s.equals(st.leksema)){
				return Integer.parseInt(st.atribut);
			}
		}
		return 0;
	}
}