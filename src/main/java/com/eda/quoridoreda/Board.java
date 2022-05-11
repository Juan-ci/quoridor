package com.eda.quoridoreda;

import org.json.JSONObject;

public class Board {

    public static JSONObject showBoard(JSONObject json) {
        JSONObject data = json.getJSONObject("data");
        int turn = Integer.parseInt(data.getString("remaining_moves"));
        int totalTurn = 200 - turn;
        char[] board = data.getString("board").toCharArray();
        String gameId = data.getString("game_id");
        
        //Armo el tablero a un array bidimensional
        char[][] normalizeBoard = armarTablero(board);
        
        System.out.println("Game id: " + gameId);
        System.out.println("Turno n°: " + totalTurn);
        //Muestro el tablero normalizado
        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 17; j++) {
                System.out.print(normalizeBoard[i][j]);
            }
            System.out.print("\n");
        }
        
        
        //Devolver json con respuesta de mover o poner pared
        return null;
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
