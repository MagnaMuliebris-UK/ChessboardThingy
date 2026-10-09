package org.example;

import java.util.ArrayList;
import java.util.Scanner;

import static org.example.shorthand.messageParseOut;

public class Chessboard{
    static int[] boardPlane = new int[2];
    public static void  setBoardPlane(Scanner s){
        System.out.printf("Please enter the size of the board %s, starting from (1,1)\n", !MODE.boardModeNxN ?"(x,y)":"(x,x)");
        int x = messageParseOut(s,"Enter x:");
        int y = -1;
        if(!MODE.boardModeNxN) y = messageParseOut(s,"Enter y:");
        boardPlane = new int[]{x, y==1?x:y};
    }
    /// OBSTRUCTIONS
    public static ArrayList<int[]> obs = new ArrayList<>();
    /// NOTE, this does NOT include the current Queen being moveChecked purely for the rainbowQueens functionality
    public static ArrayList<int[]> queenPos = new ArrayList<>();
    /// NOTE, only for when there are duplicate Queens, gives an overall count
    public static ArrayList<int[]> travelledPos = new ArrayList<>();
    /// Only really used properly when rainbowQueens is true.
    /// Initially populated when Chessboard simulation is run.
    /// Randomly iterates through queens and runs them in order.
    /// Any captured queens are removed.
    public static ArrayList<Queen> movableQueens;
    /// Stores all queens
    public static ArrayList<Queen> queensInPlay = new ArrayList<>();

    public static Queen getQueenFromPos(int[] _initPos){
        for (Queen q : queensInPlay) if(q.initPos ==_initPos) return q;
        return null;
    }
    /// Will be called multiple times, iterates through each movable Queen
    public static void populateQueenPos(Queen q){
        queenPos = new ArrayList<>();
        for (Queen qu : movableQueens) if(q != qu) queenPos.add(qu.initPos);
    }
    public static int trueCount(){ return travelledPos.size(); }
    public static boolean posConflictCheck(int[] newPos){ return queenPos.contains(newPos) || obs.contains(newPos); }
    public static void queenCapture(int[] _pos){movableQueens.remove(getQueenFromPos(_pos));}
}
