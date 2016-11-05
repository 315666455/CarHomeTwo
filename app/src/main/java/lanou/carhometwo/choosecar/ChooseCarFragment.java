package lanou.carhometwo.choosecar;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

import lanou.carhometwo.R;
import lanou.carhometwo.base.BaseFragment;
import lanou.carhometwo.choosecar.newcar.NewCarFragment;
import lanou.carhometwo.choosecar.usedcar.UsedCarFragment;

/**
 * Created by dllo on 16/10/21.
 */
public class ChooseCarFragment extends BaseFragment {

    private TabLayout tabLayout;
    private ArrayList<BaseFragment> arrayList;
    private ViewPager viewPager;

    @Override
    protected void initData() {
        arrayList = new ArrayList<>();
        arrayList.add(new NewCarFragment());
        arrayList.add(new UsedCarFragment());

        ChooseCarAdapter adapter = new ChooseCarAdapter(getChildFragmentManager());
        adapter.setArrayList(arrayList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorColor(Color.BLACK);
        tabLayout.getTabAt(0).setText("新车");
        tabLayout.getTabAt(1).setText("二手车");
    }

    @Override
    protected void initView() {
        viewPager = bindView(R.id.vp_choose_car);
        tabLayout = bindView(R.id.tl_choose_car);
    }

    @Override
    protected int getLayout() {
        return R.layout.choose_car_fragment;
    }
}
