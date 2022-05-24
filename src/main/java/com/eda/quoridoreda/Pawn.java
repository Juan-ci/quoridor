package com.eda.quoridoreda;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import org.json.JSONObject;

public class Pawn {

    //Recibo jsonObject "data"
    public static String movePawn(JSONObject data, Map<String, int[]> positionPawns, char[][] normalizeBoard) {
        String namePawn = data.getString("side"); // obtener data.sidew
        String response = null;

        switch (namePawn) {
            case "N" -> {
                response = northPawn(data, positionPawns, normalizeBoard);
            }
            case "S" -> {
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
        int[] currentPositionPawnToMove = new int[2];
        int[] nextPosition = new int[2];
        String response = null;

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

        int[] pawnEnemiePosition = Checking.checkPositionPawnEnemie(side, pawnS1, pawnS2, pawnS3);

//        if (pawnEnemiePosition[0] == 10) {  //Agregar check de wall
//            System.out.println("ENTRO IF PUT WALL");
//            response = Wall.putWall(side, pawnEnemiePosition, gameId, turnToken);
//        } else {
            currentPositionPawnToMove = choosePawnToMove(side, pawnN1, pawnN2, pawnN3);
            currentRow = currentPositionPawnToMove[0];
            currentCol = currentPositionPawnToMove[1];

            if (Checking.checkMoveForward(normalizeBoard, currentPositionPawnToMove, side)) {
                nextPosition = Move.moveForward(normalizeBoard, currentPositionPawnToMove, side);

                nextRow = nextPosition[0];
                nextCol = nextPosition[1];
            } else if (Checking.checkMoveLeftOneStep(normalizeBoard, currentPositionPawnToMove, side)) {
                nextPosition = Move.moveLeft(normalizeBoard, currentPositionPawnToMove, side);

                nextRow = nextPosition[0];
                nextCol = nextPosition[1];
            } else if (Checking.checkMoveRightOneStep(normalizeBoard, currentPositionPawnToMove, side)) {
                nextPosition = Move.moveRight(normalizeBoard, currentPositionPawnToMove, side);

                nextRow = nextPosition[0];
                nextCol = nextPosition[1];
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

            response = jsonResponse.toString();
//        }

        return response;
    }

    // Function to move pawn S
    private static String southPawn(JSONObject json, Map<String, int[]> positionPawns, char[][] normalizeBoard) {
        String gameId = json.getString("game_id");
        String turnToken = json.getString("turn_token");
        JSONObject jsonResponse = new JSONObject();
        char side = json.getString("side").charAt(0);
        int[] currentPositionPawnToMove = new int[2];
        int[] nextPosition = new int[2];

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

        currentPositionPawnToMove = choosePawnToMove(side, pawnS1, pawnS2, pawnS3);
        currentRow = currentPositionPawnToMove[0];
        currentCol = currentPositionPawnToMove[1];

        if (Checking.checkMoveForward(normalizeBoard, currentPositionPawnToMove, side)) {
            nextPosition = Move.moveForward(normalizeBoard, currentPositionPawnToMove, side);

            nextRow = nextPosition[0];
            nextCol = nextPosition[1];
        } else if (Checking.checkMoveRightOneStep(normalizeBoard, currentPositionPawnToMove, side)) {
            nextPosition = Move.moveRight(normalizeBoard, currentPositionPawnToMove, side);

            nextRow = nextPosition[0];
            nextCol = nextPosition[1];
        } else if (Checking.checkMoveLeftOneStep(normalizeBoard, currentPositionPawnToMove, side)) {
            nextPosition = Move.moveLeft(normalizeBoard, currentPositionPawnToMove, side);

            nextRow = nextPosition[0];
            nextCol = nextPosition[1];
        }

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

    public static int[] choosePawnToMove(char namePawn, int[] pawn1, int[] pawn2, int[] pawn3) {

        if (pawn1[0] == pawn2[0] && pawn1[0] == pawn3[0]) {
            return pickRandomPawn(pawn1, pawn2, pawn3);
        }
        switch (namePawn) {
            case 'N' -> {
                if (pawn1[0] > pawn2[0] && pawn1[0] > pawn3[0]) {
                    return pawn1;
                } else if (pawn2[0] > pawn3[0] && pawn2[0] > pawn1[0]) {
                    return pawn2;
                } else {
                    return pawn3;
                }
            }
            case 'S' -> {
                if (pawn1[0] < pawn2[0] && pawn1[0] < pawn3[0]) {   //Verifica cual está más adelantado
                    return pawn1;
                } else if (pawn2[0] < pawn3[0] && pawn2[0] < pawn1[0]) {
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

    public static int[] pickRandomPawn(int[] pawn1, int[] pawn2, int[] pawn3) {
        int min = 1;
        int max = 3;

        int getRandomValue = ThreadLocalRandom.current().nextInt(0, max) + min;
        System.out.println("Pawn n° " + getRandomValue + " selected.");

        switch (getRandomValue) {
            case 1 -> {
                return pawn1;
            }
            case 2 -> {
                return pawn2;
            }
            case 3 -> {
                return pawn3;
            }
            default -> {
                System.out.println("ERROR PICKING RANDOM PAWN.");
                return pawn2;
            }
        }
    }
}
