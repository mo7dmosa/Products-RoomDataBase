package com.example.productwith_rdb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.productwith_rdb.databinding.CustomCategoryBinding;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder>{
List<Categoery>categoeries;
Context context;
Clicked_listner listner;

    public CategoryAdapter(List<Categoery> categoeries, Context context) {
        this.categoeries = categoeries;
        this.context = context;
    }

    public Clicked_listner getListner() {
        return listner;
    }

    public void setListner(Clicked_listner listner) {
        this.listner = listner;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CustomCategoryBinding binding=CustomCategoryBinding.inflate(LayoutInflater.from(context),parent,false);
        return new CategoryHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        Categoery categoery= categoeries.get(position);
        holder.binding.tvCatTitle.setText(categoery.getTitle());
        holder.binding.cv.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        listner.onClicked(categoery);
    }
});

    }

    @Override
    public int getItemCount() {
        return categoeries.size();
    }

    class CategoryHolder extends RecyclerView.ViewHolder {
CustomCategoryBinding binding;
        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            binding=CustomCategoryBinding.bind(itemView);
        }
    }
}
