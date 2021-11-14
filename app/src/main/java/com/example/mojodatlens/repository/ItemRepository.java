package com.example.mojodatlens.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.mojodatlens.model.dao.ItemDao;
import com.example.mojodatlens.model.database.AppDatabase;
import com.example.mojodatlens.model.entity.Item;

import java.util.List;

public class ItemRepository
{
    //region Variables
    private final ItemDao itemDao;
    //endregion

    //region Constructor
    public ItemRepository(Application application)
    {
        itemDao = AppDatabase.getInstance(application).getItemDao();
    }
    //endregion

    //region Methods
    public void insert(Item item)
    {
        AppDatabase.databaseWriteExecutor.execute(() ->
        {
            itemDao.insert(item);
        });
    }

    public LiveData<Item> findByBarcode(String barcode)
    {
        return itemDao.findByBarcode(barcode);
    }
    //endregion
}

