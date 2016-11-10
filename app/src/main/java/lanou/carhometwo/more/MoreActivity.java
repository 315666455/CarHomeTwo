package lanou.carhometwo.more;

import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import java.util.ArrayList;

import lanou.carhometwo.R;
import lanou.carhometwo.base.BaseActivity;
import lanou.carhometwo.bean.MoreBean;

public class MoreActivity extends BaseActivity{

    private RecyclerView recyclerView;
    ArrayList<MoreBean> arrayList;
    private MoreAdapter moreAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_more;
    }

    @Override
    protected void initViews() {
        recyclerView = bindView(R.id.rv_more);
    }

    @Override
    protected void initData() {
        moreAdapter = new MoreAdapter(this);
        arrayList = new ArrayList<>();
        MoreBean bean = new MoreBean();
        bean.setImg(R.mipmap.hangqing);
        bean.setTitle("行情");
        arrayList.add(bean);
        MoreBean bean0 = new MoreBean();
        bean0.setImg(R.mipmap.daogou);
        bean0.setTitle("导购");
        arrayList.add(bean0);
        MoreBean bean1 = new MoreBean();
        bean1.setImg(R.mipmap.xinwen);
        bean1.setTitle("新闻");
        arrayList.add(bean1);
        MoreBean bean2 = new MoreBean();
        bean2.setImg(R.mipmap.pingce);
        bean2.setTitle("评测");
        arrayList.add(bean2);
        MoreBean bean3 = new MoreBean();
        bean3.setImg(R.mipmap.jishu);
        bean3.setTitle("技术");
        arrayList.add(bean3);
        MoreBean bean4 = new MoreBean();
        bean4.setImg(R.mipmap.youji);
        bean4.setTitle("游记");
        arrayList.add(bean4);
        MoreBean bean5 = new MoreBean();
        bean5.setImg(R.mipmap.youchuang);
        bean5.setTitle("优创+");
        arrayList.add(bean5);
        MoreBean bean6 = new MoreBean();
        bean6.setImg(R.mipmap.wenhua);
        bean6.setTitle("文化");
        arrayList.add(bean6);
        MoreBean bean7 = new MoreBean();
        bean7.setImg(R.mipmap.shuoke);
        bean7.setTitle("说客");
        arrayList.add(bean7);
        MoreBean bean8 = new MoreBean();
        bean8.setImg(R.mipmap.shipin);
        bean8.setTitle("视频");
        arrayList.add(bean8);
        MoreBean bean9 = new MoreBean();
        bean9.setImg(R.mipmap.kuaibao);
        bean9.setTitle("快报");
        arrayList.add(bean9);
        MoreBean bean10 = new MoreBean();
        bean10.setImg(R.mipmap.yongche);
        bean10.setTitle("用车");
        arrayList.add(bean10);
        MoreBean bean11 = new MoreBean();
        bean11.setImg(R.mipmap.gaizhuang);
        bean11.setTitle("改装");
        arrayList.add(bean11);

        ItemTouchHelper.Callback callback = mCallback();
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerView);

        moreAdapter.setArrayList(arrayList);
        recyclerView.setAdapter(moreAdapter);
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL));
        GridLayoutManager manager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(manager);
    }

    private ItemTouchHelper.Callback mCallback() {
        return new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

                if(layoutManager instanceof GridLayoutManager){
                    return ItemTouchHelper.Callback.makeMovementFlags(
                            ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END, 0);
                }else {
                    return ItemTouchHelper.Callback.makeMovementFlags(
                            ItemTouchHelper.UP | ItemTouchHelper.DOWN, 0);
                }
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();
                moreAdapter.move(from, to);
                return false;
            }

            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                super.onSelectedChanged(viewHolder, actionState);
                if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                    View view = viewHolder.itemView;
                    view.setBackgroundColor(Color.LTGRAY);
                }
            }

            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                viewHolder.itemView.setBackgroundColor(Color.WHITE);
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            }
        };
    }
}
