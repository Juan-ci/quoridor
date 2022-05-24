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
        
        assertFalse(Checking.checkMoveLeftOneStep(normalized, currentPositionS, 'S'));
        assertFalse(Checking.checkMoveLeftOneStep(normalized, currentPositionN, 'N'));
    }
        
    @Test
    public void shouldReturnTrueCheckMoveLeft() {
        board = "  N     N     N                                                                                                                                                                                                                                                                   S     S     S  ".toCharArray();
        normalized = Board.armarTablero(board);
        int[] currentPositionN = new int[]{0, 2};
        int[] currentPositionS = new int[]{16, 8};
        
        assertTrue(Checking.checkMoveLeftOneStep(normalized, currentPositionN, 'N'));
        assertTrue(Checking.checkMoveLeftOneStep(normalized, currentPositionS, 'S'));
    }
    
    @Test
    public void shouldReturnFalseCheckMoveRight() {
        board = "N   N         N S                                                                                                                                                  -*-              S                                                                                                           S".toCharArray();
        normalized = Board.armarTablero(board);
        int[] currentPositionN = new int[]{0, 14};
        int[] currentPositionS = new int[]{16, 16};
        
        //Testing that could not jump an enemie if it is in a border spot
        assertFalse(Checking.checkMoveRightOneStep(normalized, currentPositionN, 'N'));
        
        //Testing that could not move right if it is in the right border
        assertFalse(Checking.checkMoveRightOneStep(normalized, currentPositionS, 'S'));
    }
        
    @Test
    public void shouldReturnTrueCheckMoveRight() {
        board = "  N     N   S                                                                                                                                                                                                                                                                     S     S   N    ".toCharArray();
        normalized = Board.armarTablero(board);
        int[] currentPositionN = new int[]{0, 8};
        int[] currentPositionS = new int[]{16, 8};
        
        assertTrue(Checking.checkMoveRightOneStep(normalized, currentPositionN, 'N'));
        assertTrue(Checking.checkMoveRightOneStep(normalized, currentPositionS, 'S'));
    }
    
    @Test
    public void shouldReturnFourCheckQuantityMoveRight() {
        board = "  N     N                -*-                                                                                                                                                                                                                                     -*-              S     S   N   S".toCharArray();
        normalized = Board.armarTablero(board);
        int[] currentPositionN = new int[]{0, 8};
        int[] currentPositionS = new int[]{16, 2};
        
        assertEquals( 2, Checking.checkQuantityMoveToRight(normalized, currentPositionN, 'N'));
        assertEquals( 2, Checking.checkQuantityMoveToRight(normalized, currentPositionS, 'S'));
    }
    
    @Test
    public void shouldReturnPawn3() {
        int[] pawnS1 = new int[]{16, 12};
        int[] pawnS2 = new int[]{14, 2};
        int[] pawnS3 = new int[]{10, 8};
        int[] pawnN1 = new int[]{0, 12};
        int[] pawnN2 = new int[]{4, 2};
        int[] pawnN3 = new int[]{10, 6};
        
        //assertArrayEquals(Checking.checkPositionPawnEnemie('N', pawnS1, pawnS2, pawnS3), pawnS3);
        assertArrayEquals( pawnS3,Checking.checkPositionPawnEnemie('N', pawnS1, pawnS2, pawnS3));
        assertArrayEquals( pawnN3, Checking.checkPositionPawnEnemie('S', pawnN1, pawnN2, pawnN3));
    }
}
