package com.dreamers.shiweitian.News_page;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dreamers.shiweitian.JsonUtil;
import com.dreamers.shiweitian.Operaton;
import com.dreamers.shiweitian.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by stzha on 2017/1/8.
 */

public class RecommendFragment_11 extends Fragment {

    private RelativeLayout layout1,layout2,layout3,layout4,layout5,layout6,layout7,layout8,layout9;
    private TextView title1,title2,title3,title4,title5,title6,title7,title8,title9;
    private TextView source1,source2,source3,source4,source5,source6,source7,source8,source9;
    private TextView date1,date2,date3,date4,date5,date6,date7,date8,date9;
    private ImageView photo1,photo2,photo3,photo4,photo5,photo6,photo7,photo8,photo9;

    private String title01,title02,title03,title04,title05,title06,title07,title08,title09;
    private String source01,source02,source03,source04,source05,source06,source07,source08,source09;
    private String date01,date02,date03,date04,date05,date06,date07,date08,date09;
    private String photo01,photo02,photo03,photo04,photo05,photo06,photo07,photo08,photo09;

    private String id;
    private String source;  //推送内容（文字）
    private String title;  //推送内容（图片）
    private String content;      //时间
    private String time;      //时间
    private String photo;      //时间
    private String category;

    private Bitmap bitmap;


    public RecommendFragment_11() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //动态加载if
//        View rootView = inflater.inflate(R.layout.recommend,container,false);
//        TextView tv=(TextView)rootView.findViewById(R.id.aa);
//        tv.setText("lalala");
//        return rootView;

        View rootView = inflater.inflate(R.layout.recommend_load,container,false);
        layout1=(RelativeLayout)rootView.findViewById(R.id.layout1);
        layout2=(RelativeLayout)rootView.findViewById(R.id.layout2);
        layout3=(RelativeLayout)rootView.findViewById(R.id.layout3);
        layout4=(RelativeLayout)rootView.findViewById(R.id.layout4);
        layout5=(RelativeLayout)rootView.findViewById(R.id.layout5);
        layout6=(RelativeLayout)rootView.findViewById(R.id.layout6);
        layout7=(RelativeLayout)rootView.findViewById(R.id.layout7);
        layout8=(RelativeLayout)rootView.findViewById(R.id.layout8);
        layout9=(RelativeLayout)rootView.findViewById(R.id.layout9);

        new Thread(new Runnable() {

            public void run() {
                Operaton operaton=new Operaton();
                String category =0+"";
                String result=operaton.loading("NewsServlet", category);
                List<News> list=JsonUtil.StringFromJson(result);

                source01=list.get(0).getSource();
                title01=list.get(0).getTitle();
                date01=list.get(0).getTime();
                photo01= list.get(0).getPhoto();

                source02=list.get(1).getSource();
                title02=list.get(1).getTitle();
                date02=list.get(1).getTime();
                photo02= list.get(1).getPhoto();

                Message msg_title1=new Message();
                msg_title1.obj=title01;
                handler_title1.sendMessage(msg_title1);

                Message msg_source1=new Message();
                msg_source1.obj=source01;
                handler_source1.sendMessage(msg_source1);

                Message msg_date1=new Message();
                msg_date1.obj=date01;
                handler_date1.sendMessage(msg_date1);

                Message msg_title2=new Message();
                msg_title2.obj=title02;
                handler_title2.sendMessage(msg_title2);

                Message msg_source2=new Message();
                msg_source2.obj=source02;
                handler_source2.sendMessage(msg_source2);

                Message msg_date2=new Message();
                msg_date2.obj=date02;
                handler_date2.sendMessage(msg_date2);


                String url="http://60.205.182.144:8080/shiweitian3/images/"+photo01;
                try {
                    bitmap=getBitmap(url);
                }
                catch (Exception e){

                }
                Message msg_photo1=new Message();
                msg_photo1.obj=bitmap;
                handler_photo1.sendMessage(msg_photo1);

            }
        }).start();


        title1=(TextView)rootView.findViewById(R.id.title1);
        title2=(TextView)rootView.findViewById(R.id.title2);
        title3=(TextView)rootView.findViewById(R.id.title3);
        title4=(TextView)rootView.findViewById(R.id.title4);
        title5=(TextView)rootView.findViewById(R.id.title5);

        source1=(TextView)rootView.findViewById(R.id.source1);
        source2=(TextView)rootView.findViewById(R.id.source2);
        source3=(TextView)rootView.findViewById(R.id.source3);
        source4=(TextView)rootView.findViewById(R.id.source4);
        source5=(TextView)rootView.findViewById(R.id.source5);
        source6=(TextView)rootView.findViewById(R.id.source6);

