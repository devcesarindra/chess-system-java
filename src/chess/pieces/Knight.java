package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Knight extends ChessPiece {
    public Knight(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "C";
    }
    private boolean canMovie(Position position) {
        ChessPiece p = (ChessPiece) getBoard().piece(position);
        return p == null || p.getColor() != getColor();
    }
    @Override
    public boolean[][] possibleMovies() {
        boolean [][] mat = new boolean[getBoard().getRows()][getBoard().getCollums()];

        Position p = new Position(0, 0);

        p.setValues(position.getRow() -1, position.getCollum() -2);
        if(getBoard().positionExists(p) && canMovie(p)) {
            mat[p.getRow()][p.getCollum()] = true;
        }

        p.setValues(position.getRow() -2, position.getCollum() -1 );
        if(getBoard().positionExists(p) && canMovie(p)) {
            mat[p.getRow()][p.getCollum()] = true;
        }
        p.setValues(position.getRow() -2, position.getCollum() +1);
        if(getBoard().positionExists(p) && canMovie(p)) {
            mat[p.getRow()][p.getCollum()] = true;
        }

        p.setValues(position.getRow() -1, position.getCollum() +2);
        if(getBoard().positionExists(p) && canMovie(p)) {
            mat[p.getRow()][p.getCollum()] = true;
        }

        p.setValues(position.getRow() +1, position.getCollum()+2);
        if(getBoard().positionExists(p) && canMovie(p)) {
            mat[p.getRow()][p.getCollum()] = true;
        }
        p.setValues(position.getRow() +2, position.getCollum()+1);
        if(getBoard().positionExists(p) && canMovie(p)) {
            mat[p.getRow()][p.getCollum()] = true;
        }

        p.setValues(position.getRow() +2, position.getCollum()-1);
        if(getBoard().positionExists(p) && canMovie(p)) {
            mat[p.getRow()][p.getCollum()] = true;
        }
        p.setValues(position.getRow() +1, position.getCollum()-2);
        if(getBoard().positionExists(p) && canMovie(p)) {
            mat[p.getRow()][p.getCollum()] = true;
        }
        return mat;
    }
}