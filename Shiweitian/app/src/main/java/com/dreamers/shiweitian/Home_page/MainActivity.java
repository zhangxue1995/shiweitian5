package com.dreamers.shiweitian.Home_page;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ActionMenuItemView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.dreamers.shiweitian.ListViewAdapter;
import com.dreamers.shiweitian.News_page.Foodmonitoringnews;
import com.dreamers.shiweitian.News_page.Healthcolumn_more;
import com.dreamers.shiweitian.News_page.News_detail;
import com.dreamers.shiweitian.Personal_information;
import com.dreamers.shiweitian.QandA_page.ListViewAdapter_qanda;
import com.dreamers.shiweitian.QandA_page.QandA;
import com.dreamers.shiweitian.QandA_page.QandA_page;
import com.dreamers.shiweitian.R;
import com.dreamers.shiweitian.Report_page.Feedback;
import com.dreamers.shiweitian.Report_page.Report_merchant;
import com.dreamers.shiweitian.Report_page.Report_page;
import com.dreamers.shiweitian.Report_page.Report_product;
import com.dreamers.shiweitian.Search_page.Search_page;
import com.dreamers.shiweitian.News_page.News_page;
import com.dreamers.shiweitian.Search_page.Searchfood;
import com.dreamers.shiweitian.User;
import com.dreamers.shiweitian.zxing.QR_report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements OnClickListener,AdapterView.OnItemClickListener{
    private TextView tab1;
    private TextView tab2;
    private TextView tab3;
    private TextView tab4;
    private TextView tab5;

    private final static int SCANNIN_GREQUEST_CODE = 1;

    private ListView listView;
    private ListViewAdapter_news listViewAdapter;
    private List<Map<String, Object>> listItems;
    private String[] title1 = {"转基因食品安全否？有哪些优点和缺点？",
            "食品安全法对于食物流通许可有哪些规定？",
            "鸭肉与什么搭配最有营养？",
            "有什么食疗方法可以通便？",
            "番茄搭配哪些食物最营养？"};
    private Integer[] imgeIDs = {R.drawable.chocolate1,
            R.drawable.chocolate2, R.drawable.chocolate3,
            R.drawable.chocolate4, R.drawable.chocolate5};


    private MyGridView gview;
    private List<Map<String, Object>> data_list;
    private SimpleAdapter sim_adapter;  private SimpleAdapter sim_adapter1;
    // 图片封装为一个数组
    private int[] icon = { R.drawable.jubao_chanpin, R.drawable.jubao_shangjia,
            R.drawable.saomajubao, R.drawable.jubaofankui, R.drawable.shipinchaxun,
            R.drawable.zhuanjiawenda, R.drawable.jiankangzixun, R.drawable.shijianxinwen,
            };
    private String[] iconName = { "举报产品", "举报商家", "扫码举报", "举报跟踪", "食品查询", "热门问答", "健康资讯",
            "食监新闻" };

    private TextView more;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        User user=(User)getApplication();
//        user.isLogin=true;

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.KITKAT) {
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


        //底部导航栏
        tab1 = (TextView)this.findViewById(R.id.txt_1);
        tab2 = (TextView)this.findViewById(R.id.txt_2);
        tab3 = (TextView)this.findViewById(R.id.txt_3);
        tab4 = (TextView)this.findViewById(R.id.txt_4);
        tab5 = (TextView)this.findViewById(R.id.txt_5);
        tab1.setOnClickListener(this);
        tab2.setOnClickListener(this);
        tab3.setOnClickListener(this);
        tab4.setOnClickListener(this);
        tab5.setOnClickListener(this);
        tab1.setSelected(true);

//gridview
        gview = (MyGridView) findViewById(R.id.gview);
        //新建List
        data_list = new ArrayList<Map<String, Object>>();
        //获取数据
        getData();
        //新建适配器
        String [] from ={"image","text"};
        int [] to = {R.id.image,R.id.text};
        sim_adapter = new SimpleAdapter(this, data_list, R.layout.item, from, to);
        //配置适配器
        gview.setAdapter(sim_adapter);
        gview.setOnItemClickListener(this);



        listView = (ListView) findViewById(R.id.list1);
        listItems = getListItems();
        listViewAdapter = new ListViewAdapter_news(this, listItems); //创建适配器
        listView.setAdapter(listViewAdapter);
        setListViewHeightBasedOnChildren(listView);
        listView.setFocusable(false);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent();
                it.setClass(MainActivity.this, News_detail.class);
//                it.putExtra("str1", listItems.get(position).get("title1").toString());//给intent添加额外数据，key为“str”,key值为"Intent Demo"
////                it.putExtra("str2",listItems.get(position).get("title2").toString() );
                startActivity(it);
            }
        });

        more=(TextView)this.findViewById(R.id.more);
        more.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent();
                it.setClass(MainActivity.this, Healthcolumn_more.class);
                startActivity(it);
            }
        });
    }


    public List<Map<String, Object>> getData(){
        //cion和iconName的长度是相同的，这里任选其一都可以
        for(int i=0;i<icon.length;i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[i]);
            map.put("text", iconName[i]);
            data_list.add(map);
        }

        return data_list;
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
                break;

            case R.id.txt_2:
                selected();
                tab2.setSelected(true);
                Intent it2 =new Intent();
                it2.setClass(MainActivity.this, News_page.class);
                startActivity(it2);
                MainActivity.this.finish();
                break;

            case R.id.txt_3:
                selected();
                tab3.setSelected(true);
                Intent it3 =new Intent();
                it3.setClass(MainActivity.this, Report_page.class);
                startActivity(it3);
                MainActivity.this.finish();
                break;

            case R.id.txt_4:
                selected();
                tab4.setSelected(true);
                Intent it4 =new Intent();
                it4.setClass(MainActivity.this, QandA_page.class);
                startActivity(it4);
                MainActivity.this.finish();
                break;

            case R.id.txt_5:
                selected();
                tab5.setSelected(true);
                Intent it5 =new Intent();
                it5.setClass(MainActivity.this, Search_page.class);
                startActivity(it5);
                MainActivity.this.finish();
                break;
        }

