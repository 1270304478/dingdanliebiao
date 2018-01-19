package com.bwei.shidongliang20180119.model;


import com.bwei.shidongliang20180119.bean.AddCartBean;
import com.bwei.shidongliang20180119.utils.MyAppliction;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Menglucywhh on 2017/12/7.
 */

public class AddCartModel {

  public void getData(String pid,String uid, final AddCartModelCallBack addCartModelCallBack) {
      // https://www.zhaoapi.cn/product/addCart?source=android&uid=1650&pid=57
      //"uid": 1650,
      // "token": "2FC3EF31EA25696D2715A971ADE38DE1",
      //"pid":57
        Map<String,String> map = new HashMap<>();
        map.put("source","android");
        map.put("uid",uid);
        map.put("token","2FC3EF31EA25696D2715A971ADE38DE1");
        map.put("pid",pid);
      MyAppliction.inters.addCart(map).enqueue(new Callback<AddCartBean>() {
            @Override
            public void onResponse(Call<AddCartBean> call, Response<AddCartBean> response) {
                AddCartBean addCartBean = response.body();
                addCartModelCallBack.success(addCartBean);
            }
            @Override
            public void onFailure(Call<AddCartBean> call, Throwable t) {
                addCartModelCallBack.failure();
            }
        });
    }
}
