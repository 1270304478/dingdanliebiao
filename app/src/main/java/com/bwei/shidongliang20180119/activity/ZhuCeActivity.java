package com.bwei.shidongliang20180119.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.shidongliang20180119.MainActivity;
import com.bwei.shidongliang20180119.R;
import com.bwei.shidongliang20180119.bean.RegistBean;
import com.bwei.shidongliang20180119.utils.MyAppliction;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ZhuCeActivity extends Activity {

    @BindView(R.id.fan)
    TextView fan;
    @BindView(R.id.uname)
    EditText uname;
    @BindView(R.id.upass)
    EditText upass;
    @BindView(R.id.uupass)
    EditText uupass;
    @BindView(R.id.you)
    EditText you;
    @BindView(R.id.zhu)
    Button zhu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu_ce);
        ButterKnife.bind(this);
        fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @OnClick(R.id.zhu)
    public void onViewClicked() {
        final String p=uname.getText().toString().trim();
        final  String pa=upass.getText().toString().trim();
        MyAppliction.inters.getRegist(p,pa).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegistBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegistBean registBean) {
            String code=registBean.getCode();
            if (code.equals("0")) {
                Toast.makeText(ZhuCeActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(ZhuCeActivity.this, MainActivity.class);
                startActivity(intent);
            }else {
                Toast.makeText(ZhuCeActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
            }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                    @Override
                    public void onComplete() {

                    }
                });
    }
}
