package lanou.carhometwo.more;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import lanou.carhometwo.R;
import lanou.carhometwo.bean.MoreBean;

/**
 * Created by dllo on 16/11/9.
 */
public class MoreAdapter extends RecyclerView.Adapter<MoreAdapter.MyViewHolder> {

    ArrayList<MoreBean> arrayList;
    Context context;

    public MoreAdapter(Context context) {
        this.context = context;
    }

    public void setArrayList(ArrayList<MoreBean> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_more_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.textView.setText(arrayList.get(position).getTitle());
        holder.imageView.setImageResource(arrayList.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout linearLayout;
        private TextView textView;
        private ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.ll_more);
            textView = (TextView) itemView.findViewById(R.id.tv_more_item);
            imageView = (ImageView) itemView.findViewById(R.id.iv_more_item);
        }
    }

    public void move(int from, int to) {
        Collections.swap(arrayList, from, to);
        notifyItemMoved(from, to);
    }
}
