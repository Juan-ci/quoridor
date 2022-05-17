package com.eda.quoridoreda;

import org.json.JSONObject;

public class Challenge {
    
    private static String challengeId;
    
    private static final String action = "accept_challenge";
    
    public Challenge() {
        
    }
    /*
        recibo el json con el siguiente formato
            {
                "event" : "challenge",
                "data" : {
                            "opponent" : "userName(email)",
                            "challenge_id" : "idChallenge"
                        }
            }
        Para aceptar debo enviar el siguiente json
            {
                "action" : "accept_challenge",
                "data" : {
                            "challenge_id" : "idChallengeRecibido"
                        }
            }
    */
    public static JSONObject acceptChallenge(JSONObject request) {
        challengeId = request.getJSONObject("data").getString("challenge_id");
        
        JSONObject response = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("challenge_id", challengeId);
        response.put("action", action);
        response.put("data", data);
        
        return response;
    }
}
