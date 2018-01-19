package com.bwei.shidongliang20180119.model;
import com.bwei.shidongliang20180119.bean.Bean;
import com.bwei.shidongliang20180119.utils.MyAppliction;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
/**
 * 此类的作用：
 *
 * @author: forever
 * @date: 2018/1/18 14:51
 */
public class MainModelImpl {
   public void getData(final ModelCallBack callBack){
       MyAppliction.inters.getShangPin()
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Consumer<Bean>() {
                   @Override
                   public void accept(Bean bean) throws Exception {
                       callBack.success(bean);
                   }
               });
   }
    public interface ModelCallBack {
        public void success(Bean bean);
    }
}
