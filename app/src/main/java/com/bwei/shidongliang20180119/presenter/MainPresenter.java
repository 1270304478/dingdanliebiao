package com.bwei.shidongliang20180119.presenter;

import com.bwei.shidongliang20180119.bean.Bean;
import com.bwei.shidongliang20180119.model.MainModelImpl;
import com.bwei.shidongliang20180119.view.MaView;

/**
 * 此类的作用：
 *
 * @author: forever
 * @date: 2018/1/18 15:06
 */
public class MainPresenter {
    private MaView view;
    private MainModelImpl model;
    public MainPresenter(MaView view) {
        this.view = view;
        this.model = new MainModelImpl();
    }

    public void get(){
        model.getData(new MainModelImpl.ModelCallBack() {
            @Override
            public void success(Bean bean) {
                if(view != null){
                    view.success(bean);
                }
            }
        });
    }
    public void detach(){
        view = null;
    }
}
