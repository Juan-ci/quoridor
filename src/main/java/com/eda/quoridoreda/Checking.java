package com.eda.quoridoreda;

public class Checking {

    public static boolean checkMoveForward(char[][] normalizeBoard, int[] currentPosition, char pawn) {
        int currentRow = currentPosition[0];
        int currentCol = currentPosition[1];
        boolean moveAllowed = false;

        System.out.println("Checking move forward...");
        switch (pawn) {
            case 'N' -> {
                if (normalizeBoard[currentRow + 1][currentCol] == '-') { //Check wall
                    return moveAllowed;
                }
                if (normalizeBoard[currentRow + 2][currentCol] == 'S' && (currentRow + 2) == 16) {
                    return moveAllowed;
                }
                for (int i = currentRow + 1; i < 17; i++) {
                    if (i < 13 && normalizeBoard[i + 1][currentCol] == 'S' //Check enemie
                            && normalizeBoard[i + 2][currentCol] == ' ' //Check there isn´t a wall
                            && normalizeBoard[i + 3][currentCol] == ' ') {
                        moveAllowed = true;
                        return moveAllowed;
                    }
                }
            }
            case 'S' -> {
                if (normalizeBoard[currentRow - 1][currentCol] == '-') {
                    return moveAllowed;
                }
                if (normalizeBoard[currentRow - 2][currentCol] == 'N' && (currentRow - 2) == 0) {
                    return moveAllowed;
                }
                for (int i = currentRow - 1; i > 0; i--) {
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

    public static boolean checkMoveRight(char[][] normalizeBoard, int[] currentPosition, char side) {
        int currentRow = currentPosition[0];
        int currentCol = currentPosition[1];
        boolean moveAllowed = false;

        System.out.println("Checking move right...");
        switch (side) {
            case 'N': {
                if (currentCol == 16
                        || (currentCol == 14 && currentRow == 14
                        && normalizeBoard[currentRow + 2][currentCol + 2] == 'S')) {
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
                if (currentCol == 16
                        || (currentCol == 14 && currentRow == 2
                        && normalizeBoard[currentRow - 2][currentCol + 2] == 'N')) {
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

    public static boolean checkMoveLeft(char[][] normalizeBoard, int[] currentPosition, char side) {
        int currentRow = currentPosition[0];
        int currentCol = currentPosition[1];
        boolean moveAllowed = false;

        System.out.println("Checking move left...");
        switch (side) {
            case 'N': {
                //check 1 step right, if is empty then check 1 step forward
                if (currentCol == 0
                        || (currentCol == 2 && currentRow == 14
                        && normalizeBoard[currentRow + 2][currentCol - 2] == 'S')) {
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
                if (currentCol == 0
                        || (currentCol == 2 && currentRow == 2
                        && normalizeBoard[currentRow - 2][currentCol - 2] == 'N')) {
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

    public static int[] checkPositionPawnEnemie(char namePawn, int[] pawn1, int[] pawn2, int[] pawn3) {
        switch (namePawn) {
            case 'N' -> {
                System.out.println("CHECKING POSITION PAWN ENEMIE");
                System.out.println(pawn1[0]);
                System.out.println(pawn2[0]);
                System.out.println(pawn3[0]);
                if (pawn1[0] < pawn2[0] && pawn1[0] < pawn3[0]) {
                    return pawn1;
                } else if (pawn2[0] < pawn3[0] && pawn2[0] < pawn1[0]) {
                    return pawn2;
                } else {
                    return pawn3;
                }
            }
            case 'S' -> {
                if (pawn1[0] > pawn2[0] && pawn1[0] > pawn3[0]) {   //Verifica cual está más adelantado
                    return pawn1;
                } else if (pawn2[0] > pawn3[0] && pawn2[0] > pawn1[0]) {
                    return pawn2;
                } else {
                    return pawn3;
                }
            }
            default -> {
                System.out.println("ERROR IN CLASS Pawn METHOD choosePawnToMove.");
                return pawn1;
            }
        }
    }
}
