import java.io.*;
import java.util.*;
public class E_closure{

    static Integer epsilon[][]= {
		                 {1},
		                 {2,3,7},
		                 {null},
		                 {null},
		                 {6},
		                 {6},
		                 {1,7},
		                 {null},

		                 {9},
		                 {10,11,15},
		                 {null},
		                 {null},
		                 {14},
		                 {14},
		                 {15,9},
		                 {null}
						};

    public static void main(String args[])throws IOException{
		BufferedReader obj= new BufferedReader (new InputStreamReader (System.in));
        System.out.print("Stanje = ");
        int stanje = Integer.parseInt(obj.readLine());
        Set<Integer> skupEclosure = eclosure(stanje);
		System.out.println("E-closure("+stanje+") = " + skupEclosure);
    }

	public static Set<Integer> eclosure(Integer stanje){
		Stack<Integer> stek = new Stack<>();
		Set<Integer> skupEclosure = new HashSet<>();
		skupEclosure.add(stanje);
		stek.push(stanje);
		while(!stek.empty()){
			int t = stek.pop();
			for(int j = 0; j<epsilon[t].length; j++){
				if(epsilon[t][j] != null){
					skupEclosure.add(epsilon[t][j]);
					stek.push(epsilon[t][j]);
				}
			}
		}
		return skupEclosure;
	}
}