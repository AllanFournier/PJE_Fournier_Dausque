package com.example.dausque.pje_dausque_fournier.Activity;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dausque.pje_dausque_fournier.Entity.Recording;
import com.example.dausque.pje_dausque_fournier.Entity.Trip;
import com.example.dausque.pje_dausque_fournier.Others.RecordingAdapter;
import com.example.dausque.pje_dausque_fournier.R;

import java.io.File;
import java.util.ArrayList;

public class RecordingListActivity extends AppCompatActivity {

    String songs_folder_list;
    private RecyclerView recyclerViewRecordings;
    private ArrayList<Recording> recordingArraylist;
    private RecordingAdapter recordingAdapter;
    private TextView textViewNoRecordings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recording_list);

        recordingArraylist = new ArrayList<Recording>();

        initViews();
        getIncomingIntentFromRec();
        fetchRecordings();

    }

    private void getIncomingIntentFromRec() {

        if (getIntent().hasExtra("folder")) {
            songs_folder_list = getIntent().getStringExtra("folder");
            System.out.println("song for rec" + songs_folder_list);
        }
    }

    private void fetchRecordings() {

        File root = android.os.Environment.getExternalStorageDirectory();
        String path = root.getAbsolutePath() +  "/VoiceRecorderSimplifiedCoding/Audios/"+songs_folder_list+"/";
        Log.d("Files", "Path: " + path);
        File directory = new File(path);
        File[] files = directory.listFiles();
        if( files!=null ){
            if(files.length >0) {
                Log.d("Files", "Size: " + files.length);
                for (int i = 0; i < files.length; i++) {

                    Log.d("Files", "FileName:" + files[i].getName());
                    String fileName = files[i].getName();
                    String recordingUri = root.getAbsolutePath() + "/VoiceRecorderSimplifiedCoding/Audios/" + songs_folder_list + "/" + fileName;

                    Recording recording = new Recording(recordingUri, fileName, false);
                    recordingArraylist.add(recording);
                }

                textViewNoRecordings.setVisibility(View.GONE);
                recyclerViewRecordings.setVisibility(View.VISIBLE);
                setAdaptertoRecyclerView();
            }
            else {
                textViewNoRecordings.setVisibility(View.VISIBLE);
                recyclerViewRecordings.setVisibility(View.GONE);
            }
        }else{
            textViewNoRecordings.setVisibility(View.VISIBLE);
            recyclerViewRecordings.setVisibility(View.GONE);
        }

    }

    private void setAdaptertoRecyclerView() {
        recordingAdapter = new RecordingAdapter(this,recordingArraylist);
        recyclerViewRecordings.setAdapter(recordingAdapter);
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
                        Recording myRecord = recordingAdapter.getRecordAtPosition(position);
                        Uri uri = Uri.parse(myRecord.getUri());
                        File fdelete = new File(uri.getPath());
                        if (fdelete.exists()) {
                            if (fdelete.delete()) {
                                System.out.println("file Deleted :" + uri.getPath());
                            } else {
                                System.out.println("file not Deleted :" + uri.getPath());
                            }
                        }
                    }
                });
        helper.attachToRecyclerView(recyclerViewRecordings);
    }

    private void initViews() {



        /** setting up recyclerView **/
        recyclerViewRecordings = (RecyclerView) findViewById(R.id.recyclerViewRecordings);
        recyclerViewRecordings.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        recyclerViewRecordings.setHasFixedSize(true);

        textViewNoRecordings = (TextView) findViewById(R.id.textViewNoRecordings);

    }

}
