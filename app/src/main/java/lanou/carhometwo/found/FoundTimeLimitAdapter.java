package lanou.carhometwo.found;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import lanou.carhometwo.R;
import lanou.carhometwo.bean.FoundBean;
import tools.CommonViewHolder;

/**
 * Created by dllo on 16/11/8.
 */
public class FoundTimeLimitAdapter extends RecyclerView.Adapter<CommonViewHolder> {
    FoundBean foundBean;
    int num;
    public void setNum(int num) {
        this.num = num;
        Log.d("1212", "num:" + num);
    }
    public void setFoundBean(FoundBean foundBean) {
        this.foundBean = foundBean;
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CommonViewHolder commonViewHolder = CommonViewHolder.getViewHolder(parent, R.layout.rv_time_limit_item);
        return commonViewHolder;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        holder.setImage(R.id.iv_found_time_limit, foundBean.getResult().getCardlist().get(num).getData().get(position).getImageurl());
    }

    @Override
    public int getItemCount() {
        return foundBean == null ? 0 : foundBean.getResult().getCardlist().get(num).getData().size();
    }


}
