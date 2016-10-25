package lanou.carhometwo.recommended.childrecommend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import lanou.carhometwo.R;

/**
 * Created by dllo on 16/10/25.
 */
public class RecommendedChildAdapter extends BaseAdapter {

    ArrayList<RecommendChildBean> arrayList;
    Context context;


    public void setArrayList(ArrayList<RecommendChildBean> arrayList) {
        this.arrayList = arrayList;
    }

    public RecommendedChildAdapter(Context context) {

        this.context = context;
    }



    @Override
    public int getCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ChildViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.recommended_child_item, null);
            viewHolder = new ChildViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ChildViewHolder) view.getTag();
        }
        return view;
    }

    class ChildViewHolder {
        public ChildViewHolder(View view) {
        }
    }
}
