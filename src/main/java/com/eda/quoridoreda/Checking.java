package com.eda.quoridoreda;

import java.util.concurrent.ThreadLocalRandom;

public class Checking {
    
    public static int[] choosePawnToMove(char namePawn, int[] pawn1, int[] pawn2, int[] pawn3) {

        switch (namePawn) {
            case 'N' -> {
                if (pawn1[0] == pawn2[0] && pawn1[0] == pawn3[0]) {
                    return pickRandomPawn(pawn1, pawn2, pawn3);
                }
                if (pawn1[0] > pawn2[0] && pawn1[0] > pawn3[0]) {
                    return pawn1;
                } else if (pawn2[0] > pawn3[0] && pawn2[0] > pawn1[0]) {
                    return pawn2;
                } else {
                    return pawn3;
                }
            }
            case 'S' -> {
                if (pawn1[0] == pawn2[0] && pawn1[0] == pawn3[0]) {
                    return pickRandomPawn(pawn1, pawn2, pawn3);
                }
                if (pawn1[0] < pawn2[0] && pawn1[0] < pawn3[0]) {   //Verifica cual está más adelantado
                    return pawn1;
                } else if (pawn2[0] < pawn3[0] && pawn2[0] < pawn1[0]) {
                    return pawn2;
                } else {
                    return pawn3;
                }
            }
            default -> {
                System.out.println("ERROR IN CLASS Pawn METHOD choosePawnToMove.");
                return null;
            }
        }
    }

    public static int[] pickRandomPawn(int[] pawn1, int[] pawn2, int[] pawn3) {
        int min = 1;
        int max = 3;

        int getRandomValue = ThreadLocalRandom.current().nextInt(0, max) + min;
        System.out.println("Pawn n° " + getRandomValue + " selected.");

        switch (getRandomValue) {
            case 1 -> {
                return pawn1;
            }
            case 2 -> {
                return pawn2;
            }
            case 3 -> {
                return pawn3;
            }
            default -> {
                System.out.println("ERROR PICKING RANDOM PAWN.");
                return pawn2;
            }
        }
    }
}
