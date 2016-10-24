package lanou.carhometwo.recommended;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import lanou.carhometwo.base.BaseFragment;

/**
 * Created by dllo on 16/10/22.
 */
public class RecommendedAdapter extends FragmentPagerAdapter {

    ArrayList<BaseFragment>arrayList;

    public void setArrayList(ArrayList<BaseFragment> arrayList) {
        this.arrayList = arrayList;
    }

    public RecommendedAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public int getCount() {
        return arrayList == null?0:arrayList.size();
    }




}
