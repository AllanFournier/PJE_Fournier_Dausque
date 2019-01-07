package com.example.dausque.pje_dausque_fournier.Activity;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.dausque.pje_dausque_fournier.Entity.Imaging;
import com.example.dausque.pje_dausque_fournier.Others.ImagingAdapter;
import com.example.dausque.pje_dausque_fournier.R;

import java.io.File;
import java.util.ArrayList;

public class ViewImagesActivity extends AppCompatActivity {

    String images_folder_list;
    private RecyclerView recyclerViewImagings;
    private ArrayList<Imaging> ImagingArraylist;
    private ImagingAdapter ImagingAdapter;
    private TextView textViewNoImagings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imaging_list);

        ImagingArraylist = new ArrayList<Imaging>();

        initViews();
        getIncomingIntentFromUpImg();
        fetchImagings();

    }

    private void getIncomingIntentFromUpImg() {

        if (getIntent().hasExtra("folder")) {
            images_folder_list = getIntent().getStringExtra("folder");
            System.out.println("images for img" + images_folder_list);
        }
    }

    private void fetchImagings() {

        File root = android.os.Environment.getExternalStorageDirectory();
        String path = root.getAbsolutePath() + "/VoiceRecorderSimplifiedCoding/Photo/" +images_folder_list+"/";
        Log.d("Files", "Path: " + path );
        File directory = new File(path);
        File[] files = directory.listFiles();
        if( files!=null ) {
            if (files.length > 0) {
                Log.d("Files", "Size: " + files.length);
                for (int i = 0; i < files.length; i++) {

                    Log.d("Files", "FileName:" + files[i].getName());
                    String fileName = files[i].getName();
                    String ImagingUri = root.getAbsolutePath() + "/VoiceRecorderSimplifiedCoding/Photo/" + images_folder_list + "/"  + fileName;

                    Imaging Imaging = new Imaging(ImagingUri, fileName);
                    ImagingArraylist.add(Imaging);
                }
                setAdaptertoRecyclerView();
            }
        }
    }

    private void setAdaptertoRecyclerView(){
        ImagingAdapter = new ImagingAdapter(this,ImagingArraylist);
        recyclerViewImagings.setAdapter(ImagingAdapter);
    }

    private void initViews() {

        /** setting up recyclerView **/
        recyclerViewImagings = (RecyclerView) findViewById(R.id.recyclerViewImagings);
        recyclerViewImagings.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false));
        recyclerViewImagings.setHasFixedSize(true);

        textViewNoImagings = (TextView) findViewById(R.id.textViewNoRecordings);
    }
}
