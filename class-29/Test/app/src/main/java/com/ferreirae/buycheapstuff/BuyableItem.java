package com.ferreirae.buycheapstuff;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.text.DecimalFormat;

@Entity
public class BuyableItem {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String title;
    private int priceInCents;

    public BuyableItem(){};
    @Ignore
    public BuyableItem(String title, int priceInCents) {
        this.title = title;
        this.priceInCents = priceInCents;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPriceInCents() {
        return priceInCents;
    }

    public String getPriceAsANiceString() {
        return new DecimalFormat("$#.00").format(this.priceInCents / 100);
    }

    public void setPriceInCents(int priceInCents) {
        this.priceInCents = priceInCents;
    }

    @Override
    public String toString() {
        return String.format("A %s that costs %d", this.title, this.priceInCents);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
