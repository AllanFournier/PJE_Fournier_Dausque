package com.example.dausque.pje_dausque_fournier.Activity;

import android.app.ActionBar;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.dausque.pje_dausque_fournier.Entity.Note;
import com.example.dausque.pje_dausque_fournier.Others.NoteListAdapter;
import com.example.dausque.pje_dausque_fournier.R;

import java.util.List;

public class ViewNotesActivity extends AppCompatActivity {
    private NoteViewModel mNoteViewModel;
    int idTrip;
    int triSel = 0;
    private boolean mTwoPane;

    private LiveData<List<Note>> mAllNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notes);

        idTrip = getIntent().getIntExtra("idTrip", 0);
        triSel = getIntent().getIntExtra("TriType",0);

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
            System.out.println(mTwoPane);
        }
        if(!mTwoPane) {
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ViewNotesActivity.this, CreateTextNoteActivity.class);
                    intent.putExtra("idTrip",idTrip);
                    startActivity(intent);
                }
            });
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        assert recyclerView != null;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final NoteListAdapter adapter = new NoteListAdapter(this, this, mTwoPane);
        recyclerView.setAdapter(adapter);
        mNoteViewModel = new NoteViewModel(getApplication(), idTrip,triSel);

        mNoteViewModel.getmAllNotes().observe((LifecycleOwner) this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable final List<Note> notes) {
                // Update the cached copy of the words in the adapter.
                System.out.println("init");
                adapter.setmNotes(notes);
            }
        });

        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView,
                                          RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder,
                                         int direction) {
                        int position = viewHolder.getAdapterPosition();
                        Note myNote = adapter.getNoteAtPosition(position);
                        Toast.makeText(ViewNotesActivity.this, "Deleting " +
                                myNote.getTitNote(), Toast.LENGTH_LONG).show();

                        // Delete the word
                        mNoteViewModel.deleteNote(myNote);
                    }
                });
        helper.attachToRecyclerView(recyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.notes_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_tri_asc:
                Toast.makeText(this, "Tri ordre", Toast.LENGTH_SHORT)
                        .show();
                triSel = 1;
                break;
            case R.id.action_tri_dsc:
                Toast.makeText(this, "Tri tag", Toast.LENGTH_SHORT)
                        .show();
                triSel = 2;
            default:
                break;
        }
        return true;
    }
}
