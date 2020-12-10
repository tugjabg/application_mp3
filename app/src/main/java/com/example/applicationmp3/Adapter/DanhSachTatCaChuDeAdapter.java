package com.example.applicationmp3.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationmp3.Model.ChuDe;
import com.example.applicationmp3.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DanhSachTatCaChuDeAdapter extends RecyclerView.Adapter<DanhSachTatCaChuDeAdapter.viewHolder>{
    Context context;
    List<ChuDe> chuDeList;

    public DanhSachTatCaChuDeAdapter(Context context, List<ChuDe> chuDeList) {
        this.context = context;
        this.chuDeList = chuDeList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_cac_chu_de, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        ChuDe chuDe = chuDeList.get(position);
        Picasso.with(context).load(chuDe.getHinhAnhChuDe()).into(holder.imageView);
        System.out.println(chuDeList.get(position).getHinhAnhChuDe());
    }

    @Override
    public int getItemCount() {
        return chuDeList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgviewdongcacchude);
        }
    }
}
