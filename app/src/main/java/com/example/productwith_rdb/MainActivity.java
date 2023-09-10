package com.example.productwith_rdb;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.productwith_rdb.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
MyViewModel model;
CategoryAdapter categoryAdapter;
ProductAdapter productAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        model=new ViewModelProvider(this).get(MyViewModel.class);
        model.getAllCategories().observe(this, new Observer<List<Categoery>>() {
        @Override
        public void onChanged(List<Categoery> categoeries) {
        categoryAdapter=new CategoryAdapter(categoeries,getBaseContext());
        binding.CategoryRv.setHasFixedSize(true);
        binding.CategoryRv.setAdapter(categoryAdapter);
        binding.CategoryRv.setLayoutManager(new LinearLayoutManager(getBaseContext(),
                    LinearLayoutManager.HORIZONTAL,false));
        categoryAdapter.setListner(new Clicked_listner() {
            @Override
            public void onClicked(Categoery categoery) {
                model.getAllProductsByCID(categoery.getId()).observe(MainActivity.this,
                        new Observer<List<Product>>() {
                    @Override
                    public void onChanged(List<Product> products) {
                        productAdapter=new ProductAdapter(products,getBaseContext());
                        binding.ProductRv.setHasFixedSize(true);
                        binding.ProductRv.setAdapter(productAdapter);
                        binding.ProductRv.setLayoutManager(new LinearLayoutManager(getBaseContext(),
                                LinearLayoutManager.VERTICAL,false));
                    }
                });
            }
        });
       /* categoryAdapter.setListner(new Clicked_listner() {
    @Override
    public void onClicked(int id) {
        model.getAllProductsByCID(id).observe(MainActivity.this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
        productAdapter=new ProductAdapter(products,getBaseContext());
                binding.ProductRv.setHasFixedSize(true);
                binding.CategoryRv.setAdapter(productAdapter);
                binding.ProductRv.setLayoutManager(new LinearLayoutManager(getBaseContext(),
                        LinearLayoutManager.VERTICAL,false));
            }
        });
    }
    });*/
    }
    });

        Visibilty_gone();
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Visibilty_visible();
            }
        });
        binding.fabCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),Add_category.class);
                startActivity(intent);
            }
        });
        binding.fabProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),Add_Product.class);
                startActivity(intent);
            }
        });
    }
    public void Visibilty_gone() {
        binding.fabCat.setVisibility(View.GONE);
        binding.fabProduct.setVisibility(View.GONE);
        binding.textView.setVisibility(View.GONE);
        binding.textView2.setVisibility(View.GONE);
    }
    public void Visibilty_visible() {
        binding.fabCat.setVisibility(View.VISIBLE);
        binding.fabProduct.setVisibility(View.VISIBLE);
        binding.textView.setVisibility(View.VISIBLE);
        binding.textView2.setVisibility(View.VISIBLE);
    }
}