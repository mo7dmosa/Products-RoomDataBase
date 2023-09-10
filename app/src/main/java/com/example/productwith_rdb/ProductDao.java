package com.example.productwith_rdb;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;

import java.util.List;

@Dao
@TypeConverters(DataConverter.class)
public interface ProductDao {
    @Insert
    long insertProduct(Product products);

    @Query("select * from Product where id=:catID")
    LiveData<List<Product>> getAllProducts(int catID);
}
