package com.example.mojodatlens.view.assetslist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mojodatlens.model.entity.Item;
import com.example.mojodatlens.repository.ItemRepository;

public class AssetsListViewModel extends AndroidViewModel {

    //region Variables
    private ItemRepository itemRepository;
    //endregion

    //region Constructor
    public AssetsListViewModel(@NonNull Application application) {
        super(application);
        itemRepository = new ItemRepository(application);
    }
    //endregion

    //region Methods
    public LiveData<Item> findByBarcode(String barcode){
        return itemRepository.findByBarcode(barcode);
    }
    //endregion
}
