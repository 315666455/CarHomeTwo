package lanou.carhometwo.recommended;


import android.support.design.widget.TabLayout;
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

        RecommendedAdapter adapter = new RecommendedAdapter(getFragmentManager());
//        ArrayList<BaseFragment> arrayList = new ArrayList<>();
//
//        arrayList.add(new RecommendedChildFragment());
//        arrayList.add(new GoodWorkFragment());
//        arrayList.add(new LobbyistsFragment());
//        arrayList.add(new VideoFragment());
//        arrayList.add(new LettersFragment());
//        arrayList.add(new MarketFragment());
//
//        adapter.setArrayList(arrayList);
        vpRecommended.setAdapter(adapter);
        tlRecommended.setupWithViewPager(vpRecommended);

//        tlRecommended.getTabAt(0).setText("推荐");
//        tlRecommended.getTabAt(1).setText("优创+");
//        tlRecommended.getTabAt(2).setText("说客");
//        tlRecommended.getTabAt(3).setText("视频");
//        tlRecommended.getTabAt(4).setText("快报");
//        tlRecommended.getTabAt(5).setText("行情");

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
