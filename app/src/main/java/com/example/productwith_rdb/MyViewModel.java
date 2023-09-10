package com.example.productwith_rdb;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MyViewModel extends AndroidViewModel {
    private MyRepository Repository;

    public MyViewModel(Application application) {
        super(application);
        Repository = new MyRepository(application);
    }
public void insertProduct(Product products ,InsertLisnther lisnther){
        Repository.insertProduct(products, lisnther);
}
    public void insertCategory(Categoery Category ,InsertLisnther lisnther){
        Repository.insertCategory(Category, lisnther);
    }
    public LiveData<List<Product>>getAllProductsByCID(int category_id){
        return Repository.getAllProductsByCID(category_id);
    }
    public  LiveData<List<Categoery>> getAllCategories(){
    return Repository.getAllCategories();
    }
}
