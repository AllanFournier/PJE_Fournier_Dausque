package com.example.dausque.pje_dausque_fournier.BD;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.dausque.pje_dausque_fournier.Entity.Note;
import com.example.dausque.pje_dausque_fournier.Entity.Trip;

@Database(entities = {Trip.class, Note.class}, version = 1)
public abstract class TripRoomDatabase extends RoomDatabase {

    public abstract TripDao tripDao();

    public abstract NoteDao noteDao();

    static volatile TripRoomDatabase INSTANCE;

    public static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        final TripDao mDao;
        final NoteDao noteDao;

        PopulateDbAsync(TripRoomDatabase db) {
            mDao = db.tripDao();
            noteDao = db.noteDao();
        }

        @Override
        public Void doInBackground(final Void... params) {
            mDao.deleteAll();
            Trip trip = new Trip("Switzerland","The most beautiful country in the world by far");
            mDao.insert(trip);
            trip = new Trip("Japan","The land of the weeb");
            mDao.insert(trip);
            return null;
        }
    }

    static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    public static TripRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TripRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here

                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TripRoomDatabase.class, "trip_database")
                            .addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }
}
