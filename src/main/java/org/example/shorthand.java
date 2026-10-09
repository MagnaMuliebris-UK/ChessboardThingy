package org.example;

import java.util.Scanner;

public class shorthand{
    public static int messageParseOut(Scanner s, String msg){System.out.println(msg); return Integer.parseInt(s.nextLine());}
    public static int[] intStance(Scanner s, String msg){
        while(true) {
            System.out.println(msg);
            try {int x = messageParseOut(s,"Enter x:"); int y = messageParseOut(s,"Enter y:"); return new int[]{x, y};}
            catch (NumberFormatException e){System.out.println("Please enter a whole number.");}
        }
    }
}
