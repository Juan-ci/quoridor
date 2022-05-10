package com.eda.quoridoreda;

import org.json.JSONObject;

public class Board {

    public static void showBoard(JSONObject json) {
        JSONObject data = json.getJSONObject("data");
        char[] board = data.getString("board").toCharArray();
        
        //Armo el tablero a un array bidimensional
        char[][] normalizeBoard = armarTablero(board);
        
        //Muestro el tablero normalizado
        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 17; j++) {
                System.out.print(normalizeBoard[i][j]);
            }
            System.out.print("\n");
        }
    }

    public static char[][] armarTablero(char[] simpleBoard) {
        char[][] boardReady = new char[17][17];
        int count = 0;

        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 17; j++) {
                boardReady[i][j] = simpleBoard[count];
                count += 1;
            }
        }
        return boardReady;
    }
}
