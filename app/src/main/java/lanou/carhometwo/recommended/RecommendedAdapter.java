package lanou.carhometwo.recommended;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import lanou.carhometwo.base.BaseFragment;
import lanou.carhometwo.recommended.childrecommend.RecommendedChildFragment;
import lanou.carhometwo.recommended.goodwork.GoodWorkFragment;
import lanou.carhometwo.recommended.letters.LettersFragment;
import lanou.carhometwo.recommended.lobbyists.LobbyistsFragment;
import lanou.carhometwo.recommended.video.VideoFragment;

/**
 * Created by dllo on 16/10/22.
 */
public class RecommendedAdapter extends FragmentPagerAdapter {

    String[] title = {"推荐", "优创+", "说客", "视频", "快报", "行情", "新闻", "评测", "导购", "用车", "技术", "文化", "改装"};

    ArrayList<BaseFragment> arrayList;


    public void setArrayList(ArrayList<BaseFragment> arrayList) {
        this.arrayList = arrayList;
    }

    public RecommendedAdapter(FragmentManager fm) {
        super(fm);

        arrayList = new ArrayList<>();
        for (int i = 0; i < title.length; i++) {
            if (i == 0) {
                arrayList.add(new RecommendedChildFragment());
            } else if (i == 1) {
                arrayList.add(new GoodWorkFragment());
            } else if (i == 3) {
                arrayList.add(new VideoFragment());
            } else if (i == 4) {
                arrayList.add(new LettersFragment());
            }else if(i == 8){
                arrayList.add(new ShaopersFragment());
            }else if(i == 11){
                arrayList.add(new CultureFragment());
            }else {
                arrayList.add(LobbyistsFragment.getInstance(i));
            }

        }

    }

    @Override
    public Fragment getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public int getCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];

    }
}
