package lanou.carhometwo.found;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import lanou.carhometwo.R;
import lanou.carhometwo.base.BaseFragment;
import lanou.carhometwo.bean.FoundBean;
import lanou.carhometwo.internet.GsonRequest;
import lanou.carhometwo.internet.URLValues;
import lanou.carhometwo.internet.VolleySingleton;
import lanou.carhometwo.recommended.childrecommend.BannerAdapter;
import lanou.carhometwo.refresh.MeiTuanListView;
import lanou.carhometwo.search.SearchActivity;

/**
 * Created by dllo on 16/10/21.
 */
public class FoundFragment extends BaseFragment implements MeiTuanListView.OnMeiTuanRefreshListener {
    private String foundUrl = URLValues.FIND_URL;
    private int wheelSize;
    private LinearLayout linearLayout;
    private ViewPager viewPager;
    private BannerAdapter bannerAdapter;
    private ArrayList<String> imgArr;
    private int pointIndex = 0;
    private List<ImageView> list;
    private ArrayList<String> arrayListId;
    private Handler handler;
    private BannerListener bannerListener;
    private RecyclerView recyclerView;
    private ImageView imageView;
    private RecyclerView rvTimeLimit;
    private RecyclerView rvSix;
    private ImageView imageViewTwo;
    private ImageView imageViewActionOne;
    private ImageView imageViewActionTwo;
    private ImageView imageViewActionThree;
    private ImageView imageViewLast;
    private MeiTuanListView listView;
    private View viewHead;
    private final static int REFRESH_COMPLETE = 0;
    private InterHandler mInterHandler = new InterHandler(this);
    private FoundLastAdapter foundLastAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        handler = new Handler(Looper.myLooper()) {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 1 && viewPager != null) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                }
                sendEmptyMessageDelayed(1, 3000);
            }
        };
    }

    @Override
    public void onRefresh() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                    mInterHandler.sendEmptyMessage(REFRESH_COMPLETE);
                    // TODO Auto-generated catch block
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private static class InterHandler extends Handler {
        private WeakReference<FoundFragment> mActivity;

        public InterHandler(FoundFragment activity) {
            mActivity = new WeakReference<FoundFragment>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            final FoundFragment activity = mActivity.get();
            if (activity != null) {
                switch (msg.what) {
                    case REFRESH_COMPLETE:
                        Toast.makeText(activity.getActivity(), "刷新成功", Toast.LENGTH_SHORT).show();
                        activity.listView.setOnRefreshComplete();
                        activity.foundLastAdapter.notifyDataSetChanged();
                        activity.listView.setSelection(0);
                        break;
                }
            } else {
                super.handleMessage(msg);
            }
        }
    }

    @Override
    protected void initView() {
        listView = bindView(R.id.lv_found_last);
        viewHead = LayoutInflater.from(getActivity()).inflate(R.layout.found_headview, null);
        viewPager = bindView(viewHead, R.id.vp_found_wheel);
        linearLayout = bindView(viewHead, R.id.ll_found_wheel);
        recyclerView = bindView(viewHead, R.id.rv_found_ten);
        rvTimeLimit = bindView(viewHead, R.id.rv_time_limit);
        imageViewActionOne = bindView(viewHead, R.id.iv_found_action_one);
        imageViewActionTwo = bindView(viewHead, R.id.iv_found_action_two);
        imageViewActionThree = bindView(viewHead, R.id.iv_found_action_three);
        imageViewLast = bindView(viewHead, R.id.iv_found_last);
        ImageView imageViewSearch = bindView(R.id.iv_find_search);
        imageViewSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });
        listView.setOnMeiTuanRefreshListener(this);
    }


    @Override
    protected void initData() {
        GsonRequest<FoundBean> gsonRequest = new GsonRequest<FoundBean>
                (FoundBean.class, foundUrl, new Response.Listener<FoundBean>() {

                    @Override
                    public void onResponse(FoundBean response) {

                        //商品列表
                        foundLastAdapter = new FoundLastAdapter();
                        foundLastAdapter.setFoundBean(response);
                        listView.setAdapter(foundLastAdapter);

                        //活动专区
                        for (int i = 0; i < response.getResult().getCardlist().size(); i++) {
                            if (response.getResult().getCardlist().get(i).getDescription().equals("活动专区")) {
                                Picasso.with(getActivity()).load(response.getResult().getCardlist().get(i).getData().get(0).getImageurl()).into(imageViewActionOne);
                                Picasso.with(getActivity()).load(response.getResult().getCardlist().get(i).getData().get(1).getImageurl()).into(imageViewActionTwo);
                                Picasso.with(getActivity()).load(response.getResult().getCardlist().get(i).getData().get(2).getImageurl()).into(imageViewActionThree);
                            }
                        }

                        //限时抢购
                        for (int i = 0; i < response.getResult().getCardlist().size(); i++) {
                            if (response.getResult().getCardlist().get(i).getDescription().equals("限时抢购")) {
                                FoundTimeLimitAdapter foundTimeLimitAdapter = new FoundTimeLimitAdapter();
                                foundTimeLimitAdapter.setFoundBean(response);
                                Log.d("1313", "i:" + i);
                                foundTimeLimitAdapter.setNum(i);
                                rvTimeLimit.setAdapter(foundTimeLimitAdapter);
                                GridLayoutManager timeLimitManager = new GridLayoutManager(getActivity(), 1, GridLayoutManager.HORIZONTAL, false);
                                rvTimeLimit.setLayoutManager(timeLimitManager);
                            }
                        }

                        RecyclerViewTenAdapter tenAdapter = new RecyclerViewTenAdapter();
                        tenAdapter.setFoundBean(response);
                        recyclerView.setAdapter(tenAdapter);

                        GridLayoutManager manager = new GridLayoutManager(getActivity(), 5);
                        recyclerView.setLayoutManager(manager);

                        imgArr = new ArrayList<>();
                        View view;
                        wheelSize = response.getResult().getCardlist().get(0).getData().size();
                        for (int i = 0; i < wheelSize; i++) {
                            String imgUrl = response.getResult().getCardlist().get(0).getData().get(i).getImageurl();
                            imgArr.add(imgUrl);
                        }

                        list = new ArrayList<ImageView>();
                        LinearLayout.LayoutParams params;
                        for (int i = 0; i < imgArr.size(); i++) {
                            view = new View(getContext());
                            params = new LinearLayout.LayoutParams(10, 10);
                            params.leftMargin = 10;
                            view.setBackgroundResource(R.drawable.shapebackground);
                            view.setLayoutParams(params);
                            view.setEnabled(false);
                            linearLayout.addView(view);
                        }

                        bannerAdapter = new BannerAdapter(imgArr);
                        viewPager.setAdapter(bannerAdapter);
                        initAction();

                        listView.addHeaderView(viewHead);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
        VolleySingleton.getInstance().addRequest(gsonRequest);
    }


    @Override
    protected int getLayout() {
        return R.layout.found_fragment;
    }

    private void initAction() {
        bannerListener = new BannerListener();
        viewPager.addOnPageChangeListener(bannerListener);
        int index = (100 / 2) - (100 / 2 % imgArr.size());
        viewPager.setCurrentItem(index, false);
        linearLayout.getChildAt(pointIndex).setEnabled(true);
    }

    @Override
    public void onDestroyView() {
        handler.removeMessages(1);
        super.onDestroyView();
    }

    class BannerListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(final int position) {
            if (wheelSize != 0) {
                int newPosition = position % wheelSize;
                linearLayout.getChildAt(newPosition).setEnabled(true);
                linearLayout.getChildAt(pointIndex).setEnabled(false);
                pointIndex = newPosition;
            }
        }
    }
}
