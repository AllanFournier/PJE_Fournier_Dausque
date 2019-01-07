package com.example.dausque.pje_dausque_fournier.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.dausque.pje_dausque_fournier.R;

public class NoteFragDetailActivity extends AppCompatActivity {

    String noteTitre;
    String noteDesc;
    String noteAdrr;
    String noteTag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_of_note_frag);



        getIncomingIntent();

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            NoteFragmentDetail fragment =  NoteFragmentDetail.newInstance(noteTitre,noteDesc,noteAdrr,noteTag);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.note_detail_container, fragment)
                    .commit();
        }
    }

    private void getIncomingIntent(){

        if(getIntent().hasExtra("note_titre") && getIntent().hasExtra("note_desc")){
            noteTitre = getIntent().getStringExtra("note_titre");
            System.out.println("note titre" + noteTitre);
            noteDesc = getIntent().getStringExtra("note_desc");
            System.out.println("note desc" + noteDesc);
            noteAdrr = getIntent().getStringExtra("note_addr");
            System.out.println("note desc" + noteAdrr);
            noteTag = getIntent().getStringExtra("note_tag");
            System.out.println("note desc" + noteTag);

        }
    }
}
