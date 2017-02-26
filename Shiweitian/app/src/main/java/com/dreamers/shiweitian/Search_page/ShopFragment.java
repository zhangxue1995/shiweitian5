package com.dreamers.shiweitian.Search_page;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.dreamers.shiweitian.R;
import com.dreamers.shiweitian.Report_page.ListViewAdapter_nearby;
import com.dreamers.shiweitian.Report_page.Report_merchant;
import com.dreamers.shiweitian.Report_page.Report_merchant_detail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopFragment extends Fragment {
    private ListView listView;
    private ListViewAdapter_nearby listViewAdapter;
    private List<Map<String, Object>> listItems;
    private String[] title1 = {"德芙榛仁葡萄干巧克力", "费列罗榛果威化巧克力","M&M's花生牛奶巧克力","健达儿童牛奶巧克力","好时牛奶巧克力"," "};
    private String[] title2 = {"爱芬食品（北京）有限公司",
            "费列罗贸易（上海）有限公司",
            "玛氏食品（中国）有限公司",
            "费列罗贸易（上海）有限公司",
            "好时（上海）有限公司"," "};
    private String[] title3 = {"800m",
            "600m",
            "540m",
            "300m",
            "100m"," "};
    private Integer[] imgeIDs = {R.drawable.chocolate1,
            R.drawable.chocolate2, R.drawable.chocolate3,
            R.drawable.chocolate4, R.drawable.chocolate5,R.drawable.blank};


    public ShopFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.search_shop,container,false);
        listView = (ListView)rootView.findViewById(R.id.listView1);
        listItems = getListItems();
        listViewAdapter = new ListViewAdapter_nearby(this.getContext(), listItems); //创建适配器
        listView.setAdapter(listViewAdapter);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                listItems.get(position).get("title2");

                Intent it =new Intent();
                it.setClass(getActivity(), ShopFragment_detail.class);
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
            map.put("image", imgeIDs[i]);
            map.put("title1", title1[i]);               //图片资源
            map.put("title2", title2[i]);              //物品标题
            map.put("title3", title3[i]);                 //物品名称
            listItems.add(map);
        }
        return listItems;
    }


}
