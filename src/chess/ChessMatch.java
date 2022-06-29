package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChessMatch {
    private Board board;
    private int turn;
    private Color currentPlayer;
    private boolean check;
    private List<Piece> piecesOnTheBoard = new ArrayList<>();
    private List<Piece> capturedPieces = new ArrayList<>();



    public ChessMatch() {
        board = new Board(8, 8);
        turn = 1;
        currentPlayer = Color.WHITE;
        initialSetup();
    }
    public int getTurn() {
        return turn;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean getCheck() {
        return check;
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

    public boolean[][] possibleMovies(ChessPosition sourcePosition) {
        Position position = sourcePosition.toPosition();
        validateSourcePosition(position);
        return board.piece(position).possibleMovies();
    }
    public ChessPiece performChessMovie(ChessPosition sourcePosition, ChessPosition targetPosition) {
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        validateTargetPosition(source, target);
        Piece capturedPiece = makeMovie(source, target);
        if (testCheck(currentPlayer)) {
            undoMovie(source, target, capturedPiece);
            throw new ChessException("You can't put yourself in check");
        }
        check = (testCheck(opponent(currentPlayer))) ? true: false;
        nextTurn();
        return (ChessPiece) capturedPiece;
    }

    private void undoMovie(Position source, Position target, Piece capturedPiece) {
        Piece p = board.removePiece(target);
        board.placePiece(p, source);

        if(capturedPiece != null) {
            board.placePiece(capturedPiece, target);
            capturedPieces.remove(capturedPiece);
            piecesOnTheBoard.add(capturedPiece);
        }
    }

    private Piece makeMovie(Position source, Position target) {
        Piece p = board.removePiece(source);
        Piece capturedPiece = board.removePiece(target);
        board.placePiece(p, target);
        if(capturedPiece != null) {
            piecesOnTheBoard.remove(capturedPiece);
            capturedPieces.add(capturedPiece);
        }
        return capturedPiece;
    }
    private void validateSourcePosition(Position position) {
        if(!board.thereIsAPiece(position)) {
            throw new ChessException("There is no piece on source position");
        }
        if(currentPlayer != ((ChessPiece)board.piece(position)).getColor()) {
            throw new ChessException("The chosen piece is not yours");
        }
        if(!board.piece(position).isThereAnyPossibleMovie()) {
            throw new ChessException("There is no possible movies for chosen piece");
        }
    }

    private void validateTargetPosition(Position source, Position target) {
        if(!board.piece(source).possibleMovie(target)) {
            throw new ChessException("The piece chosen can't move to target position");
        }
    }

    private void nextTurn() {
        turn++;
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private Color opponent(Color color) {
        if (color == Color.WHITE) {
            return Color.BLACK;
        }
        return Color.WHITE;
    }

    private ChessPiece king(Color color) {
        List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
        for (Piece p:list) {
            if (p instanceof King) {
                return (ChessPiece) p;
            }
        }
        throw new IllegalStateException("There is no " + color + " king on the board");
    }
    private boolean testCheck(Color color) {
        Position kingPosition = king(color).getChessPosition().toPosition();
        List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());
        for (Piece p: opponentPieces) {
            boolean[][] mat = p.possibleMovies();
            if(mat[kingPosition.getRow()][kingPosition.getCollum()]) {
                return true;
            }
        }
        return false;
    }
    private void placeNewPiece(char collum, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(collum, row).toPosition());
        piecesOnTheBoard.add(piece);
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
