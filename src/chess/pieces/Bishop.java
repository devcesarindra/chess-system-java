package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Bishop extends ChessPiece {

    public Bishop(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "♝";
    }

    @Override
    public boolean[][] possibleMovies() {
        boolean [][] mat = new boolean[getBoard().getRows()][getBoard().getCollums()];

        Position p = new Position(0, 0);
        //NW
        p.setValues(position.getRow() -1, position.getCollum() -1);
        while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRow()][p.getCollum()] = true;
            p.setValues(p.getRow()-1, p.getCollum() -1);
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getCollum()] = true;
        }


        //NE
        p.setValues(position.getRow() - 1, position.getCollum()+1);
        while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRow()][p.getCollum()] = true;
            p.setValues(p.getRow() -1,p.getCollum()+1);
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getCollum()] = true;
        }


        //SE
        p.setValues(position.getRow() +1, position.getCollum() +1);
        while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRow()][p.getCollum()] = true;
            p.setValues(p.getRow()+1, p.getCollum() +1);
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getCollum()] = true;
        }

        //SW
        p.setValues(position.getRow()+1, position.getCollum()-1);
        while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRow()][p.getCollum()] = true;
            p.setValues(p.getRow() +1,p.getCollum()-1);
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getCollum()] = true;
        }
        return mat;
    }
}
