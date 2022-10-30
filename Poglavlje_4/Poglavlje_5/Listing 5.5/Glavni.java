import java.io.*;
class Glavni{
	public static void main(String[] args) throws Exception{
		//File file = new File("ulaz.txt");
        //TroadresniKod kod = new TroadresniKod(file);
        //String ulaz = "a:=x1*x+(2+x*(y+y))*y";
        //String ulaz = "z = x*(x + 2*y+x + y*(y+b))";
        //String ulaz = "(x + y) * (y + z) + (x + y + z)";
        //String ulaz = "p = vel - ( z + x + y + 1800) / w";
        String ulaz = "d:=(a+b)+(a-c)+(a-c)";
        System.out.println(ulaz);
        System.out.println();
        TroadresniKod kod = new TroadresniKod(ulaz);
        kod.generatorKoda();
        System.out.println(kod.vratiKod());
	}

}