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

        currentPositionPawnToMove = choosePawnToMove(side, pawnN1, pawnN2, pawnN3);
        currentRow = currentPositionPawnToMove[0];
        currentCol = currentPositionPawnToMove[1];

        if (checkMoveForward(normalizeBoard, currentPositionPawnToMove, side)) {
            nextPosition = moveForward(normalizeBoard, currentPositionPawnToMove, side);

            nextRow = nextPosition[0];
            nextCol = nextPosition[1];
        } else if (checkMoveLeft(normalizeBoard, currentPositionPawnToMove, side)) {
            nextPosition = moveLeft(normalizeBoard, currentPositionPawnToMove, side);

            nextRow = nextPosition[0];
            nextCol = nextPosition[1];
        } else if (checkMoveRight(normalizeBoard, currentPositionPawnToMove, side)) {
            nextPosition = moveRight(normalizeBoard, currentPositionPawnToMove, side);

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

        String response = jsonResponse.toString();

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

        if (checkMoveForward(normalizeBoard, currentPositionPawnToMove, side)) {
            nextPosition = moveForward(normalizeBoard, currentPositionPawnToMove, side);

            nextRow = nextPosition[0];
            nextCol = nextPosition[1];
        } else if (checkMoveRight(normalizeBoard, currentPositionPawnToMove, side)) {
            nextPosition = moveRight(normalizeBoard, currentPositionPawnToMove, side);

            nextRow = nextPosition[0];
            nextCol = nextPosition[1];
        } else if (checkMoveLeft(normalizeBoard, currentPositionPawnToMove, side)) {
            nextPosition = moveLeft(normalizeBoard, currentPositionPawnToMove, side);

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

    private static int[] choosePawnToMove(char namePawn, int[] pawn1, int[] pawn2, int[] pawn3) {

        switch (namePawn) {
            case 'N' -> {
                if (pawn1[0] == pawn2[0] && pawn1[0] == pawn3[0]) {
                    return pickRandomPawn(pawn1, pawn2, pawn3);
                }
                if (pawn1[0] > pawn2[0] && pawn1[0] > pawn3[0]) {
                    return pawn1;
                } else if (pawn2[0] > pawn3[0] && pawn2[0] > pawn1[0]) {
                    return pawn2;
                } else {
                    return pawn3;
                }
            }
            case 'S' -> {
                if (pawn1[0] == pawn2[0] && pawn1[0] == pawn3[0]) {
                    return pickRandomPawn(pawn1, pawn2, pawn3);
                }
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

    private static int[] pickRandomPawn(int[] pawn1, int[] pawn2, int[] pawn3) {
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

    private static boolean checkMoveForward(char[][] normalizeBoard, int[] currentPosition, char pawn) {
        int currentRow = currentPosition[0];
        int currentCol = currentPosition[1];
        boolean moveAllowed = false;

        System.out.println("Checking move forward...");
        switch (pawn) {
            case 'N' -> {
                if (normalizeBoard[currentRow + 2][currentCol] == 'S' && (currentRow + 2) == 16) {
                    return moveAllowed;
                }
                for (int i = currentRow + 1; i < 17; i++) {
                    if (normalizeBoard[i][currentCol] == '-') { //Check wall
                        return moveAllowed;
                    }
                    if (i < 13 && normalizeBoard[i + 1][currentCol] == 'S' //Check enemie
                            && normalizeBoard[i + 2][currentCol] == ' ' //Check there isn´t a wall
                            && normalizeBoard[i + 3][currentCol] == ' ') {
                        moveAllowed = true;
                        return moveAllowed;
                    }
                }
            }
            case 'S' -> {
                if (normalizeBoard[currentRow - 2][currentCol] == 'N' && (currentRow - 2) == 0) {
                    return moveAllowed;
                }
                for (int i = currentRow - 1; i > 0; i--) {
                    if (normalizeBoard[i][currentCol] == '-') {
                        return moveAllowed;
                    }
                    if (i > 4 && normalizeBoard[i - 1][currentCol] == 'N' //Check enemie
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

    private static boolean checkMoveRight(char[][] normalizeBoard, int[] currentPosition, char side) {
        int currentRow = currentPosition[0];
        int currentCol = currentPosition[1];
        boolean moveAllowed = false;

        System.out.println("Checking move right...");
        switch (side) {
            case 'N': {
                //check 1 step right, if is empty then check 1 step forward
                if (currentCol == 16) {
                    return moveAllowed;
                }
                for (int j = currentCol + 1; j < 17; j++) {
                    if (normalizeBoard[currentRow][j] == ' ' && j < 17) {            //Check if there isn´t a wall
                        if (normalizeBoard[currentRow][j + 1] == ' ' //Check if there isn´t a pawn enemie
                                && normalizeBoard[currentRow + 1][j + 1] == ' ') {  //Check if there isn´t also a wall forward
                            moveAllowed = true;
                            return moveAllowed;
                        }
                    }
                }
            }
            case 'S': {
                if (currentCol == 16) {
                    return moveAllowed;
                }
                for (int j = currentCol + 1; j < 17; j++) {
                    if (normalizeBoard[currentRow][j] == ' ' && j < 16) {            //Check if there isn´t a wall
                        if (normalizeBoard[currentRow][j + 1] == ' ' //Check if there isn´t a pawn enemie
                                && normalizeBoard[currentRow - 1][j + 1] == ' ') {  //Check if there isn´t also a wall forward to next move
                            moveAllowed = true;
                            return moveAllowed;
                        }
                    }
                }
            }
            default: {
                System.out.println("ERROR IN CLASS Pawn METHOD checkMoveRight.");
            }
        }
        return moveAllowed;
    }

    private static boolean checkMoveLeft(char[][] normalizeBoard, int[] currentPosition, char side) {
        int currentRow = currentPosition[0];
        int currentCol = currentPosition[1];
        boolean moveAllowed = false;

        System.out.println("Checking move left...");
        switch (side) {
            case 'N': {
                //check 1 step right, if is empty then check 1 step forward
                if (currentCol == 0) {
                    return moveAllowed;
                }
                for (int j = currentCol - 1; j > 0; j--) {
                    if (normalizeBoard[currentRow][j] == ' ' && j > 0) {            //Check if there isn´t a wall
                        if (normalizeBoard[currentRow][j - 1] == ' ' //Check if there isn´t a pawn enemie
                                && normalizeBoard[currentRow + 1][j - 1] == ' ') {  //Check if there isn´t also a wall forward
                            moveAllowed = true;
                            return moveAllowed;
                        }
                    }
                }
            }
            case 'S': {
                if (currentCol == 0) {
                    return moveAllowed;
                }
                for (int j = currentCol - 1; j > 0; j--) {
                    if (normalizeBoard[currentRow][j] == ' ' && j > 0) {    //Check if there isn´t a wall
                        if (normalizeBoard[currentRow][j - 1] == ' ' //Check if there isn´t a pawn enemie
                                && normalizeBoard[currentRow - 1][j - 1] == ' ') {  //Check if there isn´t also a wall forward to next move
                            moveAllowed = true;
                            return moveAllowed;
                        }
                    }
                }
            }
            default: {
                System.out.println("ERROR IN CLASS Pawn METHOD checkMoveLeft.");
            }
        }
        return moveAllowed;
    }

    //Move
    private static int[] moveForward(char[][] normalizeBoard, int[] currentPosition, char side) {
        System.out.println("Move Forward");
        int currentRow = currentPosition[0];
        int currentCol = currentPosition[1];
        int[] nextPosition = new int[2];

        //Agregar switch N, S para mover adelante
        switch (side) {
            case 'N' -> {
                if (normalizeBoard[currentRow + 2][currentCol] == ' ') {
                    System.out.println("Single Step");
                    //Single step
                    nextPosition[0] = currentRow + 2;
                    nextPosition[1] = currentCol;
                }
                if (normalizeBoard[currentRow + 2][currentCol] == 'S') {
                    if (currentRow + 4 < 17) {   //Check is inside the board
                        System.out.println("Step to jump");
                        //Next position to jump
                        nextPosition[0] = currentRow + 4;
                        nextPosition[1] = currentCol;
                    }
                }
            }
            case 'S' -> {
                if (normalizeBoard[currentRow - 2][currentCol] == ' ') {
                    System.out.println("Single Step");
                    //Single step
                    nextPosition[0] = currentRow - 2;
                    nextPosition[1] = currentCol;
                }
                if (normalizeBoard[currentRow - 2][currentCol] == 'N') {
                    if (currentRow - 4 > 0) {   //Check is inside the board
                        System.out.println("Step to jump");
                        //Next position to jump
                        nextPosition[0] = currentRow - 4;
                        nextPosition[1] = currentCol;
                    }
                }
            }
            default -> {
                System.out.println("ERROR IN moveForward CLASS Pawn.");
            }
        }
        return nextPosition;
    }

    private static int[] moveRight(char[][] normalizeBoard, int[] currentPosition, char side) {
        System.out.println("Move Right");
        int currentRow = currentPosition[0];
        int currentCol = currentPosition[1];
        int[] nextPosition = new int[2];

        //Agregar switch N, S para mover adelante
        switch (side) {
            case 'N' -> {
                if (normalizeBoard[currentRow][currentCol + 2] == ' ') {
                    System.out.println("Single right step");
                    //Single step
                    nextPosition[0] = currentRow;
                    nextPosition[1] = currentCol + 2;
                }
            }
            case 'S' -> {
                if (normalizeBoard[currentRow][currentCol + 2] == ' ') {
                    System.out.println("Single right step");
                    //Single step
                    nextPosition[0] = currentRow;
                    nextPosition[1] = currentCol + 2;
                }
            }
            default -> {
                System.out.println("ERROR IN moveForward CLASS Pawn.");
            }
        }
        return nextPosition;
    }

    private static int[] moveLeft(char[][] normalizeBoard, int[] currentPosition, char side) {
        System.out.println("Move Left");
        int currentRow = currentPosition[0];
        int currentCol = currentPosition[1];
        int[] nextPosition = new int[2];

        //Agregar switch N, S para mover adelante
        switch (side) {
            case 'N' -> {
                if (normalizeBoard[currentRow][currentCol - 2] == ' ') {
                    System.out.println("Single left step");
                    //Single step
                    nextPosition[0] = currentRow;
                    nextPosition[1] = currentCol - 2;
                }
            }
            case 'S' -> {
                if (normalizeBoard[currentRow][currentCol - 2] == ' ') {
                    System.out.println("Single left step");
                    //Single step
                    nextPosition[0] = currentRow;
                    nextPosition[1] = currentCol - 2;
                }
            }
            default -> {
                System.out.println("ERROR IN moveForward CLASS Pawn.");
            }
        }
        return nextPosition;
    }

    private static int[] moveDiagonal(char[][] normalizeBoard, int[] currentPosition, char side) {
        int row = currentPosition[0];
        int col = currentPosition[1];
        int[] nextPosition = new int[2];

        //Move for N
        switch (side) {
            case 'N' -> {
                if ((row + 1) < 17) { //Check move forward is inside the board
                    if ((col + 1) < 17 && normalizeBoard[row + 1][col + 1] == ' ' //Check diagonal col is inside the board and there isn´t a wall 
                            && normalizeBoard[row + 2][col + 2] == ' ') {    //Check next position is empty
                        System.out.println("MOVE DIAGONAL DERECHA N");
                        nextPosition[0] = row + 2;
                        nextPosition[1] = col + 2;
                    } else if ((col - 1) > 0 && normalizeBoard[row + 1][col - 1] == ' ' //Check there isn´t a wall and is inside the board
                            && normalizeBoard[row + 2][col - 2] == ' ') {    //Check next position is empty
                        System.out.println("MOVE DIAGONAL IZQUIERDA N");
                        nextPosition[0] = row + 2;
                        nextPosition[1] = col - 2;
                    }
                }
            }
            case 'S' -> {
                if ((row - 1) >= 0) {
                    if ((col - 1) >= 0 && normalizeBoard[row - 1][col - 1] == ' ' //Check there isn´t a wall and is inside the board
                            && normalizeBoard[row - 2][col - 2] == ' ') {    //Check next position is empty
                        System.out.println("MOVE DIAGONAL IZQUIERDA S");
                        nextPosition[0] = row - 2;
                        nextPosition[1] = col - 2;
                    }
                }
            }
            default -> {
                System.out.println("ERROR IN METHOD moveDiagonal");
            }
        }
        System.out.println("NEXT row move Diagonal " + nextPosition[0]);

        return nextPosition;
    }
}
