package com.example.productwith_rdb;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Dao;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.productwith_rdb.databinding.ActivityAddProductBinding;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Add_Product extends AppCompatActivity {
    DatePickerDialog.OnDateSetListener date;
    MyViewModel model1;
    ActivityAddProductBinding binding;
    Uri imageuri;
    String title,des;
    double price;
    long category_id;
    Date ex_date;
    ActivityResultLauncher<String[]>arl;
    Calendar myCalendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAddProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        model1=new ViewModelProvider(this).get(MyViewModel.class);
        model1.getAllCategories().observe(this, new Observer<List<Categoery>>() {
            @Override
            public void onChanged(List<Categoery> categoeries) {
                SpinnerAdapter adapter=new SpinnerAdapter(categoeries,getBaseContext());
                binding.spCategory.setAdapter(adapter);
            }
        });
        arl=registerForActivityResult(new ActivityResultContracts.OpenDocument()
                , new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
            imageuri=result;
                try {
                    getContentResolver().takePersistableUriPermission(imageuri, (Intent.FLAG_GRANT_READ_URI_PERMISSION
                            | Intent.FLAG_GRANT_WRITE_URI_PERMISSION));
                } catch (Exception e) {

                }
                binding.ivDetails.setImageURI(imageuri);
            }
        });
        binding.ivDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arl.launch(new String[]{"image/*"});
            }
        });
        binding.etPEx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //binding.etPEx.setEnabled(false);
                MaterialDatePicker.Builder<Long> builder= MaterialDatePicker.Builder.datePicker();
                MaterialDatePicker<Long>datePicker= builder.build();
                datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
                    @Override
                    public void onPositiveButtonClick(Long selection) {
                        myCalendar=Calendar.getInstance();
                        myCalendar.setTimeInMillis(selection);
                        binding.etPEx.setText(myCalendar.get(Calendar.DAY_OF_MONTH ) + "/" + myCalendar.get(Calendar.MONTH )
                                + "/" +myCalendar.get(Calendar.YEAR ) );
                    }
                });
                datePicker.show(getSupportFragmentManager(),"");
            }
        });
        binding.btnProductSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            title=binding.etPTitle.getText().toString();
            des=binding.etPDes.getText().toString();
            price=Double.parseDouble(binding.etPTprice.getText().toString());
            category_id=binding.spCategory.getSelectedItemId();
            Product product=new Product(title,des,imageuri.toString(),price,ex_date,category_id);
            model1.insertProduct(product, new InsertLisnther() {
                @Override
                public void onSuccess() {
                runOnUiThread(new Runnable() {
                @Override
                public void run() {
        Toast.makeText(getBaseContext(), "product added successfully", Toast.LENGTH_SHORT).show();
    }
});
                }
                @Override
                public void onFailuer() {
                runOnUiThread(new Runnable() {
                @Override
                public void run() {
        Toast.makeText(getBaseContext(), "product added failure", Toast.LENGTH_SHORT).show();

    }
});
                }
            });
            finish();
            }
        });
    }
}