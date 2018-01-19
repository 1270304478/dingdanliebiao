package com.bwei.shidongliang20180119.model;


import com.bwei.shidongliang20180119.bean.ShopBean;
import com.bwei.shidongliang20180119.utils.MyAppliction;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainModel {

    public void getData(String uid,final MainModelCallBack callBack) {
        MyAppliction.inters.getCare(uid,"android")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ShopBean>() {
                    @Override
                    public void accept(ShopBean bean) throws Exception {
                        callBack.success(bean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                    }
                });
    }

}
