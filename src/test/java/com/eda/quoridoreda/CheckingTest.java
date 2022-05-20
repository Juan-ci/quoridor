package com.eda.quoridoreda;

import static org.junit.Assert.*;
import org.junit.Test;

public class CheckingTest {

    char[] board = null;
    char[][] normalized = new char[17][17];
    
    @Test
    public void shouldReturnFalseCheckMoveForward() {
        board = "    N         N                           N                                                                                                                          -*-              S                                                                                             S   S        ".toCharArray();
        normalized = Board.armarTablero(board);
        int[] currentPosition = new int[]{10, 12};
        
        assertFalse(Checking.checkMoveForward(normalized, currentPosition, 'S'));
    }
    
    @Test
    public void shouldReturnTrueCheckMoveForward() {
        board = "  N     N     N                                                                                                                                                                                                                                                                   S     S     S  ".toCharArray();
        normalized = Board.armarTablero(board);
        int[] currentPosition = new int[]{2, 2};
        
        assertTrue(Checking.checkMoveForward(normalized, currentPosition, 'N'));
    }
}
