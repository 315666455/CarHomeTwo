package lanou.carhometwo.recommended.childrecommend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import lanou.carhometwo.R;
import lanou.carhometwo.bean.RecommendChildBean;
import lanou.carhometwo.internet.VolleySingleton;

/**
 * Created by dllo on 16/10/25.
 */
public class RecommendedChildAdapter extends BaseAdapter {

    private RecommendChildBean recommendChildBean;
    Context context;


    public void setRecommendChildBean(RecommendChildBean recommendChildBean) {
        this.recommendChildBean = recommendChildBean;
        notifyDataSetChanged();
    }

    public RecommendedChildAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return recommendChildBean == null ? 0 : recommendChildBean.getResult().getNewslist().size();
    }

    @Override
    public Object getItem(int i) {
        return recommendChildBean.getResult().getNewslist().size();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ChildViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.recommended_child_item, viewGroup, false);
            viewHolder = new ChildViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ChildViewHolder) view.getTag();
        }

        RecommendChildBean.ResultBean.NewslistBean newslistBean = recommendChildBean.getResult().getNewslist().get(i);

        String imgUrl = newslistBean.getSmallpic();
        String time = newslistBean.getTime();
        String title = newslistBean.getTitle();
        int replycount = newslistBean.getReplycount();


        viewHolder.tvTitle.setText(title);
        viewHolder.tvTime.setText(time);
        viewHolder.tvReplycount.setText(String.valueOf(replycount)+"条 评论");
            VolleySingleton.getInstance().getImage(imgUrl, viewHolder.ivSmallpic);
        return view;
    }

    class ChildViewHolder {
        private TextView tvTitle;
        private TextView tvTime;
        private TextView tvReplycount;
        private ImageView ivSmallpic;

        public ChildViewHolder(View view) {
            tvTitle = (TextView) view.findViewById(R.id.tv_recommend_child_title);
            tvTime = (TextView) view.findViewById(R.id.tv_recommend_child_time);
            tvReplycount = (TextView) view.findViewById(R.id.tv_recommend_child_replycount);
            ivSmallpic = (ImageView) view.findViewById(R.id.iv_recommend_child_img);
        }
    }
}
