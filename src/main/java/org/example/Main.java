package org.example;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Objects;
import java.util.Scanner;

import static org.example.Chessboard.*;

public class Main {
    public static void main() {
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
            if (Objects.equals(select, "1")) chessboardRun();
            else if (Objects.equals(select, "2")) {
                if (MODE.boardModeNxN) {
                    System.out.println("Please enter the size of the board (n,n), starting from (1,1)");
                    int n = Integer.parseInt(s.nextLine());
                    boardPlane = new int[]{n, n};
                } else {
                    System.out.println("Please enter the size of the board (x,y), starting from (1,1)");
                    System.out.println("Enter x:");
                    int x = Integer.parseInt(s.nextLine());
                    System.out.println("Enter y:");
                    int y = Integer.parseInt(s.nextLine());
                    boardPlane = new int[]{x, y};
                }
            } else if (Objects.equals(select, "3")) obsMenu();
            else if (Objects.equals(select, "4")) queenMenu();
             else if (Objects.equals(select, "5")) settingsMenu();
             else if (Objects.equals(select, "6")) System.exit(0);


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
            catch (Exception e)
            {
                //Haha, the game is off
                TheGameIsOn = false;
            }
        }
        int truth = trueCount();
    }

    static void shuffle(Queen[] a)
    {
        for (int i = 1; i < a.length; i++)
            swap(a, i, (int)(Math.random() * i));
    }

    static void swap(Queen[] a, int i, int j)
    {
        Queen temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static void settingsMenu() {
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.printf("""
                    THE CHESSBOARD MENU - SETTINGS
                    
                    1. Capturable Obstructions (%s)
                    2. Count initial position (%s)
                    3. Board dimensions are always square (%s)
                    4. Capturable Queens (%s)
                    
                    5. Back
                    """, MODE.countIfObstruct ? "Active" : "Inactive", MODE.countInitPos ? "Active" : "Inactive", MODE.boardModeNxN ? "Active" : "Inactive", MODE.rainbowQueen ? "Active" : "Inactive");
            String select = s.nextLine();
            if(select.equalsIgnoreCase("1")) MODE.countIfObstruct = !MODE.countIfObstruct;
            else if (select.equalsIgnoreCase("2")) MODE.countInitPos = !MODE.countInitPos;
            else if (select.equalsIgnoreCase("3")) MODE.boardModeNxN= !MODE.boardModeNxN;
            else if (select.equalsIgnoreCase("4")) MODE.rainbowQueen = !MODE.rainbowQueen;
            else if (select.equalsIgnoreCase("5")) break;
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
                System.out.println("Please enter the position of the Queen in the form (x,y)");
                System.out.println("Enter x:");
                int x = Integer.parseInt(s.nextLine());
                System.out.println("Enter y:");
                int y = Integer.parseInt(s.nextLine());
                if (!posConflictCheck(new int[]{x, y})) queensInPlay.add(new Queen(new int[]{x, y}));
            } else if (select.equalsIgnoreCase("2")) {
                System.out.println("Please enter the position of the Queen in the form (x,y)");
                System.out.println("Enter x:");
                int x = Integer.parseInt(s.nextLine());
                System.out.println("Enter y:");
                int y = Integer.parseInt(s.nextLine());
                if (posConflictCheck(new int[]{x, y}))
                    queensInPlay.remove(getQueenFromPos(new int[]{x, y}));
            } else if (select.equalsIgnoreCase("3")) {
                System.out.println("Please enter the position of the Queen in the form (x,y)");
                System.out.println("Enter x:");
                int x = Integer.parseInt(s.nextLine());
                System.out.println("Enter y:");
                int y = Integer.parseInt(s.nextLine());
                if (posConflictCheck(new int[]{x, y})) {
                    queensInPlay.remove(getQueenFromPos(new int[]{x, y}));

                    System.out.println("Please enter the new position of the Queen in the form (x,y)");
                    System.out.println("Enter x:");
                    int x2 = Integer.parseInt(s.nextLine());
                    System.out.println("Enter y:");
                    int y2 = Integer.parseInt(s.nextLine());
                    if (!posConflictCheck(new int[]{x2, y2}))
                        queensInPlay.add(new Queen(new int[]{x2, y2}));
                }
            } else if (select.equalsIgnoreCase("4")) {
                break;
            }
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
                System.out.println("Please enter the position of the obstruction in the form (x,y)");
                System.out.println("Enter x:");
                int x = Integer.parseInt(s.nextLine());
                System.out.println("Enter y:");
                int y = Integer.parseInt(s.nextLine());
                if (!posConflictCheck(new int[]{x, y})) obs.add(new int[]{x, y});
            } else if (select.equalsIgnoreCase("2")) {
                System.out.println("Please enter the position of the obstruction in the form (x,y)");
                System.out.println("Enter x:");
                int x = Integer.parseInt(s.nextLine());
                System.out.println("Enter y:");
                int y = Integer.parseInt(s.nextLine());
                if (posConflictCheck(new int[]{x, y})) obs.remove(new int[]{x, y});
            } else if (select.equalsIgnoreCase("3")) {
                System.out.println("Please enter the position of the obstruction in the form (x,y)");
                System.out.println("Enter x:");
                int x = Integer.parseInt(s.nextLine());
                System.out.println("Enter y:");
                int y = Integer.parseInt(s.nextLine());
                if (posConflictCheck(new int[]{x, y})) {
                    obs.remove(new int[]{x, y});

                    System.out.println("Please enter the new position of the obstruction in the form (x,y)");
                    System.out.println("Enter x:");
                    int x2 = Integer.parseInt(s.nextLine());
                    System.out.println("Enter y:");
                    int y2 = Integer.parseInt(s.nextLine());
                    if (!posConflictCheck(new int[]{x2, y2})) obs.add(new int[]{x2, y2});
                }
            } else if (select.equalsIgnoreCase("4")) break;

        }
    }
}
