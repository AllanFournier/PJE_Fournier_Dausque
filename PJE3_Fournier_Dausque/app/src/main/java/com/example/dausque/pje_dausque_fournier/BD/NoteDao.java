package com.example.dausque.pje_dausque_fournier.BD;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dausque.pje_dausque_fournier.Entity.Note;

import java.util.List;

@Dao
public interface NoteDao {
    @Insert
    void insert(Note note);

    @Update
    void update(Note ... note);

    @Delete
    void deleteNote(Note note);

    @Query("DELETE FROM Note")
    void deleteAll();

    @Query("SELECT * FROM Note WHERE idTrip=:idTrip ORDER BY tagNote ASC")
    LiveData<List<Note>> findNotesForTripTag(final int idTrip);
    @Query("SELECT * FROM Note WHERE idTrip=:idTrip")
    LiveData<List<Note>> findNotesForTrip(final int idTrip);

}
