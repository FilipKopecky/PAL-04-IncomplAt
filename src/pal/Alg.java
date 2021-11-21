package pal;

public class Alg {

    int[][] states;
    byte[][] words;
    int numPositive;
    int exampleLength;

    public Alg(int[][] states, byte[][] words, int numPositive, int exampleLength) {
        this.states = states;
        this.words = words;
        this.numPositive = numPositive;
        this.exampleLength = exampleLength;
    }

    public int walkThrough(int startingPoint, int wordIndex) {

        for (int l = 0; l < exampleLength; l++) {
            int nextStateIndex = words[wordIndex][l] - 97;
            startingPoint = states[startingPoint][nextStateIndex];
        }
        return startingPoint;
    }




}
