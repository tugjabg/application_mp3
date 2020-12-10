package com.example.applicationmp3.Adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationmp3.Model.BaiHat;
import com.example.applicationmp3.R;

import java.util.List;

public class DanhSachBaiHatAdapter extends RecyclerView.Adapter<DanhSachBaiHatAdapter.viewHolder> {
    Context context;
    List<BaiHat> baiHats;

    public DanhSachBaiHatAdapter(Context context, List<BaiHat> baiHats) {
        this.context = context;
        this.baiHats = baiHats;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_danh_sach_bai_het, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        BaiHat baiHat = baiHats.get(position);
        if (baiHat.getCasi() == null){
            baiHat.setCasi("a");
        }
        if (baiHat.getTenBaiHat()==null){
            baiHat.setTenBaiHat("");
        }
        if (baiHat.getHinhBaiHat() == null){
            baiHat.setHinhBaiHat("0");
        }
        holder.txtcasi.setText(baiHat.getTenBaiHat());
        holder.txttenbaihat.setText(baiHat.getTenBaiHat());
        holder.txtIndex.setText((position+1)+".");
    }

    @Override
    public int getItemCount() {
        return baiHats.size();
    }

    class viewHolder extends RecyclerView.ViewHolder {
        TextView txtIndex, txttenbaihat, txtcasi;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txtIndex = itemView.findViewById(R.id.txtViewDanhSachIndex);
            txttenbaihat = itemView.findViewById(R.id.txtViewTenBaiHat);
            txtcasi = itemView.findViewById(R.id.tenCasiCuaBaiHat);
        }
    }
}
