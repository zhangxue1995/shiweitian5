package com.dreamers.shiweitian.News_page;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dreamers.shiweitian.JsonUtil;
import com.dreamers.shiweitian.Operaton;
import com.dreamers.shiweitian.R;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.dreamers.shiweitian.Search_page.ListViewAdapter_food;
import com.dreamers.shiweitian.User;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * Created by stzha on 2017/1/8.
 */

public class FoodknowlegeFragment extends Fragment {

    private TextView reloading;

    private String[] title;
    private String[] source;
    private String[] date;
    private String[] photo;

    private ListView listView;
    private ListViewAdapter_foodknowledge listViewAdapter;
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


    private View rootView;
    public static String IMAGE_CACHE_PATH = "imageloader/Cache"; // 图片缓存路径

    private ViewPager adViewPager;
    private List<ImageView> imageViews;// 滑动的图片集合

    private List<View> dots; // 图片标题正文的那些点
    private List<View> dotList;

    private TextView tv_date;
    private TextView tv_title;
    private TextView tv_topic_from;
    private TextView tv_topic;
    private int currentItem = 0; // 当前图片的索引号
    // 定义的五个指示点
    private View dot0;
    private View dot1;
    private View dot2;
    private View dot3;
    private View dot4;

    private ScheduledExecutorService scheduledExecutorService;

    // 异步加载图片
    private ImageLoader mImageLoader;
    private DisplayImageOptions options;

