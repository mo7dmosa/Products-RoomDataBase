package com.example.productwith_rdb;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MyRepository {
    private ProductDao productDao;
    private  CategoryDao categoryDao;

public MyRepository(Application application){
    MyRoomDatabase database=MyRoomDatabase.getDatabase(application);
    categoryDao=database.categoryDao();
    productDao= database.ProductDao();
}
    public void insertProduct(Product products,InsertLisnther lisnther){
    MyRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
        @Override
        public void run() {
            long res =productDao.insertProduct(products);
            if (res>0)
                lisnther.onSuccess();
            else
                lisnther.onFailuer();
        }
    });
}
    public void insertCategory(Categoery categoery,InsertLisnther lisnther) {
        MyRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                long res = categoryDao.insertCategory(categoery);
                if (res > 0)
                    lisnther.onSuccess();
                else
                    lisnther.onFailuer();
            }
        });
    }
    public LiveData<List<Product>>getAllProductsByCID(int category_id){
        return productDao.getAllProducts(category_id);
    }
    public LiveData<List<Categoery>> getAllCategories(){
        return categoryDao.getAllCategory();
    }
}
