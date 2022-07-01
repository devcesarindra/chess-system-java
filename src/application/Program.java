package application;

import boardgame.Board;
import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();
        List<ChessPiece> captured = new ArrayList<>();

        while(!chessMatch.getCheckMate()) {
            try {
                UI.clearScreen();
                UI.printMatch(chessMatch, captured);
                System.out.println();
                System.out.println("Source: ");
                ChessPosition source = UI.readChessPosition(sc);
                boolean[][] possibleMovies = chessMatch.possibleMovies(source);
                UI.clearScreen();
                UI.printBoard(chessMatch.getPieces(), possibleMovies);

                System.out.println();
                System.out.println("Target: ");
                ChessPosition target = UI.readChessPosition(sc);

                ChessPiece capturedPiece = chessMatch.performChessMovie(source, target);
                if(capturedPiece != null) {
                    captured.add(capturedPiece);
                }
                if(chessMatch.getPromoted() != null) {
                    System.out.println("Enter piece for promotion (B/T/C/R)");
                    String type = sc.nextLine().toUpperCase();
                    while(!type.equals("B") && !type.equals("C") && !type.equals("B") && !type.equals("T") && !type.equals("R")) {
                        System.out.println("Invalid value!. Enter piece for promotion (B/T/C/R)");
                        type = sc.nextLine().toUpperCase();
                    }
                    chessMatch.replacePromotedPiece(type);
                }
            } catch (ChessException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }
        UI.clearScreen();
        UI.printMatch(chessMatch, captured);
    }
}
