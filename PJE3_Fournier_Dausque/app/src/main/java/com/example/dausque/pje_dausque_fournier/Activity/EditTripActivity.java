package com.example.dausque.pje_dausque_fournier.Activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dausque.pje_dausque_fournier.R;
import com.example.dausque.pje_dausque_fournier.Entity.Trip;

public class EditTripActivity extends AppCompatActivity {

    private EditText mEditTripView;
    private EditText mEditTripDesc;
    private TripViewModel mTripViewModel;
    private int cpId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_trip);
        mEditTripView = findViewById(R.id.edit_trip_ed);
        mEditTripDesc = findViewById(R.id.edit_desc_ed);
        cpId = getIntent().getIntExtra("idTrip", 0);
        mTripViewModel = ViewModelProviders.of(this).get(TripViewModel.class);

        final Button button = findViewById(R.id.button_save_ed);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditTripView.getText())) {
                    //setResult(RESULT_CANCELED, replyIntent);
                    Toast.makeText(
                            getApplicationContext(),
                            R.string.empty_not_saved,
                            Toast.LENGTH_LONG).show();
                } else {
                    String tripstr = mEditTripView.getText().toString();
                    String descstr = mEditTripDesc.getText().toString();
                    Toast.makeText(
                            getApplicationContext(),
                            tripstr,
                            Toast.LENGTH_LONG).show();
                    Trip trip = new Trip(tripstr, descstr);
                    trip.setId(cpId);
                    trip.setMTrip(tripstr);
                    trip.setDTrip(descstr);
                    mTripViewModel.update(trip);
                }
                finish();
            }
        });
    }
}
