package com.eda.quoridoreda;

import org.json.JSONObject;
import org.junit.Test;
import static org.junit.Assert.*;

public class ChallengeTest {

    @Test
    public void shouldAcceptChallenge() {
        assertEquals(Challenge.acceptChallenge(stubJsonChallenge()), stubJsonAcceptChallenge());
    }

    /*
        Making the json with action challenge
        {
            "event": "challenge", 
            "data": {
                "opponent": "eldalai@gmail.com", 
                "challenge_id": "fb586191-867e-46d9-8476-d7bf0cbdc8be"
            }
        }
     */
    public JSONObject stubJsonChallenge() {
        JSONObject response = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("challenge_id", "fb586191-867e-46d9-8476-d7bf0cbdc8be");
        data.put("opponent", "juan.ci.caballero@gmail.com");
        response.put("event", "accept_challenge");
        response.put("data", data);

        return response;
    }

    /*
        Making the json response with action accept_challenge
        {
            "action": "accept_challenge", 
            "data": {
                "challenge_id": "fb586191-867e-46d9-8476-d7bf0cbdc8be"
            }
        }
     */
    public String stubJsonAcceptChallenge() {
        JSONObject response = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("challenge_id", "fb586191-867e-46d9-8476-d7bf0cbdc8be");
        response.put("action", "accept_challenge");
        response.put("data", data);

        return response.toString();
    }
}
