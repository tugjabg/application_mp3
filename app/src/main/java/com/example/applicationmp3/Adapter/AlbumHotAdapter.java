package com.example.applicationmp3.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationmp3.Activity.DanhSachBaiHatActivity;
import com.example.applicationmp3.Model.Album;
import com.example.applicationmp3.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AlbumHotAdapter extends RecyclerView.Adapter<AlbumHotAdapter.ViewHolder>{
    Context context; // truyen vao man hinh
    List<Album> list;

    public AlbumHotAdapter(Context context, List<Album> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { // gan layout
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_album, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) { // gan gia tri
        Album album = list.get(position);
        holder.caSiAlbum.setText(album.getTenCaSiAlbum());
        holder.tenAlbum.setText(album.getTenAlbum());
        Picasso.with(context).load(album.getHinhAlbum()).into(holder.imgAlbum);
    }

    @Override
    public int getItemCount() { // size cua item
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgAlbum;
        TextView tenAlbum, caSiAlbum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAlbum = itemView.findViewById(R.id.imgAlbum);
            tenAlbum = itemView.findViewById(R.id.txtViewNameAlbum);
            caSiAlbum = itemView.findViewById(R.id.txttencasi);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                    intent.putExtra("album", list.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
