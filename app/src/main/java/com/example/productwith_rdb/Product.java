package com.example.productwith_rdb;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity(foreignKeys = {@ForeignKey (entity =Categoery.class,parentColumns
        = {"id"},childColumns = {"category_id"})})
@TypeConverters(DataConverter.class)
public class Product {
    @ColumnInfo
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo
    @NonNull
    private String title;
    @ColumnInfo
    private String description;
    @ColumnInfo
    private String image;
    @ColumnInfo
    @NonNull
    private double price;
    @ColumnInfo
    @NonNull
    private Date exp_date;
    @ColumnInfo
    @NonNull
    private long category_id;

    public Product(int id, @NonNull String title,
                   String description, String image, double price, @NonNull Date exp_date, long category_id) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.price = price;
        this.exp_date = exp_date;
        this.category_id = category_id;
    }

    public Product(@NonNull String title,
                   String description, String image, double price, @NonNull Date exp_date, long category_id) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.price = price;
        this.exp_date = exp_date;
        this.category_id = category_id;
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @NonNull
    public Date getExp_date() {
        return exp_date;
    }

    public void setExp_date(@NonNull Date exp_date) {
        this.exp_date = exp_date;
    }

    public long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
    }
}



