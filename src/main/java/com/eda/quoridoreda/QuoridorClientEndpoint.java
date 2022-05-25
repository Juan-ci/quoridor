package com.eda.quoridoreda;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;

public class QuoridorClientEndpoint extends WebSocketClient {

    //private static CountDownLatch latch;
    private Logger logger = Logger.getLogger(this.getClass().getName());
    private static final String TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjoianVhbi5jaS5jYWJhbGxlcm9AZ21haWwuY29tIn0.svm-hP13ElhqvavewnuFcrAFYj661GOVLGVUN-1ZatM";
    private static final String URI_WEB_SOCKET
            = "wss://4yyity02md.execute-api.us-east-1.amazonaws.com/ws?token=" + TOKEN;

    public QuoridorClientEndpoint(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        logger.info("CONEXION LOGRADA CON EXITO!!!");
        // if you plan to refuse connection based on ip or httpfields overload: onWebsocketHandshakeReceivedAsClient
    }

    @Override
    public void onMessage(String message) {
        System.out.println("received: " + message);
        JSONObject json = new JSONObject(message);
        String event = json.getString("event");

        switch (event) {
            case "list_users" -> {
                //No hacer nada en ese caso ya que no tiene ningún tipo de respuesta
                System.out.println("List user received.");
            }
            case "challenge" -> {
                //If agregado para hacer pruebas para challenges conmigo mismo, BORRAR TERMINADAS LAS PRUEBAS
                String opponent = json.getJSONObject("data").getString("opponent");
                //if (("juan.ci.caballero@gmail.com").equals(opponent)) {
                    System.out.println("received: " + message);
                    String challengeResponse = Challenge.acceptChallenge(json);
                    System.out.println("Respuesta " + challengeResponse);
                    //System.out.println("Action " + challengeResponse.get("data"));

                    send(challengeResponse);
                //}
            }
            case "your_turn" -> {
                System.out.println("Your Turn: muestro el tablero.");
                //Envío el json tal cual lo recibo
                String response = Board.showBoard(json);
                System.out.println("MUESTRO la respuesta de movimiento" + response);

                send(response);
            }
            case "game_over" -> {
                System.out.println("Game over.");
            }
            default ->
                logger.log(Level.INFO, "ERROR EN onMessage. Evento recibido: {0}", event);
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
        // if the error is fatal then onClose will be called additionally
        System.out.println(ex.getMessage());
    }

    public static void main(String[] args) throws URISyntaxException {
        QuoridorClientEndpoint c = new QuoridorClientEndpoint(
                new URI(URI_WEB_SOCKET));
        c.connect();
    }
}
