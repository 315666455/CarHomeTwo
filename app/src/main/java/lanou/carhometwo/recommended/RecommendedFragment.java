package lanou.carhometwo.recommended;


import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

import lanou.carhometwo.R;
import lanou.carhometwo.base.BaseFragment;
import lanou.carhometwo.recommended.childrecommend.RecommendedChildFragment;
import lanou.carhometwo.recommended.goodwork.GoodWorkFragment;


public class RecommendedFragment extends BaseFragment {

    private TabLayout tlRecommended;
    private ViewPager vpRecommended;
//    private ArrayList<String> arrayListUrl;

    @Override
    protected void initData() {

//        arrayListUrl = new ArrayList<>();
//        arrayListUrl.add(URLValues.URL_NEW);
//        arrayListUrl.add(URLValues.URL_YC);
//        arrayListUrl.add(URLValues.URL_SAY);
//        arrayListUrl.add(URLValues.URL_VIEDIO);
//        arrayListUrl.add(URLValues.URL_QUICK);
//        arrayListUrl.add(URLValues.URL_MARKET);
//        arrayListUrl.add(URLValues.URL_NEWS);
//        arrayListUrl.add(URLValues.URL_TESTCAR);
//        arrayListUrl.add(URLValues.URL_SHOP);
//        arrayListUrl.add(URLValues.URL_USECAR);
//        arrayListUrl.add(URLValues.URL_TECHNOLOGY);
//        arrayListUrl.add(URLValues.URL_CULTURE);
//        arrayListUrl.add(URLValues.URL_CHANGE);

//        RecommendedAdapter adapter = new RecommendedAdapter(getChildFragmentManager(),arrayListUrl);
        ArrayList<BaseFragment>arrayList = new ArrayList<>();
        arrayList.add(new RecommendedChildFragment());
        arrayList.add(new GoodWorkFragment());
        RecommendedAdapter adapter = new RecommendedAdapter(getChildFragmentManager());
        adapter.setArrayList(arrayList);
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
