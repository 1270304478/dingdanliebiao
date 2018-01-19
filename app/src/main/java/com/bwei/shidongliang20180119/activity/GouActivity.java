package com.bwei.shidongliang20180119.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.shidongliang20180119.R;
import com.bwei.shidongliang20180119.adapter.ShopAdapter;
import com.bwei.shidongliang20180119.bean.ShopBean;
import com.bwei.shidongliang20180119.presenter.MainPresente;
import com.bwei.shidongliang20180119.view.MainViewListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GouActivity extends Activity implements MainViewListener {

    @BindView(R.id.third_recyclerview)
    RecyclerView thirdRecyclerview;
    @BindView(R.id.third_allselect)
    CheckBox thirdAllselect;
    @BindView(R.id.third_totalprice)
    TextView thirdTotalprice;
    @BindView(R.id.third_totalnum)
    TextView thirdTotalnum;
    @BindView(R.id.third_submit)
    TextView thirdSubmit;
    @BindView(R.id.third_pay_linear)
    LinearLayout thirdPayLinear;
    private MainPresente presenter;
    private ShopAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gou);
        ButterKnife.bind(this);
        SharedPreferences sp = getSharedPreferences("sp_demo", Context.MODE_PRIVATE);
        int uid = sp.getInt("uid", 0);
        presenter = new MainPresente(this);
        presenter.getData(uid+"");
        adapter = new ShopAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        thirdRecyclerview.setLayoutManager(manager);
        thirdRecyclerview.setAdapter(adapter);
        adapter.setListener(new ShopAdapter.UpdateUiListener() {
            @Override
            public void setTotal(String total, String num,boolean allCheck) {
                thirdAllselect.setChecked(allCheck);
                thirdTotalnum.setText("总数:"+num);
                thirdTotalprice.setText("总价:"+total);
            }
        });
        thirdSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GouActivity.this, DingActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void success(ShopBean bean) {
        adapter.add(bean);
    }

    @Override
    public void failure(Exception e) {
        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }
    @OnClick(R.id.third_allselect)
    public void onViewClicked() {
        adapter.selectAll(thirdAllselect.isChecked());
    }
}
