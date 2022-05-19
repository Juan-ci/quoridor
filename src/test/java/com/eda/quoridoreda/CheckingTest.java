package com.eda.quoridoreda;

import mockit.MockUp;
import mockit.Mock;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

public class CheckingTest {
    
    private int[] pawn1 = new int[2];
    private int[] pawn2 = new int[2];
    private int[] pawn3 = new int[2];
    
    @Test
    public void shouldReturnPawn2() {
        pawn1[0] = 0;
        pawn1[1] = 0;
        pawn2[0] = 2;
        pawn2[1] = 8;
        pawn3[0] = 0;
        pawn3[1] = 16;
        
        assertArrayEquals(Checking.choosePawnToMove('N', pawn1, pawn2, pawn3), pawn2);
    }
}
