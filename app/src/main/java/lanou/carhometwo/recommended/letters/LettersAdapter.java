package lanou.carhometwo.recommended.letters;

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
public class LettersAdapter extends BaseAdapter {
    Context context;
    LobbyistsBean lobbyistsBean;

    public void setLobbyistsBean(LobbyistsBean lobbyistsBean) {
        this.lobbyistsBean = lobbyistsBean;
    }



    public LettersAdapter(Context context) {

        this.context = context;
    }

    @Override
    public int getCount() {
        return lobbyistsBean == null?0:lobbyistsBean.getResult().getList().size();
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
        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(view,viewGroup, R.layout.letters_item);
        viewHolder.setText(R.id.tv_letters_title, lobbyistsBean.getResult().getList().get(i).getTitle());
        viewHolder.setText(R.id.tv_letters_time, lobbyistsBean.getResult().getList().get(i).getAdvancetime());
        viewHolder.setText(R.id.tv_letters_playcount, String.valueOf(lobbyistsBean.getResult().getList().get(i).getReviewcount())+"位观众");
        viewHolder.setImage(R.id.iv_letters, lobbyistsBean.getResult().getList().get(i).getSmallpic());
        return viewHolder.getItemView();

    }
}
