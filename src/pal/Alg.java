package pal;

import java.util.ArrayList;

public class Alg {

    int[][] states;
    ArrayList<char[]> words;
    int numPositive;
    int exampleLength;

    public Alg(int[][] states, ArrayList<char[]>words, int numPositive, int exampleLength) {
        this.states = states;
        this.words = words;
        this.numPositive = numPositive;
        this.exampleLength = exampleLength;
    }

    public int walkThrough(int startingPoint, int wordIndex) {

        for (int l = 0; l < exampleLength; l++) {
            int nextStateIndex = words.get(wordIndex)[l] - 97;
            startingPoint = states[startingPoint][nextStateIndex];
        }
        return startingPoint;
    }




}
