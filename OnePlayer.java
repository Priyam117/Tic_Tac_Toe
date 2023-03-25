public class OnePlayer extends Game {


    public OnePlayer(Board board, Player[] players) {
        super(board, players);
    }

    @Override
    public int[] getIndex() {
        if (this.turn == 1) {
            while (true) {
                int idx = (int) (Math.random() * (board.size * board.size - 1) + 1);
                int rowNo = idx / board.size;
                int columnNo = idx % board.size;
                if (board.board[rowNo][columnNo] != '-') {
                    System.out.println("Position already filled");
                    continue;
                }
                return new int[]{rowNo, columnNo};
            }
        }
        while (true) {
            System.out.println(players[turn].name + "'s turn , give index:");
            int idx = scn.nextInt() - 1;
            int rowNo = idx / board.size;
            int columnNo = idx % board.size;
            if (rowNo < 0 || columnNo < 0 || rowNo >= board.size || columnNo >= board.size) {
                System.out.println(" out of bound index");
                continue;
                }
            if (board.board[rowNo][columnNo] != '-') {
                    System.out.println("Position already filled");
                    continue;
            }
          return new int[]{rowNo, columnNo};
        }
    }
}

