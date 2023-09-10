package com.example.productwith_rdb;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

@Entity
public class Categoery {
    @ColumnInfo
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo
    @NonNull
    private String title;

    public Categoery() {
    }

    public Categoery(int id, @NonNull String title) {
        this.id = id;
        this.title = title;
    }

    public Categoery(@NonNull String title) {
        this.title = title;
    }

    public Categoery(int id) {
        this.id = id;
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
}
