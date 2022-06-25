package boardgame;

public class Board {
    private int rows;
    private int collums;
    private Piece[][] pieces;

    public Board(int rows, int collums) {
        this.rows = rows;
        this.collums = collums;
        pieces = new Piece[rows][collums];
    }


}
