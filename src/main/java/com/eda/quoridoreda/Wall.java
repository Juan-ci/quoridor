package com.eda.quoridoreda;

import org.json.JSONObject;

public class Wall {

    public static String putWall(char side, int[] enemiePosition, String gameId, String turnToken, char[][] normalizeBoard) {
        JSONObject data = new JSONObject();
        JSONObject json = new JSONObject();
        System.out.println("PUT WALL");
        int enemieRow = enemiePosition[0];
        int enemieCol = enemiePosition[1];
        int row = 0;
        int col = 0;
        String orientation = null;

        switch (side) {
            case 'N' -> {
                if (normalizeBoard[enemieRow - 1][enemieCol] == ' ') {  //Check to put wall 'h'
                    orientation = "h";
                    row = enemieRow - 2;
                    if (enemieCol == 2 && normalizeBoard[enemieRow - 1][enemieCol - 1] == ' '
                            && normalizeBoard[enemieRow - 1][enemieCol - 2] == ' ') {
                        col = enemieCol - 2;
                    } else if (enemieCol < 15 && normalizeBoard[enemieRow - 1][enemieCol + 1] == ' ' //Check there isn´t '*'
                            && normalizeBoard[enemieRow - 1][enemieCol + 2] == ' ') {
                        col = enemieCol;
                    }
                } else if (enemieCol > 1 && enemieRow < 15
                        && normalizeBoard[enemieRow - 1][enemieCol] == '-'
                        && normalizeBoard[enemieRow - 1][enemieCol - 1] == '*'
                        && normalizeBoard[enemieRow][enemieCol + 1] == ' ') {
                    orientation = "v";
                    col = enemieCol;
                    row = enemieRow - 2;
                } else {
                    //Check to put wall 'v'
                    if (enemieCol > 1 && normalizeBoard[enemieRow][enemieCol - 1] == ' ' //Check if there is a vertical wall
                            && normalizeBoard[enemieRow - 1][enemieCol - 2] == ' ') {
                        orientation = "v";
                        col = enemieCol - 2;
                        if (normalizeBoard[enemieRow - 2][enemieCol - 2] == ' ') {  //Check to put wall on left
                            row = enemieRow;
                        } else {  //Check to put wall on left
                            row = enemieRow - 2;
                        }
                    } else if (enemieCol < 14 && normalizeBoard[enemieRow][enemieCol + 2] == ' ') {  //Check to put wall on left
                        orientation = "v";
                        col = enemieCol + 2;
                        if (normalizeBoard[enemieRow - 2][enemieCol + 2] == ' ') {  //Check to put wall on right
                            row = enemieRow;
                        } else {  //Check to put wall on right
                            row = enemieRow - 2;
                        }
                    }
                }

                row /= 2;
                col /= 2;

                data.put("game_id", gameId);
                data.put("turn_token", turnToken);
                data.put("row", row);
                data.put("col", col);
                data.put("orientation", orientation);

                json.put("action", "wall");
                json.put("data", data);
                return json.toString();
            }
            case 'S' -> {

                if (normalizeBoard[enemieRow + 1][enemieCol] == ' ') {  //Check to put wall 'h'
                    orientation = "h";
                    row = enemieRow;
                    if (enemieCol == 2 && normalizeBoard[enemieRow + 1][enemieCol - 1] == ' '
                            && normalizeBoard[enemieRow + 1][enemieCol - 2] == ' ') {
                        col = enemieCol - 2;
                    } else if (enemieCol < 15 && normalizeBoard[enemieRow + 1][enemieCol + 1] == ' ' //Check there isn´t '*'
                            && normalizeBoard[enemieRow + 1][enemieCol + 2] == ' ') {
                        col = enemieCol;
                    }
                } else if (enemieCol > 1 && enemieRow < 15
                        && normalizeBoard[enemieRow + 1][enemieCol] == '-'
                        && normalizeBoard[enemieRow + 1][enemieCol - 1] == '*'
                        && normalizeBoard[enemieRow][enemieCol + 1] == ' ') {
                    orientation = "v";
                    col = enemieCol;
                    row = enemieRow + 2;
                } else {
                    //Check to put wall 'v'
                    if (enemieCol > 1 && normalizeBoard[enemieRow][enemieCol - 1] == ' ' //Check if there is a vertical wall
                            && normalizeBoard[enemieRow + 1][enemieCol - 2] == ' ') {
                        orientation = "v";
                        col = enemieCol - 2;
                        if (normalizeBoard[enemieRow + 2][enemieCol - 2] == ' ') {  //Check to put wall on left
                            row = enemieRow;
                        } else {  //Check to put wall on left
                            row = enemieRow + 2;
                        }
                    } else if (enemieCol < 14 && normalizeBoard[enemieRow][enemieCol + 2] == ' ') {  //Check to put wall on left
                        orientation = "v";
                        col = enemieCol + 2;
                        if (normalizeBoard[enemieRow + 2][enemieCol + 2] == ' ') {  //Check to put wall on right
                            row = enemieRow;
                        } else {  //Check to put wall on right
                            row = enemieRow + 2;
                        }
                    }
                }

                row /= 2;
                col /= 2;

                data.put("game_id", gameId);
                data.put("turn_token", turnToken);
                data.put("row", row);
                data.put("col", col);
                data.put("orientation", orientation);

                json.put("action", "wall");
                json.put("data", data);
                return json.toString();
            }
            default -> {
                return null;
            }
        }
    }
}
