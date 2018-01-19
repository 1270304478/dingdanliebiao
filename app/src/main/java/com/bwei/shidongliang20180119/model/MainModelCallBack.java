package com.bwei.shidongliang20180119.model;


import com.bwei.shidongliang20180119.bean.ShopBean;

public interface MainModelCallBack {

    public void success(ShopBean bean);
    public void failure(Exception e);
}
