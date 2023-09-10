package com.example.productwith_rdb;

import androidx.room.TypeConverter;

import java.util.Date;

public class DataConverter {
    @TypeConverter
    public Long fromDate(Date date){
        if (date!=null)
            return date.getTime();
        else
            return new Date().getTime();

    }
    @TypeConverter
    public Date toDate(Long millis){
        if (millis>0)
            return new Date(millis);
        else
            return  null;
    }
}
