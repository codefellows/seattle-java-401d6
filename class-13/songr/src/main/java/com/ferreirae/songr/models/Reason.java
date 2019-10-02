package com.ferreirae.songr.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Entity
public class Reason {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    long id;

    @ManyToOne
    Emotion emotion;

    String text;
    java.sql.Timestamp time;

    public Reason(){};

    public Reason (String text){
        this.text = text;
        this.time = new java.sql.Timestamp(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
    }

    public void setEmotion(Emotion e) {
        this.emotion = e;
    }

    public String toString(){
        return this.text;
    }
}
