package com.example.poemreadergui;

public class tableStorage {

    private String word;
    private int frequency;

    public tableStorage(String word, int frequency) {
        this.word = word;
        this.frequency = frequency;
    }

    public String getWord() {
        return word;
    }

    public int getFrequency() {
        return frequency;
    }

}
