package com.example.applicationmp3.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationmp3.Model.BaiHat;
import com.example.applicationmp3.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BaiHatHotAdapter extends RecyclerView.Adapter<BaiHatHotAdapter.viewHolder>{
    Context context;
    List<BaiHat> baiHatList;

    public BaiHatHotAdapter(Context context, List<BaiHat> baiHatList) {
        this.context = context;
        this.baiHatList = baiHatList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_bai_hat_hot, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        BaiHat baiHat = baiHatList.get(position);
        holder.txtCasi.setText(baiHat.getCasi());
        holder.txtTen.setText(baiHat.getTenBaiHat());
        Picasso.with(context).load(baiHat.getHinhBaiHat()).into(holder.hinh);
    }

    @Override
    public int getItemCount() {
        return baiHatList.size();
    }

    class viewHolder extends RecyclerView.ViewHolder {
        TextView txtTen, txtCasi;
        ImageView hinh, yeuthich;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txtTen = itemView.findViewById(R.id.txtViewTenBaiHatHot);
            txtCasi = itemView.findViewById(R.id.txtViewTenCaSiBaiHatHot);
            hinh = itemView.findViewById(R.id.imgViewBaiHatHot);
            yeuthich = itemView.findViewById(R.id.imgViewYeuThich);
        }
    }
}
