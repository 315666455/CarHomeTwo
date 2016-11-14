package lanou.carhometwo.recommended;


import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import lanou.carhometwo.R;
import lanou.carhometwo.base.BaseFragment;
import lanou.carhometwo.internet.URLValues;
import lanou.carhometwo.more.MoreActivity;
import lanou.carhometwo.search.SearchActivity;


public class RecommendedFragment extends BaseFragment implements View.OnClickListener {

    private TabLayout tlRecommended;
    private ViewPager vpRecommended;
    private ArrayList<String> arrayListUrl;
    private ImageView imageViewMenu;
    private ImageView imageViewSearch;

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

        RecommendedAdapter adapter = new RecommendedAdapter(getChildFragmentManager(), arrayListUrl);
        vpRecommended.setAdapter(adapter);
        tlRecommended.setupWithViewPager(vpRecommended);
        tlRecommended.setSelectedTabIndicatorColor(Color.BLACK);
        tlRecommended.setTabMode(TabLayout.MODE_SCROLLABLE);
//        vpRecommended.setCurrentItem();
    }

    @Override
    protected void initView() {
        imageViewSearch = bindView(R.id.iv_recommend_search);
        imageViewMenu = bindView(R.id.iv_recommend_menu);
        vpRecommended = bindView(R.id.vp_recommeded);
        tlRecommended = bindView(R.id.tl_recommend);
        imageViewMenu.setOnClickListener(this);
        imageViewSearch.setOnClickListener(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.recommended_fragment;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_recommend_menu:
                Intent intent = new Intent(getActivity(), MoreActivity.class);
                startActivity(intent);
                break;

            case R.id.iv_recommend_search:
                Intent intent1 = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent1);
                break;


        }


    }


}
