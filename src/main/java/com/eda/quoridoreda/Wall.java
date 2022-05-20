package com.eda.quoridoreda;

import org.json.JSONObject;

public class Wall {

    public static String putWall(char side, int[] enemiePosition, String gameId, String turnToken) {
        JSONObject data = new JSONObject();
        JSONObject json = new JSONObject();
        System.out.println("PUT WALL");
        
        switch(side) {
            case 'N' -> {
                int row = (enemiePosition[0] - 2) / 2;
                int col = enemiePosition[1] / 2;
                data.put("game_id", gameId);
                data.put("turn_token", turnToken);
                data.put("row", row);
                data.put("col", col);
                data.put("orientation", "h");
                
                json.put("action", "wall");
                json.put("data", data);
                return json.toString();
            }
            case 'S' -> {
                
                return null;
            }
            default -> {
                return null;
            }
        }
    }
}
