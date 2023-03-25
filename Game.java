import java.util.*;

public class Game {
    Board board;
    Player[]players;
    int noOfMoves;
    int turn;
    String zeroPattern;
    String crossPattern;
//    boolean gameOver;
    public static Scanner scn = new Scanner(System.in);

    Game(Board board , Player[]players){
        this.board = board;
        this.players = players;
        this.turn = 0;
        this.noOfMoves = 0;
//        this.gameOver = false;
        zeroPattern = "";
        crossPattern = "";
        for(int i = 0 ; i < board.size ; i++){
            zeroPattern += 'o';
            crossPattern += 'X';
        }
    }

    public void play(){
        System.out.println(board);
        while(true){
            this.noOfMoves++;
            if (this.noOfMoves > board.size * board.size){
                System.out.println("Match Draw");
                return;
            }
            int [] idxs = getIndex();
            int rowNo = idxs[0];
            int columnNo = idxs[1];
            board.board[rowNo][columnNo] = players[turn].symbol;
            if(this.noOfMoves >= board.size && ifTheGameIsEnded()){
                System.out.println(board);
                System.out.println(players[turn].name + "has won !");
                return;
            }
            turn = (turn + 1) % 2;
            System.out.println(board);
        }
    }
    public int[] getIndex(){
        while (true){
            System.out.println(players[turn].name +" turn , give index:");
            int idx = scn.nextInt() -1;
            int rowNo = idx / board.size;
            int columnNo = idx % board.size;
            if(rowNo < 0 || columnNo < 0 || rowNo >= board.size || columnNo >= board.size){
                System.out.println(" out of bound index");
                continue;
            }
            if(board.board[rowNo][columnNo] != '-'){
                System.out.println("Position already filled");
                continue;
            }
            return new int [] {rowNo , columnNo} ;
        }
    }
    public boolean ifTheGameIsEnded(){
        StringBuilder sb;


//        for Row/Horizontal Check

        for(int i=0;i<board.size;i++){
            sb = new StringBuilder();
            for(int j=0;j< board.size;j++) sb.append(board.board[i][j]);
            if(getResult(sb)) return true;
        }

//        for column/Vertical check


        for(int i=0;i<board.size;i++){
            sb = new StringBuilder();
            for(int j=0;j< board.size;j++) sb.append(board.board[j][i]);
            if(getResult(sb)) return true;
        }
//        for Major Diagonal check


        int i = 0 , j = 0;
        sb = new StringBuilder();
        while(i< board.size){
            sb.append(board.board[i++][j++]);
        }
        if(getResult(sb)) return true;

//      for Minor Diagonal check

         i = 0 ;  j = board.size - 1;
        sb = new StringBuilder();
        while(i< board.size){
           sb.append(board.board[i++][j--]);
        }
        if(getResult(sb)) return true;
        return false;
    }
    public boolean getResult(StringBuilder sb){
        if(sb.toString().equals(zeroPattern) || sb.toString().equals(crossPattern)) return  true;
        return false;
    }
}
