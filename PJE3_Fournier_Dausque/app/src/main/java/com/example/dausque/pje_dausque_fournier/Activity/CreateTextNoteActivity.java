package com.example.dausque.pje_dausque_fournier.Activity;

import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.dausque.pje_dausque_fournier.Entity.Note;
import com.example.dausque.pje_dausque_fournier.Entity.Trip;
import com.example.dausque.pje_dausque_fournier.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class CreateTextNoteActivity extends AppCompatActivity {

    private EditText mEditNoteTitle;
    private EditText mEditNoteCont;
    private EditText mEditNoteTag;
    private NoteViewModel mNoteViewModel;
    private int idTrip;
    double lat, lng;

    private static final int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 1;
    private FusedLocationProviderClient mFusedLocationClient;
    private String titre = "no title";
    private String cont = "no description";
    private String address = "no adress";
    private String tag = "nothing";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_text_note);
        mEditNoteTitle = findViewById(R.id.edit_titre_note);
        mEditNoteCont = findViewById(R.id.edit_cont_note);
        mEditNoteTag = findViewById(R.id.edit_tag_note);

        requestPermission();
        // location
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        Button btn_loc = findViewById(R.id.btn_getloc);
        btn_loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(CreateTextNoteActivity.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    return;
                }
                mFusedLocationClient.getLastLocation().addOnSuccessListener(CreateTextNoteActivity.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            TextView textview_loc = findViewById(R.id.textview_location);
                            lat = location.getLatitude();
                            lng = location.getLongitude();
                            Geocoder geocoder = new Geocoder(CreateTextNoteActivity.this, Locale.getDefault());
                            try {
                                List<android.location.Address> addresses = geocoder.getFromLocation(lat, lng, 1);
                                address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()

                                textview_loc.setText(address);
                            } catch (IOException e) {
                                return;
                            }
                        }
                    }
                });
            }
        });


        idTrip = getIntent().getIntExtra("idTrip", 0);
        mNoteViewModel = new NoteViewModel(getApplication(), idTrip, 1);
        System.out.println(idTrip);

        final Button button = findViewById(R.id.btn_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (TextUtils.isEmpty(mEditNoteTitle.getText())) {
                    Toast.makeText(
                            getApplicationContext(),
                            R.string.empty_not_saved,
                            Toast.LENGTH_LONG).show();
                } else {
                    titre = mEditNoteTitle.getText().toString();
                    cont = mEditNoteCont.getText().toString();
                    tag = mEditNoteTag.getText().toString();
                    Toast.makeText(
                            getApplicationContext(),
                            titre,
                            Toast.LENGTH_LONG).show();
                    Note note = new Note(titre, tag, cont, address, lat, lng, idTrip);
                    mNoteViewModel.insert(note);
                }
                finish();
            }
        });
    }


    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, 1);
    }


}

