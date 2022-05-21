package com.eda.quoridoreda;

import static org.junit.Assert.*;
import org.junit.Test;

public class CheckingTest {

    private char[] board = null;
    private char[][] normalized = new char[17][17];
    
    @Test
    public void shouldReturnFalseCheckMoveForward() {
        board = "    N         N                                                                                                                                       N              -*-              S                                                                                             S   S        ".toCharArray();
        normalized = Board.armarTablero(board);
        int[] currentPositionS = new int[]{10, 12};
        int[] currentPositionN = new int[]{8, 14};
        
        assertFalse(Checking.checkMoveForward(normalized, currentPositionS, 'S'));
        assertFalse(Checking.checkMoveForward(normalized, currentPositionN, 'N'));
    }
    
    @Test
    public void shouldReturnTrueCheckMoveForward() {
        board = "  N     N     N                                                                                                                                                                                                                                                                   S     S     S  ".toCharArray();
        normalized = Board.armarTablero(board);
        int[] currentPositionN = new int[]{2, 2};
        int[] currentPositionS = new int[]{16, 8};
        
        assertTrue(Checking.checkMoveForward(normalized, currentPositionN, 'N'));
        assertTrue(Checking.checkMoveForward(normalized, currentPositionS, 'S'));
    }
    
    @Test
    public void shouldReturnFalseCheckMoveLeft() {
        board = "N   N                                                                                                                                               N              -*-              S                                                                                           S       S        ".toCharArray();
        normalized = Board.armarTablero(board);
        int[] currentPositionS = new int[]{16, 0};
        int[] currentPositionN = new int[]{0, 0};
        
        assertFalse(Checking.checkMoveLeft(normalized, currentPositionS, 'S'));
        assertFalse(Checking.checkMoveLeft(normalized, currentPositionN, 'N'));
    }
        
    @Test
    public void shouldReturnTrueCheckMoveLeft() {
        board = "  N     N     N                                                                                                                                                                                                                                                                   S     S     S  ".toCharArray();
        normalized = Board.armarTablero(board);
        int[] currentPositionN = new int[]{0, 2};
        int[] currentPositionS = new int[]{16, 8};
        
        assertTrue(Checking.checkMoveLeft(normalized, currentPositionN, 'N'));
        assertTrue(Checking.checkMoveLeft(normalized, currentPositionS, 'S'));
    }
    
    @Test
    public void shouldReturnFalseCheckMoveRight() {
        board = "N   N         N S                                                                                                                                                  -*-              S                                                                                                           S".toCharArray();
        normalized = Board.armarTablero(board);
        int[] currentPositionN = new int[]{0, 14};
        int[] currentPositionS = new int[]{16, 16};
        
        assertFalse(Checking.checkMoveRight(normalized, currentPositionN, 'N'));
        assertFalse(Checking.checkMoveRight(normalized, currentPositionS, 'S'));
    }
        
//    @Test
//    public void shouldReturnTrueCheckMoveRight() {
//        board = "  N     N     N                                                                                                                                                                                                                                                                   S     S     S  ".toCharArray();
//        normalized = Board.armarTablero(board);
//        int[] currentPositionN = new int[]{0, 2};
//        int[] currentPositionS = new int[]{16, 8};
//        
//        assertTrue(Checking.checkMoveRight(normalized, currentPositionN, 'N'));
//        assertTrue(Checking.checkMoveRight(normalized, currentPositionS, 'S'));
//    }
}
