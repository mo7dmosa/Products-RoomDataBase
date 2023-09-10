package com.example.productwith_rdb;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.productwith_rdb.databinding.CustomProductBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {
List<Product> products;
Context context;

    public ProductAdapter(List<Product> products, Context context) {
        this.products = products;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CustomProductBinding binding=CustomProductBinding.inflate(LayoutInflater.from(context),parent,false);

        return new ProductHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        Product product= products.get(position);
        holder.binding.tvPTitle.setText(product.getTitle());
        holder.binding.tvPPrice.setText(String.valueOf(product.getPrice()));
        holder.binding.tvPEx.setText(String.valueOf(product.getExp_date()));
        holder.binding.customIv.setImageURI(Uri.parse(product.getImage()));
       // Picasso.get().load(product.getImage()).into(holder.binding.customIv);

    }
    @Override
    public int getItemCount() {
        return products.size();
    }

    class ProductHolder extends RecyclerView.ViewHolder{
CustomProductBinding binding;
        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            binding=CustomProductBinding.bind(itemView);
        }
    }
}
