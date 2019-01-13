package com.example.dausque.pje_dausque_fournier.Activity;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.example.dausque.pje_dausque_fournier.Entity.Note;
import com.example.dausque.pje_dausque_fournier.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    int idTrip;
    private List<Note> mAllNotes;
    private NoteViewModel mNoteViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        idTrip = getIntent().getIntExtra("idTrip", 0);
        mNoteViewModel = new NoteViewModel(getApplication(), idTrip, 1);
        mNoteViewModel.getmAllNotes().observe((LifecycleOwner) this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable final List<Note> notes) {
                for (int i = 0; i < notes.size(); i++) {
                    LatLng notecurr = new LatLng(notes.get(i).getLat(), notes.get(i).getLng());
                    mMap.addMarker(new MarkerOptions().position(notecurr).title(notes.get(i).getTitNote()).snippet(notes.get(i).getContNote()));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(notecurr));
                }
            }
        });
    }
}
