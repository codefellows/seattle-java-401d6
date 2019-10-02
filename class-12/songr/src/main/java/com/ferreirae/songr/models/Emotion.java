package com.ferreirae.songr.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Emotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    public String emotionName;
    int strength;
    String reason;

    public Emotion(){}

    public Emotion(String emotionName, int strength, String reason) {
        this.emotionName = emotionName;
        this.strength = strength;
        this.reason = reason;
    }

    public String toString() {
        return String.format("Feeling %s because %s", emotionName, reason);
    }
}
