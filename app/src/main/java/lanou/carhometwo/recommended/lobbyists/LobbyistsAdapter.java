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
        commonViewHolder.setText(R.id.tv_lobbyists_time,lobbyistsBean.getResult().getList().get(i).getTime());
        commonViewHolder.setText(R.id.tv_lobbyists_replycount,String.valueOf(lobbyistsBean.getResult().getList().get(i).getReplycount())+"条 评论");
        commonViewHolder.setImage(R.id.iv_lobbyists_img,lobbyistsBean.getResult().getList().get(i).getSmallpic());


        return commonViewHolder.getItemView();
    }
}
