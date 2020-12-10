package com.example.applicationmp3.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.applicationmp3.Activity.DanhSachBaiHatActivity;
import com.example.applicationmp3.R;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.applicationmp3.Model.Quangcao;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

// custom lại ViewPager
public class BannerAdapter extends PagerAdapter {
    private Context context;
    private List<Quangcao> quangcaoList;

    public BannerAdapter(Context context, List<Quangcao> quangcaoList) {
        this.context = context;
        this.quangcaoList = quangcaoList;
    }

    @Override
    public int getCount() {
        return quangcaoList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {// trả về view tùy theo object định hình
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) { // gán object tượng trưng cho mỗi page
        LayoutInflater layoutInflater  = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.col_banner, null);
        ImageView imgBackGroundbannder = view.findViewById(R.id.imgViewBackGroundBanner);
        ImageView imgSongBanner = view.findViewById(R.id.imgViewBanner);
        TextView txtViewTitle = view.findViewById(R.id.txtTitleBanner);
        TextView txtContent = view.findViewById(R.id.txtContent);
        //Picasso .with(context).load(quangcaoList.get(position).getHinhanh()).into(imgBackGroundbannder);
        Picasso .with(context).load(quangcaoList.get(position).getHinhbaihat()).into(imgBackGroundbannder);
        Picasso.with(context).load(quangcaoList.get(position).getHinhbaihat()).into(imgSongBanner);
        txtViewTitle.setText(quangcaoList.get(position).getTenbaihat());
        txtContent.setText(quangcaoList.get(position).getNoidung());
        container.addView(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                intent.putExtra("banner", quangcaoList.get(position));
                context.startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
        //super.destroyItem(container, position, object);
    }
}
