package lanou.carhometwo.recommended.goodwork;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import lanou.carhometwo.R;
import lanou.carhometwo.internet.VolleySingleton;
import lanou.carhometwo.weiget.CircleHead;

/**
 * Created by dllo on 16/10/25.
 */
public class GoodWorkAdapter extends RecyclerView.Adapter {

    private Context context;
    GoodWorkBean goodWorkBean;

    public void setGoodWorkBean(GoodWorkBean goodWorkBean) {
        this.goodWorkBean = goodWorkBean;
        notifyDataSetChanged();
    }

    public GoodWorkAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return goodWorkBean.getResult().getNewslist().get(position).getMediatype();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == 3) {
            View videoView = LayoutInflater.from(context).inflate(R.layout.good_work_video_item, parent, false);
            VideoViewHolder videoViewHolder = new VideoViewHolder(videoView);
            return videoViewHolder;
        } else {
            View newsView = LayoutInflater.from(context).inflate(R.layout.good_work_item, parent, false);
            NewsViewHolder newsViewHolder = new NewsViewHolder(newsView);
            return newsViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type = goodWorkBean.getResult().getNewslist().get(position).getMediatype();
        if (type == 3) {
            VideoViewHolder videoViewHolder = (VideoViewHolder) holder;
            videoViewHolder.tvUser.setText(goodWorkBean.getResult().getNewslist().get(position).getUsername());
            videoViewHolder.tvVideoTitle.setText(goodWorkBean.getResult().getNewslist().get(position).getTitle());
            videoViewHolder.tvReplycount.setText(String.valueOf(goodWorkBean.getResult().getNewslist().get(position).getReplycount()));
            videoViewHolder.tvVideoTime.setText(goodWorkBean.getResult().getNewslist().get(position).getPublishtime());
            videoViewHolder.tvPraisenum.setText(String.valueOf(goodWorkBean.getResult().getNewslist().get(position).getPraisenum()));
            VolleySingleton.getInstance().getImage(goodWorkBean.getResult().getNewslist().get(position).getUserpic(), videoViewHolder.imgSmallpic);
            VolleySingleton.getInstance().getImage(goodWorkBean.getResult().getNewslist().get(position).getThumbnailpics().get(0), videoViewHolder.imgVideo);
        } else {
            NewsViewHolder newsViewHolder = (NewsViewHolder) holder;
            newsViewHolder.tvTitle.setText(goodWorkBean.getResult().getNewslist().get(position).getTitle());
            newsViewHolder.tvTime.setText(goodWorkBean.getResult().getNewslist().get(position).getPublishtime());
            newsViewHolder.tvReplycount.setText(String.valueOf(goodWorkBean.getResult().getNewslist().get(position).getReplycount()));
            VolleySingleton.getInstance().getImage(goodWorkBean.getResult().getNewslist().get(position).getUserpic(), newsViewHolder.ivUser);
            VolleySingleton.getInstance().getImage(goodWorkBean.getResult().getNewslist().get(position).getThumbnailpics().get(0), newsViewHolder.ivSmallpic);
            newsViewHolder.tvPraisenum.setText(String.valueOf(goodWorkBean.getResult().getNewslist().get(position).getPraisenum()));
        }
    }

    @Override
    public int getItemCount() {
        return goodWorkBean == null ? 0 : goodWorkBean.getResult().getNewslist().size();
    }


    class VideoViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgVideo;
        private TextView tvVideoTitle;
        private TextView tvVideoTime;
        private TextView tvReplycount;
        private TextView tvUser;
        private ImageView imgSmallpic;
        private TextView tvPraisenum;

        public VideoViewHolder(View itemView) {
            super(itemView);

            tvPraisenum = (TextView) itemView.findViewById(R.id.tv_good_work_video_praisenum);
            imgSmallpic = (CircleHead) itemView.findViewById(R.id.good_work_small_pic);
            tvUser = (TextView) itemView.findViewById(R.id.good_work_username);
            imgVideo = (ImageView) itemView.findViewById(R.id.iv_good_work_video);
            tvVideoTitle = (TextView) itemView.findViewById(R.id.tv_good_work_video_title);
            tvVideoTime = (TextView) itemView.findViewById(R.id.tv_good_work_video_time);
            tvReplycount = (TextView) itemView.findViewById(R.id.tv_good_work_video_replycount);
        }
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {
        private CircleHead ivUser;
        private TextView tvTitle;
        private TextView tvTime;
        private TextView tvReplycount;
        private ImageView ivSmallpic;
        private TextView tvPraisenum;

        public NewsViewHolder(View itemView) {
            super(itemView);
            ivUser = (CircleHead) itemView.findViewById(R.id.good_work_news_small_pic);
            tvPraisenum = (TextView) itemView.findViewById(R.id.tv_good_work_news_praisenum);
            ivSmallpic = (ImageView) itemView.findViewById(R.id.iv_good_work_news);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_good_work_news_title);
            tvTime = (TextView) itemView.findViewById(R.id.tv_good_work_news_time);
            tvReplycount = (TextView) itemView.findViewById(R.id.tv_good_work_news_replycount);

        }
    }
}
