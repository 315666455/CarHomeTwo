package lanou.carhometwo.recommended.childrecommend;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;

import java.util.ArrayList;
import java.util.List;

import lanou.carhometwo.R;
import lanou.carhometwo.base.BaseFragment;
import lanou.carhometwo.bean.RecommendChildBean;
import lanou.carhometwo.internet.GsonRequset;
import lanou.carhometwo.internet.VolleySingleton;
import lanou.carhometwo.weiget.DividerItemDecoration;

/**
 * Created by dllo on 16/10/24.
 */
public class RecommendedChildFragment extends BaseFragment {

    private RecyclerView lvRecommendedChild;
    private LinearLayout llShuffing;
    private String childUrl = "http://app.api.autohome.com.cn/autov4.8.8/news/newslist-pm1-c0-nt0-p1-s30-l0.json";
    private ViewPager mViewPager;
    private List<ImageView> mList;
    private BannerAdapter mAdapter;
    private BannerListener bannerListener;
    private Context context;
    private int pointIndex = 0;
    private ArrayList<String> imgArr;
    private int wheelSize;
    private Handler mHandler;
    private RecommendedChildAdapter reAdapter;
    private RecyclerViewHeader recyclerViewHeader;
    private View view;

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
        GsonRequset<RecommendChildBean> gsonRequset = new GsonRequset<RecommendChildBean>(RecommendChildBean.class, childUrl, new Response.Listener<RecommendChildBean>() {

            @Override
            public void onResponse(RecommendChildBean response) {

                imgArr = new ArrayList<>();
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

        VolleySingleton.getInstance().addRequest(gsonRequset);
    }

    @Override
    protected void initView() {

        lvRecommendedChild = bindView(R.id.lv_recommend_child);
        recyclerViewHeader = bindView(R.id.rv_head_recommend);
        lvRecommendedChild.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        mViewPager = bindView(recyclerViewHeader,R.id.vp_shuffling);
        llShuffing = bindView(recyclerViewHeader,R.id.ll_shuffling_points);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
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
        llShuffing.getChildAt(pointIndex).setEnabled(true);//dian
    }

    @Override
    protected int getLayout() {
        return R.layout.recommended_child_fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
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
                view = llShuffing.getChildAt(newPosition);
                view.setEnabled(true);

//                llShuffing.getChildAt(newPosition).setEnabled(true);
//                llShuffing.getChildAt(pointIndex).setEnabled(false);
                pointIndex = newPosition;
            }
        }
    }

}
