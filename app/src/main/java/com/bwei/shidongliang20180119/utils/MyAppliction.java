package com.bwei.shidongliang20180119.utils;

import android.app.Application;

import com.bwei.shidongliang20180119.retrofit.IGetDataBase;
import com.facebook.drawee.backends.pipeline.Fresco;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 此类的作用：
 *
 * @author: forever
 * @date: 2018/1/18 13:57
 */
public class MyAppliction extends Application {

    public  static IGetDataBase inters;
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://120.27.23.105")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        inters = retrofit.create(IGetDataBase.class);
    }
}
