package com.example.dausque.pje_dausque_fournier.Others;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dausque.pje_dausque_fournier.Activity.NoteFragDetailActivity;
import com.example.dausque.pje_dausque_fournier.Entity.Imaging;
import com.example.dausque.pje_dausque_fournier.Entity.Note;
import com.example.dausque.pje_dausque_fournier.R;

import java.util.ArrayList;

public class ImagingAdapter extends RecyclerView.Adapter<ImagingAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Imaging> ImagingArrayList;

    public ImagingAdapter(Context context, ArrayList<Imaging> ImagingArrayList) {
        this.context = context;
        this.ImagingArrayList = ImagingArrayList;
    }

    @Override
    public ImagingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_image_item, parent, false);
        return new ImagingAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImagingAdapter.ViewHolder holder, int position) {
        if (ImagingArrayList != null) {
            final Imaging current = ImagingArrayList.get(position);
            Uri uri = Uri.parse(current.getUri());
            System.out.println(uri.getPath());
            holder.imageViewPlay.setImageBitmap(BitmapFactory.decodeFile(uri.getPath()));
            holder.imageViewPlay.setScaleType(ImageView.ScaleType.CENTER_CROP);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,current.getFileName(),Toast.LENGTH_SHORT).show();
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return ImagingArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewPlay;

        public ViewHolder(View itemView) {
            super(itemView);
            imageViewPlay = itemView.findViewById(R.id.img);
        }
    }
}
