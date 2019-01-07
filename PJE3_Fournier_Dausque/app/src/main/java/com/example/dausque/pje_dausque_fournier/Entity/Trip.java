package com.example.dausque.pje_dausque_fournier.Entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "trip_table")
public class Trip {


    @PrimaryKey(autoGenerate = true)
    private int id;
    private String mTrip;
    private String dTrip;

    public Trip() {

    }

    public Trip(String trip, String desc_trip) {
        this.mTrip = trip;
        this.dTrip = desc_trip;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getMTrip() { return this.mTrip; }

    public void setMTrip(String mTrip) {
        this.mTrip = mTrip;
    }

    public String getDTrip() {
        return this.dTrip;
    }

    public void setDTrip(String dTrip) {
        this.dTrip = dTrip;
    }


}