//        transaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent it =new Intent();
                it.setClass(MainActivity.this, Personal_information.class);
                startActivity(it);
            //    MainActivity.this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private List<Map<String, Object>> getListItems() {
        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < title1.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", imgeIDs[i]);     //物品名称
            map.put("title1", title1[i]);               //图片资源
            listItems.add(map);
        }
        return listItems;
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                Intent it0 =new Intent();
                it0.setClass(MainActivity.this, Report_product.class);
                startActivity(it0);
                break;
            case 1:
                Intent it1 =new Intent();
                it1.setClass(MainActivity.this, Report_merchant.class);
                startActivity(it1);
               // MainActivity.this.finish();
                break;
            case 2:
//                Intent it2 =new Intent();
//                it2.setClass(MainActivity.this, QR_report.class);
//                startActivity(it2);
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, QR_report.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
                break;
            case 3:
                Intent it3 =new Intent();
                it3.setClass(MainActivity.this, Feedback.class);
                startActivity(it3);
                break;
            case 4:
                Intent it4 =new Intent();
                it4.setClass(MainActivity.this, Searchfood.class);
                startActivity(it4);
                break;
            case 5:
                Intent it5 =new Intent();
                it5.setClass(MainActivity.this, QandA.class);
                startActivity(it5);
                break;
            case 6:
                Intent it6 =new Intent();
                it6.setClass(MainActivity.this, Healthcolumn_more.class);
                startActivity(it6);
                break;
            case 7:
                Intent it7 =new Intent();
                it7.setClass(MainActivity.this, Foodmonitoringnews.class);
                startActivity(it7);
                break;
            default:


                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SCANNIN_GREQUEST_CODE:
                if(resultCode == RESULT_OK){
                    Bundle bundle = data.getExtras();
                    //显示扫描到的内容
                    Toast.makeText(this,bundle.getString("result"),Toast.LENGTH_LONG);

                }
                break;
        }
    }
}
