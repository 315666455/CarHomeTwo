package lanou.carhometwo.bbs;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import lanou.carhometwo.base.BaseFragment;

/**
 * Created by dllo on 16/11/1.
 */
public class BbsAdapter extends FragmentPagerAdapter {

    ArrayList<BaseFragment>arrayList;

    String[] title = {"精选","论坛"};

    public BbsAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setArrayList(ArrayList<BaseFragment> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public Fragment getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public int getCount() {
        return arrayList == null?0:arrayList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
