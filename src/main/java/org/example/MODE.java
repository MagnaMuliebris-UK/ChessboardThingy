package org.example;

public class MODE{
    /// An obstruction is either considered 'capturable' if true (Counts) or 'void' if false (e.g. Off the chessboard)
    static boolean countIfObstruct = false;
    /// Counts the Initial Position of the Queen if true
    static boolean countInitPos = false;
    /// Allow the chessboard to be made so x and y are always the same on true.
    static boolean boardModeNxN = true;
    /// Duplicate Queen is capturable if true
    static boolean rainbowQueen = true; //Rainbow as in many colours, just as white may capture black or vice versa, basically countIfObstruct in another use case.
}
