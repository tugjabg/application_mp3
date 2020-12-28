package com.example.applicationmp3.Adapter;

import android.content.Context;
import android.content.Intent;
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

public class TatCaAlbumAdapter extends RecyclerView.Adapter<TatCaAlbumAdapter.viewHolder>{
    Context context;
    List<Album> albumList;

    public TatCaAlbumAdapter(Context context, List<Album> albumList) {
        this.context = context;
        this.albumList = albumList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_all_album, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Album album = albumList.get(position);
        holder.tenAllAlbum.setText(album.getTenAlbum());
        holder.casiAlbum.setText(album.getTenCaSiAlbum());
        Picasso.with(context).load(album.getHinhAlbum()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tenAllAlbum, casiAlbum;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgViewDongTatCaCacAlbum);
            tenAllAlbum = itemView.findViewById(R.id.tenTatCaAlbum);
            casiAlbum = itemView.findViewById(R.id.tenCaSiCuaTatCaAlbum);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                    intent.putExtra("album", albumList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
