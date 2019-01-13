package com.example.dausque.pje_dausque_fournier.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.dausque.pje_dausque_fournier.R;

public class TriSelectActivity extends AppCompatActivity {
    int idTrip;
    int triTrip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tri_sel);
        getIncomingIntent();

    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("idTrip")) {
            idTrip = getIntent().getIntExtra("idTrip", 0);
            System.out.println(idTrip);
        }
    }

    public void viewTripClassique(View view) {
        triTrip = 1;
        Intent viewNotesIntent = new Intent(this, ViewNotesActivity.class);
        viewNotesIntent.putExtra("idTrip", idTrip);
        viewNotesIntent.putExtra("TriType",triTrip);
        startActivity(viewNotesIntent);
    }

    public void viewTripTAG(View view) {
        triTrip = 2;
        Intent viewNotesIntent = new Intent(this, ViewNotesActivity.class);
        viewNotesIntent.putExtra("idTrip", idTrip);
        viewNotesIntent.putExtra("TriType",triTrip);
        startActivity(viewNotesIntent);
    }

    public void viewTripAdr(View view) {
        triTrip = 3;
        Intent viewNotesIntent = new Intent(this, ViewNotesActivity.class);
        viewNotesIntent.putExtra("idTrip", idTrip);
        viewNotesIntent.putExtra("TriType",triTrip);
        startActivity(viewNotesIntent);
    }

}
