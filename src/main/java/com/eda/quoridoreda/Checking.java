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
                            && normalizeBoard[i + 2][currentCol] == ' ') {  //Check there isn´t a wall
                        if (normalizeBoard[i + 3][currentCol] == ' ') {
                            moveAllowed = true;
                            return moveAllowed;
                        } else {
                            return moveAllowed;
                        }
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
                            && normalizeBoard[i - 2][currentCol] == ' ') { //Check there isn´t a wall
                        if (normalizeBoard[i - 3][currentCol] == ' ') {
                            moveAllowed = true;
                            return moveAllowed;
                        } else {
                            return moveAllowed;
                        }
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

    public static boolean checkMoveRightOneStep(char[][] normalizeBoard, int[] currentPosition, char side) {
        int currentRow = currentPosition[0];
        int currentCol = currentPosition[1];
        boolean moveAllowed = false;

        System.out.println("Checking move right...");
        switch (side) {
            case 'N': {
                if (currentCol == 16
                        || (currentCol == 14
                        && normalizeBoard[currentRow][currentCol + 2] == 'S')
                        || normalizeBoard[currentRow][currentCol + 1] == '|') {
                    return moveAllowed;
                }
                if (normalizeBoard[currentRow][currentCol + 1] == ' ' && (currentCol + 1) < 17) {            //Check if there isn´t a wall
                    if (normalizeBoard[currentRow][currentCol + 2] == ' ' //Check if there isn´t a pawn enemie
                            && normalizeBoard[currentRow + 1][currentCol + 2] == ' ') {  //Check if there isn´t also a wall forward
                        moveAllowed = true;
                        return moveAllowed;
                    } else if ((currentCol + 4) < 17 //Still inside the board
                            && normalizeBoard[currentRow][currentCol + 2] == 'S' //If there are an opponent
                            && normalizeBoard[currentRow][currentCol + 3] == ' ' //If there isn´t a wall
                            && normalizeBoard[currentRow][currentCol + 4] == ' ' //If is empty to jump there
                            && normalizeBoard[currentRow + 1][currentCol + 4] == ' ') { //If there isn´t a wall
                        moveAllowed = true;
                        return moveAllowed;
                    } else {
                        return moveAllowed;
                    }
                } else {
                    return moveAllowed;
                }
            }
            case 'S': {
                if (currentCol == 16
                        || (currentCol == 14 && currentRow == 2
                        && normalizeBoard[currentRow - 2][currentCol + 2] == 'N')) {
                    return moveAllowed;
                }
                if (normalizeBoard[currentRow][currentCol + 1] == ' ' && (currentCol + 1) < 16) {            //Check if there isn´t a wall
                    if (normalizeBoard[currentRow][currentCol + 2] == ' ' //Check if there isn´t a pawn enemie
                            && normalizeBoard[currentRow - 1][currentCol + 2] == ' ') {  //Check if there isn´t also a wall forward to next move
                        moveAllowed = true;
                        return moveAllowed;
                    } else if ((currentCol + 4) < 17 //Still inside the board
                            && normalizeBoard[currentRow][currentCol + 2] == 'N' //If there are an opponent
                            && normalizeBoard[currentRow][currentCol + 3] == ' ' //If there isn´t a wall
                            && normalizeBoard[currentRow][currentCol + 4] == ' ' //If is empty to jump there
                            && normalizeBoard[currentRow - 1][currentCol + 4] == ' ') { //If there isn´t a wall
                        moveAllowed = true;
                        return moveAllowed;
                    } else {
                        return moveAllowed;
                    }
                } else {
                    return moveAllowed;
                }
            }
            default: {
                System.out.println("ERROR IN CLASS Pawn METHOD checkMoveRight.");
            }
        }
        return moveAllowed;
    }

    public static int checkQuantityMoveToRight(char[][] normalizeBoard, int[] currentPosition, char side) {
        int currentRow = currentPosition[0];
        int currentCol = currentPosition[1];
        int quantityMoves = 17;

        System.out.println("Checking quantity move right...");
        switch (side) {
            case 'N': {
                if (currentCol == 16
                        || (currentCol == 14
                        && normalizeBoard[currentRow][currentCol + 2] == 'S')
                        || normalizeBoard[currentRow][currentCol + 1] == '|') {
                    return quantityMoves;
                }
                quantityMoves = 0;
                for (int j = currentCol; j < 17; j++) {
                    if (normalizeBoard[currentRow][j] == ' ' && j < 17) {            //Check if there isn´t a wall
                        if ((j % 2) == 0 //Check if the column is even
                                && normalizeBoard[currentRow + 1][j] == ' ') {  //Check if there isn´t also a wall forward
                            quantityMoves /= 2;
                            return quantityMoves;
                        } else if (j == 16 && normalizeBoard[currentRow + 1][j] == '-') {  //If there is a wall return a number out of bounds
                            quantityMoves = 17;
                            return quantityMoves;
                        } else if (normalizeBoard[currentRow][j] == '|') {
                            quantityMoves = 17;
                            return quantityMoves;
                        } else {
                            quantityMoves++;
                        }
                    } else {
                        quantityMoves++;
                    }
                }
            }
            case 'S': {
                if (currentCol == 16
                        || (currentCol == 14 && currentRow == 2
                        && normalizeBoard[currentRow - 2][currentCol + 2] == 'N')) {
                    return quantityMoves;
                }
                quantityMoves = 0;
                for (int j = currentCol; j < 17; j++) {
                    if (normalizeBoard[currentRow][j] == ' ' && j < 17) {            //Check if there isn´t a wall
                        if ((j % 2) == 0 //Check if the column is even
                                && normalizeBoard[currentRow - 1][j] == ' ') {  //Check if there isn´t also a wall forward to next move
                            quantityMoves /= 2;
                            return quantityMoves;
                        } else if (j == 16 && normalizeBoard[currentRow - 1][j] == '-') {  //If there is a wall return a number out of bounds
                            quantityMoves = 17;
                            return quantityMoves;
                        } else {
                            quantityMoves++;
                        }
                    } else if (normalizeBoard[currentRow][j] == '|') {
                        quantityMoves = 17;
                        return quantityMoves;
                    } else {
                        quantityMoves++;
                    }
                }
            }
            default: {
                System.out.println("ERROR IN CLASS Pawn METHOD checkMoveRight.");
            }
        }
        return quantityMoves;
    }

    public static boolean checkMoveLeftOneStep(char[][] normalizeBoard, int[] currentPosition, char side) {
        int currentRow = currentPosition[0];
        int currentCol = currentPosition[1];
        boolean moveAllowed = false;

        //  ADD POSIBILITY OF JUMPT TO LEFT!!!
        System.out.println("Checking move left...");
        switch (side) {
            case 'N': {
                //check 1 step right, if is empty then check 1 step forward
                if (currentCol == 0
                        || (currentCol == 2 && currentRow == 14
                        && normalizeBoard[currentRow + 2][currentCol - 2] == 'S')
                        || normalizeBoard[currentRow][currentCol - 1] == '|') { //Checking if there is a wall
                    return moveAllowed;
                }
                if (normalizeBoard[currentRow][currentCol - 1] == ' ' && (currentCol - 1) > 0) {            //Check if there isn´t a wall
                    if (normalizeBoard[currentRow][currentCol - 2] == ' ' //Check if there isn´t a pawn enemie
                            && normalizeBoard[currentRow + 1][currentCol - 2] == ' ') {  //Check if there isn´t also a wall forward
                        moveAllowed = true;
                        return moveAllowed;
                    } else if ((currentCol - 4) >= 0 //Still inside the board
                            && normalizeBoard[currentRow][currentCol - 2] == 'S' //If there are an opponent
                            && normalizeBoard[currentRow][currentCol - 3] == ' ' //If there isn´t a wall
                            && normalizeBoard[currentRow][currentCol - 4] == ' ' //If is empty to jump there
                            && normalizeBoard[currentRow + 1][currentCol - 4] == ' ') { //If there isn´t a wall
                        moveAllowed = true;
                        return moveAllowed;
                    } else {
                        return moveAllowed;
                    }
                }
            }
            case 'S': {
                if (currentCol == 0
                        || (currentCol == 2 && currentRow == 2
                        && normalizeBoard[currentRow - 2][currentCol - 2] == 'N')
                        || normalizeBoard[currentRow][currentCol - 1] == '|') { //Checking if there is a wall
                    return moveAllowed;
                }
                if (normalizeBoard[currentRow][currentCol - 1] == ' ' && (currentCol - 1) > 0) {    //Check if there isn´t a wall
                    if (normalizeBoard[currentRow][currentCol - 2] == ' ' //Check if there isn´t a pawn enemie
                            && normalizeBoard[currentRow - 1][currentCol - 2] == ' ') {  //Check if there isn´t also a wall forward to next move
                        moveAllowed = true;
                        return moveAllowed;
                    } else if ((currentCol - 4) >= 0 //Still inside the board
                            && normalizeBoard[currentRow][currentCol - 2] == 'N' //If there are an opponent
                            && normalizeBoard[currentRow][currentCol - 3] == ' ' //If there isn´t a wall
                            && normalizeBoard[currentRow][currentCol - 4] == ' ' //If is empty to jump there
                            && normalizeBoard[currentRow - 1][currentCol - 4] == ' ') { //If there isn´t a wall
                        moveAllowed = true;
                        return moveAllowed;
                    } else {
                        return moveAllowed;
                    }
                }
            }
            default: {
                System.out.println("ERROR IN CLASS Pawn METHOD checkMoveLeft.");
            }
        }
        return moveAllowed;
    }

    public static int checkQuantityMoveToLeft(char[][] normalizeBoard, int[] currentPosition, char side) {
        int currentRow = currentPosition[0];
        int currentCol = currentPosition[1];
        int quantityMoves = 17;

        System.out.println("Checking quantity move left...");
        switch (side) {
            case 'N': {
                //check 1 step right, if is empty then check 1 step forward
                if (currentCol == 0
                        || (currentCol == 2 && currentRow == 14
                        && normalizeBoard[currentRow + 2][currentCol - 2] == 'S')
                        || normalizeBoard[currentRow][currentCol - 1] == '|') {
                    return quantityMoves;
                }
                quantityMoves = 0;
                for (int j = currentCol; j >= 0; j--) {
                    if (normalizeBoard[currentRow][j] == ' ' && j >= 0) {            //Check if there isn´t a wall
                        if ((j % 2) == 0 //Check if the column is even
                                && normalizeBoard[currentRow + 1][j] == ' ') {  //Check if there isn´t also a wall forward
                            quantityMoves /= 2;
                            return quantityMoves;
                        } else if (normalizeBoard[currentRow][j] == '|'
                                || (j == 0 && normalizeBoard[currentRow + 1][j] == '-')) {
                            quantityMoves = 17;
                            return quantityMoves;
                        } else {
                            quantityMoves++;
                        }
                    } else if (normalizeBoard[currentRow][j] == 'N' && j == 0) {
                        quantityMoves = 17;
                        return quantityMoves;
                    } else {
                        quantityMoves++;
                    }
                }
            }
            case 'S': {
                if (currentCol == 0
                        || (currentCol == 2 && currentRow == 2
                        && normalizeBoard[currentRow - 2][currentCol - 2] == 'N')) {
                    return quantityMoves;
                }
                quantityMoves = 0;
                for (int j = currentCol; j >= 0; j--) {
                    if (normalizeBoard[currentRow][j] == ' ' && j >= 0) {    //Check if there isn´t a wall
                        if ((j % 2) == 0 //Check if the column is even
                                && normalizeBoard[currentRow - 1][j] == ' ') {  //Check if there isn´t also a wall forward to next move
                            quantityMoves /= 2;
                            return quantityMoves;
                        } else if (normalizeBoard[currentRow][j] == '|'
                                || (j == 0 && normalizeBoard[currentRow - 1][j] == '-')) {
                            quantityMoves = 17;
                            return quantityMoves;
                        } else {
                            quantityMoves++;
                        }
                    } else if (normalizeBoard[currentRow][j] == 'S' && j == 0) {
                        quantityMoves = 17;
                        return quantityMoves;
                    } else {
                        quantityMoves++;
                    }
                }
            }
            default: {
                System.out.println("ERROR IN CLASS Pawn METHOD checkQuantityMoveLeft.");
            }
        }
        return quantityMoves;
    }

    public static int[] checkPositionPawnEnemie(char namePawn, int[] pawn1, int[] pawn2, int[] pawn3) {
        System.out.println("Checking position enemie");
        switch (namePawn) {
            case 'N' -> {
                if (pawn1[0] < pawn2[0] && pawn1[0] < pawn3[0]) {
                    return pawn1;
                } else if (pawn2[0] < pawn3[0] && pawn2[0] < pawn1[0]) {
                    return pawn2;
                } else if (pawn1[0] == pawn2[0] && pawn1[0] == pawn3[0]) {
                    pawn1[0] = 16;  //I modified de row just to not enter to if to put a wall
                    return pawn1;
                } else {
                    return pawn3;
                }
            }
            case 'S' -> {
                if (pawn1[0] > pawn2[0] && pawn1[0] > pawn3[0]) {   //Verifica cual está más adelantado
                    return pawn1;
                } else if (pawn2[0] > pawn3[0] && pawn2[0] > pawn1[0]) {
                    return pawn2;
                } else if (pawn1[0] == pawn2[0] && pawn1[0] == pawn3[0]) {
                    pawn1[0] = 16;  //I modified de row just to not enter to if to put a wall
                    return pawn1;
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

    public static boolean checkCanPutWall(char[][] normalizeBoard, int[] enemiePosition, char side) {
        int enemieRow = enemiePosition[0];
        int enemieCol = enemiePosition[1];

        switch (side) {
            case 'N' -> {
                //Checking next position for 'S'
                if (normalizeBoard[enemieRow - 1][enemieCol] == '-') {  //Check if there is a wall in front of it
                    if(enemieCol == 2 && normalizeBoard[enemieRow][enemieCol + 1] == '|'
                            && normalizeBoard[enemieRow - 1][enemieCol - 1] == '*') {
                        return false;
                    }
                    if (checkAside(normalizeBoard, enemieRow, enemieCol, side)) {  //Check if there is a wall at right and left side
                        //Tiene los dos costados con pared, hora de chequear pared vertical
                        if (normalizeBoard[enemieRow - 1][enemieCol - 1] == '*') {  //Check left col
                            if (normalizeBoard[enemieRow][enemieCol - 2] == ' ') {
                                return true;
                            }
                        } else if (normalizeBoard[enemieRow - 1][enemieCol + 1] == '*') {   //Check right col
                            if (enemieCol < 14 && normalizeBoard[enemieRow][enemieCol + 2] == ' '
                                    && normalizeBoard[enemieRow][enemieCol + 3] == ' ') {
                                return true;
                            }
                        }
                    } else if (enemieCol == 0) {
                        if (normalizeBoard[enemieRow - 1][enemieCol] == '-'
                                || normalizeBoard[enemieRow - 1][enemieCol + 2] == '-') {
                            return false;
                        }
                        if (normalizeBoard[enemieRow - 2][enemieCol] == ' '
                                && normalizeBoard[enemieRow - 1][enemieCol + 2] == ' ') {
                            return true;
                        }
                    } else if (normalizeBoard[enemieRow - 1][enemieCol - 1] == '*'
                            || normalizeBoard[enemieRow - 1][enemieCol + 1] == '*') {
                        return true;
                    }
                } else if (enemieCol < 15 && normalizeBoard[enemieRow - 1][enemieCol] == ' '
                        && normalizeBoard[enemieRow - 1][enemieCol + 2] == ' ') {
                    return true;
                } else if (enemieCol < 15 && normalizeBoard[enemieRow - 1][enemieCol] == ' '
                        && normalizeBoard[enemieRow - 1][enemieCol + 2] == '-'
                        && normalizeBoard[enemieRow - 1][enemieCol - 2] == ' ') {
                    return true;
                } else if (enemieCol > 0 && enemieCol == 16 && normalizeBoard[enemieRow - 1][enemieCol] == ' '
                        && normalizeBoard[enemieRow - 1][enemieCol - 2] == ' '
                        && normalizeBoard[enemieRow - 2][enemieCol] == ' '
                        && normalizeBoard[enemieRow - 2][enemieCol - 2] == ' ') {
                    return true;
                }
            }
            case 'S' -> {
                //Checking next position for 'N'
                if (normalizeBoard[enemieRow + 1][enemieCol] == '-') {  //Check if there is a wall in front of it
                    if(enemieCol == 2 && normalizeBoard[enemieRow][enemieCol + 1] == '|'
                            && normalizeBoard[enemieRow + 1][enemieCol - 1] == '*') {
                        return false;
                    }
                    if (checkAside(normalizeBoard, enemieRow, enemieCol, side)) {  //Check if it is time to put wall 'v'
                        if (normalizeBoard[enemieRow + 1][enemieCol - 1] == '*') {  //Check left col
                            if (normalizeBoard[enemieRow][enemieCol - 2] == ' ') {
                                return true;
                            }
                        } else if (normalizeBoard[enemieRow + 1][enemieCol + 1] == '*') {   //Check right col
                            if (enemieCol < 14 && normalizeBoard[enemieRow][enemieCol + 2] == ' '
                                    && normalizeBoard[enemieRow][enemieCol + 3] == ' ') {
                                return true;
                            }
                        }
                    } else if (enemieCol == 0) {
                        if (normalizeBoard[enemieRow + 1][enemieCol] == '-'
                                || normalizeBoard[enemieRow + 1][enemieCol + 2] == '-') {
                            return false;
                        }
                        if (normalizeBoard[enemieRow + 2][enemieCol] == ' '
                                && normalizeBoard[enemieRow + 1][enemieCol + 2] == ' ') {
                            return true;
                        }
                    } else if (normalizeBoard[enemieRow + 1][enemieCol - 1] == '*'
                            || normalizeBoard[enemieRow + 1][enemieCol + 1] == '*') {
                        return true;
                    }
                } else if (enemieCol < 15 && normalizeBoard[enemieRow + 1][enemieCol] == ' '
                        && normalizeBoard[enemieRow + 1][enemieCol + 2] == ' ') {
                    return true;
                } else if (enemieCol < 15 && normalizeBoard[enemieRow + 1][enemieCol] == ' '
                        && normalizeBoard[enemieRow + 1][enemieCol + 2] == '-'
                        && normalizeBoard[enemieRow + 1][enemieCol - 2] == ' ') {
                    return true;
                } else if (enemieCol > 0 && enemieCol == 16 && normalizeBoard[enemieRow + 1][enemieCol] == ' '
                        && normalizeBoard[enemieRow + 1][enemieCol - 2] == ' '
                        && normalizeBoard[enemieRow + 2][enemieCol] == ' '
                        && normalizeBoard[enemieRow + 2][enemieCol - 2] == ' ') {
                    return true;
                }
            }
            default -> {
                System.out.println("ERROR IN CASE checkCanPutWall.");
            }
        }
        return false;
    }

    public static boolean checkAside(char[][] normalizeBoard, int enemieRow, int enemieCol, char side) {

        switch (side) {
            case 'N' -> {
                if (enemieCol > 0 && enemieCol < 16 //Check it is inside the board
                        && normalizeBoard[enemieRow - 1][enemieCol - 2] == '-'
                        && normalizeBoard[enemieRow - 1][enemieCol + 2] == '-') {
                    return true;
                }
            }
            case 'S' -> {
                if (enemieCol > 0 && enemieCol < 16 //Check it is inside the board
                        && normalizeBoard[enemieRow + 1][enemieCol - 2] == '-'
                        && normalizeBoard[enemieRow + 1][enemieCol + 2] == '-') {
                    return true;
                }
            }
            default -> {
                System.out.println("ERROR IN SWITCH checkAside.");
            }
        }
        return false;
    }
}
