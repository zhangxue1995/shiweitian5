package com.dreamers.shiweitian.Search_page;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.dreamers.shiweitian.ListViewAdapter;
import com.dreamers.shiweitian.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodFragment extends Fragment {

    private RelativeLayout chocolate,chip,cookie,candy,drink;

    private ListView listView;
    private ListViewAdapter_food listViewAdapter;
    private List<Map<String, Object>> listItems;
    private String[] title1 = {"德芙榛仁葡萄干巧克力", "费列罗榛果威化巧克力","M&M's花生牛奶巧克力","健达儿童牛奶巧克力","好时牛奶巧克力"," "};
    private String[] title2 = {"爱芬食品（北京）有限公司",
            "费列罗贸易（上海）有限公司",
            "玛氏食品（中国）有限公司",
            "费列罗贸易（上海）有限公司",
            "好时（上海）有限公司"," "};
    private Integer[] imgeIDs = {R.drawable.chocolate1,
            R.drawable.chocolate2, R.drawable.chocolate3,
            R.drawable.chocolate4, R.drawable.chocolate5,R.drawable.blank};

    public FoodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.search_food,container,false);


        chocolate=(RelativeLayout)rootView.findViewById(R.id.chocolate);
        chip=(RelativeLayout)rootView.findViewById(R.id.chip);
        cookie=(RelativeLayout)rootView.findViewById(R.id.cookie);
        candy=(RelativeLayout)rootView.findViewById(R.id.candy);
        drink=(RelativeLayout)rootView.findViewById(R.id.drink);
        chocolate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title1= new String[]{"德芙榛仁葡萄干巧克力", "费列罗榛果威化巧克力","M&M's花生牛奶巧克力","健达儿童牛奶巧克力","好时牛奶巧克力"," "};
                title2 = new String[]{"爱芬食品（北京）有限公司",
                        "费列罗贸易（上海）有限公司",
                        "玛氏食品（中国）有限公司",
                        "费列罗贸易（上海）有限公司",
                        "好时（上海）有限公司"," "};
                imgeIDs = new Integer[]{R.drawable.chocolate1,
                        R.drawable.chocolate2, R.drawable.chocolate3,
                        R.drawable.chocolate4, R.drawable.chocolate5,R.drawable.blank};
            }
        });
        listView = (ListView)rootView.findViewById(R.id.list);
        listItems = getListItems();
        listViewAdapter = new ListViewAdapter_food(this.getContext(), listItems); //创建适配器
        listView.setAdapter(listViewAdapter);
        return rootView;

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        chocolate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title1= new String[]{"德芙榛仁葡萄干巧克力", "费列罗榛果威化巧克力","M&M's花生牛奶巧克力","健达儿童牛奶巧克力","好时牛奶巧克力"," "};
                title2 = new String[]{"爱芬食品（北京）有限公司",
                        "费列罗贸易（上海）有限公司",
                        "玛氏食品（中国）有限公司",
                        "费列罗贸易（上海）有限公司",
                        "好时（上海）有限公司"," "};
                imgeIDs = new Integer[]{R.drawable.chocolate1,
                        R.drawable.chocolate2, R.drawable.chocolate3,
                        R.drawable.chocolate4, R.drawable.chocolate5,R.drawable.blank};
                listItems.clear();
                listItems = getListItems();
                listViewAdapter = new ListViewAdapter_food(getContext(), listItems); //创建适配器
                listView.setAdapter(listViewAdapter);
                listViewAdapter.notifyDataSetChanged();
            }
        });
        chip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                title1= new String[]{"乐事薯片", "可比克薯片","上好佳薯片"};
                title2 = new String[]{"百事(中国)投资有限公司",
                        "达利集团有限公司",
                        "上好佳（中国）有限公司",};
                imgeIDs = new Integer[]{R.drawable.chocolate1,
                        R.drawable.chocolate2, R.drawable.chocolate3,};
                listItems.clear();
                listItems = getListItems();
                listViewAdapter = new ListViewAdapter_food(getContext(), listItems); //创建适配器
                listView.setAdapter(listViewAdapter);
                listViewAdapter.notifyDataSetChanged();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it =new Intent();
                it.setClass(getActivity(), FoodFragment_detail.class);
                it.putExtra("str1",listItems.get(position).get("title1").toString() );//给intent添加额外数据，key为“str”,key值为"Intent Demo"
//                it.putExtra("str2",listItems.get(position).get("title2").toString() );
                startActivity(it);
            }
        });
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
            map.put("image", imgeIDs[i]);     //物品名称
            listItems.add(map);
        }
        return listItems;
    }

}
