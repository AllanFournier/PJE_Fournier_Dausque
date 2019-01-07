package com.example.dausque.pje_dausque_fournier.Entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;


@Entity(foreignKeys = @ForeignKey(entity = Trip.class,
        parentColumns = "id",
        childColumns = "idTrip",
        onDelete = CASCADE))
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int idNote;

    // fk
    private int idTrip;

    private String titNote;
    private String tagNote;
    private String contNote;
    private String adress;
    private double lat;
    private double lng;

    public Note() {

    }

    public Note(String titreNote, String contenuNote, int idTrip) {
        this.titNote = titreNote;
        this.contNote = contenuNote;
        this.idTrip = idTrip;
    }

    public Note(String titreNote,String tagNote, String contenuNote, String adress, double lat, double lng, int idTrip) {
        this.titNote = titreNote;
        this.tagNote = tagNote;
        this.contNote = contenuNote;
        this.idTrip = idTrip;
        this.adress = adress;
        this.lat = lat;
        this.lng = lng;
    }

    public int getIdNote() { return idNote; }

    public void setIdNote(int id) { this.idNote = id; }

    public int getIdTrip() { return idTrip; }

    public void setIdTrip(int idTrip) { this.idTrip = idTrip; }

    public String getTitNote() { return this.titNote; }

    public void setTitNote(String titreNote) { this.titNote = titreNote; }

    public String getTagNote() { return tagNote; }

    public void setTagNote(String tagNote) { this.tagNote = tagNote; }

    public String getContNote(){ return this.contNote; }

    public void setContNote(String contNote) { this.contNote = contNote; }

    public String getAdress(){ return this.adress; }

    public void setAdress(String adress) { this.adress = adress; }

    public double getLat() { return lat; }

    public void setLat(double lat) { this.lat = lat; }

    public double getLng() { return lng; }

    public void setLng(double lng) { this.lng = lng; }

}