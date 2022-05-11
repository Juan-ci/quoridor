package com.eda.quoridoreda;

import org.json.JSONObject;

public class Pawn {

    public static String movePawn(JSONObject json) {
        String namePawn = json.getJSONObject("date").getString("side"); // obtener data.side
        String response = null;
        
        switch(namePawn) {
            case "N" -> {
                response = northPawn(json);
            }
            case "S" -> {
                response = southPawn(json);
            }
            default ->
                System.out.println("Error when tried to define the pawn name, must be N or S.");
        }
        return response;
    }
    
    // Function to move pawn N
    public static String northPawn(JSONObject json) {
        //Mover hacia adelante en primer momento, incrementando índice i
        return null;
    }
    
    // Function to move pawn S
    public static String southPawn(JSONObject json) {
    
        return null;
    }
}
