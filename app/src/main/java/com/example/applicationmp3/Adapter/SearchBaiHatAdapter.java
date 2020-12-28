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

import com.example.applicationmp3.Activity.PlayMusicActivity;
import com.example.applicationmp3.Model.BaiHat;
import com.example.applicationmp3.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchBaiHatAdapter extends RecyclerView.Adapter<SearchBaiHatAdapter.viewHolder>{
    Context context;
    List<BaiHat> list;

    public SearchBaiHatAdapter(Context context, List<BaiHat> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_search_bai_hat, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        BaiHat baiHat = list.get(position);
        Picasso.with(context).load(baiHat.getHinhBaiHat()).into(holder.imageView);
        holder.tencasi.setText(baiHat.getCasi());
        holder.tenbaihat.setText(baiHat.getTenBaiHat());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class viewHolder extends RecyclerView.ViewHolder {
        TextView tenbaihat, tencasi;
        ImageView imageView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tenbaihat = itemView.findViewById(R.id.txtSearchTenBaiHat);
            tencasi = itemView.findViewById(R.id.tenCaSiSearch);
            imageView = itemView.findViewById(R.id.imgSearchBaiHat);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayMusicActivity.class);
                    intent.putExtra("cakhuc", list.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }

    }
}
