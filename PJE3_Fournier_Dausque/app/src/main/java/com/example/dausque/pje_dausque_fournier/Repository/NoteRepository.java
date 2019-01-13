package com.example.dausque.pje_dausque_fournier.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.dausque.pje_dausque_fournier.BD.NoteDao;
import com.example.dausque.pje_dausque_fournier.BD.TripDao;
import com.example.dausque.pje_dausque_fournier.BD.TripRoomDatabase;
import com.example.dausque.pje_dausque_fournier.Entity.Note;
import com.example.dausque.pje_dausque_fournier.Entity.Trip;

import java.util.List;

public class NoteRepository {

    private NoteDao mNoteDao;
    private LiveData<List<Note>> mAllNotes;
    private LiveData<List<Note>> mAllTripNotes;
    private int order;

    /*public NoteRepository(Application application) {
        TripRoomDatabase db = TripRoomDatabase.getDatabase(application);
        mNoteDao = db.noteDao();
        mAllNotes = mNoteDao.getAllNotes();
    }*/

   public NoteRepository(Application application,int id,int tri) {
        TripRoomDatabase db = TripRoomDatabase.getDatabase(application);
        mNoteDao = db.noteDao();
        if(tri == 1) {
            mAllTripNotes = mNoteDao.findNotesForTrip(id);
        }
        if(tri == 2){
            mAllTripNotes = mNoteDao.findNotesForTripTag(id);
        }
       if(tri == 3){
           mAllTripNotes = mNoteDao.findNotesForTripAdr(id);
       }
    }



    public LiveData<List<Note>> getAllNotes() {
        return mAllTripNotes;
    }

    public void insert (Note note) {
        new insertAsyncTask(mNoteDao).execute(note);
    }

    public void deleteNote(Note note) { new deleteNoteAsyncTask(mNoteDao).execute(note); }

    public static class deleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao mAsyncTaskDao;

        deleteNoteAsyncTask(NoteDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Note... params) {
            mAsyncTaskDao.deleteNote(params[0]);
            return null;
        }
    }

    public static class insertAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDao mAsyncTaskDao;

        insertAsyncTask(NoteDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Note... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
