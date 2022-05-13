package com.eda.quoridoreda;

import java.util.Map;
import org.json.JSONObject;

public class Pawn {

    //Recibo jsonObject "data"
    public static String movePawn(JSONObject data, Map<String, int[]> positionPawns, char[][] normalizeBoard) {
        String namePawn = data.getString("side"); // obtener data.sidew
        String response = null;

        switch (namePawn) {
            case "N" -> {
                System.out.println("Estoy en switch case N");
                response = northPawn(data, positionPawns, normalizeBoard);
            }
            case "S" -> {
                System.out.println("Estoy en switch case S");
                response = southPawn(data, positionPawns, normalizeBoard);
            }
            default ->
                System.out.println("Error when tried to define the pawn name, must be N or S.");
        }
        return response;
    }

    // Function to move pawn N, recibo jsonObject data, map con nombre peon y posicion, y tablero
    public static String northPawn(JSONObject json, Map<String, int[]> positionPawns, char[][] normalizeBoard) {
        //Mover hacia adelante en primer momento, incrementando índice i
        String gameId = json.getString("game_id");
        System.out.println("GAME ID: " + gameId);
        String turnToken = json.getString("turn_token");
        System.out.println("TURN TOKEN: " + turnToken);

        //Next position
        int nextRow = 0;
        int nextCol = 0;

        int[] pawnN1 = positionPawns.get("Pawn N 1");

        System.out.println("Pawn a mover " + normalizeBoard[pawnN1[0]][pawnN1[1]]);

        //Current position pawn 1, in normalized board 17x17
        int currentRow = pawnN1[0];
        int currentCol = pawnN1[1];
        System.out.println("Fila de N 1: " + currentRow + " Columna de N: " + currentCol);
        
        int[] pawnN2 = positionPawns.get("Pawn N 2");
        System.out.println("Fila de N 2: " + pawnN2[0] + " Columna de N: " + pawnN2[1]);
        
        int[] pawnN3 = positionPawns.get("Pawn N 3");
        System.out.println("Fila de N 3: " + pawnN3[0] + " Columna de N: " + pawnN3[1]);

        /*
            En caso de usar a futuro las posiciones del peon oponente
            int[] pawnS1 = positionPawns.get("Pawn S 1");
            int[] pawnS2 = positionPawns.get("Pawn S 2");
            int[] pawnS3 = positionPawns.get("Pawn S 3");
         */
        //agregar verificación de pared entre medio del peon oponente
        if (normalizeBoard[currentRow + 2][currentCol] == ' ') {
            nextRow = currentRow + 2;
            nextCol = currentCol;
        } else if (normalizeBoard[currentRow + 2][currentCol] == 'S' && normalizeBoard[currentRow + 4][currentCol] == ' ') {
            nextRow = currentRow + 4;
            nextCol = currentCol;
        }
        
        currentRow /= 2;
        currentCol /= 2;
        nextRow /= 2;
        nextCol /= 2;

        System.out.println("NextRow " + nextRow + " NextCol " + nextCol);
        //Armando json object 'data'
        JSONObject data = new JSONObject();
        data.put("to_col", nextCol);
        data.put("turn_token", turnToken);
        data.put("game_id", gameId);
        data.put("to_row", nextRow);
        data.put("from_col", currentCol);
        data.put("from_row", currentRow);

        //Armando json de respuesta de movimiento
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("action", "move");
        jsonResponse.put("data", data);

        String response = jsonResponse.toString();

        return response;
    }

    // Function to move pawn S
    public static String southPawn(JSONObject json, Map<String, int[]> positionPawns, char[][] normalizeBoard) {
        //Mover hacia adelante en primer momento, decrementando índice i

        String gameId = json.getString("game_id");
        String turnToken = json.getString("turn_token");
        JSONObject jsonResponse = new JSONObject();

        //Next position
        int nextRow = 0;
        int nextCol = 0;

        int[] pawnN1 = positionPawns.get("Pawn N 1");
        //Current position pawn 1
        int[] pawnN2 = positionPawns.get("Pawn N 2");
        int[] pawnN3 = positionPawns.get("Pawn N 3");

        //En caso de usar a futuro las posiciones del peon oponente
        int[] pawnS1 = positionPawns.get("Pawn S 1");
        //Current position pawn 1
        int currentRow = pawnS1[0] / 2;
        int currentCol = pawnS1[1] / 2;
        System.out.println("Fila de S: " + pawnS1[0] + " Columna de S: " + pawnS1[1]);
        int[] pawnS2 = positionPawns.get("Pawn S 2");
        System.out.println("Fila de S: " + pawnS2[0] + " Columna de S: " + pawnS2[1]);
        int[] pawnS3 = positionPawns.get("Pawn S 3");
        System.out.println("Fila de S: " + pawnS3[0] + " Columna de S: " + pawnS3[1]);

        if (normalizeBoard[currentRow - 1][currentCol] == ' ') {
            nextRow = currentRow - 1;
            nextCol = currentCol;
        } else if (normalizeBoard[currentRow - 1][currentCol] == 'N' && normalizeBoard[currentRow - 2][currentCol] == ' ') {
            nextRow = currentRow - 2;
            nextCol = currentCol;
        }

        /*
            AGREGAR EL MOSTRAR TABLERO Y VER SI MUEVE LA FICHA------
         */
        //Armando json object 'data'
        JSONObject data = new JSONObject();
        data.put("game_id", gameId);
        data.put("turn_token", turnToken);
        data.put("from_row", currentRow);
        data.put("from_col", currentCol);
        data.put("to_row", nextRow);
        data.put("to_col", nextCol);

        //Armando json de respuesta de movimiento
        jsonResponse.put("action", "move");
        jsonResponse.put("data", data);

        return jsonResponse.toString();
    }

    public int[] checkNextPosition(int[] currentPosition, String namePawn) {
        /*
        Si está vacío, ir hacia adelante, si hay un peon contrario, saltarlo,
        y si está en la úlitma fila, mover para el costado
         */

        return null;
    }
}
