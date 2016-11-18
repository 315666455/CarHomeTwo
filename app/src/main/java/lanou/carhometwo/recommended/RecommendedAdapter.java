package lanou.carhometwo.recommended;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.util.SparseArray;

import java.util.ArrayList;

import lanou.carhometwo.R;
import lanou.carhometwo.base.BaseFragment;
import lanou.carhometwo.bean.MoreBean;
import lanou.carhometwo.recommended.childrecommend.RecommendedChildFragment;
import lanou.carhometwo.recommended.goodwork.GoodWorkFragment;
import lanou.carhometwo.recommended.letters.LettersFragment;
import lanou.carhometwo.recommended.lobbyists.LobbyistsFragment;
import lanou.carhometwo.recommended.video.VideoFragment;
import tools.LiteOrmSingleton;

/**
 * Created by dllo on 16/10/22.
 */
public class RecommendedAdapter extends FragmentPagerAdapter {

    private SparseArray<BaseFragment> arrayList;
    private ArrayList<String> arrayListUrl;
    ArrayList<MoreBean> arrayList1;

    public RecommendedAdapter(FragmentManager fm, ArrayList<String> arrayListUrl) {
        super(fm);
        this.arrayListUrl = arrayListUrl;
        LiteOrmSingleton.getIntstance().deleteMoreData();

        arrayList = new SparseArray<>();
        arrayList1 = new ArrayList<>();

        MoreBean bean = new MoreBean();
        bean.setImg(R.mipmap.hangqing);
        bean.setTitle("行情");
        arrayList1.add(bean);
        MoreBean bean0 = new MoreBean();
        bean0.setImg(R.mipmap.daogou);
        bean0.setTitle("导购");
        arrayList1.add(bean0);
        MoreBean bean1 = new MoreBean();
        bean1.setImg(R.mipmap.xinwen);
        bean1.setTitle("新闻");
        arrayList1.add(bean1);
        MoreBean bean2 = new MoreBean();
        bean2.setImg(R.mipmap.pingce);
        bean2.setTitle("评测");
        arrayList1.add(bean2);
        MoreBean bean3 = new MoreBean();
        bean3.setImg(R.mipmap.jishu);
        bean3.setTitle("技术");
        arrayList1.add(bean3);
        MoreBean bean4 = new MoreBean();
        bean4.setImg(R.mipmap.youji);
        bean4.setTitle("游记");
        arrayList1.add(bean4);
        MoreBean bean5 = new MoreBean();
        bean5.setImg(R.mipmap.youchuang);
        bean5.setTitle("优创+");
        arrayList1.add(bean5);
        MoreBean bean6 = new MoreBean();
        bean6.setImg(R.mipmap.wenhua);
        bean6.setTitle("文化");
        arrayList1.add(bean6);
        MoreBean bean7 = new MoreBean();
        bean7.setImg(R.mipmap.shuoke);
        bean7.setTitle("说客");
        arrayList1.add(bean7);
        MoreBean bean8 = new MoreBean();
        bean8.setImg(R.mipmap.shipin);
        bean8.setTitle("视频");
        arrayList1.add(bean8);
        MoreBean bean9 = new MoreBean();
        bean9.setImg(R.mipmap.kuaibao);
        bean9.setTitle("快报");
        arrayList1.add(bean9);
        MoreBean bean10 = new MoreBean();
        bean10.setImg(R.mipmap.yongche);
        bean10.setTitle("用车");
        arrayList1.add(bean10);
        MoreBean bean11 = new MoreBean();
        bean11.setImg(R.mipmap.gaizhuang);
        bean11.setTitle("改装");
        arrayList1.add(bean11);
        Log.d("4561", "arrayList1:" + arrayList1);
        LiteOrmSingleton.getIntstance().insertData(arrayList1);
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
                if (arrayList.get(position) == null) {
                    arrayList.put(position, LobbyistsFragment.getInstance(position, arrayListUrl));
                }
                break;
        }
        return arrayList.get(position);
    }


    @Override
    public int getCount() {
        return arrayList1 == null ? 0 : arrayList1.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return arrayList1.get(position).getTitle();
    }
}
