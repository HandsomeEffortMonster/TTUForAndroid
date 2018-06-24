package com.game.serialport;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.lzh.Bean.ElectricBean;
import com.lzh.dao.ElectricDao;
import com.lzh.tools.OrmLiteHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * lzh 20180604 历史记录界面
 */
public class HistoricalActivity extends AppCompatActivity implements View.OnClickListener{

    private Context context;
    private ElectricDao electricDao;
    private ListView listView;
    private Button btn_goback;
    private Button btn_download;
    private EditText et_queryTime;
    private Button btn_queryTime;

    private SimpleAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historical);
        context = HistoricalActivity.this;


        electricDao = new ElectricDao(getApplicationContext());

        listView =(ListView)this.findViewById(R.id.list_history);
        btn_download = (Button)this.findViewById(R.id.btn_download);
        btn_goback = (Button)this.findViewById(R.id.btn_gobackElectric);
        et_queryTime = (EditText)this.findViewById(R.id.edit_queryTime);
        btn_queryTime = (Button)this.findViewById(R.id.btn_queryTime);
        btn_download.setOnClickListener(this);
        btn_goback.setOnClickListener(this);
        btn_queryTime.setOnClickListener(this);

        /*List<ElectricBean> electricBeens = electricDao.queryForAll();*/
        /*获取总条数  然后获取最近10条*/
        long num = electricDao.getCount();
        List<ElectricBean> electricBeens = electricDao.queryForNum(num-15);//获取最近15条

        List<HashMap<String,Object>> data = new ArrayList<>();
        for(ElectricBean electricBean:electricBeens){
            /*item 中的string key 必须与 new String【】中的字符对应*/
            HashMap<String,Object> item = new HashMap<String,Object>();
            item.put("id",electricBean.get_id());
            item.put("voltageA",electricBean.getVoltageA());
            item.put("currentA",electricBean.getCurrentA());
            item.put("averagePowerA",electricBean.getAveragePowerA());
            item.put("apparentA",electricBean.getApparentA());

            item.put("voltageB",electricBean.getVoltageB());
            item.put("currentB",electricBean.getCurrentB());
            item.put("averagePowerB",electricBean.getAveragePowerB());
            item.put("apparentB",electricBean.getApparentB());

            item.put("voltageC",electricBean.getVoltageC());
            item.put("currentC",electricBean.getCurrentC());
            item.put("averagePowerC",electricBean.getAveragePowerC());
            item.put("apparentC",electricBean.getApparentC());

            item.put("createTime",electricBean.getCreateTime());
            data.add(item);

        }
        Collections.reverse(data);
        //创建SimpleAdapter适配器将数据绑定到item显示控件上
         adapter = new SimpleAdapter(this,data,R.layout.eletriclist,
                new String[]{"id","voltageA","currentA","averagePowerA","apparentA",
                        "voltageB","currentB","averagePowerB","apparentB",
                        "voltageC","currentC","averagePowerC","apparentC","createTime"},
                new int[]{R.id.th_id,R.id.th_voltageA,R.id.th_currentA,R.id.th_averagePowerA,R.id.th_apparentPowerA,
                        R.id.th_voltageB,R.id.th_currentB,R.id.th_averagePowerB,R.id.th_apparentPowerB,
                        R.id.th_voltageC,R.id.th_currentC,R.id.th_averagePowerC,R.id.th_apparentPowerC,R.id.th_createTime});
        //是实现列表显示
        listView.setAdapter(adapter);
        //条目点击事件
        listView.setOnItemClickListener(new ItemClickListener());
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_download:{

            }
            break;
            case R.id.btn_gobackElectric:{
                Intent intent = new Intent(HistoricalActivity.this,ElectricActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.btn_queryTime: {
 /*               String et_str = et_queryTime.getText().toString();
                List<ElectricBean> electricBeens = electricDao.queryForTime(et_str);
                List<HashMap<String,Object>> data = new ArrayList<>();
                Log.i("historyActivity","query for time");

                for(ElectricBean electricBean:electricBeens){
            *//*item 中的string key 必须与 new String【】中的字符对应*//*
                    HashMap<String,Object> item = new HashMap<String,Object>();
                    item.put("id",electricBean.get_id());
                    item.put("voltageA",electricBean.getVoltageA());
                    item.put("currentA",electricBean.getCurrentA());
                    item.put("averagePowerA",electricBean.getAveragePowerA());
                    item.put("apparentA",electricBean.getApparentA());

                    item.put("voltageB",electricBean.getVoltageB());
                    item.put("currentB",electricBean.getCurrentB());
                    item.put("averagePowerB",electricBean.getAveragePowerB());
                    item.put("apparentB",electricBean.getApparentB());

                    item.put("voltageC",electricBean.getVoltageC());
                    item.put("currentC",electricBean.getCurrentC());
                    item.put("averagePowerC",electricBean.getAveragePowerC());
                    item.put("apparentC",electricBean.getApparentC());

                    item.put("createTime",electricBean.getCreateTime());
                    data.add(item);
                }
                //创建SimpleAdapter适配器将数据绑定到item显示控件上
                 adapter = new SimpleAdapter(this,data,R.layout.eletriclist,
                        new String[]{"id","voltageA","currentA","averagePowerA","apparentA",
                                "voltageB","currentB","averagePowerB","apparentB",
                                "voltageC","currentC","averagePowerC","apparentC","createTime"},
                        new int[]{R.id.th_id,R.id.th_voltageA,R.id.th_currentA,R.id.th_averagePowerA,R.id.th_apparentPowerA,
                                R.id.th_voltageB,R.id.th_currentB,R.id.th_averagePowerB,R.id.th_apparentPowerB,
                                R.id.th_voltageC,R.id.th_currentC,R.id.th_averagePowerC,R.id.th_apparentPowerC,R.id.th_createTime});
                 adapter.notifyDataSetChanged();*/


            }
            break;
        }
    }
    //获取点击事件
    private final class ItemClickListener implements AdapterView.OnItemClickListener{
        public void onItemClick(AdapterView<?>parent, View view,int position,long id){
            ListView listView = (ListView) parent;
            HashMap<String,Object> data = (HashMap<String,Object>) listView.getItemAtPosition( position);
            String message = data.get("id").toString();
            ElectricBean electricBean_temp = electricDao.queryForId((int)(id+1));
            /*Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();*/
            Toast.makeText(getApplicationContext(),electricBean_temp.toString(),Toast.LENGTH_SHORT).show();
        }
    }
}
