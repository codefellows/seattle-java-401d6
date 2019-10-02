package com.ferreirae.songr.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Emotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

//    the string in mappedBy specifies the instance variable of the other class
    @OneToMany(mappedBy = "emotion")
    List<Reason> reasons;

    public String emotionName;
    int strength;

    public Emotion(){}

    public Emotion(String emotionName, int strength) {
        this.emotionName = emotionName;
        this.strength = strength;
    }

    public String toString() {
        return String.format("Feeling %s because %s", emotionName, reasons.toString());
    }
}
