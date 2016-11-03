package lanou.carhometwo.recommended.lobbyists;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import lanou.carhometwo.R;
import lanou.carhometwo.bean.LobbyistsBean;
import tools.CommonViewHolder;


public class LobbyistsAdapter extends BaseAdapter {
    LobbyistsBean lobbyistsBean;

    public void setLobbyistsBean(LobbyistsBean lobbyistsBean) {
        this.lobbyistsBean = lobbyistsBean;
    }

    @Override
    public int getCount() {
        int count = 0;
//        try {
            count = lobbyistsBean.getResult().getList().size();
//        } catch (NullPointerException e) {
//        }
        return count;
    }

    @Override
    public Object getItem(int i) {
        return lobbyistsBean == null?0:lobbyistsBean.getResult().getList().size();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CommonViewHolder commonViewHolder = CommonViewHolder.getViewHolder(view, viewGroup, R.layout.lobbyists_item);
        commonViewHolder.setText(R.id.tv_lobbyists_title, lobbyistsBean.getResult().getList().get(i).getTitle());
        return commonViewHolder.getItemView();
    }
}
