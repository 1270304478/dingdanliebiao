package com.bwei.shidongliang20180119.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bwei.shidongliang20180119.R;


/**
 * Created by Adminjs on 2017/12/19.
 */

public class PlusView extends LinearLayout {


    private EditText editText;
    private Button revserse;
    private Button add;
    private int mCount = 1 ;


    public PlusView(Context context) {
        super(context);
    }

    public PlusView(final Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View view =   LayoutInflater.from(context).inflate(R.layout.plus_layout,null,false);
        revserse = (Button) view.findViewById(R.id.revserse);
        add = (Button) view.findViewById(R.id.add);
        editText = (EditText) view.findViewById(R.id.content);
        revserse.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                //减号
                try {
                    String content =  editText.getText().toString().trim() ;
                    int count =  Integer.valueOf(content);

                    if(count > 1){
                        mCount = count-1;
                        editText.setText(mCount+"");
                        if(listener != null){
                            listener.click(mCount);
                        }
                    }else{
                        Toast.makeText(context, "不能小于一", Toast.LENGTH_SHORT).show();

                    }

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }


            }
        });


        add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //加号
                try {
                    String content =  editText.getText().toString().trim() ;
                    int count =  Integer.valueOf(content)+1;
                    mCount = count;

                    editText.setText(count+"");
                    if(listener != null){
                        listener.click(count);
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        addView(view);
    }

    public PlusView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    public void setEditText(int num){
        if(editText != null){
            editText.setText(num+"");
        }
    }

    public ClickListener listener;

    public void setListener(ClickListener listener){
        this.listener = listener;
    }

    /**
     * 加减号 点击事件
     */
    public interface ClickListener {
        public void click(int count);
    }
}
