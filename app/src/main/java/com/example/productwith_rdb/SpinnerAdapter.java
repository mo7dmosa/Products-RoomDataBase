package com.example.productwith_rdb;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.productwith_rdb.databinding.CustomSpinnerCategoryBinding;

import java.util.ArrayList;
import java.util.List;

public class SpinnerAdapter extends BaseAdapter {
   List<Categoery> categoeries;
    Context context;

    public SpinnerAdapter(List<Categoery> categoeries, Context context) {
        this.categoeries = categoeries;
        this.context = context;
    }

    @Override
    public int getCount() {
        return categoeries.size();
    }

    @Override
    public Categoery getItem(int i) {
        return categoeries.get(i);
    }

    @Override
    public long getItemId(int i) {
        return categoeries.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CustomSpinnerCategoryBinding binding=CustomSpinnerCategoryBinding.inflate(LayoutInflater.from(context));
        Categoery categoery= categoeries.get(i);
        binding.TVCCSp.setText(categoery.getTitle());
        return binding.getRoot();
    }
}
