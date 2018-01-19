package com.bwei.shidongliang20180119.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bwei.shidongliang20180119.R;
import com.bwei.shidongliang20180119.bean.DingdanBean;
import com.bwei.shidongliang20180119.model.MyModel;
import com.bwei.shidongliang20180119.view.MyViewCallBack2;

import java.util.List;


public class MyRecyAdapter extends RecyclerView.Adapter<MyRecyAdapter.MyViewHolder>{

    MyModel myModel = new MyModel();
   // private List<DingdanBean.DataBean> listDa ;
    Context context;
    MyViewCallBack2 myViewCallBack2;
    List<DingdanBean.DataBean> listDa;
    public MyRecyAdapter(Context context, List<DingdanBean.DataBean> listDa, MyViewCallBack2 myViewCallBack2) {
        this.myViewCallBack2 = myViewCallBack2;
        this.context = context;
        this.listDa = listDa;
    }

//接受传来的数据 适配
    /*public void addData(List<DingdanBean.DataBean> listDingdan) {
        if(listDa==null){
            listDa = new ArrayList<>();
        }
        listDa.addAll(listDingdan);
        notifyDataSetChanged();
    }*/
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.my_recy,null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        //设置显示
        holder.price.setText("价格 : "+listDa.get(position).getPrice()+"");
        holder.time.setText("创建时间 : "+listDa.get(position).getCreatetime());
        holder.title.setText(listDa.get(position).getTitle());

        if(listDa.get(position).getStatus()==0) {
            holder.status.setText("待支付");
            holder.status.setTextColor(Color.RED);
        }else if(listDa.get(position).getStatus()==1){
            holder.status.setText("已支付");
            holder.status.setTextColor(Color.BLACK);
        }else{
            holder.status.setText("已取消");
            holder.status.setTextColor(Color.BLACK);
        }

        if(listDa.get(position).getStatus()==0) {
            holder.quxiaoBtn.setText("取消订单");
        }else if(listDa.get(position).getStatus()==1){
            holder.quxiaoBtn.setText("查看订单");
        }else{
            holder.quxiaoBtn.setText("查看订单");
        }


        holder.quxiaoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.quxiaoBtn.getText().toString().equals("取消订单")) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(context);

                    builder.setTitle("提示");
                    builder.setMessage("确定取消订单吗?");
                    builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            //改成已取消
                            holder.status.setText("已取消");
                            holder.status.setTextColor(Color.BLACK);
                            holder.quxiaoBtn.setText("查看订单");
                            //取消的接口
                            String path = "https://www.zhaoapi.cn/product/updateOrder?uid=71&orderId=" + listDa.get(position).getOrderid() + "&status=2";
                            //调用model层的取消接口的方法
                            int orderid = listDa.get(position).getOrderid();
                            myModel.quXiao(orderid, new MyModel.ModelCallBack1() {

                                @Override
                                public void success(String body) {
                                    myViewCallBack2.viewSuccess2(body);

                                }

                                @Override
                                public void fail() {
                                    myViewCallBack2.viewFail2();
                                }
                            });

                        }
                    });
                    builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();
                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return listDa==null?0:listDa.size();
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final TextView status;
        private final TextView price;
        private final TextView time;
        private final Button quxiaoBtn;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            status = (TextView) itemView.findViewById(R.id.status);
            price = (TextView) itemView.findViewById(R.id.price);
            time = (TextView) itemView.findViewById(R.id.time);
            quxiaoBtn = (Button) itemView.findViewById(R.id.quxiao);
        }
    }
}
