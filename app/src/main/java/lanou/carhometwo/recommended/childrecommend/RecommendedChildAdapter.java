package lanou.carhometwo.recommended.childrecommend;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import lanou.carhometwo.R;
import lanou.carhometwo.bean.RecommendChildBean;
import lanou.carhometwo.internet.VolleySingleton;

/**
 * Created by dllo on 16/10/25.
 */
public class RecommendedChildAdapter extends RecyclerView.Adapter {

    private Context context;
    RecommendChildBean recommendChildBean;

    public void setRecommendChildBean(RecommendChildBean recommendChildBean) {
        this.recommendChildBean = recommendChildBean;
        notifyDataSetChanged();
    }

    public RecommendedChildAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return recommendChildBean.getResult().getNewslist().get(position).getMediatype();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == 3) {
            View videoView = LayoutInflater.from(context).inflate(R.layout.recommend_video_item, parent, false);
            VideoViewHolder videoViewHolder = new VideoViewHolder(videoView);
            return videoViewHolder;
        } else {
            View newsView = LayoutInflater.from(context).inflate(R.layout.recommended_child_item, parent, false);
            NewsViewHolder newsViewHolder = new NewsViewHolder(newsView);
            return newsViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type = recommendChildBean.getResult().getNewslist().get(position).getMediatype();
        if (type == 3) {
            VideoViewHolder videoViewHolder = (VideoViewHolder) holder;
            videoViewHolder.tvVideoTitle.setText(recommendChildBean.getResult().getNewslist().get(position).getTitle());
            videoViewHolder.tvReplycount.setText(String.valueOf(recommendChildBean.getResult().getNewslist().get(position).getReplycount())+"次 播放");
            videoViewHolder.tvVideoTime.setText(recommendChildBean.getResult().getNewslist().get(position).getTime());
            VolleySingleton.getInstance().getImage(recommendChildBean.getResult().getNewslist().get(position).getSmallpic(), videoViewHolder.imgVideo);
        } else {

            NewsViewHolder newsViewHolder = (NewsViewHolder) holder;
            newsViewHolder.tvTitle.setText(recommendChildBean.getResult().getNewslist().get(position).getTitle());
            newsViewHolder.tvTime.setText(recommendChildBean.getResult().getNewslist().get(position).getTime());
            newsViewHolder.tvReplycount.setText(String.valueOf(recommendChildBean.getResult().getNewslist().get(position).getReplycount()) + "条 评论");
            VolleySingleton.getInstance().getImage(recommendChildBean.getResult().getNewslist().get(position).getSmallpic(), newsViewHolder.ivSmallpic);
        }
    }

    @Override
    public int getItemCount() {
        return recommendChildBean == null ? 0 : recommendChildBean.getResult().getNewslist().size();
    }


    class NewsViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvTime;
        private TextView tvReplycount;
        private ImageView ivSmallpic;


        public NewsViewHolder(View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tv_recommend_child_title);
            tvTime = (TextView) itemView.findViewById(R.id.tv_recommend_child_time);
            tvReplycount = (TextView) itemView.findViewById(R.id.tv_recommend_child_replycount);
            ivSmallpic = (ImageView) itemView.findViewById(R.id.iv_recommend_child_img);
        }
    }

    class VideoViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgVideo;
        private TextView tvVideoTitle;
        private TextView tvVideoTime;
        private TextView tvReplycount;

        public VideoViewHolder(View itemView) {
            super(itemView);
            imgVideo = (ImageView) itemView.findViewById(R.id.iv_recommend_video);
            tvVideoTitle = (TextView) itemView.findViewById(R.id.tv_recommend_video_title);
            tvVideoTime = (TextView) itemView.findViewById(R.id.tv_recommend_video_time);
            tvReplycount = (TextView) itemView.findViewById(R.id.tv_recommend_video_replycount);
        }
    }


}
