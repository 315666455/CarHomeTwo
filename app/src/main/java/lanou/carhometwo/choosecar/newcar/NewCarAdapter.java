package lanou.carhometwo.choosecar.newcar;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import lanou.carhometwo.R;
import lanou.carhometwo.bean.NewCarBean;
import tools.CommonViewHolder;

/**
 * Created by dllo on 16/11/5.
 */
public class NewCarAdapter extends BaseAdapter {

    NewCarBean newCarBean;

    @Override
    public int getCount() {
        return newCarBean.getResult().getBrandlist().size();
    }

    @Override
    public Object getItem(int i) {
        return newCarBean.getResult().getBrandlist().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public NewCarAdapter(Context context) {

    }

    public void setNewCarBean(NewCarBean newCarBean) {
        this.newCarBean = newCarBean;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CommonViewHolder commonViewHolder = CommonViewHolder.getViewHolder(view,viewGroup, R.layout.new_car_head_item);
        commonViewHolder.setText(R.id.tv_new_car_head,newCarBean.getResult().getBrandlist().get(i).getLetter());
        return commonViewHolder.getItemView();
    }
}
