package tools;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import lanou.carhometwo.internet.VolleySingleton;

/**
 * Created by dllo on 16/10/31.
 * 通用的ViewHolder
 */
public class CommonViewHolder extends RecyclerView.ViewHolder {
    //SparseArray用法和HashMap相似
    //但Key为int类型
    //用它来存放所有的View,key就是View的id
    private SparseArray<View> views;
    private View itemView;//行布局

    public CommonViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        views = new SparseArray<>();
    }

    //通过view的id来获得指定的view
    //如果该view没有赋值,就先执行findviewbyid
    //然后把它放到view的集合里
    //使用方形来取消强转
    public <T extends View> T getView(int id) {
        View view = views.get(id);
        if (view == null) {
            //证明SparseArray里没有这个view
            view = itemView.findViewById(id);
            views.put(id, view);
        }
        return (T) view;
    }

    //专门给listview使用的方法
    public static CommonViewHolder getViewHolder(View itemView, ViewGroup viewGroup, int itemId) {
        CommonViewHolder commonViewHolder;
        if (itemView == null) {
            Context context = viewGroup.getContext();
            itemView = LayoutInflater.from(context).inflate(itemId, viewGroup, false);
            commonViewHolder = new CommonViewHolder(itemView);
            itemView.setTag(commonViewHolder);
        } else {
            commonViewHolder = (CommonViewHolder) itemView.getTag();

        }
        return commonViewHolder;
    }

    //专门给recyclerview使用的方法
    public static CommonViewHolder getViewHolder(ViewGroup viewGroup, int itemId) {
        return getViewHolder(null, viewGroup, itemId);
    }

    //行布局设置点击事件
    public CommonViewHolder setItemClick(View.OnClickListener listener) {
        itemView.setOnClickListener(listener);
        return this;
    }

    //返回行布局
    public View getItemView() {
        return itemView;
    }

    /************
     * ViewHolder 设置数据的方法
     ****************/
    public CommonViewHolder setText(int id, String text) {
        TextView textView = getView(id);
        textView.setText(text);
        return this;
    }

    public CommonViewHolder setImage(int id, int imageId) {
        ImageView imageView = getView(id);
        imageView.setImageResource(imageId);
        return this;
    }

    public CommonViewHolder setImage(int id, String url) {
        ImageView imageView = getView(id);
        //网络请求图片
        VolleySingleton.getInstance().getImage(url, imageView);
        return this;
    }

    //设置点击事件
    public CommonViewHolder setViewClick(int id, View.OnClickListener listener) {
        getView(id).setOnClickListener(listener);
        return this;
    }








}
