package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
    private Board board;

    public ChessMatch() {
        board = new Board(8, 8);
        initialSetup();
    }

    public ChessPiece[][] getPieces() {
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getCollums()];
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getCollums(); j++) {
                mat[i][j] = (ChessPiece) board.piece(i, j);
            }
        }

        return mat;
    }
    public ChessPiece performChessMovie(ChessPosition sourcePosition, ChessPosition targetPosition) {
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        Piece capturedPiece = makeMovie(source, target);
        return (ChessPiece) capturedPiece;
    }

    private Piece makeMovie(Position source, Position target) {
        Piece p = board.removePiece(source);
        Piece capturedPiece = board.removePiece(target);
        board.placePiece(p, target);
        return capturedPiece;
    }
    private void validateSourcePosition(Position position) {
        if(!board.thereIsAPiece(position)) {
            throw new ChessException("There is no piece on source position");
        }
    }
    private void placeNewPiece(char collum, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(collum, row).toPosition());
    }
    private void initialSetup() {
        placeNewPiece('a', 1, new Rook(board, Color.BLACK));
        placeNewPiece('a', 8, new Rook(board, Color.WHITE));
        placeNewPiece('h', 1,new Rook(board, Color.BLACK));
        placeNewPiece('h', 8, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1,new King(board, Color.BLACK));
        placeNewPiece('e', 8,new King(board, Color.WHITE));
    }
}
