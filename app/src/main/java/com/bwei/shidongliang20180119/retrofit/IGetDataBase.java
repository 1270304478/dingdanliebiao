package com.bwei.shidongliang20180119.retrofit;


import com.bwei.shidongliang20180119.bean.AddCartBean;
import com.bwei.shidongliang20180119.bean.Bean;
import com.bwei.shidongliang20180119.bean.DeleteBean;
import com.bwei.shidongliang20180119.bean.LoginBean;
import com.bwei.shidongliang20180119.bean.RegistBean;
import com.bwei.shidongliang20180119.bean.SelectCartBean;
import com.bwei.shidongliang20180119.bean.ShopBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;


/**
 * 此类的作用：
 *
 * @author: forever
 * @date: 2018/1/18 13:37
 */
public interface IGetDataBase {

    //http://120.27.23.105/product/searchProducts?keywords=%E7%AC%94%E8%AE%B0%E6%9C%AC&page=1

    @GET("https://www.zhaoapi.cn/ad/getAd")
    Observable<Bean> getShangPin();

    //删除
    //https://www.zhaoapi.cn/product/deleteCart?uid=1650&pid=58
    @GET("/product/deleteCart")
    Call<DeleteBean> deleteCart(@QueryMap Map<String, String> map);

    @GET("/product/addCart")
    Call<AddCartBean> addCart(@QueryMap Map<String, String> map);

    @GET("/product/getCarts")
    Observable<ShopBean> getCare(@Query("uid") String uid, @Query("source") String source);

    //https://www.zhaoapi.cn/product/getCarts?source=android&uid=1650&token=2FC3EF31EA25696D2715A971ADE38DE1
    //uid": 1650,
    // "token": "2FC3EF31EA25696D2715A971ADE38DE1",
    @GET("product/getCarts")
    Call<SelectCartBean> selectCart(@QueryMap Map<String, String> map);

    @GET("user/login")
    Observable<LoginBean> getLogin(@Query("mobile") String mobile, @Query("password") String passwprd);

    @GET("user/reg")
    Observable<RegistBean> getRegist(@Query("mobile") String mobile, @Query("password") String passwprd);
}
