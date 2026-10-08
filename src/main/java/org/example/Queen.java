package org.example;

public class Queen{
    int[] initPos = new int[2];
    int[] moveCheck = initPos;
    int count = 0;
    public Queen(int[] _initPos){
        initPos = _initPos;
        moveCheck = initPos;
    }
    public int queenMove(){
        count=0;
        if(MODE.countInitPos) count++;
        left();
        upLeft();
        up();
        upRight();
        right();
        downRight();
        down();
        downLeft();
        return count;
    }
    public void left(){
        moveCheck = initPos;
        while(true) {
            moveCheck[0]--;
            if (moveCheck[0] == 1) {
                count++;
                if(!Chessboard.travelledPos.contains(moveCheck)) Chessboard.travelledPos.add(moveCheck);
                break;
            }
            else if(Chessboard.obs.contains(moveCheck)){
                if(MODE.countIfObstruct) {
                    count++;
                    if(!Chessboard.travelledPos.contains(moveCheck)) Chessboard.travelledPos.add(moveCheck);
                }
                break;
            }
            else if(Chessboard.queenPos.contains(moveCheck)){
                if(MODE.rainbowQueen){
                    count++;
                    Chessboard.queenCapture(moveCheck);
                    if(!Chessboard.travelledPos.contains(moveCheck)) Chessboard.travelledPos.add(moveCheck);
                }
                break;
            }
            else{
                count++;
                if(!Chessboard.travelledPos.contains(moveCheck)) Chessboard.travelledPos.add(moveCheck);
            }
        }
    }
    public void upLeft() {
        moveCheck = initPos;
        while(true) {
            moveCheck[0]--;
            moveCheck[1]++;
            if (moveCheck[0] == 1||moveCheck[1]==Chessboard.boardPlane[1]) {
                count++;
                if(!Chessboard.travelledPos.contains(moveCheck)) Chessboard.travelledPos.add(moveCheck);
                break;
            }
            else if(Chessboard.obs.contains(moveCheck)){
                if(MODE.countIfObstruct) {
                    count++;
                    if (!Chessboard.travelledPos.contains(moveCheck)) Chessboard.travelledPos.add(moveCheck);
                }
                break;
            }
            else if(Chessboard.queenPos.contains(moveCheck)){
                if(MODE.rainbowQueen){
                    count++;
                    if(!Chessboard.travelledPos.contains(moveCheck)) Chessboard.travelledPos.add(moveCheck);
                    Chessboard.queenCapture(moveCheck);
                }
                break;
            }
            else{
                count++;
                if(!Chessboard.travelledPos.contains(moveCheck)) Chessboard.travelledPos.add(moveCheck);
            }
        }
    }
    public void up(){
        moveCheck = initPos;
        while(true) {
            moveCheck[1]++;
            if (moveCheck[1]==Chessboard.boardPlane[1]) {
                count++;
                if(!Chessboard.travelledPos.contains(moveCheck)) Chessboard.travelledPos.add(moveCheck);
                break;
            }
            else if(Chessboard.obs.contains(moveCheck)){
                if(MODE.countIfObstruct) {
                    count++;
                    if(!Chessboard.travelledPos.contains(moveCheck)) Chessboard.travelledPos.add(moveCheck);
                }
                break;
            }
            else if(Chessboard.queenPos.contains(moveCheck)){
                if(MODE.rainbowQueen){
                    count++;
                    if(!Chessboard.travelledPos.contains(moveCheck)) Chessboard.travelledPos.add(moveCheck);
                    Chessboard.queenCapture(moveCheck);
                }
                break;
            }
            else{
                count++;
                if(!Chessboard.travelledPos.contains(moveCheck)) Chessboard.travelledPos.add(moveCheck);
            }
        }
    }
    public void upRight(){
        moveCheck = initPos;
        while(true) {
            moveCheck[0]++;
            moveCheck[1]++;
            if (moveCheck[0] == Chessboard.boardPlane[0]||moveCheck[1]==Chessboard.boardPlane[1]) {
                count++;
                if(!Chessboard.travelledPos.contains(moveCheck)) Chessboard.travelledPos.add(moveCheck);
                break;
            }
            else if(Chessboard.obs.contains(moveCheck)){
                if(MODE.countIfObstruct) {
                    count++;
                    if(!Chessboard.travelledPos.contains(moveCheck)) Chessboard.travelledPos.add(moveCheck);
                }
                break;
            }
            else if(Chessboard.queenPos.contains(moveCheck)){
                if(MODE.rainbowQueen){
                    count++;
                    Chessboard.queenCapture(moveCheck);
                    if(!Chessboard.travelledPos.contains(moveCheck)) Chessboard.travelledPos.add(moveCheck);
                }
                break;
            }
            else{
                count++;
                if(!Chessboard.travelledPos.contains(moveCheck)) Chessboard.travelledPos.add(moveCheck);
            }
        }
    }
    public void right(){
        moveCheck = initPos;
        while(true) {
            moveCheck[0]++;
            if (moveCheck[0] == Chessboard.boardPlane[0]) {
                count++;
                if(!Chessboard.travelledPos.contains(moveCheck)) Chessboard.travelledPos.add(moveCheck);
                break;
            }
            else if(Chessboard.obs.contains(moveCheck)){
                if(MODE.countIfObstruct) {
                    count++;
                    if (!Chessboard.travelledPos.contains(moveCheck)) Chessboard.travelledPos.add(moveCheck);
                }
                break;
            }
            else if(Chessboard.queenPos.contains(moveCheck)){
                if(MODE.rainbowQueen){
                    count++;
                    Chessboard.queenCapture(moveCheck);
                    if(!Chessboard.travelledPos.contains(moveCheck)) Chessboard.travelledPos.add(moveCheck);
                }
                break;
            }
            else{
                count++;
                if(!Chessboard.travelledPos.contains(moveCheck)) Chessboard.travelledPos.add(moveCheck);
            }
        }
    }
    public void downRight(){
        moveCheck = initPos;
        while(true) {
            moveCheck[0]++;
            moveCheck[1]--;
            if (moveCheck[0] == Chessboard.boardPlane[0]||moveCheck[1]==1) {
                count++;
                if(!Chessboard.travelledPos.contains(moveCheck)) Chessboard.travelledPos.add(moveCheck);
                break;
            }
            else if(Chessboard.obs.contains(moveCheck)){
                if(MODE.countIfObstruct) {
                    count++;
                    if (!Chessboard.travelledPos.contains(moveCheck)) Chessboard.travelledPos.add(moveCheck);
                }
                break;
            }
            else if(Chessboard.queenPos.contains(moveCheck)){
                if(MODE.rainbowQueen){
                    count++;
                    Chessboard.queenCapture(moveCheck);
                    if(!Chessboard.travelledPos.contains(moveCheck)) Chessboard.travelledPos.add(moveCheck);
                }
                break;
            }
            else{
                count++;
                if(!Chessboard.travelledPos.contains(moveCheck)) Chessboard.travelledPos.add(moveCheck);
            }
        }
    }
    public void down(){
        moveCheck = initPos;
        while(true) {
            moveCheck[1]--;
            if (moveCheck[1]==1) {
                count++;
                if(!Chessboard.travelledPos.contains(moveCheck)) Chessboard.travelledPos.add(moveCheck);
                break;
            }
            else if(Chessboard.obs.contains(moveCheck)){
                if(MODE.countIfObstruct) {
                    count++;
                    if (!Chessboard.travelledPos.contains(moveCheck)) Chessboard.travelledPos.add(moveCheck);
                }
                break;
            }
            else if(Chessboard.queenPos.contains(moveCheck)){
                if(MODE.rainbowQueen){
                    count++;
                    if(!Chessboard.travelledPos.contains(moveCheck)) Chessboard.travelledPos.add(moveCheck);
                    Chessboard.queenCapture(moveCheck);
                }
                break;
            }
            else{
                count++;
                if(!Chessboard.travelledPos.contains(moveCheck)) Chessboard.travelledPos.add(moveCheck);
            }
        }
    }
    public void downLeft(){
        moveCheck = initPos;
        while(true) {
            moveCheck[0]--;
            moveCheck[1]--;
            if (moveCheck[0] == 1||moveCheck[1]==1) {
                count++;
                if(!Chessboard.travelledPos.contains(moveCheck)) Chessboard.travelledPos.add(moveCheck);
                break;
            }
            else if(Chessboard.obs.contains(moveCheck)){
                if(MODE.countIfObstruct) {
                    count++;
                    if (!Chessboard.travelledPos.contains(moveCheck)) Chessboard.travelledPos.add(moveCheck);
                }
                break;
            }
            else if(Chessboard.queenPos.contains(moveCheck)){
                if(MODE.rainbowQueen){
                    count++;
                    Chessboard.queenCapture(moveCheck);
                    if(!Chessboard.travelledPos.contains(moveCheck)) Chessboard.travelledPos.add(moveCheck);
                }
                break;
            }
            else{
                count++;
                if(!Chessboard.travelledPos.contains(moveCheck)) Chessboard.travelledPos.add(moveCheck);
            }
        }
    }
}
