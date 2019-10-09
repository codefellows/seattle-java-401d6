package com.ferreirae.dragons.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Dragon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne
    ApplicationUser owner;

    @ManyToMany
    @JoinTable(
            name="dragon_quests",
            joinColumns = { @JoinColumn(name="questDoer")},
            inverseJoinColumns = {@JoinColumn(name = "questGiver")}
    )
    Set<Dragon> dragonsIHaveCompletedQuestsFor;

    @ManyToMany(mappedBy = "dragonsIHaveCompletedQuestsFor")
    Set<Dragon> dragonsThatCompletedQuestsForMe;

    private String color;
    private int headCount;
    private String element;
    private boolean spikes;
    private String quest;

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

    public String getQuest() { return quest; }



    public long getId() {
        return id;
    }

    public Dragon(){};


    public Dragon(String color, int headCount, String element, boolean spikes, ApplicationUser owner, String quest){

        this.color = color;
        this.headCount = headCount;
        this.element = element;
        this.spikes = spikes;
        this.owner = owner;
        this.quest = quest;
    }

    public String toString(){
        StringBuilder quested = new StringBuilder();

        for(Dragon dragon : dragonsIHaveCompletedQuestsFor){
            quested.append(dragon.getElement());
            quested.append(" and ");
        }
        quested.delete(quested.length() -5, quested.length() -1);

        return String.format("I am a %s %s dragon with %d heads and I have completed quests for %s", this.color, this.element, this.headCount, quested);
    }

    public void completeQuest(Dragon questGiver){
        dragonsIHaveCompletedQuestsFor.add(questGiver);
    }
}
