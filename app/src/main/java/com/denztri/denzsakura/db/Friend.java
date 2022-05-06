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

    @ColumnInfo(name = "drawable_name")
    public String drawableName;

    public Friend(String firstName, String lastName,String drawableName){
        this.firstName = firstName;
        this.lastName = lastName;
        this.drawableName = drawableName;
    }

    public static Friend[] populateData(){
        return new Friend[]{
          new Friend("Tri","Tafriyadi", "ic_tri_chad"),
          new Friend("Najib", "Raffi", "ic_jib"),
          new Friend("Rheza","Pramana", "ic_rheza"),
        };
    }
}
