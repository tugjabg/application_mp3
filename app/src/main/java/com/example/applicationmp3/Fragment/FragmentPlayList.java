package com.example.applicationmp3.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.applicationmp3.Adapter.PlayListAdapter;
import com.example.applicationmp3.Model.PlayList;
import com.example.applicationmp3.R;
import com.example.applicationmp3.Service.APIService;
import com.example.applicationmp3.Service.DataService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentPlayList extends Fragment {
    View view;
    ListView listViewPlayList;
    TextView txtTitlePlayList, txtXemThemPlayList;
    PlayListAdapter playListAdapter;
    List<PlayList> list;


    @Nullable
    @Override // gắn Layout vào cho fragment
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_playlist, container, false);
        GetData();
        listViewPlayList = view.findViewById(R.id.listViewPlayList);
        txtTitlePlayList = view.findViewById(R.id.textViewTitlePlayList);
        txtXemThemPlayList = view.findViewById(R.id.textViewMorePlayList);
        return view;
    }

    private void GetData() { // cấu hình lại retrofit, gửi lên cho server
        DataService dataService = APIService.getService();
        Call<List<PlayList>> callback = dataService.GetPlayListCurrentDay();
        callback.enqueue(new Callback<List<PlayList>>() { // implement 2 sự kiện lắng nghe và thất bại
            @Override
            public void onResponse(Call<List<PlayList>> call, Response<List<PlayList>> response) {
                list = response.body();
                playListAdapter = new PlayListAdapter(getActivity(), android.R.layout.simple_list_item_1, list);
                listViewPlayList.setAdapter(playListAdapter);
                System.out.println(list.size());
            }

            @Override
            public void onFailure(Call<List<PlayList>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
//        setListViewHeightBasedOnChildren(listViewPlayList);
    }
//    public static void setListViewHeightBasedOnChildren(ListView listView) {
//        ListAdapter listAdapter = listView.getAdapter();
//        if (listAdapter == null) {
//            // pre-condition
//            return;
//        }
//
//        int totalHeight = 0;
//        for (int i = 0; i < listAdapter.getCount(); i++) {
//            View listItem = listAdapter.getView(i, null, listView);
//            listItem.measure(0, 0);
//            totalHeight += listItem.getMeasuredHeight();
//        }
//
//        ViewGroup.LayoutParams params = listView.getLayoutParams();
//        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
//        listView.setLayoutParams(params);
//        listView.requestLayout();
//    }

}
