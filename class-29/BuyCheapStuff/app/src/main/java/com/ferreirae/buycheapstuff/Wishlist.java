package com.ferreirae.buycheapstuff;

import java.util.List;


public class Wishlist {

    private String name;

    public List<BuyableItem> getBuyableItems() {
        return buyableItems;
    }

    public void setBuyableItems(List<BuyableItem> buyableItems) {
        this.buyableItems = buyableItems;
    }

    private List<BuyableItem> buyableItems;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Wishlist(){};

    public Wishlist(String name){
        this.name = name;
        this.name = name;
    }
}
