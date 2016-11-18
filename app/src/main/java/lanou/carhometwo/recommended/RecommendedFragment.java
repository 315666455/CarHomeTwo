package lanou.carhometwo.recommended;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import lanou.carhometwo.R;
import lanou.carhometwo.base.BaseFragment;
import lanou.carhometwo.bean.MoreBean;
import lanou.carhometwo.internet.URLValues;
import lanou.carhometwo.login.TestBean;
import lanou.carhometwo.more.MoreActivity;
import lanou.carhometwo.search.SearchActivity;
import tools.LiteOrmSingleton;

public class RecommendedFragment extends BaseFragment implements View.OnClickListener {

    private TabLayout tlRecommended;
    private ViewPager vpRecommended;
    private ArrayList<String> arrayListUrl;
    private ImageView imageViewMenu;
    private ImageView imageViewSearch;
    ArrayList<MoreBean> arrayList;

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
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
    }

    @Subscribe
    public void getTextEvent(TestBean event) {
        LiteOrmSingleton.getIntstance().queryAllData(new LiteOrmSingleton.OnQueryListenerAll<MoreBean>() {
            @Override
            public void onQuery(List<MoreBean> moreBeen) {
                tlRecommended.getTabAt(0).setText(moreBeen.get(0).getTitle());
                tlRecommended.getTabAt(1).setText(moreBeen.get(1).getTitle());
                tlRecommended.getTabAt(2).setText(moreBeen.get(2).getTitle());
                tlRecommended.getTabAt(3).setText(moreBeen.get(3).getTitle());
                tlRecommended.getTabAt(4).setText(moreBeen.get(4).getTitle());
                tlRecommended.getTabAt(5).setText(moreBeen.get(5).getTitle());
                tlRecommended.getTabAt(6).setText(moreBeen.get(6).getTitle());
                tlRecommended.getTabAt(7).setText(moreBeen.get(7).getTitle());
                tlRecommended.getTabAt(8).setText(moreBeen.get(8).getTitle());
                tlRecommended.getTabAt(9).setText(moreBeen.get(9).getTitle());
                tlRecommended.getTabAt(10).setText(moreBeen.get(10).getTitle());
                tlRecommended.getTabAt(11).setText(moreBeen.get(11).getTitle());
                tlRecommended.getTabAt(12).setText(moreBeen.get(12).getTitle());
            }
        }, MoreBean.class);
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
