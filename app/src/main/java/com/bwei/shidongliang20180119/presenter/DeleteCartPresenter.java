package com.bwei.shidongliang20180119.presenter;


import com.bwei.shidongliang20180119.bean.DeleteBean;
import com.bwei.shidongliang20180119.view.DeleteCartViewCallBack;


public class DeleteCartPresenter {

    DeleteCartModel deleteCartModel = new DeleteCartModel();
    DeleteCartViewCallBack deleteCartViewCallBack;
    public DeleteCartPresenter(DeleteCartViewCallBack deleteCartViewCallBack) {
        this.deleteCartViewCallBack = deleteCartViewCallBack;
    }

    public void delete(String pid,String uid) {
        deleteCartModel.delete(pid,uid, new DeleteCartModelCallBack() {
            @Override
            public void success(DeleteBean deleteBean) {
            deleteCartViewCallBack.success(deleteBean);
            }

            @Override
            public void failure() {
            deleteCartViewCallBack.failure();
            }
        });
    }
}
