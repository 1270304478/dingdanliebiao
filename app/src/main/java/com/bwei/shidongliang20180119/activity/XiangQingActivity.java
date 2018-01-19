package com.bwei.shidongliang20180119.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.shidongliang20180119.R;
import com.bwei.shidongliang20180119.bean.AddCartBean;
import com.bwei.shidongliang20180119.bean.EventBean;
import com.bwei.shidongliang20180119.model.AddCartModelCallBack;
import com.bwei.shidongliang20180119.presenter.AddCartPresenter;
import com.bwei.shidongliang20180119.utils.GlideImageLoader;
import com.bwei.shidongliang20180119.view.AddCartViewCallBack;
import com.youth.banner.Banner;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class XiangQingActivity extends Activity implements AddCartModelCallBack, AddCartViewCallBack {

    @BindView(R.id.jiecao_Player)
    JCVideoPlayerStandard jiecaoPlayer;
    @BindView(R.id.image)
    Banner image;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.uprice)
    TextView uprice;
    @BindView(R.id.jia)
    Button jia;
    @BindView(R.id.gou)
    Button gou;
    private AddCartPresenter addCartPresenter;
    private JCVideoPlayerStandard jcVideoPlayerStandard;
    String url="http://ips.ifeng.com/video19.ifeng.com/video09/2014/06/16/1989823-102-086-0009.mp4";
    private String image1;
    private String pid1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang_qing);
        ButterKnife.bind(this);
        jiecaoPlayer.setUp(url,jcVideoPlayerStandard.SCREEN_LAYOUT_NORMAL,"淘宝");
        EventBus.getDefault().register(this);
        addCartPresenter = new AddCartPresenter(this);
        jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = getSharedPreferences("sp_demo", Context.MODE_PRIVATE);
                final int uid = sp.getInt("uid", 0);
                addCartPresenter.getData(pid1,uid+"");
            }
        });
    }
    @Subscribe(sticky = true)
    public void event(EventBean eventBean){
        pid1 = eventBean.getPid();
        image1 = eventBean.getImage();
        final String[] split = this.image1.split("\\|");
        image.setImageLoader(new GlideImageLoader());
        List<String> bannerList = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            bannerList.add(split[i]);
        }
        image.setImages(bannerList);
        image.start();
        name.setText(eventBean.getName());
        uprice.setText("￥"+eventBean.getPrice());
    }
    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()){
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }
    @Override
    public void success(AddCartBean addCartBean) {
        Toast.makeText(this,""+addCartBean.getMsg(),Toast.LENGTH_LONG).show();
        Intent intent = new Intent(XiangQingActivity.this, GouActivity.class);
        startActivity(intent);
    }

    @Override
    public void failure() {
        Toast.makeText(this,""+"加入失败",Toast.LENGTH_LONG).show();
    }
}
