package com.dreamers.shiweitian.QandA_page;

import android.app.LauncherActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.dreamers.shiweitian.Home_page.MainActivity;
import com.dreamers.shiweitian.Home_page.MyGridView;
import com.dreamers.shiweitian.Home_page.SystemBarTintManager;
import com.dreamers.shiweitian.News_page.News_page;
import com.dreamers.shiweitian.Personal_information;
import com.dreamers.shiweitian.R;
import com.dreamers.shiweitian.Report_page.Report_page;
import com.dreamers.shiweitian.Search_page.FoodFragment_detail;
import com.dreamers.shiweitian.Search_page.Search_page;

import com.dreamers.shiweitian.ListViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by stzha on 2017/1/8.
 */

public class QandA_page extends AppCompatActivity implements View.OnClickListener {
    private TextView tab1;
    private TextView tab2;
    private TextView tab3;
    private TextView tab4;
    private TextView tab5;

//    // 声明ListView控件
//    private ListView mListView;
//    // 声明数组链表，其装载的类型是ListItem(封装了一个Drawable和一个String的类)
//    private ArrayList<ListItem> mList;
private ListView listView;
    private ListViewAdapter listViewAdapter;
    private List<Map<String, Object>> listItems;
    private String[] title1 = {"食品安全", "法律法规","营养搭配","食疗保健","营养搭配"};
    private String[] title2 = {"转基因食品安全否？有哪些优点和缺点？",
            "食品安全法对于食物流通许可有哪些规定？",
            "鸭肉与什么搭配最有营养？",
            "有什么食疗方法可以通便？",
            "番茄搭配哪些食物最营养？"};
    private String[] title3 = {"112","56","45","33","18"};
    private String[] title4 = {"转基因本身是一种技术名词，无所谓好坏。说简单点，就是发现A类生物有一个缺点，导致生活质量不高，B类生物",
    "《食品安全法》第二十九条 国家对食品生产经营实行许可制度。从事食品生产、食品流通、餐饮服务，应当依法",
    "鸭肉搭配山药：说到益肺止咳、健脾养胃，中药材中就必须要数山药了。而在肉类中具有同样功效的就是鸭肉",
    "每天早上杂粮粉加芝麻糊一包加燕麦片，一同煮，连续服用一个月！杂粮粉的功效是补充维生素，芝麻糊是补充",
    "番茄炒菜花，这道菜非常适合老年人吃，是一道开胃佳肴。番茄具有健胃消食，养阴生津的功效，菜花含有"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qanda_page);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setTintColor(Color.parseColor("#FF4CAF50"));//设置状态栏颜色

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.user_photo);
        actionBar.setTitle("这是昵称");


        listView = (ListView)findViewById(R.id.list1);
        listItems = getListItems();
        listViewAdapter = new ListViewAdapter(this, listItems); //创建适配器
        listView.setAdapter(listViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it =new Intent();
                it.setClass(QandA_page.this, QandA_detail.class);
                it.putExtra("str1",listItems.get(position).get("title1").toString() );//给intent添加额外数据，key为“str”,key值为"Intent Demo"
//                it.putExtra("str2",listItems.get(position).get("title2").toString() );
                startActivity(it);
            }
        });


        //底部导航栏
        tab1 = (TextView) this.findViewById(R.id.txt_1);
        tab2 = (TextView) this.findViewById(R.id.txt_2);
        tab3 = (TextView) this.findViewById(R.id.txt_3);
        tab4 = (TextView) this.findViewById(R.id.txt_4);
        tab5 = (TextView) this.findViewById(R.id.txt_5);
        tab1.setOnClickListener(this);
        tab2.setOnClickListener(this);
        tab3.setOnClickListener(this);
        tab4.setOnClickListener(this);
        tab5.setOnClickListener(this);
        tab4.setSelected(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent it =new Intent();
                it.setClass(QandA_page.this, Personal_information.class);
                startActivity(it);
                //    MainActivity.this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //重置所有文本的选中状态
    public void selected(){
        tab1.setSelected(false);
        tab2.setSelected(false);
        tab3.setSelected(false);
        tab4.setSelected(false);
        tab5.setSelected(false);
    }
    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.txt_1:
                selected();
                tab1.setSelected(true);
                Intent it1 =new Intent();
                it1.setClass(QandA_page.this, MainActivity.class);
                startActivity(it1);
                QandA_page.this.finish();
                break;

            case R.id.txt_2:
                selected();
                tab2.setSelected(true);
                Intent it2 =new Intent();
                it2.setClass(QandA_page.this, News_page.class);
                startActivity(it2);
                QandA_page.this.finish();
                break;

            case R.id.txt_3:
                selected();
                tab3.setSelected(true);
                Intent it3 =new Intent();
                it3.setClass(QandA_page.this, Report_page.class);
                startActivity(it3);
                QandA_page.this.finish();
                break;

            case R.id.txt_4:
                selected();
                tab4.setSelected(true);
                break;

            case R.id.txt_5:
                selected();
                tab5.setSelected(true);
                Intent it5 =new Intent();
                it5.setClass(QandA_page.this, Search_page.class);
                startActivity(it5);
                QandA_page.this.finish();
                break;
        }

//        transaction.commit();
    }


    /**
     * 初始化商品信息
     */
    private List<Map<String, Object>> getListItems() {
        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
        for(int i = 0; i < title1.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("title1", title1[i]);               //图片资源
            map.put("title2", title2[i]);              //物品标题
            map.put("title3", title3[i]);     //物品名称
            map.put("title4", title4[i]); //物品详情
            listItems.add(map);
        }
        return listItems;
    }
}
