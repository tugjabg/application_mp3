package com.example.applicationmp3.Service;

// tạo retrofit dùng để tương tác với server

import android.text.format.Time;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIRetrofitClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient(String base_url){ // trả về cấu hình sau khi thực hiện xong retrofit, truyền vào một url link tới server
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .readTimeout(10000, TimeUnit.MILLISECONDS) // tgian mà ngắt kết nối khi server không phản hồi
//                .writeTimeout(10000, TimeUnit.MILLISECONDS) //
//                .connectTimeout(10000, TimeUnit.MILLISECONDS) // thời gian trả lời kết nối
//                .retryOnConnectionFailure(true) // cố gắng kết nối lại
//                .protocols(Arrays.asList(Protocol.HTTP_1_1)) // giao thức
//                .build(); // => kiểm tra giao thức
//        Gson gson = new GsonBuilder().setLenient().create(); // API, convert các từ khóa API thành interface của java
//        retrofit = new Retrofit.Builder()
//                    .baseUrl(base_url)
//                    .client(okHttpClient)
//                    .addConverterFactory(GsonConverterFactory.create(gson))
//                    .build();
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
