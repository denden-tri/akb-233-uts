package com.denztri.denzsakura.db;

import androidx.room.TypeConverter;

import com.denztri.denzsakura.ui.media.tab.music.MusicImagesList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Converters {
    public static Gson gson = new Gson();

    @TypeConverter
    public static List<String> artisFromString(String value){
        Type listType = new TypeToken<List<String>>(){}.getType();
        return gson.fromJson(value,listType);
    }

    @TypeConverter
    public static String artisFromList(List<String> list){
        return gson.toJson(list);
    }

    @TypeConverter
    public static List<MusicImagesList> imageFromString(String value){
        Type listType = new TypeToken<List<MusicImagesList>>(){}.getType();
        return gson.fromJson(value,listType);
    }

    @TypeConverter
    public static String imageFromList(List<MusicImagesList> list){
        return gson.toJson(list);
    }
}
