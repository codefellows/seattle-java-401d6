package com.ferreirae.buycheapstuff;

import java.text.DecimalFormat;

public class BuyableItem {
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
