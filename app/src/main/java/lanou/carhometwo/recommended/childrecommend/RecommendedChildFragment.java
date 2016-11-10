package lanou.carhometwo.recommended.childrecommend;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;

import java.util.ArrayList;
import java.util.List;

import lanou.carhometwo.R;
import lanou.carhometwo.base.BaseFragment;
import lanou.carhometwo.bean.RecommendChildBean;
import lanou.carhometwo.internet.GsonRequest;
import lanou.carhometwo.internet.URLValues;
import lanou.carhometwo.internet.VolleySingleton;
import tools.DividerItemDecoration;
import tools.EndLessOnScrollLisener;

/**
 * Created by dllo on 16/10/24.
 */
public class RecommendedChildFragment extends BaseFragment {

    private RecyclerView lvRecommendedChild;
    private String childUrl = URLValues.URL_NEW;
    private Context context;
    private RecommendedChildAdapter reAdapter;
    private RecyclerViewHeader recyclerViewHeader;
    private int wheelSize;
    private LinearLayout llShuffing;
    private ViewPager mViewPager;
    private BannerAdapter mAdapter;
    private ArrayList<String> imgArr;
    private BannerListener bannerListener;
    private int pointIndex = 0;
    private Handler mHandler;
    private List<ImageView> mList;
    private ArrayList<String> arrayListId;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecommendChildBean recommendChildBean;
    private LinearLayoutManager manager;
    private boolean is = false;
    private int i = 15;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new Handler(Looper.myLooper()) {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 1 && mViewPager != null) {
                    mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
                }
                sendEmptyMessageDelayed(1, 3000);
            }
        };
    }

    @Override
    protected void initData() {

        GsonRequest<RecommendChildBean> gsonRequest = new GsonRequest<RecommendChildBean>
                (RecommendChildBean.class, childUrl, new Response.Listener<RecommendChildBean>() {

                    @Override
                    public void onResponse(RecommendChildBean response) {

                        imgArr = new ArrayList<>();
                        View view;
                        wheelSize = response.getResult().getFocusimg().size();
                        for (int i = 0; i < wheelSize; i++) {
                            String imgUrl = response.getResult().getFocusimg().get(i).getImgurl();
                            imgArr.add(imgUrl);
                        }

                        mList = new ArrayList<ImageView>();
                        LinearLayout.LayoutParams params;
                        for (int i = 0; i < imgArr.size(); i++) {
                            view = new View(getContext());
                            params = new LinearLayout.LayoutParams(10, 10);
                            params.leftMargin = 10;
                            view.setBackgroundResource(R.drawable.shapebackground);
                            view.setLayoutParams(params);
                            view.setEnabled(false);
                            llShuffing.addView(view);
                        }

                        reAdapter = new RecommendedChildAdapter(getActivity());
                        reAdapter.setRecommendChildBean(response);
                        lvRecommendedChild.setAdapter(reAdapter);
                        mAdapter = new BannerAdapter(imgArr);
                        mViewPager.setAdapter(mAdapter);
                        initAction();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });

        VolleySingleton.getInstance().addRequest(gsonRequest);


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                RefreshAs refreshAs = new RefreshAs();
                refreshAs.execute();
                reAdapter.notifyDataSetChanged();
            }
        });

        lvRecommendedChild.addOnScrollListener(new EndLessOnScrollLisener(manager) {

            @Override
            protected void onLoadMore(int curentPage) {
                Log.d("RecommendedChildFragmen", "呵呵");
                i = i + 15;
                GsonRequest<RecommendChildBean> gsonRequestRefresh = new GsonRequest<RecommendChildBean>(RecommendChildBean.class, "http://app.api.autohome.com.cn/autov4.8.8/news/newslist-pm1-c0-nt0-p1-s30-l" + i + ".json", new Response.Listener<RecommendChildBean>() {
                    @Override
                    public void onResponse(RecommendChildBean response) {
                        reAdapter.setRecommendChildBean(response,is);
                        reAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
                VolleySingleton.getInstance().addRequest(gsonRequestRefresh);
            }
        });
    }

    @Override
    protected void initView() {

        swipeRefreshLayout = bindView(R.id.sr_recommend_child);
        lvRecommendedChild = bindView(R.id.lv_recommend_child);
        recyclerViewHeader = bindView(R.id.rv_head_recommend);
        lvRecommendedChild.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        mViewPager = bindView(recyclerViewHeader, R.id.vp_shuffling);
        llShuffing = bindView(recyclerViewHeader, R.id.ll_shuffling_points);

        manager = new LinearLayoutManager(getActivity());
        lvRecommendedChild.setLayoutManager(manager);
        recyclerViewHeader.attachTo(lvRecommendedChild, true);

        RecommendedChildAdapter adapter = new RecommendedChildAdapter(context);
        lvRecommendedChild.setAdapter(adapter);
        mHandler.sendEmptyMessage(1);
    }

    private void initAction() {
        bannerListener = new BannerListener();
        mViewPager.addOnPageChangeListener(bannerListener);
        int index = (100 / 2) - (100 / 2 % imgArr.size());
        mViewPager.setCurrentItem(index, false);
        llShuffing.getChildAt(pointIndex).setEnabled(true);
    }

    @Override
    protected int getLayout() {
        return R.layout.recommended_child_fragment;
    }


    @Override
    public void onDestroyView() {
        mHandler.removeMessages(1);
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
                llShuffing.getChildAt(newPosition).setEnabled(true);
                llShuffing.getChildAt(pointIndex).setEnabled(false);
                pointIndex = newPosition;
            }
        }
    }

    class RefreshAs extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            swipeRefreshLayout.setRefreshing(false);
            Toast.makeText(RecommendedChildFragment.this.getContext(), "刷新成功", Toast.LENGTH_SHORT).show();
        }
    }
}
