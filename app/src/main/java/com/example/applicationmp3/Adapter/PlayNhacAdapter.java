package com.example.applicationmp3.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationmp3.Model.BaiHat;
import com.example.applicationmp3.R;

import java.util.List;

public class PlayNhacAdapter extends RecyclerView.Adapter<PlayNhacAdapter.viewHolder>{
    List<BaiHat> list;
    Context context;

    public PlayNhacAdapter(List<BaiHat> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_play_bai_hat,parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        BaiHat baiHat = list.get(position);
        holder.index.setText(position+1+" ");
        holder.tenbaihat.setText(baiHat.getTenBaiHat());
        holder.tencasi.setText(baiHat.getCasi());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView index, tenbaihat, tencasi;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            index = itemView.findViewById(R.id.txtViewplaynhacindex);
            tenbaihat = itemView.findViewById(R.id.txtViewPlayNhacTenBaiHat);
            tencasi = itemView.findViewById(R.id.txtViewPlayNhacTenCaSi);
        }
    }
}
