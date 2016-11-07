package lanou.carhometwo.choosecar.newcar;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import lanou.carhometwo.R;
import lanou.carhometwo.bean.NewCarHotBean;
import tools.CommonViewHolder;

/**
 * Created by dllo on 16/11/7.
 */
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

//        holder.setText()
        // TODO: 16/11/7 添加数据 
        
    }

    @Override
    public int getItemCount() {
        return newCarHotBean == null ? 0 : newCarHotBean.getResult().getList().size();
    }
}
