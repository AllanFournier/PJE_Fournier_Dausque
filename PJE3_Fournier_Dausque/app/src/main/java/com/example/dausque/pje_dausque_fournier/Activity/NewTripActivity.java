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

public class NewTripActivity extends AppCompatActivity {

    private EditText mEditTripView;
    private EditText mEditTripDesc;
    private TripViewModel mTripViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_trip);
        mEditTripView = findViewById(R.id.edit_trip);
        mEditTripDesc = findViewById(R.id.edit_desc);
        mTripViewModel = ViewModelProviders.of(this).get(TripViewModel.class);

        final Button button = findViewById(R.id.button_save);
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
                    /*replyIntent.putExtra(EXTRA_REPLY, trip);
                    setResult(RESULT_OK, replyIntent);*/
                    Trip trip = new Trip(tripstr, descstr);
                    mTripViewModel.insert(trip);
                    Intent createTextNoteIntent = new Intent(getApplicationContext(), UpdateTripActivity.class);
                    startActivity(createTextNoteIntent);
                }
                finish();
            }
        });
    }
}
