

import java.io.*;
import java.util.*;
public class Postfix {
    public static void main(String[] args) {
        Parser p = new Parser("(4+3)*2-(7*(5+6))".toCharArray()) ;
        p.E() ;
        System.out.write('\n') ;
    }
}