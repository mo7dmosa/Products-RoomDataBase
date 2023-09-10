package com.example.productwith_rdb;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CategoryDao {

    @Insert
    long insertCategory(Categoery categoerys);

    @Query("select * from Categoery")
    LiveData<List<Categoery>>getAllCategory();

}
