package org.example;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Scanner;

import static org.example.Chessboard.*;
import static org.example.shorthand.intStance;

public class Main {
    static void main() {
        while (true) {
            Scanner s = new Scanner(System.in);
            System.out.println("""
                    THE CHESSBOARD MENU
                    
                    1. Run Chessboard Simulation
                    
                    2. Change Chessboard Plane
                    3. Edit Obstructions
                    4. Edit Queen Positions
                    
                    5. Change Settings
                    
                    6. Exit
                    """);
            String select = s.nextLine();
            switch (select) {


                case "1": chessboardRun(); break;
                case "2": setBoardPlane(s); break;
                case "3": obsMenu(); break;
                case "4": queenMenu(); break;
                case "5": settingsMenu(); break;
                case "6": System.exit(0); break;
                default:System.out.println("Please enter a valid entry");
            }
        }
    }

    private static void chessboardRun() {
        movableQueens = queensInPlay;
        Queen[] movableQueenArr = movableQueens.toArray(new Queen[0]);
        shuffle(movableQueenArr);
        movableQueens = (java.util.ArrayList<Queen>) Arrays.stream(movableQueenArr).toList();
        boolean TheGameIsOn = true;
        int iter = 0;
        while(TheGameIsOn){
            try {
                populateQueenPos(movableQueens.get(iter));
                System.out.printf("Queen at [%s,%s] can move a total of %s tiles\n", movableQueens.get(iter).initPos[0], movableQueens.get(iter).initPos[1], movableQueens.get(iter).queenMove());
                iter++;
            }
            catch(ConcurrentModificationException e){
                //get over yourself. You are FINE. Totally...
            }
            catch (Exception e) {TheGameIsOn = false;}
        }
        System.out.printf("Queens can move a total of %s tiles.", trueCount());
    }

    static void shuffle(Queen[] a){for (int i = 1; i < a.length; i++) swap(a, i, (int)(Math.random() * i));}

    static void swap(Queen[] a, int i, int j) {Queen temp = a[i]; a[i] = a[j]; a[j] = temp;}

    private static void settingsMenu() {
        Scanner s = new Scanner(System.in); boolean seting = true;
        while (seting) {
            //long ternary sequence to be compact
            System.out.printf("""
                    THE CHESSBOARD MENU - SETTINGS
                    
                    1. Capturable Obstructions (%s)
                    2. Count initial position (%s)
                    3. Board dimensions are always square (%s)
                    4. Capturable Queens (%s)
                    
                    5. Back
                    """, MODE.countIfObstruct ? "Active" : "Inactive", MODE.countInitPos ? "Active" : "Inactive", MODE.boardModeNxN ? "Active" : "Inactive", MODE.rainbowQueen ? "Active" : "Inactive");
            String select = s.nextLine();
            switch (select) {
                case "1": MODE.countIfObstruct = !MODE.countIfObstruct;break;
                case "2": MODE.countInitPos = !MODE.countInitPos;break;
                case "3": MODE.boardModeNxN = !MODE.boardModeNxN;break;
                case "4": MODE.rainbowQueen = !MODE.rainbowQueen;break;
                case "5": seting = false;break;
                default:System.out.println("Please enter a valid entry");
            }
        }
    }

    private static void queenMenu() {
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    THE CHESSBOARD MENU - QUEENS
                    
                    1. Add Queen
                    2. Remove Queen
                    3. Edit Queens
                    
                    4. Back
                    """);
            String select = s.nextLine();
            if (select.equalsIgnoreCase("1")) {
                int[] xy = intStance(s, "Please enter the position of the Queen in the form (x,y)");
                if (!posConflictCheck(xy)) queensInPlay.add(new Queen(xy));
            } else if (select.equalsIgnoreCase("2")) {
                int[] xy = intStance(s, "Please enter the position of the Queen in the form (x,y)");
                if (posConflictCheck(xy)) queensInPlay.remove(getQueenFromPos(xy));
            } else if (select.equalsIgnoreCase("3")) {
                int[] xy = intStance(s, "Please enter the position of the Queen in the form (x,y)");
                if (posConflictCheck(xy)) {
                    queensInPlay.remove(getQueenFromPos(xy));

                    int[] xy2 = intStance(s, "Please enter the position of the Queen in the form (x,y)");
                    if (!posConflictCheck(xy2)) queensInPlay.add(new Queen(xy2));
                }
            } else if (select.equalsIgnoreCase("4")) break;
        }
    }

    public static void obsMenu() {
        while (true) {
            Scanner s = new Scanner(System.in);
            System.out.println("""
                    THE CHESSBOARD MENU - OBSTRUCTIONS
                    
                    1. Add Obstruction
                    2. Remove Obstruction
                    3. Edit Obstructions
                    
                    4. Back
                    """);
            String select = s.nextLine();
            if (select.equalsIgnoreCase("1")) {
                int[] xy = intStance(s,"Please enter the position of the obstruction in the form (x,y)");
                if (!posConflictCheck(xy)) obs.add(xy);
            } else if (select.equalsIgnoreCase("2")) {
                int[] xy = intStance(s,"Please enter the position of the obstruction in the form (x,y)");
                if (posConflictCheck(xy)) obs.remove(xy);
            } else if (select.equalsIgnoreCase("3")) {
                int[] xy = intStance(s,"Please enter the position of the obstruction in the form (x,y)");
                if (posConflictCheck(xy)) {obs.remove(xy);
                    int[] xy2= intStance(s,"Please enter the position of the obstruction in the form (x,y)");
                    if (!posConflictCheck(xy2)) obs.add(xy2);
                }
            } else if (select.equalsIgnoreCase("4")) break;

        }
    }
}

