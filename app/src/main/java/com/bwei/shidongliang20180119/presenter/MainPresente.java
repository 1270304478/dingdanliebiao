package com.bwei.shidongliang20180119.presenter;


import com.bwei.shidongliang20180119.bean.ShopBean;
import com.bwei.shidongliang20180119.model.MainModel;
import com.bwei.shidongliang20180119.model.MainModelCallBack;
import com.bwei.shidongliang20180119.view.MainViewListener;

public class MainPresente {
    private MainViewListener listener;
    private MainModel mainModel;

    public MainPresente(MainViewListener listener) {
        this.listener = listener;
        this.mainModel = new MainModel();
    }

    public void getData(String uid){
        mainModel.getData(uid,new MainModelCallBack() {
            @Override
            public void success(ShopBean bean) {
                if (listener != null){
                    listener.success(bean);
                }
            }

            @Override
            public void failure(Exception e) {
                if (listener != null){
                    listener.failure(e);
                }
            }
        });
    }
    public void detach(){
        listener = null;
    }
}
