# Quoridor
Game developed for EDA 6

Class QuoridorClientEndpoint (main):
  In charge of receive the messages from the websocket server, depends on wich kind of event that received is the path that is going to take, and eventually to send a response.
  
Class Challenge:
  In charge of make a json object just in case of accept the challenge.
  
Class Board:
  In charge of take a vector from the received message and normalized the board. While the board is normalizing, it tooks the current position of the pawns and it is kept in a map with the name of the pawn.
  After the board is normalized, the board is showned. After that the Pawn class is called.
  This class returned to the QuoridorClientEndpoint class the response received from Pawn class.

Class Pawn:
  Firstable, this class recognized wich pawn(N or S) has to move/put wall. Then call the suitable method(northPawn or southPawn), the diference is when a pawn want to go forward for example N, the row has to incremente, in the other side S, the row has to decrement. This methods call the Checking class Move class or Wall class, depends on the situation.
  In case to move, call the method choosePawnToMove, return a pawn depending on the current position of them. Took the pawn nearlier from the opponent border. In case that all the pawns are in the same row, call the method pickRandomPawn that returns a random pawn(it has to change for a smarter way to choose it).
  
Class Checking:
  In charge of prove if a move(forward, right, left) is allowed or not. Checking if there are a pawn opponent and could jump, or if there are a wall, or if the posible move is still inside of the board.
  
Class Move:
  In charge of make the action of move(forward, right, left), a single step or jump an opponent. This class is called after the respective checking was made.
  
Class Wall:
  In charge of put a wall, it is also called after a couple of comprobation.
  
  
  ***
  Developed by:
    Juan Ignacio Caballero - <strong>https://www.linkedin.com/in/juan-ignacio-caballero/</strong>
