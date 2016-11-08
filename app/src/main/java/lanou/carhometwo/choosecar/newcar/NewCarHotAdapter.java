package lanou.carhometwo.choosecar.newcar;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import lanou.carhometwo.R;
import lanou.carhometwo.bean.NewCarHotBean;
import tools.CommonViewHolder;

public class NewCarHotAdapter extends RecyclerView.Adapter<CommonViewHolder> {

    NewCarHotBean newCarHotBean;

    public void setNewCarHotBean(NewCarHotBean newCarHotBean) {
        this.newCarHotBean = newCarHotBean;
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CommonViewHolder commonViewHolder = CommonViewHolder.getViewHolder(parent, R.layout.new_car_rv_item);
        return commonViewHolder;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        holder.setText(R.id.tv_new_car_hot_name, " " + newCarHotBean.getResult().getList().get(position).getName());
        holder.setImage(R.id.iv_new_car_hot, newCarHotBean.getResult().getList().get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return newCarHotBean == null ? 0 : newCarHotBean.getResult().getList().size();
    }
}
