package com.eda.quoridoreda;

public class Move {

    public static int[] moveForward(char[][] normalizeBoard, int[] currentPosition, char side) {
        System.out.println("Move Forward");
        int currentRow = currentPosition[0];
        int currentCol = currentPosition[1];
        int[] nextPosition = new int[2];

        //Agregar switch N, S para mover adelante
        switch (side) {
            case 'N' -> {
                
                if (normalizeBoard[currentRow + 1][currentCol] == ' ') {
                    System.out.println("Single Step");
                    //Single step
                    nextPosition[0] = currentRow + 2;
                    nextPosition[1] = currentCol;
                }
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

    public static int[] moveRight(char[][] normalizeBoard, int[] currentPosition, char side) {
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

    public static int[] moveLeft(char[][] normalizeBoard, int[] currentPosition, char side) {
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

    public static int[] moveDiagonal(char[][] normalizeBoard, int[] currentPosition, char side) {
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
