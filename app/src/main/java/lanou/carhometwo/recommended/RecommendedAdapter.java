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

    String[] title = {"推荐","优创+", "说客", "视频", "快报", "行情", "新闻", "评测", "导购", "用车", "技术", "文化", "改装" };
    ArrayList<BaseFragment> arrayList;

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

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}

//    ArrayList<String> arrayListPos;
//    public RecommendedAdapter(FragmentManager fm, ArrayList<String> arrayListPos) {
//        super(fm);
//        this.arrayListPos = arrayListPos;
//        arrayList = new SparseArray<>();
//    }
//    @Override
//    public Fragment getItem(int position) {
//
//        if (arrayList.get(position) == null) {
//            arrayList.put(position, RecommendedChildFragment.getInstance(position, arrayListPos));
//        }
//        return arrayList.get(position);
//    }

//
//        if (arrayList.get(position) == null) {
//            arrayList.put(position, RecommendedChildFragment.getInstance(position);
//        }
//        return arrayList.get(position);