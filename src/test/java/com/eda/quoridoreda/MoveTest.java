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
    
    @Test
    public void shouldMoveRight() {
        board = "    N                                           N                                 S                                                                   N                               S                                                                                                 S        ".toCharArray();
        normalizedBoard = Board.armarTablero(board);
        char sideS = 'S';
        char sideN = 'N';
        
        //Single step
        int[] currentNPosition = {0, 4};
        int[] currentSPosition = {16, 8};
        int[] expNResult = {0, 6};
        int[] expSResult = {16, 10};
        int[] resultN = Move.moveRight(normalizedBoard, currentNPosition, sideN);
        int[] resultS = Move.moveRight(normalizedBoard, currentSPosition, sideS);
        assertArrayEquals(expNResult, resultN);
        assertArrayEquals(expSResult, resultS);
    
        //ADD CHECK AND TEST FOR JUMP TO RIGHT
    }
    
    @Test
    public void shouldMoveLeft() {
        board = "    N                                           N                                 S                                                                   N                               S                                                                                                 S        ".toCharArray();
        normalizedBoard = Board.armarTablero(board);
        char sideS = 'S';
        char sideN = 'N';
        
        //Single step
        int[] currentNPosition = {0, 4};
        int[] currentSPosition = {16, 8};
        int[] expNResult = {0, 2};
        int[] expSResult = {16, 6};
        int[] resultN = Move.moveLeft(normalizedBoard, currentNPosition, sideN);
        int[] resultS = Move.moveLeft(normalizedBoard, currentSPosition, sideS);
        assertArrayEquals(expNResult, resultN);
        assertArrayEquals(expSResult, resultS);
    
        //ADD CHECK AND TEST FOR JUMP TO LEFT
    }
}
