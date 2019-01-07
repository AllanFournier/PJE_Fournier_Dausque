package com.example.dausque.pje_dausque_fournier.Others;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dausque.pje_dausque_fournier.Activity.FragDetailActivity;
import com.example.dausque.pje_dausque_fournier.Activity.FragmentDetail;
import com.example.dausque.pje_dausque_fournier.Activity.NoteFragDetailActivity;
import com.example.dausque.pje_dausque_fournier.Activity.ViewNotesActivity;
import com.example.dausque.pje_dausque_fournier.Entity.Note;
import com.example.dausque.pje_dausque_fournier.Entity.Trip;
import com.example.dausque.pje_dausque_fournier.R;

import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>{
    private List<Note> mNotes;
    private final LayoutInflater mInflater;
    private Context mContext;
    private boolean mTwoPane;
    private final ViewNotesActivity mParentActivity;

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        private final TextView noteTitreView;
        private final TextView noteDescView;
        private final TextView addressView;
        private final TextView tagView;


        public NoteViewHolder(View itemView) {
            super(itemView);
            noteTitreView = itemView.findViewById(R.id.titre);
            noteDescView = itemView.findViewById(R.id.desc);
            addressView = itemView.findViewById(R.id.address_note);
            tagView = itemView.findViewById(R.id.tag_note);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + noteTitreView.getText() + "'";
        }
    }

    public void setmNotes(List<Note> notes) {
        mNotes = notes;
        notifyDataSetChanged();
    }

    public NoteListAdapter(Context context, ViewNotesActivity parent, boolean twoPane) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        mParentActivity = parent;
        mTwoPane = twoPane;
    }

    @Override
    public NoteListAdapter.NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_note_item, parent, false);
        return new NoteListAdapter.NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final NoteListAdapter.NoteViewHolder holder, final int position) {

        if (mNotes != null) {
            Note current = mNotes.get(position);
            holder.noteTitreView.setText(current.getTitNote());
            holder.noteDescView.setText(current.getContNote());
            holder.addressView.setText(current.getAdress());
            holder.tagView.setText(current.getTagNote());

        } else {
            // Covers the case of data not being ready yet.
            holder.noteTitreView.setText("No Trip");
            holder.noteDescView.setText("No Desc");
            holder.addressView.setText("No address");
            holder.addressView.setText("No tag");
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Details for note " + holder.noteTitreView.getText());
                final Note n = mNotes.get(position);
                System.out.println(n.getIdNote());
                    Intent intent = new Intent(mContext, NoteFragDetailActivity.class);
                    intent.putExtra("note_titre", n.getTitNote());
                    intent.putExtra("note_desc", n.getContNote());
                    intent.putExtra("note_addr", n.getAdress());
                    intent.putExtra("note_tag", n.getTagNote());
                    mContext.startActivity(intent);
            }
        });
    }

    public Note getNoteAtPosition(int position) {
        return mNotes.get(position);
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mNotes != null)
            return mNotes.size();
        else return 0;
    }
}
