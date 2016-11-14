package lanou.carhometwo.bbs;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import lanou.carhometwo.R;
import lanou.carhometwo.base.BaseFragment;
import lanou.carhometwo.bbs.bbschild.BbsChildFragment;
import lanou.carhometwo.bbs.select.SelectFragment;
import lanou.carhometwo.search.SearchActivity;

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
        ImageView imageView = bindView(R.id.iv_found_search);
        tl = bindView(R.id.tl_bbs);
        vp = bindView(R.id.vp_bbs);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected int getLayout() {
        return R.layout.bbs_fragment;
    }
}
