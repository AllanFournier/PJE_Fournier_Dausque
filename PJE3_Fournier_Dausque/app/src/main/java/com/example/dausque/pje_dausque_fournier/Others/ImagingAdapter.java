package com.example.dausque.pje_dausque_fournier.Others;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dausque.pje_dausque_fournier.Entity.Imaging;
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
            Imaging current = ImagingArrayList.get(position);
            Uri uri = Uri.parse(current.getUri());
            System.out.println(uri.getPath());
            holder.imageViewPlay.setImageBitmap(BitmapFactory.decodeFile(uri.getPath()));
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
            imageViewPlay = itemView.findViewById(R.id.image_gallery);
        }
    }
}
