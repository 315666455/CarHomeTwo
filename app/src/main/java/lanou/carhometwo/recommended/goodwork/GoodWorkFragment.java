package lanou.carhometwo.recommended.goodwork;

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
import lanou.carhometwo.internet.GsonRequset;
import lanou.carhometwo.internet.VolleySingleton;
import lanou.carhometwo.recommended.childrecommend.BannerAdapter;
import lanou.carhometwo.recommended.childrecommend.RecommendedChildAdapter;
import lanou.carhometwo.weiget.DividerItemDecoration;
import lanou.carhometwo.weiget.URLValues;

/**
 * Created by dllo on 16/10/24.
 */
public class GoodWorkFragment extends BaseFragment {
    private String childUrl = URLValues.URL_YC;
    private Context context;
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
    private RecyclerView lvRecommendedChild;
    private GoodWorkAdapter adapter;

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
        GsonRequset<GoodWorkBean> gsonRequset = new GsonRequset<GoodWorkBean>(GoodWorkBean.class, childUrl, new Response.Listener<GoodWorkBean>() {

            @Override
            public void onResponse(GoodWorkBean response) {

                imgArr = new ArrayList<>();
                View view;
                wheelSize = response.getResult().getFocusimgs().size();
                for (int i = 0; i < wheelSize; i++) {
                    String imgUrl = response.getResult().getFocusimgs().get(i).getImgurl();
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

                adapter = new GoodWorkAdapter(getContext());

                adapter.setGoodWorkBean(response);

                lvRecommendedChild.setAdapter(adapter);
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

        lvRecommendedChild = bindView(R.id.lv_good_work);
        recyclerViewHeader = bindView(R.id.rv_head_good_work);
        lvRecommendedChild.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        mViewPager = bindView(R.id.vp_shuffling_good_work);
        llShuffing = bindView(R.id.ll_shuffling_points_good_work);

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
        llShuffing.getChildAt(pointIndex).setEnabled(true);
    }

    @Override
    protected int getLayout() {
        return R.layout.good_work_fragment;
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
                llShuffing.getChildAt(newPosition).setEnabled(true);
                llShuffing.getChildAt(pointIndex).setEnabled(false);
                pointIndex = newPosition;
            }
        }
    }
}
