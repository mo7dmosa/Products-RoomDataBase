package com.example.productwith_rdb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

import com.example.productwith_rdb.databinding.ActivityTaplayoutBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

public class TaplayoutActivity extends AppCompatActivity implements ShowButtonListener {
    ActivityTaplayoutBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTaplayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


       MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);

        model.getAllCategories().observe(this, new Observer<List<Categoery>>() {
            @Override
            public void onChanged(List<Categoery> categoeries) {
                PagerAdapter adapter = new PagerAdapter(TaplayoutActivity.this,categoeries);
                binding.pager.setAdapter(adapter);
                new TabLayoutMediator(binding.tl, binding.pager, new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText(categoeries.get(position).getTitle());
                    }
                }).attach();
            }
        });
    }

    @Override
    public void onShow(int numofProduct ) {
        Toast.makeText(this, "size is: "+numofProduct, Toast.LENGTH_SHORT).show();
    }
}