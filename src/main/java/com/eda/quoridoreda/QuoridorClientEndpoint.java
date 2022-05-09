package com.eda.quoridoreda;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;

public class QuoridorClientEndpoint extends WebSocketClient {

    private static CountDownLatch latch;
    private Logger logger = Logger.getLogger(this.getClass().getName());
    private static final String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjoianVhbi5jaS5jYWJhbGxlcm9AZ21haWwuY29tIn0.svm-hP13ElhqvavewnuFcrAFYj661GOVLGVUN-1ZatM";
    private static final String uriWebSocket
            = "wss://4yyity02md.execute-api.us-east-1.amazonaws.com/ws?token=" + token;
    

    public QuoridorClientEndpoint(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        logger.info("CONEXION LOGRADA CON EXITO!!!");
        System.out.println("opened connection");
        // if you plan to refuse connection based on ip or httpfields overload: onWebsocketHandshakeReceivedAsClient
    }

    @Override
    public void onMessage(String message) {
        System.out.println("received: " + message);
        JSONObject json = new JSONObject(message);
        String event = json.getString("event");
        
        switch(event) {
            case "list_users" -> //No hacer nada en ese caso ya que no tiene ningún tipo de respuesta
                System.out.println("List user received.");
            case "challenge" -> {
                Challenge requestChallenge = new Challenge();
                String response = requestChallenge.acceptChallenge(json);
            }
            case "your_turn" -> {
            }
            default -> logger.log(Level.INFO, "ERROR EN onMessage. Evento recibido: {0}", event);
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        // The close codes are documented in class org.java_websocket.framing.CloseFrame
        System.out.println(
                "Connection closed by " + (remote ? "remote peer" : "us") + " Code: " + code + " Reason: "
                + reason);
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
        // if the error is fatal then onClose will be called additionally
    }

    public static void main(String[] args) throws URISyntaxException {
        QuoridorClientEndpoint c = new QuoridorClientEndpoint(
                new URI(uriWebSocket));
        c.connect();
    }
}
