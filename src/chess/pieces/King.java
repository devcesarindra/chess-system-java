package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {
    private ChessMatch chessMatch;
    public King(Board board, Color color, ChessMatch chessMatch) {

        super(board, color);
        this.chessMatch = chessMatch;
    }

    @Override
    public String toString() {
        return "♚";
    }
    private boolean canMovie(Position position) {
        ChessPiece p = (ChessPiece) getBoard().piece(position);
        return p == null || p.getColor() != getColor();
    }
    private boolean testRookCastling(Position position) {
        ChessPiece p = (ChessPiece) getBoard().piece(position);
        return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
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

        // Castling
        if(getMoveCount() == 0 && !chessMatch.getCheck()){
            //King side
            Position posT1 = new Position(position.getRow(), position.getCollum() + 3);
            if(testRookCastling(posT1)) {
                Position p1 = new Position(position.getRow(), position.getCollum() + 1);
                Position p2 = new Position(position.getRow(), position.getCollum() + 2);
                if(getBoard().piece(p1) == null && getBoard().piece(p2) == null) {
                    mat[position.getRow()][position.getCollum() + 2] = true;
                }
            }

            //Queen side
            Position posT2 = new Position(position.getRow(), position.getCollum() -4);
            if(testRookCastling(posT2)) {
                Position p1 = new Position(position.getRow(), position.getCollum() -1);
                Position p2 = new Position(position.getRow(), position.getCollum() -2);
                Position p3 = new Position(position.getRow(), position.getCollum() -3);
                if(getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null) {
                    mat[position.getRow()][position.getCollum() -2] = true;
                }
            }
        }
        return mat;
    }
}
