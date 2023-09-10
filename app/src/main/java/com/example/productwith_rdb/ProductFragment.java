package com.example.productwith_rdb;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.productwith_rdb.databinding.FragmentProductBinding;

import java.util.List;

public class ProductFragment extends Fragment {

    private static final String CATEGORY_ID = "catid";
    private  ShowButtonListener listener;
    int numberofProduct;

    // TODO: Rename and change types of parameters
    private int catId;

    public ProductFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (ShowButtonListener) context;

    }

    // TODO: Rename and change types and number of parameters
    public static ProductFragment newInstance(int catId) {
        ProductFragment fragment = new ProductFragment();
        Bundle args = new Bundle();
        args.putInt(CATEGORY_ID, catId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            catId = getArguments().getInt(CATEGORY_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentProductBinding binding =FragmentProductBinding.inflate(getLayoutInflater());
        // Inflate the layout for this fragment
        MyViewModel myViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        myViewModel.getAllProductsByCID(catId).observe(requireActivity(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                numberofProduct = products.size();
                 ProductAdapter productAdapter=new ProductAdapter(products,requireActivity());
                binding.fRv.setHasFixedSize(true);
                binding.fRv.setAdapter(productAdapter);
                binding.fRv.setLayoutManager(new LinearLayoutManager(requireActivity(),
                        LinearLayoutManager.VERTICAL,false));
            }
        });
        binding.btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onShow(numberofProduct);
            }
        });
        return binding.getRoot();
}}