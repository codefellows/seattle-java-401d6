package com.ferreirae.buycheapstuff;

import com.amazonaws.amplify.generated.graphql.ListBuyableItemsQuery;
import com.amazonaws.amplify.generated.graphql.OnCreateBuyableItemSubscription;

import java.text.DecimalFormat;

public class BuyableItem {
    private String title;
    private int priceInCents;

    public BuyableItem(String title, int priceInCents) {
        this.title = title;
        this.priceInCents = priceInCents;
    }

    public BuyableItem(ListBuyableItemsQuery.Item item){
        this.title = item.title();
        this.priceInCents = item.priceInCents();
    }

    public BuyableItem(OnCreateBuyableItemSubscription.OnCreateBuyableItem item){
        this.title = item.title();
        this.priceInCents = item.priceInCents();
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
