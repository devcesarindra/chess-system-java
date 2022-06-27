package boardgame;

public class Board {
    private int rows;
    private int collums;
    private Piece[][] pieces;

    public Board(int rows, int collums) {
        if (rows < 1 || collums < 1) {
            throw new BoardException("Erro creating board: there must be at least 1 row and 1 collum");
        }
        this.rows = rows;
        this.collums = collums;
        pieces = new Piece[rows][collums];
    }

    public int getRows() {
        return rows;
    }
    public int getCollums() {
        return collums;
    }

    public Piece piece(int row, int collum) {
        if(!positionExists(row, collum)) {
            throw new BoardException("Position not on the board ");
        }
        return pieces[row][collum];
    }

    public Piece piece(Position position) {
        if(!positionExists(position)) {
            throw new BoardException("Position not on the board");
        }
        return pieces[position.getRow()][position.getCollum()];
    }

    public void placePiece(Piece piece, Position position) {
        if(thereIsAPiece(position)) {
            throw new BoardException("There is already a piece on position" + position);
        }
        pieces[position.getRow()][position.getCollum()] = piece;
        piece.position = position;
    }
    public boolean positionExists(int row, int collum) {
        return row >= 0 && row < rows && collum >= 0 && collum < collums;
    }
    public boolean positionExists(Position position) {
        return positionExists(position.getRow(), position.getCollum());
    }

    public boolean thereIsAPiece(Position position) {
        if(!positionExists(position)) {
            throw new BoardException("Position not on the board");
        }
        return  piece(position) != null;
    }
}
