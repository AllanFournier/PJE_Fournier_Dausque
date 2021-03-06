package com.example.dausque.pje_dausque_fournier.Activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dausque.pje_dausque_fournier.R;

import java.io.File;


public class FragmentDetail extends Fragment {

    private static final String ARG_1 = "ok1";
    private static final String ARG_2 = "ok2";
    private static final String ARG_3 = "ok3";
    private static final String ARG_4 = "ok4";

    private String cpTitre = "test1";
    private String cpDesc = "test2";
    private ImageView cpImg;
    private int cpId = 3;

    public FragmentDetail() {
    }

    public static FragmentDetail newInstance(String param1, String param2, int param3) {
        FragmentDetail fragment = new FragmentDetail();
        Bundle args = new Bundle();
        args.putString(ARG_1, param1);
        args.putString(ARG_2, param2);
        args.putInt(ARG_3, param3);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            cpTitre = getArguments().getString(ARG_1);
            System.out.println(cpTitre);
            cpDesc = getArguments().getString(ARG_2);
            System.out.println(cpDesc);
            cpId = getArguments().getInt(ARG_3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.details_of_trip_frag_vue, container, false);

        final TextView titre = rootView.findViewById(R.id.image_description);
        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), UpdateTripActivity.class);
                intent.putExtra("image_titre", cpTitre);
                intent.putExtra("idTrip", cpId);
                startActivity(intent);
            }
        });

        if (cpDesc != null && cpTitre != null) {
            System.out.println("Change");
            ((TextView) rootView.findViewById(R.id.image_description)).setText(cpTitre);
            ((TextView) rootView.findViewById(R.id.image_descriptionPlus)).setText(cpDesc);
            File root = android.os.Environment.getExternalStorageDirectory();
            String path = root.getAbsolutePath() + "/MyGoodMedia/Photo/" + cpTitre + "/";
            Log.d("Files", "Path: " + path);
            File directory = new File(path);
            File[] files = directory.listFiles();
            if (files != null) {
                if (files.length > 0) {
                    Log.d("Files", "Size: " + files.length);
                    Log.d("Files", "FileName:" + files[0].getName());
                    String fileName = files[0].getName();
                    String ImagingUri = root.getAbsolutePath() + "/MyGoodMedia/Photo/" + cpTitre + "/" + fileName;
                    Uri uri = Uri.parse(ImagingUri);
                    ((ImageView) rootView.findViewById(R.id.image)).setImageBitmap(BitmapFactory.decodeFile(uri.getPath()));
                }
            }
        }

        return rootView;
    }
}
