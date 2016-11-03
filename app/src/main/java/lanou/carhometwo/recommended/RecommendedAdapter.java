package lanou.carhometwo.recommended;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;

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

    private String[] title = {"推荐", "优创+", "说客", "视频", "快报", "行情", "新闻", "评测", "导购", "用车", "技术", "文化", "改装"};
    private SparseArray<BaseFragment> arrayList;
    private ArrayList<String> arrayListUrl;


    public RecommendedAdapter(FragmentManager fm, ArrayList<String> arrayListUrl) {
        super(fm);
        this.arrayListUrl = arrayListUrl;
        arrayList = new SparseArray<>();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                arrayList.put(position, new RecommendedChildFragment());
                break;
            case 1:
                arrayList.put(position, new GoodWorkFragment());
                break;
            case 3:
                arrayList.put(position, new VideoFragment());
                break;
            case 4:
                arrayList.put(position, new LettersFragment());
                break;
            default:
            if(arrayList.get(position) == null){
                arrayList.put(position,LobbyistsFragment.getInstance(position,arrayListUrl));
            }
                break;
        }
        return arrayList.get(position);
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
