package lanou.carhometwo.found;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import lanou.carhometwo.R;
import lanou.carhometwo.base.BaseFragment;
import lanou.carhometwo.bean.FoundBean;
import lanou.carhometwo.internet.GsonRequest;
import lanou.carhometwo.internet.URLValues;
import lanou.carhometwo.internet.VolleySingleton;
import lanou.carhometwo.recommended.childrecommend.BannerAdapter;

/**
 * Created by dllo on 16/10/21.
 */
public class FoundFragment extends BaseFragment {
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
    private ListView listView;
    private View viewHead;

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
    protected void initData() {
        GsonRequest<FoundBean> gsonRequest = new GsonRequest<FoundBean>
                (FoundBean.class, foundUrl, new Response.Listener<FoundBean>() {

                    @Override
                    public void onResponse(FoundBean response) {


                        FoundLastAdapter foundLastAdapter = new FoundLastAdapter();

                        foundLastAdapter.setFoundBean(response);
                        listView.setAdapter(foundLastAdapter);


                        FoundSixAdapter foundSixAdapter = new FoundSixAdapter();
                        foundSixAdapter.setFoundBean(response);
                        rvSix.setAdapter(foundSixAdapter);

                        GridLayoutManager sixManager = new GridLayoutManager(getActivity(), 3, GridLayoutManager.HORIZONTAL, false);
                        rvSix.setLayoutManager(sixManager);

                        Picasso.with(getActivity()).load(response.getResult().getCardlist().get(3).getData().get(0).getImageurl()).into(imageView);
                        Picasso.with(getActivity()).load(response.getResult().getCardlist().get(6).getData().get(0).getImageurl()).into(imageViewTwo);
                        Picasso.with(getActivity()).load(response.getResult().getCardlist().get(7).getData().get(0).getImageurl()).into(imageViewActionOne);
                        Picasso.with(getActivity()).load(response.getResult().getCardlist().get(7).getData().get(1).getImageurl()).into(imageViewActionTwo);
                        Picasso.with(getActivity()).load(response.getResult().getCardlist().get(7).getData().get(2).getImageurl()).into(imageViewActionThree);
                        Picasso.with(getActivity()).load(response.getResult().getCardlist().get(8).getData().get(0).getImageurl()).into(imageViewLast);

                        FoundTimeLimitAdapter foundTimeLimitAdapter = new FoundTimeLimitAdapter();
                        foundTimeLimitAdapter.setFoundBean(response);
                        rvTimeLimit.setAdapter(foundTimeLimitAdapter);

                        GridLayoutManager timeLimitManager = new GridLayoutManager(getActivity(), 1, GridLayoutManager.HORIZONTAL, false);
                        rvTimeLimit.setLayoutManager(timeLimitManager);

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
    protected void initView() {
        listView = bindView(R.id.lv_found_last);
        viewHead = LayoutInflater.from(getActivity()).inflate(R.layout.found_headview, null);
        viewPager = bindView(viewHead, R.id.vp_found_wheel);
        linearLayout = bindView(viewHead, R.id.ll_found_wheel);
        recyclerView = bindView(viewHead, R.id.rv_found_ten);
        imageView = bindView(viewHead, R.id.iv_found_three);
        rvTimeLimit = bindView(viewHead, R.id.rv_time_limit);
        rvSix = bindView(viewHead, R.id.rv_found_six);
        imageViewTwo = bindView(viewHead, R.id.iv_found_two);
        imageViewActionOne = bindView(viewHead, R.id.iv_found_action_one);
        imageViewActionTwo = bindView(viewHead, R.id.iv_found_action_two);
        imageViewActionThree = bindView(viewHead, R.id.iv_found_action_three);
        imageViewLast = bindView(viewHead, R.id.iv_found_last);

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
