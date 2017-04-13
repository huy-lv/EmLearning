package com.hudati.emlearning.model;

import java.util.ArrayList;

/**
 * Created by huylv on 13-Apr-17.
 */

public class Sentence {
    public ArrayList<String> words;

    public Sentence() {
        words = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Sentence{" +
                "words=" + words +
                '}';
    }
}
