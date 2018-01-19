package com.bwei.shidongliang20180119.view;


import com.bwei.shidongliang20180119.bean.DingdanBean;

public interface MyViewCallBack {
    public void viewSuccess(DingdanBean dingdanBean);
    public void viewFail(Exception e);
}