        date1=(TextView)rootView.findViewById(R.id.date1);
        date2=(TextView)rootView.findViewById(R.id.date2);
        date3=(TextView)rootView.findViewById(R.id.date3);
        date4=(TextView)rootView.findViewById(R.id.date4);



        photo1=(ImageView)rootView.findViewById(R.id.img1);



        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent();
                it.setClass(getActivity(), News_detail.class);
//                it.putExtra("str1",listItems.get(position).get("title1").toString() );//给intent添加额外数据，key为“str”,key值为"Intent Demo"
//                it.putExtra("str2",listItems.get(position).get("title2").toString() );
                startActivity(it);
            }
        });
        layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent();
                it.setClass(getActivity(), News_detail.class);
//                it.putExtra("str1",listItems.get(position).get("title1").toString() );//给intent添加额外数据，key为“str”,key值为"Intent Demo"
//                it.putExtra("str2",listItems.get(position).get("title2").toString() );
                startActivity(it);
            }
        });
        layout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent();
                it.setClass(getActivity(), News_detail.class);
//                it.putExtra("str1",listItems.get(position).get("title1").toString() );//给intent添加额外数据，key为“str”,key值为"Intent Demo"
//                it.putExtra("str2",listItems.get(position).get("title2").toString() );
                startActivity(it);
            }
        });
        layout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent();
                it.setClass(getActivity(), News_detail.class);
//                it.putExtra("str1",listItems.get(position).get("title1").toString() );//给intent添加额外数据，key为“str”,key值为"Intent Demo"
//                it.putExtra("str2",listItems.get(position).get("title2").toString() );
                startActivity(it);
            }
        });
        layout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent();
                it.setClass(getActivity(), News_detail.class);
//                it.putExtra("str1",listItems.get(position).get("title1").toString() );//给intent添加额外数据，key为“str”,key值为"Intent Demo"
//                it.putExtra("str2",listItems.get(position).get("title2").toString() );
                startActivity(it);
            }
        });
        layout6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent();
                it.setClass(getActivity(), News_detail.class);
//                it.putExtra("str1",listItems.get(position).get("title1").toString() );//给intent添加额外数据，key为“str”,key值为"Intent Demo"
//                it.putExtra("str2",listItems.get(position).get("title2").toString() );
                startActivity(it);
            }
        });
        layout7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent();
                it.setClass(getActivity(), News_detail.class);
//                it.putExtra("str1",listItems.get(position).get("title1").toString() );//给intent添加额外数据，key为“str”,key值为"Intent Demo"
//                it.putExtra("str2",listItems.get(position).get("title2").toString() );
                startActivity(it);
            }
        });
        layout8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent();
                it.setClass(getActivity(), News_detail.class);
//                it.putExtra("str1",listItems.get(position).get("title1").toString() );//给intent添加额外数据，key为“str”,key值为"Intent Demo"
//                it.putExtra("str2",listItems.get(position).get("title2").toString() );
                startActivity(it);
            }
        });
        layout9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent();
                it.setClass(getActivity(), News_detail.class);
//                it.putExtra("str1",listItems.get(position).get("title1").toString() );//给intent添加额外数据，key为“str”,key值为"Intent Demo"
//                it.putExtra("str2",listItems.get(position).get("title2").toString() );
                startActivity(it);
            }
        });

        return rootView;


    }
    Handler handler_title1=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String string=(String) msg.obj;
            title1.setText(string);
            super.handleMessage(msg);
        }
    };
    Handler handler_source1=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String string=(String) msg.obj;
            source1.setText(string);
            super.handleMessage(msg);
        }
    };
    Handler handler_date1=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String string=(String) msg.obj;
            date1.setText(string);
            super.handleMessage(msg);
        }
    };
    Handler handler_photo1=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Bitmap string=(Bitmap) msg.obj;
            photo1.setImageBitmap(string);
            super.handleMessage(msg);
        }
    };

    Handler handler_title2=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String string=(String) msg.obj;
            title2.setText(string);
            super.handleMessage(msg);
        }
    };
    Handler handler_source2=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String string=(String) msg.obj;
            source2.setText(string);
            super.handleMessage(msg);
        }
    };
    Handler handler_date2=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String string=(String) msg.obj;
            date2.setText(string);
            super.handleMessage(msg);
        }
    };

    //通过URL获取图片
    public static Bitmap getBitmap(String path) throws IOException {
        path="http://60.205.182.144:8080/shiweitian3/images/2_1.jpg";
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
        if(conn.getResponseCode() == 200){
            InputStream inputStream = conn.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        }
        return null;
    }


}