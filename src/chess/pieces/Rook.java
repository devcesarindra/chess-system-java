package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {
    public Rook(Board board, Color color) {
        super(board, color);
    }

//    @Override
//    public String toString() {
//        if (getColor() == Color.BLACK) {
//            return "♖";
//        } else {
//            return "♜";
//        }
//    }

    @Override
    public String toString() {
        return "T";
    }

    @Override
    public boolean[][] possibleMovies() {
        boolean [][] mat = new boolean[getBoard().getRows()][getBoard().getCollums()];

        Position p = new Position(0, 0);
        //ABOVE
        p.setValues(position.getRow() -1, position.getCollum());
        while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRow()][p.getCollum()] = true;
            p.setRow(p.getRow()-1);
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getCollum()] = true;
        }


        //LEFT
        p.setValues(position.getRow(), position.getCollum()-1);
        while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRow()][p.getCollum()] = true;
            p.setCollum(p.getCollum()-1);
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getCollum()] = true;
        }


        //BELOW
        p.setValues(position.getRow() +1, position.getCollum());
        while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRow()][p.getCollum()] = true;
            p.setRow(p.getRow()+1);
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getCollum()] = true;
        }


        //RIGHT
        p.setValues(position.getRow(), position.getCollum()+1);
        while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRow()][p.getCollum()] = true;
            p.setCollum(p.getCollum()+1);
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getCollum()] = true;
        }
        return mat;
    }
}
