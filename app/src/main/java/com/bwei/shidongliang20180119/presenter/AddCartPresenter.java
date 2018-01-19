package com.bwei.shidongliang20180119.presenter;


import com.bwei.shidongliang20180119.bean.AddCartBean;
import com.bwei.shidongliang20180119.model.AddCartModel;
import com.bwei.shidongliang20180119.model.AddCartModelCallBack;
import com.bwei.shidongliang20180119.view.AddCartViewCallBack;

/**
 * Created by Menglucywhh on 2017/12/7.
 */

public class AddCartPresenter {
    AddCartModel addCartModel = new AddCartModel();

    AddCartViewCallBack addCartViewCallBack;
    public AddCartPresenter(AddCartViewCallBack addCartViewCallBack) {
        this.addCartViewCallBack = addCartViewCallBack;
    }


    public void getData(String pid,String uid) {

        addCartModel.getData(pid,uid, new AddCartModelCallBack() {
            @Override
            public void success(AddCartBean addCartBean) {
                addCartViewCallBack.success(addCartBean);
            }

            @Override
            public void failure() {
                addCartViewCallBack.failure();
            }
        });

    }
}
