package com.denztri.denzsakura.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Friend {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    public Friend(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static Friend[] populateData(){
        return new Friend[]{
          new Friend("Tri","Tafriyadi"),
          new Friend("Joyson","Sitorus"),
          new Friend("Najib", "Raffi"),
                new Friend("Rheza","P"),
        };
    }
}
