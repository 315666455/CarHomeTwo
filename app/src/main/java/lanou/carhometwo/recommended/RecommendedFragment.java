package lanou.carhometwo.recommended;


import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lanou.carhometwo.R;
import lanou.carhometwo.base.BaseFragment;
import lanou.carhometwo.bean.FirstBean;
import lanou.carhometwo.internet.VolleySingleton;


public class RecommendedFragment extends BaseFragment implements View.OnClickListener {

    private TabLayout tlRecommended;
    private ViewPager vpRecommended;
    private TextView tvMusic;

    @Override
    protected void initData() {

        RecommendedAdapter adapter = new RecommendedAdapter(getChildFragmentManager());
        vpRecommended.setAdapter(adapter);
        tlRecommended.setupWithViewPager(vpRecommended);
        tlRecommended.setSelectedTabIndicatorColor(Color.BLACK);
        tlRecommended.setTabMode(TabLayout.MODE_SCROLLABLE);


        FirstBean bean = new FirstBean();
        ArrayList<FirstBean> arrayList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            bean.setMessage("你好");
            arrayList.add(bean);
        }
        VolleySingleton.getInstance().liteInsert(arrayList);
    }

    @Override
    protected void initView() {
        tvMusic = bindView(R.id.tv_recommend_music);
        tlRecommended = bindView(R.id.tl_recommend);
        vpRecommended = bindView(R.id.vp_recommeded);
        tvMusic.setOnClickListener(this);

    }

    @Override
    protected int getLayout() {
        return R.layout.recommended_fragment;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_recommend_music:
                
                VolleySingleton.getInstance().liteQueryDate(new VolleySingleton.OnQueryListenerAll<FirstBean>() {
                    @Override
                    public void onQuery(List<FirstBean> T) {
                        Log.d("RecommendedFragment", "T:" + T);
                    }
                },FirstBean.class);
                break;
        }
    }
}
