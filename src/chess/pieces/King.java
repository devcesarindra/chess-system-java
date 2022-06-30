package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {
    public King(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "♚";
    }
    private boolean canMovie(Position position) {
        ChessPiece p = (ChessPiece) getBoard().piece(position);
        return p == null || p.getColor() != getColor();
    }
    @Override
    public boolean[][] possibleMovies() {
        boolean [][] mat = new boolean[getBoard().getRows()][getBoard().getCollums()];

        Position p = new Position(0, 0);

        //ABOVE
        p.setValues(position.getRow() -1, position.getCollum());
        if(getBoard().positionExists(p) && canMovie(p)) {
            mat[p.getRow()][p.getCollum()] = true;
        }

        //BELOW
        p.setValues(position.getRow() +1, position.getCollum());
        if(getBoard().positionExists(p) && canMovie(p)) {
            mat[p.getRow()][p.getCollum()] = true;
        }
        //LEFT
        p.setValues(position.getRow(), position.getCollum() -1);
        if(getBoard().positionExists(p) && canMovie(p)) {
            mat[p.getRow()][p.getCollum()] = true;
        }
        //RIGHT
        p.setValues(position.getRow(), position.getCollum() +1);
        if(getBoard().positionExists(p) && canMovie(p)) {
            mat[p.getRow()][p.getCollum()] = true;
        }

        //NW
        p.setValues(position.getRow() -1, position.getCollum()-1);
        if(getBoard().positionExists(p) && canMovie(p)) {
            mat[p.getRow()][p.getCollum()] = true;
        }
        //NE
        p.setValues(position.getRow() -1, position.getCollum()+1);
        if(getBoard().positionExists(p) && canMovie(p)) {
            mat[p.getRow()][p.getCollum()] = true;
        }
        //SW
        p.setValues(position.getRow() +1, position.getCollum()-1);
        if(getBoard().positionExists(p) && canMovie(p)) {
            mat[p.getRow()][p.getCollum()] = true;
        }
        //NE
        p.setValues(position.getRow() +1, position.getCollum()+1);
        if(getBoard().positionExists(p) && canMovie(p)) {
            mat[p.getRow()][p.getCollum()] = true;
        }
        return mat;
    }
}
