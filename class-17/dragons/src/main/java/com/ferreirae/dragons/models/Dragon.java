package com.ferreirae.dragons.models;

import javax.persistence.*;

@Entity
public class Dragon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne
    ApplicationUser owner;

    private String color;
    private int headCount;
    private String element;
    private boolean spikes;

    public String getColor() {
        return color;
    }

    public int getHeadCount() {
        return headCount;
    }

    public String getElement() {
        return element;
    }

    public boolean isSpikes() {
        return spikes;
    }



    public long getId() {
        return id;
    }

    public Dragon(){};


    public Dragon(String color, int headCount, String element, boolean spikes, ApplicationUser owner){

        this.color = color;
        this.headCount = headCount;
        this.element = element;
        this.spikes = spikes;
        this.owner = owner;
    }

    public String toString(){
        return String.format("I am a %s %s dragon with %d heads and yeah", this.color, this.element, this.headCount);
    }
}
