package lanou.carhometwo.recommended;


import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

import lanou.carhometwo.R;
import lanou.carhometwo.base.BaseFragment;
import lanou.carhometwo.internet.URLValues;


public class RecommendedFragment extends BaseFragment {

    private TabLayout tlRecommended;
    private ViewPager vpRecommended;
    private ArrayList<String> arrayListUrl;

    @Override
    protected void initData() {

        arrayListUrl = new ArrayList<>();
        arrayListUrl.add("0");
        arrayListUrl.add("1");
        arrayListUrl.add(URLValues.URL_SAY);
        arrayListUrl.add("3");
        arrayListUrl.add("4");
        arrayListUrl.add(URLValues.URL_MARKET);
        arrayListUrl.add(URLValues.URL_NEWS);
        arrayListUrl.add(URLValues.URL_TESTCAR);
        arrayListUrl.add(URLValues.URL_SHOP);
        arrayListUrl.add(URLValues.URL_USECAR);
        arrayListUrl.add(URLValues.URL_TECHNOLOGY);
        arrayListUrl.add(URLValues.URL_CULTURE);
        arrayListUrl.add(URLValues.URL_CHANGE);

        RecommendedAdapter adapter = new RecommendedAdapter(getChildFragmentManager(),arrayListUrl);
        vpRecommended.setAdapter(adapter);
        tlRecommended.setupWithViewPager(vpRecommended);
        tlRecommended.setSelectedTabIndicatorColor(Color.BLACK);
        tlRecommended.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    @Override
    protected void initView() {
        vpRecommended = bindView(R.id.vp_recommeded);
        tlRecommended = bindView(R.id.tl_recommend);
    }

    @Override
    protected int getLayout() {
        return R.layout.recommended_fragment;
    }
}
