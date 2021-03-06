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
        board = "  N         S N                                                                                                                                                                                                                                                                   S         N S  ".toCharArray();
        normalized = Board.armarTablero(board);
        
        //One step to left
        int[] currentPositionN = new int[]{0, 2};
        int[] currentPositionS = new int[]{16, 8};
        
        assertTrue(Checking.checkMoveLeftOneStep(normalized, currentPositionN, 'N'));
        assertTrue(Checking.checkMoveLeftOneStep(normalized, currentPositionS, 'S'));
        
        //There is an opponent One step to left so it has to jump
        int[] currentJumpPositionN = new int[]{0, 14};
        int[] currentJumpPositionS = new int[]{16, 14};
        
        assertTrue(Checking.checkMoveLeftOneStep(normalized, currentJumpPositionN, 'N'));
        assertTrue(Checking.checkMoveLeftOneStep(normalized, currentJumpPositionS, 'S'));
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
        board = "  N     N S                                                                                                                                                                                                                                                                       S     S N      ".toCharArray();
        normalized = Board.armarTablero(board);
        
        //One step to left
        int[] currentPositionN = new int[]{0, 2};
        int[] currentPositionS = new int[]{16, 2};
        
        assertTrue(Checking.checkMoveRightOneStep(normalized, currentPositionN, 'N'));
        assertTrue(Checking.checkMoveRightOneStep(normalized, currentPositionS, 'S'));
        
        //There is an opponent One step to left so it has to jump
        int[] currentJumpPositionN = new int[]{0, 8};
        int[] currentJumpPositionS = new int[]{16, 8};
        
        assertTrue(Checking.checkMoveRightOneStep(normalized, currentJumpPositionN, 'N'));
        assertTrue(Checking.checkMoveRightOneStep(normalized, currentJumpPositionS, 'S'));
    }
    
    @Test
    public void shouldReturnFiveCheckQuantityMoveRight() {
        board = "      N              -*- -*- -*-        S                                                                     N                                       N                                                                                                                           S           S  ".toCharArray();
        normalized = Board.armarTablero(board);
        int[] currentPositionN = new int[]{0, 6};
        int[] currentPositionS = new int[]{2, 6};
        
        assertEquals( 5, Checking.checkQuantityMoveToRight(normalized, currentPositionN, 'N'));
        assertEquals( 5, Checking.checkQuantityMoveToRight(normalized, currentPositionS, 'S'));
    }
    
    @Test
    public void shouldReturnSixCheckQuantityMoveLeft() {
        board = "              N      -*- -*- -*-                S                                                             N                                       N                                                                                                                           S           S  ".toCharArray();
        normalized = Board.armarTablero(board);
        int[] currentPositionN = new int[]{0, 14};
        int[] currentPositionS = new int[]{2, 14};
        
        assertEquals( 6, Checking.checkQuantityMoveToLeft(normalized, currentPositionN, 'N'));
        assertEquals( 6, Checking.checkQuantityMoveToLeft(normalized, currentPositionS, 'S'));
    }
    
    @Test
    public void shouldReturnTrueCheckCanPutWall() {
        board = "              N                         N       S      -*- -*-                                                N                                                                                                              -*- -*-            | S              *                |           S  ".toCharArray();
        normalized = Board.armarTablero(board);
        int[] currentPositionN = new int[]{0, 14};
        int[] currentPositionS = new int[]{2, 14};
        int[] currentPositionOneWallInFrontN = new int[]{6, 8};
        int[] currentPositionOneWallInFrontS = new int[]{16, 14};
        int[] currentPositionWallAsideN = new int[]{2, 6};
        int[] currentPositionWallAsideS = new int[]{14, 4};
        
        //Situation: no wall in front of the pawns
        //Turn pawn S, pass the position of pawn enemie N
        assertTrue(Checking.checkCanPutWall(normalized, currentPositionN, 'S'));
        //Turn pawn N, pass the position of pawn enemie S
        assertTrue(Checking.checkCanPutWall(normalized, currentPositionS, 'N'));
        
        //Situation: there is a wall in front of it, but there isn??t another wall aside of this wall
        assertTrue(Checking.checkCanPutWall(normalized, currentPositionOneWallInFrontS, 'N'));
        assertTrue(Checking.checkCanPutWall(normalized, currentPositionOneWallInFrontN, 'S'));
        
        
        assertTrue(Checking.checkCanPutWall(normalized, currentPositionWallAsideS, 'N'));
        assertTrue(Checking.checkCanPutWall(normalized, currentPositionWallAsideN, 'S'));
    }
    
    @Test
    public void shouldReturnPawn3() {
        int[] pawnS1 = new int[]{16, 12};
        int[] pawnS2 = new int[]{14, 2};
        int[] pawnS3 = new int[]{10, 8};
        int[] pawnN1 = new int[]{0, 12};
        int[] pawnN2 = new int[]{4, 2};
        int[] pawnN3 = new int[]{10, 6};
        
        assertArrayEquals( pawnS3,Checking.checkPositionPawnEnemie('N', pawnS1, pawnS2, pawnS3));
        assertArrayEquals( pawnN3, Checking.checkPositionPawnEnemie('S', pawnN1, pawnN2, pawnN3));
    }
}
