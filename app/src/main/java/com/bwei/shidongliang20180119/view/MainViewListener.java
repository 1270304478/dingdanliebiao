package com.bwei.shidongliang20180119.view;


import com.bwei.shidongliang20180119.bean.ShopBean;

public interface MainViewListener {
    public void success(ShopBean bean);
    public void failure(Exception e);
}
