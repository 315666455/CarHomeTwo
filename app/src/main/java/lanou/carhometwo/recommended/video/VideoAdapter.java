package lanou.carhometwo.recommended.video;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import lanou.carhometwo.R;
import lanou.carhometwo.bean.LobbyistsBean;
import tools.CommonViewHolder;

/**
 * Created by dllo on 16/11/4.
 */
public class VideoAdapter extends BaseAdapter {

    Context context;
    LobbyistsBean lobbyistsBean;

    public void setLobbyistsBean(LobbyistsBean lobbyistsBean) {
        this.lobbyistsBean = lobbyistsBean;
    }

    public VideoAdapter(Context context) {
        this.context = context;
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

        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(view, viewGroup, R.layout.video_item);
        viewHolder.setText(R.id.tv_video_title, lobbyistsBean.getResult().getList().get(i).getTitle());
        viewHolder.setText(R.id.tv_video_time, lobbyistsBean.getResult().getList().get(i).getTime());
        viewHolder.setText(R.id.tv_video_replycount, String.valueOf(lobbyistsBean.getResult().getList().get(i).getReplycount())+"评论");
        viewHolder.setText(R.id.tv_video_playcount, String.valueOf(lobbyistsBean.getResult().getList().get(i).getPlaycount())+"播放");
        viewHolder.setImage(R.id.iv_video, lobbyistsBean.getResult().getList().get(i).getSmallpic());
        return viewHolder.getItemView();
    }
}
