package com.ferreirae.buycheapstuff;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {BuyableItem.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract BuyableItemDao buyableItemDao();
}
