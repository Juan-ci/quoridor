package com.eda.quoridoreda;

import java.util.HashMap;
import org.json.JSONObject;
import java.util.Map;

public class Board {

    public static String showBoard(JSONObject json) {
        //Obtengo jsonObject data
        JSONObject data = json.getJSONObject("data");
        System.out.println("IMPRIMO data: " + data.toString());

        //Obtengo los turnos restantes, en caso de que quiero mostrarlo junto con el tablero
        int turn = data.getInt("remaining_moves");
        int totalTurn = 201 - turn;

        char[] board = data.getString("board").toCharArray();
        String gameId = data.getString("game_id");
        String side = data.getString("side");

        /*
            rowCol[0] = is row position
            rowCol[1] = is col position
         */
        String nameNPawn = "Pawn N ";
        String nameSPawn = "Pawn S ";

        //Lo utilizo para formar el nombre del peon, ej: Pawn N 1
        int numberPawnN = 0;
        int numberPawnS = 0;

        //Map use to save the name of pawn and the current position
        Map<String, int[]> positionPawns = new HashMap<>();

        //Armo el tablero a un array bidimensional
        char[][] normalizeBoard = armarTablero(board);

        System.out.println("GAME ID: " + gameId);
        System.out.println("SIDE: " + side);
        System.out.println("TURNO N°: " + totalTurn);

        System.out.println("  0 1 2 3 4 5 6 7 8");
        System.out.println("  =================");
        for (int i = 0; i < 17; i++) {
            if(i % 2 == 0) {
                System.out.print((i / 2) + "|");
            } else {
                System.out.print("  ");
            }
            for (int j = 0; j < 17; j++) {
                //Agregar array para guardar las posiciones de las paredes tambien?
                if (normalizeBoard[i][j] == 'N') {
                    int[] rowCol = new int[2];
                    numberPawnN++;
                    rowCol[0] = i;
                    rowCol[1] = j;
                    String keyNPawn = nameNPawn + String.valueOf(numberPawnN);
                    positionPawns.put(keyNPawn, rowCol);
                } else if (normalizeBoard[i][j] == 'S') {
                    int[] rowCol = new int[2];
                    numberPawnS++;
                    rowCol[0] = i;
                    rowCol[1] = j;
                    String keySPawn = nameSPawn + String.valueOf(numberPawnS);
                    positionPawns.put(keySPawn, rowCol);
                }
                System.out.print(normalizeBoard[i][j]);
            }
            System.out.print("\n");
        }
        System.out.println("  =================");
        System.out.println("  0 1 2 3 4 5 6 7 8");
        
        //Devolver json con respuesta de mover o poner pared
        String response = Pawn.movePawn(data, positionPawns, normalizeBoard);

        return response;
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
