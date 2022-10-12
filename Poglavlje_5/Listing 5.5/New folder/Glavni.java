import java.io.*;
class Glavni{
	public static void main(String[] args) throws Exception{
		//File file = new File("ulaz.txt");
        //TroadresniKod kod = new TroadresniKod(file);
        //String ulaz = "a:=x*x+(2+x*(y+y))*y";
        String ulaz = "z = x*(x + 2*y+x + y*(y+b))";
        //String[] ulazi = ulaz.split("=");
        System.out.println(ulaz);
        System.out.println();
        TroadresniKod kod = new TroadresniKod(ulaz);
        kod.eliminacijazagrada();
	}

}