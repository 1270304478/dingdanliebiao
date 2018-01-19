package com.bwei.shidongliang20180119.model;
import com.bwei.shidongliang20180119.bean.DingdanBean;
import com.bwei.shidongliang20180119.okhttp.AbstractUiCallBack;
import com.bwei.shidongliang20180119.okhttp.OkhttpUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Menglucywhh on 2017/11/20.
 */
public class MyModel {
    //model层中请求数据
    public void getData(int page,final ModelCallBack modelCallBack) {
        //调用封装好的okhttp类
      Map<String,String> params = new HashMap<>();
        params.put("uid","71");
        params.put("page",page+"");
      //String path="https://www.zhaoapi.cn/product/getOrders?uid=71&page="+page;
       String path="https://www.zhaoapi.cn/product/getOrders";//post
        OkhttpUtils.getInstance().asy(params,path, new AbstractUiCallBack<DingdanBean>() {
            @Override
            public void success(DingdanBean dingdanBean) {
                //成功返回数据
                modelCallBack.success(dingdanBean);
            }

            @Override
            public void failure(Exception e) {
                modelCallBack.fail(e);

            }
        });
    }

    //model层中请求数据,待支付 已支付 已取消
    public void popUpGetData(int status,final ModelCallBack modelCallBack) {
        //调用封装好的okhttp类

        Map<String,String> params = new HashMap<>();
        params.put("uid","71");
        params.put("page","1");
        params.put("status",status+"");
       //String path="https://www.zhaoapi.cn/product/getOrders?uid=71&page=1&status="+status;
       String path="https://www.zhaoapi.cn/product/getOrders";

        OkhttpUtils.getInstance().asy(params,path, new AbstractUiCallBack<DingdanBean>() {
            @Override
            public void success(DingdanBean dingdanBean) {
                //成功返回数据
                modelCallBack.success(dingdanBean);
            }

            @Override
            public void failure(Exception e) {
                modelCallBack.fail(e);

            }
        });
    }

    //model层中请求数据 取消订单的接口
    public void quXiao(final int orderid, final ModelCallBack1 modelCallBack1) {
        //调用封装好的okhttp类
//String quXiaoPath1 = "https://www.zhaoapi.cn/product/updateOrder?uid=71&orderId=\"+listDa.get(position).getOrderid()+\"&status=2";
String quXiaoPath = "https://www.zhaoapi.cn/product/updateOrder";

        OkHttpClient client = new OkHttpClient();

        FormBody body = new FormBody.Builder()
                .add("uid","4729")
                .add("orderId",String.valueOf(orderid))
                .add("status","2")
                .build();
        Request request = new Request.Builder()
                .post(body)
                .url(quXiaoPath)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                modelCallBack1.fail();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                modelCallBack1.success(response.body().string());
            }
        });
    }


    //model层的接口
    public interface ModelCallBack{
        public void success(DingdanBean dingdanBean);
        public void fail(Exception e);
    }

    //model层的接口
    public interface ModelCallBack1{
        public void success(String body);
        public void fail();
    }
}
