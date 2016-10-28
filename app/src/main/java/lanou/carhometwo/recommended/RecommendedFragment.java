package lanou.carhometwo.recommended;


import android.graphics.Color;

import android.support.v4.view.ViewPager;

import lanou.carhometwo.R;
import lanou.carhometwo.base.BaseFragment;

/**
 * Created by dllo on 16/10/21.
 */
public class RecommendedFragment extends BaseFragment {

    private TabLayout tlRecommended;
    private ViewPager vpRecommended;

    @Override
    protected void initData() {

        RecommendedAdapter adapter = new RecommendedAdapter(getChildFragmentManager());

        vpRecommended.setAdapter(adapter);
        tlRecommended.setupWithViewPager(vpRecommended);
        tlRecommended.setSelectedTabIndicatorColor(Color.BLACK);
        tlRecommended.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    @Override
    protected void initView() {
        tlRecommended = bindView(R.id.tl_recommend);
        vpRecommended = bindView(R.id.vp_recommeded);
    }

    @Override
    protected int getLayout() {
        return R.layout.recommended_fragment;
    }
}
