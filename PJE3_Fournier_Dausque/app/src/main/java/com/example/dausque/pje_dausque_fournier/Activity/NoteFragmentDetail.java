package com.example.dausque.pje_dausque_fournier.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dausque.pje_dausque_fournier.R;


public class NoteFragmentDetail extends Fragment {

    private static final String ARG_1 ="ok1";
    private static final String ARG_2 ="ok2";
    private static final String ARG_3 ="ok3";
    private static final String ARG_4 ="ok4";

    private String cpTitre = "test1";
    private String cpDesc = "test2";
    private String cpAdr = "test1";
    private String cpTag = "test2";

    public NoteFragmentDetail() {
    }

    public static NoteFragmentDetail newInstance(String param1, String param2,String param3,String param4) {
        NoteFragmentDetail fragment = new NoteFragmentDetail();
        Bundle args = new Bundle();
        args.putString(ARG_1, param1);
        args.putString(ARG_2, param2);
        args.putString(ARG_3, param3);
        args.putString(ARG_4, param4);
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
            cpAdr = getArguments().getString(ARG_3);
            System.out.println(cpAdr);
            cpTag = getArguments().getString(ARG_4);
            System.out.println(cpTag);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.details_of_note_vue, container, false);

        final TextView titre = rootView.findViewById(R.id.image_description);

        if(cpDesc != null && cpTitre != null){
            System.out.println("Change");
            ((TextView) rootView.findViewById(R.id.titre_note_frag)).setText(cpTitre);
            ((TextView) rootView.findViewById(R.id.desc_note_frag)).setText(cpDesc);
            ((TextView) rootView.findViewById(R.id.adress_note_frag)).setText(cpAdr);
            ((TextView) rootView.findViewById(R.id.tag_note_frag)).setText(cpTag);
        }

        return rootView;
    }
}
