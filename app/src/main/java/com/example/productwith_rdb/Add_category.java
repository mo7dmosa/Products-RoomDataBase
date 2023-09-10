package com.example.productwith_rdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.productwith_rdb.databinding.ActivityAddCategoryBinding;

public class Add_category extends AppCompatActivity {
    ActivityAddCategoryBinding binding;
    String title;
    MyViewModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAddCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        model=new ViewModelProvider(this).get(MyViewModel.class);

        binding.btnAddCatSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title=binding.etAddCat.getText().toString();
                Categoery categoery=new Categoery(title);
                model.insertCategory(categoery, new InsertLisnther() {
                    @Override
                    public void onSuccess() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Add_category.this, "Category added successfully",
                                        Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                    @Override
                    public void onFailuer() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Add_category.this, "Category added failure",
                                        Toast.LENGTH_LONG).show();
                            }
                        });

                    }
                });
                finish();
            }
        });
    }
}