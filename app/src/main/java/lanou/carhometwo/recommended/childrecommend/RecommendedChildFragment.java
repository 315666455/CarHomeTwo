package lanou.carhometwo.recommended.childrecommend;

import android.content.Context;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

import lanou.carhometwo.R;
import lanou.carhometwo.base.BaseFragment;
import lanou.carhometwo.internet.GsonRequset;
import lanou.carhometwo.internet.VolleySingleton;

/**
 * Created by dllo on 16/10/24.
 */
public class RecommendedChildFragment extends BaseFragment {

    private ListView lvRecommendedChild;
    private ViewPager vpShuffing;
    private LinearLayout llShuffing;
    private String childUrl = "http://app.api.autohome.com.cn/autov4.8.8/news/newslist-pm1-c0-nt0-p1-s30-l0.json";
    int currItem;
    private boolean inisStop = false;
    private ViewPager mViewPager;
    private List<ImageView> mList;
    private TextView mTextView;
    private ScheduledExecutorService scheduledExecutorService;
    private BannerAdapter mAdapter;
    private BannerListener bannerListener;
    private String[] bannerTexts;
    private Context context;
    private int pointIndex = 0;
    private boolean isStop = false;
    private ArrayList<String> imgArr;

    @Override
    protected void initData() {

        GsonRequset<RecommendChildBean> gsonRequset = new GsonRequset<RecommendChildBean>(RecommendChildBean.class, childUrl, new Response.Listener<RecommendChildBean>() {

            @Override
            public void onResponse(RecommendChildBean response) {

                imgArr = new ArrayList<>();
                for (int i = 0; i < 6; i++) {
                    String imgUrl = response.getResult().getFocusimg().get(i).getImgurl();
                    imgArr.add(imgUrl);
                }

                View view;
                mList = new ArrayList<ImageView>();
                LinearLayout.LayoutParams params;
                for (int i = 0; i < imgArr.size(); i++) {

                    view = new View(getContext());
                    params = new LinearLayout.LayoutParams(15, 15);
                    params.leftMargin = 10;
                    view.setBackgroundResource(R.drawable.shapebackground);
                    view.setLayoutParams(params);
                    view.setEnabled(false);
                    llShuffing.addView(view);
                }

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
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.shuffling, null);
        mTextView = bindView(view, R.id.tv_bannertext);
        mViewPager = bindView(view, R.id.vp_shuffling);
        llShuffing = bindView(view, R.id.ll_shuffling_points);
        lvRecommendedChild.addHeaderView(view);
        RecommendedChildAdapter adapter = new RecommendedChildAdapter(context);
        lvRecommendedChild.setAdapter(adapter);
    }

    private void initAction() {

        bannerListener = new BannerListener();
        mViewPager.addOnPageChangeListener((ViewPager.OnPageChangeListener) bannerListener);
        int index = (100 / 2) - (100 / 2 % imgArr.size());
        mViewPager.setCurrentItem(index, false);

        llShuffing.getChildAt(pointIndex).setEnabled(true);//dian

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isStop) {
                    SystemClock.sleep(3000);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
                        }
                    });
                }
            }
        }).start();
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
    public void onDestroy() {
        isStop = true;
        super.onDestroy();
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
            bannerTexts = new String[]{
                    " ", " ", " ", " ", " ", " "
            };
            if (bannerTexts.length != 0) {
                int newPosition = position % bannerTexts.length;
                mTextView.setText(bannerTexts[newPosition]);
                llShuffing.getChildAt(newPosition).setEnabled(true);
                llShuffing.getChildAt(pointIndex).setEnabled(false);
                pointIndex = newPosition;
            }
        }
    }

}
