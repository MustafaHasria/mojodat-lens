package com.example.mojodatlens.view.assetslist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AssetsListQrReaderViewModel extends ViewModel {
    public MutableLiveData<String> code = new MutableLiveData<>("");
}
