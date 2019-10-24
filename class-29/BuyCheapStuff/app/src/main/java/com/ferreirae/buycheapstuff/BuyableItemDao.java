package com.ferreirae.buycheapstuff;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BuyableItemDao {

    @Query("SELECT * FROM buyableitem ORDER BY id DESC")
    List<BuyableItem> getAll();

    @Query("SELECT * FROM buyableitem WHERE task_title =:title")
    List<BuyableItem> getItemsByName(String title);

    @Insert
    void addItem(BuyableItem buyableItem);

    @Update
    void updateItem(BuyableItem buyableItem);

    @Delete
    void deleteItem(BuyableItem buyableItem);

}
