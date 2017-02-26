package com.dreamers.shiweitian.Home_page;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dreamers.shiweitian.R;

import java.util.List;
import java.util.Map;

/**
 * Created by stzha on 2017/1/17.
 */

public class ListViewAdapter_news extends BaseAdapter {
    private Context context;                        //运行上下文
    private List<Map<String, Object>> listItems;    //商品信息集合
    private LayoutInflater listContainer;           //视图容器
    private boolean[] hasChecked;                   //记录商品选中状态
    public final class ListItemView{                //自定义控件集合

        public TextView title1;
        private ImageView image;


    }


    public ListViewAdapter_news(Context context, List<Map<String, Object>> listItems) {
        this.context = context;
        listContainer = LayoutInflater.from(context);   //创建视图容器并设置上下文
        this.listItems = listItems;
        hasChecked = new boolean[getCount()];
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return listItems.size();
    }

    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }






    /**
     * ListView Item设置
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Log.e("method", "getView");
        final int selectID = position;
        //自定义视图
        ListViewAdapter_news.ListItemView listItemView = null;
        if (convertView == null) {
            listItemView = new ListViewAdapter_news.ListItemView();
            //获取list_item布局文件的视图
            convertView = listContainer.inflate(R.layout.list_item_news1, null);
            //获取控件对象
            listItemView.title1 = (TextView)convertView.findViewById(R.id.title1);
            listItemView.image = (ImageView)convertView.findViewById(R.id.image);
            //设置控件集到convertView
            convertView.setTag(listItemView);
        }else {
            listItemView = (ListViewAdapter_news.ListItemView)convertView.getTag();
        }
//      Log.e("image", (String) listItems.get(position).get("title"));  //测试
//      Log.e("image", (String) listItems.get(position).get("info"));

        //设置文字和图片
        listItemView.title1.setText((String) listItems.get(position).get("title1"));
        listItemView.image.setBackgroundResource((Integer) listItems.get(position).get("image"));

//        listItemView.detail.setText("商品详情");
//        //注册按钮点击时间爱你
//        listItemView.detail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //显示物品详情
//                showDetailInfo(selectID);
//            }
//        });

        return convertView;
    }
}

