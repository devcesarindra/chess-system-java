package application;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UI {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\033[1;97m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_GRAY = "\033[1;90m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    private static boolean alterColorBoard = false;

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static ChessPosition readChessPosition(Scanner sc) {
        try {
            String s = sc.nextLine();
            char collum = s.charAt(0);
            int row = Integer.parseInt(s.substring(1));
            return new ChessPosition(collum, row);
        }
        catch (RuntimeException e) {
            throw new InputMismatchException("Error reading ChessPosition. Valid values are from a1 to h8.");
        }
    }
    public static void printMatch(ChessMatch chessMatch, List<ChessPiece> captured) {
        printBoard(chessMatch.getPieces());
        System.out.println();
        printCapturedPieces(captured);
        System.out.println();
        System.out.println("Turn: " + chessMatch.getTurn());
        if(!chessMatch.getCheckMate()) {
            System.out.println("Waiting player: " + chessMatch.getCurrentPlayer());
            if(chessMatch.getCheck()) {
                System.out.println(ANSI_YELLOW + "CHECK!!!" + ANSI_RESET);
            }
        } else {
            System.out.println(ANSI_GREEN + "!!!CHECKMATE!!!" + ANSI_RESET);
            System.out.println("Winner: " + chessMatch.getCurrentPlayer());
        }
    }
    public static void printBoard(ChessPiece[][] pieces) {
        for (int i = 0; i < pieces.length ; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < pieces.length; j++) {
                printPiece(pieces[i][j], false);
                alterColorBoard = !alterColorBoard;
                if(i == 8 || j == 7) {
                    alterColorBoard = !alterColorBoard;
                };
            }
            System.out.println();
        }
        System.out.println("  ㅤAㅤ Bㅤ Cㅤ Dㅤ  Eㅤ Fㅤ Gㅤ H");
    }

    public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMovies)  {
        for (int i = 0; i < pieces.length ; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < pieces.length; j++) {
                printPiece(pieces[i][j], possibleMovies[i][j]);
                alterColorBoard = !alterColorBoard;
                if(i == 8 || j == 7) {
                    alterColorBoard = !alterColorBoard;
                };
            }
            System.out.println();
        }
        System.out.println("  ㅤAㅤ Bㅤ Cㅤ Dㅤ Eㅤ Fㅤ Gㅤ H");
    }

    private static void printPiece(ChessPiece piece, boolean background) {
        if (piece == null) {
            if(background) {
                System.out.print(ANSI_BLUE_BACKGROUND+ "ㅤㅤ " + ANSI_RESET);
            } else {
                if (alterColorBoard)  {
                    System.out.print(ANSI_WHITE_BACKGROUND+"ㅤㅤ " + ANSI_RESET);
                } else {
                    System.out.print(ANSI_BLACK_BACKGROUND+ "ㅤㅤ " + ANSI_RESET);
                }
            }
        } else {

            if(piece.getColor() == Color.BLACK) {
                if(background) {
                    System.out.print(ANSI_BLUE_BACKGROUND+" "+ANSI_GRAY+piece+ " "+ ANSI_RESET );
                } else {
                    if(alterColorBoard) {
                        System.out.print(ANSI_WHITE_BACKGROUND+" "+ANSI_GRAY+piece+ " "+ ANSI_RESET );
                    } else {
                        System.out.print(ANSI_BLACK_BACKGROUND+" "+ANSI_GRAY+piece+ " "+ ANSI_RESET );
                    }
                }
            } else {
                if(background)  {
                    System.out.print(ANSI_BLUE_BACKGROUND+" "+ANSI_WHITE+piece+ " "+ ANSI_RESET );
                } else {
                    if(alterColorBoard) {
                        System.out.print(ANSI_WHITE_BACKGROUND+" "+ ANSI_WHITE+piece + " "+ ANSI_RESET);
                    } else {
                        System.out.print(ANSI_BLACK_BACKGROUND+" "+ ANSI_WHITE+piece+ " "+ ANSI_RESET );
                    }
                }
            }
        }
    }

    private static void printCapturedPieces(List<ChessPiece> captured) {
        List<ChessPiece> white = captured.stream().filter(x -> x.getColor() == Color.WHITE).collect(Collectors.toList());
        List<ChessPiece> black = captured.stream().filter(x -> x.getColor() == Color.BLACK).collect(Collectors.toList());
        System.out.println("Captured Pieces: ");
        System.out.print("WHITE: ");
        System.out.print(ANSI_WHITE);
        System.out.println(Arrays.toString(white.toArray()));
        System.out.print(ANSI_RESET);

        System.out.print("BLACK: ");
        System.out.print(ANSI_BLACK);
        System.out.println(Arrays.toString(black.toArray()));
        System.out.print(ANSI_RESET);
    }
}
