package com.example.dausque.pje_dausque_fournier.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.dausque.pje_dausque_fournier.R;

public class FragDetailActivity extends AppCompatActivity {

    String imageTitre;
    String imageDesc;
    int idTrip;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_of_trip_frag);



        getIncomingIntent();

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            FragmentDetail fragment =  FragmentDetail.newInstance(imageTitre,imageDesc, idTrip);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.item_detail_container, fragment)
                    .commit();
        }
    }

    private void getIncomingIntent(){

        if(getIntent().hasExtra("image_titre") && getIntent().hasExtra("image_desc")){

            imageTitre = getIntent().getStringExtra("image_titre");
            System.out.println("img titre" + imageTitre);
            imageDesc = getIntent().getStringExtra("image_desc");
            System.out.println("img desc" + imageDesc);
            idTrip = getIntent().getIntExtra("idTrip",0);

        }
    }
}
