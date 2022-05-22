package com.eda.quoridoreda;

import org.junit.Test;
import static org.junit.Assert.*;

public class MoveTest {
    
    private char[] board = null;
    private char[][] normalizedBoard = new char[17][17];
    
    @Test
    public void shouldMoveForward() {
        board = "    N                                           N                                 S                                                                   N                               S                                                                                                 S        ".toCharArray();
        normalizedBoard = Board.armarTablero(board);
        char sideS = 'S';
        char sideN = 'N';
        
        //Single step
        int[] currentNPosition = {0, 4};
        int[] currentSPosition = {16, 8};
        int[] expNResult = {2, 4};
        int[] expSResult = {14, 8};
        int[] resultN = Move.moveForward(normalizedBoard, currentNPosition, sideN);
        int[] resultS = Move.moveForward(normalizedBoard, currentSPosition, sideS);
        assertArrayEquals(expNResult, resultN);
        assertArrayEquals(expSResult, resultS);
    
        //Jump forward
        int[] currentJumpNPosition = {2, 14};
        int[] currentJumpSPosition = {4, 14};
        int[] expJmupNResult = {6, 14};
        int[] expJumpSResult = {0, 14};
        int[] resultJumpN = Move.moveForward(normalizedBoard, currentJumpNPosition, sideN);
        int[] resulJumpS = Move.moveForward(normalizedBoard, currentJumpSPosition, sideS);
        assertArrayEquals(expJmupNResult, resultJumpN);
        assertArrayEquals(expJumpSResult, resulJumpS);
    }
}
