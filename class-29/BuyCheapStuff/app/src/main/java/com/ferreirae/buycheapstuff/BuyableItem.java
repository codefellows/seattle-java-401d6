package com.ferreirae.buycheapstuff;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.util.TableInfo;

import java.text.DecimalFormat;

@Entity
public class BuyableItem {

    @PrimaryKey(autoGenerate = true)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ColumnInfo(name = "task_title") //allows me to change the column name
    private String title;

    private int priceInCents;


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
}