    // 轮播banner的数据
    private List<AdDomain> adList;

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            adViewPager.setCurrentItem(currentItem);
        };
    };


    public FoodknowlegeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if (isNetworkConnected(getContext())==false) {
            rootView = inflater.inflate(R.layout.recommend, container, false);
            reloading=(TextView) rootView.findViewById(R.id.a2);
            reloading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final Dialog dialog = new Dialog(getActivity(),R.style.Translucent_NoTitle);

                    dialog.setContentView(R.layout.loading);
                    dialog.show();
                    dialog.setCanceledOnTouchOutside(false);

                    final Timer t = new Timer();
                    t.schedule(new TimerTask() {
                        public void run() {
                            dialog.dismiss(); // when the task active then close the dialog
                            t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
                        }
                    }, 2000);

//                    Intent it1 =new Intent();
//                    it1.setClass(getActivity(), News_page.class);
//                    startActivity(it1);

//                    FragmentTransaction ft = getFragmentManager().beginTransaction();
//                    ft.replace(R.id.viewpager, new RecommendFragment());
//                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//                    ft.commit();

                }
            });
            return rootView;
        }
        else {
            // 使用ImageLoader之前初始化
            initImageLoader();

            // 获取图片加载实例
            mImageLoader = ImageLoader.getInstance();
            options = new DisplayImageOptions.Builder()
                    .showStubImage(R.drawable.top_banner_android)
                    .showImageForEmptyUri(R.drawable.top_banner_android)
                    .showImageOnFail(R.drawable.top_banner_android)
                    .cacheInMemory(true).cacheOnDisc(true)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .imageScaleType(ImageScaleType.EXACTLY).build();

            rootView = inflater.inflate(R.layout.foodknowledge, container, false);
            initAdData();

            new Thread(new Runnable() {

                public void run() {
                    Operaton operaton = new Operaton();
                    String category = 1 + "";
                    String result = operaton.loading("NewsServlet", category);
                    List<News> list = JsonUtil.StringFromJson(result);

//                    for(int i=0;i<list.size();i++){
//                        source[i]=list.get(i).getSource();
//                        title[i]=list.get(i).getTitle();
//                        date[i]=list.get(i).getTime();
//                        photo[i]= list.get(i).getPhoto();
//                    }
                    ArrayList<News> newlist=new ArrayList<>();
                    for(int i=0;i<list.size();i++){
                       newlist.add(list.get(i));
                   }
                    Message msg_title=new Message();
                    User helper=new User();
                    helper.setTemp_list(newlist);
                    helper.setIsget_templist(true);
                    msg_title.obj=helper;
                    handler_title.sendMessage(msg_title);

                }
            });

            startAd();


            listView = (ListView) rootView.findViewById(R.id.list1);
            listItems = getListItems();
            listViewAdapter = new ListViewAdapter_foodknowledge(this.getContext(), listItems); //创建适配器
            listView.setAdapter(listViewAdapter);
            setListViewHeightBasedOnChildren(listView);
            listView.setFocusable(false);


            return rootView;
        }
    }

    Handler handler_title=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            User help=(User) msg.obj;
            ArrayList<News> temp=help.getTemp_list();

           // String[] string=(String[]) msg.obj;
          // for(int i=0;i<string.length;i++)
            //title1=string;

            for(int i=0;i<temp.size();i++){
                       source[i]=temp.get(i).getSource();
                       title1[i]=temp.get(i).getTitle();
                        date[i]=temp.get(i).getTime();
                        photo[i]= temp.get(i).getPhoto();
                   }
            System.out.println(title1[0]+"啦啦啦啦啦");

            super.handleMessage(msg);
        }
    };

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



    private void initImageLoader() {
        File cacheDir = com.nostra13.universalimageloader.utils.StorageUtils
                .getOwnCacheDirectory(getContext(),
                        IMAGE_CACHE_PATH);

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true).cacheOnDisc(true).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                this.getContext()).defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new LruMemoryCache(12 * 1024 * 1024))
                .memoryCacheSize(12 * 1024 * 1024)
                .discCacheSize(32 * 1024 * 1024).discCacheFileCount(100)
                .discCache(new UnlimitedDiscCache(cacheDir))
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();

        ImageLoader.getInstance().init(config);
    }

    private void initAdData() {
        // 广告数据
        adList = getBannerAd();

        imageViews = new ArrayList<ImageView>();

        // 点
        dots = new ArrayList<View>();
        dotList = new ArrayList<View>();
        dot0 = rootView.findViewById(R.id.v_dot0);
        dot1 = rootView.findViewById(R.id.v_dot1);
        dot2 = rootView.findViewById(R.id.v_dot2);
        dot3 = rootView.findViewById(R.id.v_dot3);
        dot4 = rootView.findViewById(R.id.v_dot4);
        dots.add(dot0);
        dots.add(dot1);
        dots.add(dot2);
        dots.add(dot3);
        dots.add(dot4);

        tv_date = (TextView) rootView.findViewById(R.id.tv_date);
        tv_title = (TextView) rootView.findViewById(R.id.tv_title);
        tv_topic_from = (TextView) rootView.findViewById(R.id.tv_topic_from);
        tv_topic = (TextView) rootView.findViewById(R.id.tv_topic);

        adViewPager = (ViewPager) rootView.findViewById(R.id.vp);
        adViewPager.setAdapter(new MyAdapter());// 设置填充ViewPager页面的适配器
        // 设置一个监听器，当ViewPager中的页面改变时调用
        adViewPager.setOnPageChangeListener(new MyPageChangeListener());
        addDynamicView();


    }

    private void addDynamicView() {
        // 动态添加图片和下面指示的圆点
        // 初始化图片资源
        for (int i = 0; i < adList.size(); i++) {
            ImageView imageView = new ImageView(this.getContext());
            // 异步加载图片
            mImageLoader.displayImage(adList.get(i).getImgUrl(), imageView,
                    options);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageViews.add(imageView);
            dots.get(i).setVisibility(View.VISIBLE);
            dotList.add(dots.get(i));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void startAd() {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        // 当Activity显示出来后，每两秒切换一次图片显示
        scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 2,
                TimeUnit.SECONDS);
    }

    private class ScrollTask implements Runnable {

        @Override
        public void run() {
            synchronized (adViewPager) {
                currentItem = (currentItem + 1) % imageViews.size();
                handler.obtainMessage().sendToTarget();
            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        // 当Activity不可见的时候停止切换
        scheduledExecutorService.shutdown();
    }

    private class MyPageChangeListener implements ViewPager.OnPageChangeListener {

        private int oldPosition = 0;

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int position) {
            currentItem = position;
            AdDomain adDomain = adList.get(position);
            tv_title.setText(adDomain.getTitle()); // 设置标题
            tv_date.setText(adDomain.getDate());
            tv_topic_from.setText(adDomain.getTopicFrom());
            tv_topic.setText(adDomain.getTopic());
            dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
            dots.get(position).setBackgroundResource(R.drawable.dot_focused);
            oldPosition = position;
        }
    }

    private class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return adList.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView iv = imageViews.get(position);
            ((ViewPager) container).addView(iv);
            final AdDomain adDomain = adList.get(position);
            // 在这个方法里面设置图片的点击事件
            iv.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // 处理跳转逻辑
                }
            });
            return iv;
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView((View) arg2);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {

        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {

        }

        @Override
        public void finishUpdate(View arg0) {

        }

    }

    /**
     * 轮播广播模拟数据
     *
     * @return
     */
    public static List<AdDomain> getBannerAd() {
        List<AdDomain> adList = new ArrayList<AdDomain>();

        AdDomain adDomain = new AdDomain();
        adDomain.setId("108078");
        adDomain.setDate("12月24日");
        adDomain.setTitle("中国茶文化知识大全");
        adDomain.setTopicFrom("新浪博客");
        adDomain.setTopic("“茶为一种植物，可食用，解百毒”");
        adDomain.setImgUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1485159459596&di=0b283b831d8552299ec8ba55ce95e87e&imgtype=0&src=http%3A%2F%2Fimg4q.duitang.com%2Fuploads%2Fitem%2F201504%2F28%2F20150428194610_KmYvM.jpeg");
        adDomain.setAd(false);
        adList.add(adDomain);

        AdDomain adDomain2 = new AdDomain();
        adDomain2.setId("108078");
        adDomain2.setDate("3月5日");
        adDomain2.setTitle("五谷杂粮");
        adDomain2.setTopicFrom("小巫");
        adDomain2.setTopic("“啦啦啦啦啦”");
        adDomain2
                .setImgUrl("http://www.foodqs.cn/news/imgtest/s006/201111110128264170.jpg");
        adDomain2.setAd(false);
        adList.add(adDomain2);

        AdDomain adDomain3 = new AdDomain();
        adDomain3.setId("108078");
        adDomain3.setDate("3月6日");
        adDomain3.setTitle("啦啦啦啦啦");
        adDomain3.setTopicFrom("旭东");
        adDomain3.setTopic("“啦啦啦啦啦啦啦啦啦”");
        adDomain3
                .setImgUrl("http://e.hiphotos.baidu.com/image/w%3D310/sign=392ce7f779899e51788e3c1572a6d990/8718367adab44aed22a58aeeb11c8701a08bfbd4.jpg");
        adDomain3.setAd(false);
        adList.add(adDomain3);

        AdDomain adDomain4 = new AdDomain();
        adDomain4.setId("108078");
        adDomain4.setDate("3月7日");
        adDomain4.setTitle("啦啦啦啦啦");
        adDomain4.setTopicFrom("小软");
        adDomain4.setTopic("“啦啦啦啦啦啦啦啦”");
        adDomain4
                .setImgUrl("http://d.hiphotos.baidu.com/image/w%3D310/sign=54884c82b78f8c54e3d3c32e0a282dee/a686c9177f3e670932e4cf9338c79f3df9dc55f2.jpg");
        adDomain4.setAd(false);
        adList.add(adDomain4);

        AdDomain adDomain5 = new AdDomain();
        adDomain5.setId("108078");
        adDomain5.setDate("3月8日");
        adDomain5.setTitle("啦啦啦");
        adDomain5.setTopicFrom("大熊");
        adDomain5.setTopic("“啦啦啦啦啦啦啦啦啦”");
        adDomain5
                .setImgUrl("http://e.hiphotos.baidu.com/image/w%3D310/sign=66270b4fe8c4b7453494b117fffd1e78/0bd162d9f2d3572c7dad11ba8913632762d0c30d.jpg");
        adDomain5.setAd(true); // 代表是广告
        adList.add(adDomain5);

        return adList;
    }


    //判断网络是否连接
    public boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }
}
