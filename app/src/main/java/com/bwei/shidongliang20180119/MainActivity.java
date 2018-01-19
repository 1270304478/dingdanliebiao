package com.bwei.shidongliang20180119;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.shidongliang20180119.activity.XingActivity;
import com.bwei.shidongliang20180119.activity.ZhuCeActivity;
import com.bwei.shidongliang20180119.bean.LoginBean;
import com.bwei.shidongliang20180119.utils.MyAppliction;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends Activity {

    @BindView(R.id.image_view)
    ImageView imageView;
    @BindView(R.id.denglu_Zh)
    EditText dengluZh;
    @BindView(R.id.denglu_Pwd)
    EditText dengluPwd;
    @BindView(R.id.zhuce)
    TextView zhuce;
    @BindView(R.id.dengluBtn)
    Button dengluBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.zhuce, R.id.dengluBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zhuce:
              startActivity(new Intent(MainActivity.this,ZhuCeActivity.class));
                break;
            case R.id.dengluBtn:
                String p=dengluZh.getText().toString().trim();
                String pa=dengluPwd.getText().toString().trim();
                    MyAppliction.inters.getLogin(p, pa)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<LoginBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(LoginBean loginBean) {
                                String code = loginBean.getCode();
                                if (code.equals("0")) {
                                    Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(MainActivity.this, XingActivity.class);
                                    int uid = loginBean.getData().getUid();
                                    SharedPreferences sp = getSharedPreferences("sp_demo", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor edit = sp.edit();
                                    edit.putInt("uid", uid);
                                    edit.commit();
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(MainActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });

                break;
        }
    }
}
