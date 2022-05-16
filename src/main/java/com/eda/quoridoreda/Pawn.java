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

    // Function to move pawn N, receive jsonObject data, map with pawn name and position, board
    private static String northPawn(JSONObject json, Map<String, int[]> positionPawns, char[][] normalizeBoard) {
        String gameId = json.getString("game_id");
        System.out.println("GAME ID: " + gameId);
        String turnToken = json.getString("turn_token");
        System.out.println("TURN TOKEN: " + turnToken);
        char side = json.getString("side").charAt(0);
        int[] currentPositionPawnToMove;
        int[] nextPosition;

        //Current position pawn 1, in normalized board 17x17
        int currentRow = 0;
        int currentCol = 0;

        //Next position
        int nextRow = 0;
        int nextCol = 0;

        int[] pawnN1 = positionPawns.get("Pawn N 1");
        int[] pawnN2 = positionPawns.get("Pawn N 2");
        int[] pawnN3 = positionPawns.get("Pawn N 3");

        int[] pawnS1 = positionPawns.get("Pawn S 1");
        int[] pawnS2 = positionPawns.get("Pawn S 2");
        int[] pawnS3 = positionPawns.get("Pawn S 3");

        System.out.println("Llamo choosePawnToMove N");
        currentPositionPawnToMove = choosePawnToMove(side, pawnN1, pawnN2, pawnN3);
        currentRow = currentPositionPawnToMove[0];
        currentCol = currentPositionPawnToMove[1];

//        if (normalizeBoard[currentRow + 2][currentCol] == ' ') {
//            nextRow = currentRow + 2;
//            nextCol = currentCol;
//        } else if (normalizeBoard[currentRow + 2][currentCol] == 'S' && normalizeBoard[currentRow + 4][currentCol] == ' ') {
//            nextRow = currentRow + 4;
//            nextCol = currentCol;
//        }
        System.out.println("Estoy por entrar if checkMoveForward");
        if (checkMoveForward(normalizeBoard, currentPositionPawnToMove, side)) {
            System.out.println("Dentro if checkMoveForward");
            nextPosition = moveForward(normalizeBoard, currentPositionPawnToMove, side);

            nextRow = nextPosition[0];
            nextCol = nextPosition[1];
        }   //add check right, left, backward y other*

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
    private static String southPawn(JSONObject json, Map<String, int[]> positionPawns, char[][] normalizeBoard) {
        String gameId = json.getString("game_id");
        String turnToken = json.getString("turn_token");
        JSONObject jsonResponse = new JSONObject();
        char side = json.getString("side").charAt(0);
        int[] movePawn;

        //Current position pawn 1
        int currentRow = 0;
        int currentCol = 0;

        //Next position
        int nextRow = 0;
        int nextCol = 0;

        //En caso de usar a futuro las posiciones del peon oponente
        int[] pawnS1 = positionPawns.get("Pawn S 1");
        int[] pawnS2 = positionPawns.get("Pawn S 2");
        int[] pawnS3 = positionPawns.get("Pawn S 3");

        int[] pawnN1 = positionPawns.get("Pawn N 1");
        int[] pawnN2 = positionPawns.get("Pawn N 2");
        int[] pawnN3 = positionPawns.get("Pawn N 3");

        System.out.println("Llamo choosePawnToMove N");
        movePawn = choosePawnToMove(side, pawnS1, pawnS2, pawnS3);
        currentRow = movePawn[0];
        currentCol = movePawn[1];

//        if (normalizeBoard[currentRow - 1][currentCol] == ' ') {
//            nextRow = currentRow - 1;
//            nextCol = currentCol;
//        } else if (normalizeBoard[currentRow - 1][currentCol] == 'N' && normalizeBoard[currentRow - 2][currentCol] == ' ') {
//            nextRow = currentRow - 2;
//            nextCol = currentCol;
//        }
        currentRow /= 2;
        currentCol /= 2;
        nextRow /= 2;
        nextCol /= 2;

        //Armando json object 'data' response
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

    private static int[] choosePawnToMove(char namePawn, int[] pawn1, int[] pawn2, int[] pawn3) {

        switch (namePawn) {
            case 'N' -> {
                if (pawn1[0] > pawn2[0] && pawn1[0] > pawn3[0]) {
                    return pawn1;
                } else if (pawn2[0] > pawn3[0] && pawn2[0] > pawn1[0]) {
                    return pawn2;
                } else {    //Agregar else if si todos están en la misma fila
                    return pawn3;
                }
            }
            case 'S' -> {
                if (pawn1[0] < pawn2[0] && pawn1[0] < pawn3[0]) {   //Verifica cual está más adelantado
                    return pawn1;
                } else if (pawn2[0] < pawn3[0] && pawn2[0] < pawn1[0]) {
                    return pawn2;
                } else if (pawn1[0] == pawn2[0] && pawn1[0] == pawn3[0]) {  //Si todos estan iguales, avanza peon2
                    return pawn2;
                } else {
                    return pawn3;
                }
            }
            default -> {
                System.out.println("ERROR IN CLASS Pawn METHOD choosePawnToMove.");
                return null;
            }
        }
    }

    private static boolean checkMoveForward(char[][] normalizeBoard, int[] currentPosition, char pawn) {
        int currentRow = currentPosition[0];
        int currentCol = currentPosition[1];
        boolean moveAllowed = false;

        switch (pawn) {
            case 'N' -> {
                for (int i = currentRow + 1; i < 17; i++) {
                    if (normalizeBoard[i][currentCol] == '-') { //Check wall
                        return moveAllowed;
                    }
                    if (i < 12 && normalizeBoard[i + 1][currentCol] == 'S' //Check enemie
                            && normalizeBoard[i + 2][currentCol] == ' ' //Check there isn´t a wall
                            && normalizeBoard[i + 3][currentCol] == ' ') {
                        moveAllowed = true;
                        return moveAllowed;
                    }
                }
            }
            case 'S' -> {
                for (int i = currentRow - 1; i < 17; i--) {
                    if (normalizeBoard[i][currentCol] == '-') {
                        return moveAllowed;
                    }
                    if ( i > 4 && normalizeBoard[i - 1][currentCol] == 'N' //Check enemie
                            && normalizeBoard[i - 2][currentCol] == ' ' //Check there isn´t a wall
                            && normalizeBoard[i - 3][currentCol] == ' ') {  
                        moveAllowed = true;
                        return moveAllowed;
                    }
                }
            }
            default -> {
                System.out.println("ERROR IN CLASS Pawn METHOD moveForward");
                return false;
            }
        }
        moveAllowed = true;

        return moveAllowed;
    }

    private static boolean checkMoveRight(char[][] normalizeBoard, int[] currentPosition, char pawn) {
        int currentRow = currentPosition[0];
        int currentCol = currentPosition[1];
        boolean moveAllowed = false;

        switch (pawn) {
            case 'N': {
                //check 1 step right, if is empty then check 1 step forward
                for (int j = currentCol + 1; j < 17; j++) {
                    if (normalizeBoard[currentRow][j] == ' ' && j < 16) {            //Check if there isn´t a wall
                        if (normalizeBoard[currentRow][j + 1] == ' ' //Check if there isn´t a pawn enemie
                                && normalizeBoard[currentRow + 1][j + 1] == ' ') {  //Check if there isn´t also a wall forward
                            if (normalizeBoard[currentRow + 2][j + 1] == ' ') {
                                moveAllowed = true;
                                return moveAllowed;
                            } else if (normalizeBoard[currentRow + 2][j + 1] == 'S' //Check if there is a pawn opponent
                                    && normalizeBoard[currentRow + 3][j + 1] == ' ' //Check if there isn´t a wall
                                    && normalizeBoard[currentRow + 4][j + 1] == ' ' //Check if is empty
                                    && currentRow < 14) {                               //Check currentRow is inside de board
                                moveAllowed = true;
                                return moveAllowed;
                            }
                        } else if (currentRow < 13 && j < 14 //Check is inside still inside the board
                                && normalizeBoard[currentRow][j + 1] == 'S' //Check if there is enemie
                                && normalizeBoard[currentRow][j + 2] == ' ' //Check there isn´t a wall
                                && normalizeBoard[currentRow][j + 3] == ' ' //Check if there isn´t enemie
                                && normalizeBoard[currentRow + 1][j + 3] == ' ') {  //Check there isn´t a wall
                            if (normalizeBoard[currentRow + 2][j + 3] == ' ') {
                                moveAllowed = true;
                                return moveAllowed;
                            } else if (normalizeBoard[currentRow + 2][j + 3] == 'S' //Check if there is enemie
                                    && normalizeBoard[currentRow + 3][j + 3] == ' ' //Check there isn´t a wall
                                    && normalizeBoard[currentRow + 4][j + 3] == ' ') {  //Check if there isn´t enemie
                                moveAllowed = true;
                                return moveAllowed;
                            }
                        }
                    }
                }
            }
            case 'S': {
                for (int j = currentCol + 1; j < 17; j++) {
                    if (normalizeBoard[currentRow][j] == ' ' && j < 16) {            //Check if there isn´t a wall
                        if (normalizeBoard[currentRow][j + 1] == ' ' //Check if there isn´t a pawn enemie
                                && normalizeBoard[currentRow - 1][j + 1] == ' ') {  //Check if there isn´t also a wall forward
                            if (normalizeBoard[currentRow - 2][j + 1] == ' ') {
                                moveAllowed = true;
                                return moveAllowed;
                            } else if (normalizeBoard[currentRow - 2][j + 1] == 'S' //Check if there is a pawn opponent
                                    && normalizeBoard[currentRow - 3][j + 1] == ' ' //Check if there isn´t a wall
                                    && normalizeBoard[currentRow - 4][j + 1] == ' ' //Check if is empty
                                    && currentRow > 3) {                               //Check currentRow is inside de board
                                moveAllowed = true;
                                return moveAllowed;
                            }
                        } else if (currentRow > 4 && j < 14 //Check is inside still inside the board
                                && normalizeBoard[currentRow][j + 1] == 'S' //Check if there is enemie
                                && normalizeBoard[currentRow][j + 2] == ' ' //Check there isn´t a wall
                                && normalizeBoard[currentRow][j + 3] == ' ' //Check if there isn´t enemie
                                && normalizeBoard[currentRow + 1][j + 3] == ' ') {  //Check there isn´t a wall
                            if (normalizeBoard[currentRow - 2][j + 3] == ' ') {
                                moveAllowed = true;
                                return moveAllowed;
                            } else if (normalizeBoard[currentRow - 2][j + 3] == 'S' //Check if there is enemie
                                    && normalizeBoard[currentRow - 3][j + 3] == ' ' //Check there isn´t a wall
                                    && normalizeBoard[currentRow - 4][j + 3] == ' ') {  //Check if there isn´t enemie
                                moveAllowed = true;
                                return moveAllowed;
                            }
                        }
                    }
                }
            }
            default: {
                System.out.println("ERROR IN CLASS Pawn METHOD checkMoveRight.");
            }
        }
        return false;
    }

    //Move
    private static int[] moveForward(char[][] normalizeBoard, int[] currentPosition, char side) {
        System.out.println("Move Forward");
        int currentRow = currentPosition[0];
        int currentCol = currentPosition[1];
        int[] nextPosition = new int[2];

        //Agregar switch N, S para mover adelante
        if (normalizeBoard[currentRow + 2][currentCol] == ' ') {
            System.out.println("Single Step");
            //Single step
            nextPosition[0] = currentRow + 2;
            nextPosition[1] = currentCol;
        } else if (normalizeBoard[currentRow + 2][currentCol] == 'S') {
            System.out.println("Step to jump");
            //Next position to jump
            nextPosition[0] = currentRow + 4;
            nextPosition[1] = currentCol;
        }
        return nextPosition;
    }
}
