package application;

import chess.ChessPiece;

public class UI {
    public static void printBoard(ChessPiece[][] pieces) {
        for (int i = 0; i < pieces.length ; i++) {
            System.out.print((8 - i) + "ㅤ");
            for (int j = 0; j < pieces.length; j++) {
                printPiece(pieces[i][j]);
            }
            System.out.println();
        }
        System.out.println("ㅤ Aㅤ Bㅤ Cㅤ Dㅤ Eㅤ Fㅤ Gㅤ H");
    }

    private static void printPiece(ChessPiece piece) {
        if (piece == null) {
            System.out.print("—ㅤ");
        } else {
            System.out.print(piece +  " ");
        }
        System.out.print(" ");
    }
}
