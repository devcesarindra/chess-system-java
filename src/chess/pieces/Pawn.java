package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {
    public Pawn(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "P";
    }

    @Override
    public boolean[][] possibleMovies() {
        boolean [][] mat = new boolean[getBoard().getRows()][getBoard().getCollums()];

        Position p = new Position(0, 0);

        if(getColor() == Color.WHITE) {
            p.setValues(position.getRow() -1, position.getCollum());
            if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
                mat[p.getRow()][p.getCollum()] = true;
            }
            p.setValues(position.getRow() -2, position.getCollum());
            Position p2 = new Position(position.getRow() -1, position.getCollum());
            if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) {
                mat[p.getRow()][p.getCollum()] = true;
            }
            p.setValues(position.getRow() -1, position.getCollum() -1);
            if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
                mat[p.getRow()][p.getCollum()] = true;
            }
            p.setValues(position.getRow() -1, position.getCollum() +1);
            if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
                mat[p.getRow()][p.getCollum()] = true;
            }
        } else {
            p.setValues(position.getRow() +1, position.getCollum());
            if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
                mat[p.getRow()][p.getCollum()] = true;
            }
            p.setValues(position.getRow() +2, position.getCollum());
            Position p2 = new Position(position.getRow() +1, position.getCollum());
            if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) {
                mat[p.getRow()][p.getCollum()] = true;
            }
            p.setValues(position.getRow() +1, position.getCollum() -1);
            if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
                mat[p.getRow()][p.getCollum()] = true;
            }
            p.setValues(position.getRow() +1, position.getCollum() +1);
            if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
                mat[p.getRow()][p.getCollum()] = true;
            }
        }
        return mat;
    }
}
