package lanou.carhometwo.choosecar.newcar;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import lanou.carhometwo.R;
import lanou.carhometwo.bean.NewCarBean;
import tools.CommonViewHolder;

/**
 * Created by dllo on 16/11/7.
 */
public class NewCarBodyLvAdapter extends BaseAdapter {

    NewCarBean.ResultBean.BrandlistBean listBean;

    public void setListBean(NewCarBean.ResultBean.BrandlistBean listBean) {
        this.listBean = listBean;
    }

    public NewCarBodyLvAdapter() {

    }

    @Override
    public int getCount() {
        return listBean == null?0:listBean.getList().size();
    }

    @Override
    public Object getItem(int i) {
        return listBean.getList().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        CommonViewHolder commonViewHolder = CommonViewHolder.getViewHolder(view, viewGroup, R.layout.new_car_body_lv_item);
        commonViewHolder.setImage(R.id.iv_new_car_body, listBean.getList().get(i).getImgurl());
        commonViewHolder.setText(R.id.tv_new_car_body, listBean.getList().get(i).getName());
        return commonViewHolder.getItemView();
    }
}
