package lanou.carhometwo.found;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import lanou.carhometwo.R;
import lanou.carhometwo.bean.FoundBean;
import tools.CommonViewHolder;

/**
 * Created by dllo on 16/11/8.
 */
public class FoundSixAdapter extends RecyclerView.Adapter<CommonViewHolder> {

    FoundBean foundBean;

    public void setFoundBean(FoundBean foundBean) {
        this.foundBean = foundBean;
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CommonViewHolder commonViewHolder = CommonViewHolder.getViewHolder(parent, R.layout.found_six_item);
        return commonViewHolder;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        holder.setImage(R.id.iv_found_rv_six_item,foundBean.getResult().getCardlist().get(5).getData().get(position).getImageurl());
    }

    @Override
    public int getItemCount() {
        return foundBean == null ? 0 : foundBean.getResult().getCardlist().get(5).getData().size();
    }
}
