package com.example.mojodatlens.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mojodatlens.model.entity.Item;

import java.util.List;

@Dao
public interface ItemDao {

    @Query("Select * From Item")
    List<Item> getAll();

    @Query("Select * From Item Where Barcode Like :barcode")
    LiveData<Item> findByBarcode(String barcode);

    @Insert
    void insert(Item item);
}