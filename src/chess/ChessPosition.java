package chess;

import boardgame.Position;

public class ChessPosition {
    private char collum;
    private int row;

    public ChessPosition(char collum, int row) {
        if(collum < 'a' || collum > 'h' || row < 1 || row > 8) {
            throw new ChessException("Error intantiating ChessPosition. Valid values are from A1 to H8.");
        }
        this.collum = collum;
        this.row = row;
    }

    public char getCollum() {
        return collum;
    }

    public int getRow() {
        return row;
    }

    protected Position toPosition() {
        return new Position(8 - row, collum - 'a');
    }

    protected static ChessPosition fromPosition(Position position) {
        return new ChessPosition((char)('a' - position.getCollum()), 8 - position.getRow());
    }

    @Override
    public String toString() {
        return "" + collum + row;
    }
}
