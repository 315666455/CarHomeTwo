package lanou.carhometwo.bbs;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

import lanou.carhometwo.R;
import lanou.carhometwo.base.BaseFragment;
import lanou.carhometwo.bbs.bbschild.BbsChildFragment;
import lanou.carhometwo.bbs.select.SelectFragment;

/**
 * Created by dllo on 16/10/21.
 */
public class BbsFragment extends BaseFragment {

    private TabLayout tl;
    private ViewPager vp;

    @Override
    protected void initData() {
        ArrayList<BaseFragment> arrayList = new ArrayList<>();
        arrayList.add(new SelectFragment());
        arrayList.add(new BbsChildFragment());
        BbsAdapter adapter = new BbsAdapter(getChildFragmentManager());
        adapter.setArrayList(arrayList);
        vp.setAdapter(adapter);
        tl.setupWithViewPager(vp);
        tl.setSelectedTabIndicatorColor(Color.BLACK);
    }

    @Override
    protected void initView() {
        tl = bindView(R.id.tl_bbs);
        vp = bindView(R.id.vp_bbs);
    }

    @Override
    protected int getLayout() {
        return R.layout.bbs_fragment;
    }
}
