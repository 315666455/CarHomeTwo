package lanou.carhometwo.bbs.select;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import lanou.carhometwo.R;
import lanou.carhometwo.bean.LobbyistsBean;
import tools.CommonViewHolder;

/**
 * Created by dllo on 16/11/5.
 */
public class SelectAdapter extends BaseAdapter {
    Context context;
    LobbyistsBean lobbyistsBean;

    public SelectAdapter(Context context) {
        this.context = context;
    }

    public void setLobbyistsBean(LobbyistsBean lobbyistsBean) {

        this.lobbyistsBean = lobbyistsBean;
    }

    @Override
    public int getCount() {
        return lobbyistsBean == null ? 0 : lobbyistsBean.getResult().getList().size();
    }

    @Override
    public Object getItem(int i) {
        return lobbyistsBean.getResult().getList().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(view, viewGroup, R.layout.select_item);
        viewHolder.setText(R.id.tv_select_title, lobbyistsBean.getResult().getList().get(i).getTitle());
        viewHolder.setText(R.id.tv_select_time, lobbyistsBean.getResult().getList().get(i).getTime());
        viewHolder.setText(R.id.tv_select_replycount, String.valueOf(lobbyistsBean.getResult().getList().get(i).getReplycount()) + "回帖数");
        viewHolder.setImage(R.id.iv_select_img, lobbyistsBean.getResult().getList().get(i).getSmallpic());
        return viewHolder.getItemView();
    }
}
