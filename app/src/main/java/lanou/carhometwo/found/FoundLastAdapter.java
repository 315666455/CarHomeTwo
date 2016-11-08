package lanou.carhometwo.found;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import lanou.carhometwo.R;
import lanou.carhometwo.bean.FoundBean;
import tools.CommonViewHolder;

/**
 * Created by dllo on 16/11/8.
 */
public class FoundLastAdapter extends BaseAdapter {

    FoundBean foundBean;

    @Override
    public int getCount() {
        return foundBean == null ? 0 : foundBean.getResult().getCardlist().get(11).getData().size();
    }

    @Override
    public Object getItem(int i) {
        return foundBean.getResult().getCardlist().get(11).getData().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void setFoundBean(FoundBean foundBean) {
        this.foundBean = foundBean;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CommonViewHolder commonViewHolder = CommonViewHolder.getViewHolder(view, viewGroup, R.layout.lv_found_item);
        commonViewHolder.setImage(R.id.iv_found_last_lv_item,  foundBean.getResult().getCardlist().get(11).getData().get(i).getLogo());
        commonViewHolder.setText(R.id.tv_found_lv_item_title,  foundBean.getResult().getCardlist().get(11).getData().get(i).getTitle());
        commonViewHolder.setText(R.id.tv_found_lv_item_adinfo, foundBean.getResult().getCardlist().get(11).getData().get(i).getState());
        commonViewHolder.setText(R.id.tv_found_lv_item_price, foundBean.getResult().getCardlist().get(11).getData().get(i).getPrice());
        return commonViewHolder.getItemView();
    }
}
