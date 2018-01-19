package com.bwei.shidongliang20180119.model;

import com.bwei.shidongliang20180119.bean.AddCartBean;

/**
 * 此类的作用：
 *
 * @author: forever
 * @date: 2018/1/18 19:04
 */
public interface AddCartModelCallBack {
    public void success(AddCartBean addCartBean);
    public void failure();
}
