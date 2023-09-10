package com.example.productwith_rdb;

import androidx.lifecycle.LiveData;

import java.util.List;

public interface CategoryListner {
    void onCategoriesFetched(LiveData<List<Categoery>> data);
}
