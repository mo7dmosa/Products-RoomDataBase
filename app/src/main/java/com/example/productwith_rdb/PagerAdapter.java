package com.example.productwith_rdb;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class PagerAdapter extends FragmentStateAdapter {
    List<Categoery> categoeryList;

    public PagerAdapter(@NonNull FragmentActivity fragmentActivity, List<Categoery> categoeryList) {
        super(fragmentActivity);
        this.categoeryList=categoeryList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return ProductFragment.newInstance(categoeryList.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return categoeryList.size();
    }
}
