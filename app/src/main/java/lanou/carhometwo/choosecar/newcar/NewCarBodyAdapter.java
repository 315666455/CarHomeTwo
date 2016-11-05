package lanou.carhometwo.choosecar.newcar;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import lanou.carhometwo.R;
import lanou.carhometwo.bean.NewCarBean;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import tools.CommonViewHolder;

/**
 * Created by dllo on 16/11/5.
 */
public class NewCarBodyAdapter extends BaseAdapter implements StickyListHeadersAdapter {

    NewCarBean newCarBean;

    public void setNewCarBean(NewCarBean newCarBean) {
        this.newCarBean = newCarBean;
    }

    @Override
    public int getCount() {
        return newCarBean == null ? 0 : newCarBean.getResult().getBrandlist().size();
    }

    @Override
    public Object getItem(int i) {
        return newCarBean.getResult().getBrandlist().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CommonViewHolder commonViewHolder = CommonViewHolder.getViewHolder(view, viewGroup, R.layout.new_car_body_item);
//        commonViewHolder.setImage(R.id.iv_new_car_body, newCarBean.getResult().getBrandlist().get(i).getList().get)
        // TODO: 16/11/5 再写一个listview
        return null;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(convertView, parent, R.layout.new_car_head_horizontal_item);
        viewHolder.setText(R.id.tv_new_car_horizontal_head, newCarBean.getResult().getBrandlist().get(position).getLetter());
        return viewHolder.getItemView();
    }

    @Override
    public long getHeaderId(int position) {


        return position;
    }
}
