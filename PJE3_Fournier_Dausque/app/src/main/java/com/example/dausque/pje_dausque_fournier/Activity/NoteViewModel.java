package com.example.dausque.pje_dausque_fournier.Activity;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.dausque.pje_dausque_fournier.Entity.Note;
import com.example.dausque.pje_dausque_fournier.Entity.Trip;
import com.example.dausque.pje_dausque_fournier.Repository.NoteRepository;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private NoteRepository noteRepository;

    private LiveData<List<Note>> mAllNotes;


    NoteViewModel (Application application,int id,int tri) {
        super(application);
        noteRepository = new NoteRepository(application,id,tri);
        mAllNotes = noteRepository.getAllNotes();
    }

    public LiveData<List<Note>> getmAllNotes() {
        return mAllNotes;
    }

    public void insert(Note note) {
        noteRepository.insert(note);
    }

    public void deleteNote(Note note) {noteRepository.deleteNote(note);}
}
