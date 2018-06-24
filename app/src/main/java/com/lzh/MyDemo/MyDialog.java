package com.lzh.MyDemo;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.game.serialport.R;
import com.lzh.tools.Transform;

/**
 * Created by LZH on 2018/6/6.
 * 自定义对话框 用于 直流模拟量的配置
 */

public class MyDialog extends Dialog {
    //定义回调事件 用于dialog的点击事件
    public interface  OnMyDialogListener {
        public void back(String str_name,String str_unit,int parameter);
    }

    private String title;
    private OnMyDialogListener onMyDialogListener;

    private boolean input_flag = true;
    EditText et_name;
    EditText et_unit;
    EditText et_parameter;
    Button btn_dialogCancle;
    Button btn_dialogConfirm;

    public MyDialog(Context context,String title,OnMyDialogListener onMyDialogListener){
        super(context);
        this.title = title;
        this.onMyDialogListener = onMyDialogListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mydialog);
        //设置标题
        setTitle(title);
        et_name = (EditText)findViewById(R.id.et_name);
        et_unit = (EditText)findViewById(R.id.et_unit);
        et_parameter = (EditText)findViewById(R.id.et_parameter);
        btn_dialogCancle = (Button)findViewById(R.id.btn_dialogCancle);
        btn_dialogConfirm = (Button)findViewById(R.id.btn_dialogConfirm);
        btn_dialogCancle.setOnClickListener(clickListener);
        btn_dialogConfirm.setOnClickListener(clickListener);
    }

    private View.OnClickListener clickListener = new View.OnClickListener(){
        @Override
        public void onClick(View view){
            switch (view.getId()){
                case R.id.btn_dialogCancle:{
                    MyDialog.this.dismiss();
                }
                break;
                case R.id.btn_dialogConfirm:{
                    String str_name = et_name.getText().toString();
                    String str_unit = et_unit.getText().toString();
                    String str_parameter = et_parameter.getText().toString();

                    if(str_name==""){
                        et_name.setText("**名称不能为空**");
                        input_flag = false;
                    }
                    if(str_unit==""){
                        et_unit.setText("**单位不能为空**");
                        input_flag = false;
                    }
                    if(str_parameter==""||(! Transform.isNumeric(str_parameter))){
                        et_parameter.setText("**参数必须为数字或不为空**");
                        input_flag = false;
                    }
                    if(input_flag == false){
                        input_flag = true;
                    }else{
                        onMyDialogListener.back(str_name,str_unit,Integer.valueOf(str_parameter));
                        MyDialog.this.dismiss();
                    }
                }
                break;
            }


        }
    };
}
