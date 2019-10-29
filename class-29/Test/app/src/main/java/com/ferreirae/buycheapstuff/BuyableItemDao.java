package com.ferreirae.buycheapstuff;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BuyableItemDao {

    @Query("SELECT * FROM buyableitem")
    List<BuyableItem> getAll();

    @Query("SELECT * FROM buyableitem WHERE title = :title")
    BuyableItem findByTitle(String title);

    @Insert
    void add(BuyableItem buyableItem);

    @Update
    void update(BuyableItem buyableItem);

    @Delete
    void delete(BuyableItem buyableItem);
}
