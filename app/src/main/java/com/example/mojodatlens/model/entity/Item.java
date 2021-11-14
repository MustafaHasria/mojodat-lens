package com.example.mojodatlens.model.entity;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Item")
public class Item {

    //region Property

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    private int id;

    @ColumnInfo(name = "Barcode")
    private String barcode;

    @ColumnInfo(name = "AssetDescription")
    private String assetDescription;

    @ColumnInfo(name = "AssetCategory")
    private String assetCategory;

    //endregion

    //region Constructor

    public Item(String barcode, String assetDescription, String assetCategory) {
        this.barcode = barcode;
        this.assetDescription = assetDescription;
        this.assetCategory = assetCategory;
    }

    //endregion

    //region Getters & Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getAssetDescription() {
        return assetDescription;
    }

    public void setAssetDescription(String assetDescription) {
        this.assetDescription = assetDescription;
    }

    public String getAssetCategory() {
        return assetCategory;
    }

    public void setAssetCategory(String assetCategory) {
        this.assetCategory = assetCategory;
    }

    //endregion

}
