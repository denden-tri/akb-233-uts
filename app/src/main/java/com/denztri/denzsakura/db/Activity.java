package com.denztri.denzsakura.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Activity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "activity_name")
    public String activityName;

    @ColumnInfo(name = "drawable_name")
    public String drawableName;

    public Activity(String activityName, String drawableName) {
        this.activityName = activityName;
        this.drawableName = drawableName;
    }


    public static Activity[] populateData(){
        return new Activity[]{
                new Activity("Geming Genshin","ic_genshin_2"),
                new Activity("Nonton Twice", "ic_nonton_twice"),
                new Activity("Coding", "ic_coding"),
                new Activity("Menghabiskan RAM", "ic_menghabiskan_ram"),
        };
    }
}
